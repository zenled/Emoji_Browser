import json

from builtins import id

from emoji import Emoji

# balacklist skip these emoji
blacklist = set(["37", "36", "31", "30", "32", "2a", "23", "38", "33", "34", "35", "39"])

out_folder = "./parsed/"

json_file = open("emoji.json")
json_string = json_file.read()
json_file.close()

emojis_json = json.loads(json_string)

# for parsing
all_category = {}  # {"category_name":id}
all_keywords = {}  # {"keyword_name":id}
all_emoji = set()  # set of Emoji

# Make all_emoji ---------------------------------------------------------
'''
    Goes through all emoji extracts keywords, puts them in a list
    sorts the list and puts all into all_keywords;
    Sets atributes to all_emoji
    
    Fields that counts as keywords (multi word keywords are splited):
        * name
        * category
        * shortname
        * shortname_alternates
        * ascii
        * keywords
    
'''
for id, key in enumerate(emojis_json):
    if key.lstrip("0") in blacklist:
        continue

    emoji_json = emojis_json[key]

    emoji = Emoji()

    # sets real_code (this is used when "removes some emoji")
    emoji.real_code = key

    # sets id
    emoji.id = id

    # sets code
    field = key.lstrip("0")
    emoji.code = field
    emoji.keywords.add(field)

    # sets name
    field = emoji_json["name"]
    emoji.name = field
    for s in field.split(" "):
        emoji.keywords.add(s)

    # sets category
    field = emoji_json["category"]
    emoji.category = field
    for s in field.split(" "):
        emoji.keywords.add(s)

    # sets shortname
    field = emoji_json["shortname"]
    emoji.short_name = field
    for s in field.split(" "):
        emoji.keywords.add(s)

    # sets keywords from shortname_alternates
    field = emoji_json["shortname_alternates"]
    for s in field:
        emoji.keywords.add(s)

    # sets keywords from ascii
    field = emoji_json["ascii"]
    for s in field:
        emoji.keywords.add(s)

    # sets keywords from keywords
    field = emoji_json["keywords"]
    for s in field:
        emoji.keywords.add(s)

    # sets has_tone
    '''
        If an emoji in emoji.json has something in diversities:[...]
        This means that the emoji has tone modifications but this emoji has tone 0
    '''
    diversities = emoji_json["diversities"]
    vas_tone_from_diversities = False

    if len(diversities) != 0:
        emoji.has_tone = True
        vas_tone_from_diversities = True

    if " tone" in emoji.name:
        emoji.has_tone = True

    # sets tone
    if emoji.has_tone and vas_tone_from_diversities is False:
        tone_string = emoji.short_name[len(emoji.short_name) - 2]
        tone = int(tone_string)
        emoji.tone = tone

    # sets emoji_order
    emoji.emoji_order = int(emoji_json["order"])

    emoji.all_keywords_to_lover()

    all_emoji.add(emoji)

# removes some emoji
to_remove_codes = set()
for emoji in all_emoji:
    emoji_json = emojis_json[emoji.real_code]
    genders = emoji_json["genders"]
    if len(genders) == 2:
        to_remove_codes.add(emoji.code)
        for s in emoji_json["diversities"]:
            to_remove_codes.add(s)

new_all_emoji = set()
for emoji in all_emoji:
    if emoji.code not in to_remove_codes:
        new_all_emoji.add(emoji)

all_emoji = new_all_emoji


# Make all_keywords ---------------------------------------------------------
keywords_extracted = set()
for emoji in all_emoji:
    for keyword in emoji.keywords:
        keywords_extracted.add(keyword)

for id, keyword in enumerate(keywords_extracted):
    all_keywords[keyword] = id

# Make all_category --------------------------------------------------------
category_extracted = set()
for emoji in all_emoji:
    category_extracted.add(emoji.category)

for id, category in enumerate(category_extracted):
    all_category[category] = id


# Puts everithing into out_folder ----------------------------

# categories
out_categories = open(out_folder + "category.txt", "w")
for category, id in all_category.items():
    l = {
        "id": id,
         "category": category
    }
    out_categories.write(json.dumps(l) + "\n")

out_categories.close()

# keywords
out_keywords = open(out_folder + "keyword.txt", "w")
for keyword, id in all_keywords.items():
    l = {
        "id": id,
        "keyword": keyword
    }
    out_keywords.write(json.dumps(l) + "\n")

out_keywords.close()

# emoji and emoji_keywords
out_emoji = open(out_folder + "emoji.txt", "w")
out_emoji_keyword = open(out_folder + "emoji_keyword.txt", "w")

for emoji in all_emoji:

    l_emoji = {
        "id": emoji.id,
        "code": emoji.code,
        "name": emoji.name,
        "short_name": emoji.short_name,
        "has_tone": emoji.has_tone,
        "tone": emoji.tone,
        "emoji_order": emoji.emoji_order,
        "category_id": all_category[emoji.category]
    }
    out_emoji.write(json.dumps(l_emoji) + "\n")

    for keyword in emoji.keywords:
        l_emoji_keyword = {
            "emoji_id": emoji.id,
            "keyword_id": all_keywords[keyword]
        }
        out_emoji_keyword.write(json.dumps(l_emoji_keyword) + "\n")

out_emoji.close()
out_keywords.close()

print("Finished. parsed files are in: " + out_folder)
