import json
from emoji import Emoji

# balacklist skip these emoji
blacklist = set(["37", "36", "31", "30", "32", "2a", "23", "38", "33", "34", "35", "39"])

out_folder = "./parsed/"

json_file = open("emoji.json")
json_string = json_file.read()
json_file.close()

emojis_json = json.loads(json_string)

# for parsing
all_category = {} #{"category_name":id}
all_keywords = {} #{"keyword_name":id}
all_emoji = {} #{"emojiOne_id": Emoji}

# Make all_emoji
for id, key in enumerate(emojis_json):
    all_emoji[key] = Emoji()
    all_emoji[key].id = id

# Make all_category ---------------------------------------------------------
'''
    Goes through all emoji extracts categories, puts category to Emoji, puts them in a list
    sorts the list and puts all into all_category
'''
category_extracted = []
# extracts
for key in emojis_json:
    emoji = emojis_json[key]
    category = emoji["category"]
    if category not in category_extracted:
        category_extracted.append(category)

# sorts
category_extracted.sort()

# puts into all_category
for i, s in enumerate(category_extracted):
    all_category[s] = i

# Make all_keywords and Emoji---------------------------------------------------------
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
keywords_extracted = []
# extracts
for key in emojis_json:
    emoji = emojis_json[key]
    extracted = set()

    # extracts from name
    field = emoji["name"]
    all_emoji[key].name = field
    for s in field.split(" "):
        extracted.add(s.lower())

    # extracts from category
    field = emoji["category"]
    all_emoji[key].category = field
    for s in field.split(" "):
        extracted.add(s.lower())

    # extracts from shortname
    field = emoji["shortname"]
    all_emoji[key].short_name = field
    for s in field.split(" "):
        extracted.add(s.lower())

    # extracts from shortname_alternates
    field = emoji["shortname_alternates"]
    for s in field:
        extracted.add(s.lower())

    # extracts from ascii
    field = emoji["ascii"]
    for s in field:
        extracted.add(s.lower())

    # extracts from keywords
    field = emoji["keywords"]
    for s in field:
        extracted.add(s.lower())

    # sets code
    field = key
    field = field.lstrip("0")
    all_emoji[key].code = field

    # sets has_tone
    '''
        If an emoji in emoji.json has something in diversities:[...]
        This means that the emoji has tone modifications but this emoji has tone 0
    '''
    diversities = emoji["diversities"]
    vas_tone_from_diversities = False

    if len(diversities) != 0:
        all_emoji[key].has_tone = True
        vas_tone_from_diversities = True

    if " tone" in emoji["name"]:
        all_emoji[key].has_tone = True

    # sets tone
    if all_emoji[key].has_tone and vas_tone_from_diversities is False:
        shortname = emoji["shortname"]
        tone_string = shortname[len(shortname) -2]
        tone = int(tone_string)
        all_emoji[key].tone = tone

    # sets emoji_order
    all_emoji[key].emoji_order = int(emoji["order"])

    # removes "" from extracted
    if "" in extracted:
        extracted.remove("")

    # sets keywords
    all_emoji[key].keywords = extracted



    # puts into keywords_extracted
    for s in extracted:
        if s not in keywords_extracted:
            keywords_extracted.append(s)

# sorts
keywords_extracted.sort()

# puts into all_keywords
for i, s in enumerate(keywords_extracted):
    all_keywords[s] = i

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

for emoji in all_emoji.values():

    if emoji.code in blacklist:
        continue

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
