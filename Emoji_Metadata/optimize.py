import json

resources_dir = "./parsed/"
out_dir = "./optimized/"

# optimization of category ---------------------------------------------------------------------------------------------
resource = open(resources_dir + "category.txt")

outStr = ""

for line in resource:
    line_json = json.loads(line)
    outStr += str(line_json["id"])
    outStr += "\n"
    outStr += line_json["category"]
    outStr += "\n"
resource.close()

# so that there isn't blank like at end of file
outStr = outStr[:-1]

out_file = open(out_dir + "category.txt", "w")
out_file.write(outStr)
out_file.close()

# optimization of emoji ------------------------------------------------------------------------------------------------
resource = open(resources_dir + "emoji.txt")

outStr = ""

for line in resource:
    line_json = json.loads(line)
    outStr += str(line_json["id"])
    outStr += "\n"
    outStr += line_json["code"]
    outStr += "\n"
    outStr += line_json["code_point"]
    outStr += "\n"
    outStr += line_json["name"]
    outStr += "\n"
    outStr += line_json["short_name"]
    outStr += "\n"
    outStr += str(int(line_json["has_tone"]))
    outStr += "\n"
    outStr += str(line_json["tone"])
    outStr += "\n"
    outStr += str(line_json["emoji_order"])
    outStr += "\n"
    outStr += str(line_json["category_id"])
    outStr += "\n"
resource.close()

# so that there isn't blank like at end of file
outStr = outStr[:-1]

out = open(out_dir + "emoji.txt", "w")
out.write(outStr)
out.close()

# optimization of emoji_keyword ----------------------------------------------------------------------------------------
resource = open(resources_dir + "emoji_keyword.txt")

outStr = ""

for line in resource:
    line_json = json.loads(line)
    outStr += str(line_json["emoji_id"])
    outStr += "\n"
    outStr += str(line_json["keyword_id"])
    outStr += "\n"
resource.close()

# so that there isn't blank like at end of file
outStr = outStr[:-1]

out = open(out_dir + "emoji_keyword.txt", "w")
out.write(outStr)
out.close()

# optimization of keyword ----------------------------------------------------------------------------------------------
resource = open(resources_dir + "keyword.txt")

outStr = ""

for line in resource:
    line_json = json.loads(line)
    outStr += str(line_json["id"])
    outStr += "\n"
    outStr += line_json["keyword"]
    outStr += "\n"
resource.close()

# so that there isn't blank like at end of file
outStr = outStr[:-1]

out = open(out_dir + "keyword.txt", "w")
out.write(outStr)
out.close()
