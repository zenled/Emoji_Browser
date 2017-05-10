import json

resources_dir = "./parsed/"
out_dir = "./optimized/"

# optimization of category ---------------------------------------------------------------------------------------------
sql = "INSERT INTO Category (id, category) VALUES "

resource = open(resources_dir + "category.txt")

for line in resource:
    line_json = json.loads(line)
    s = "( " + str(line_json["id"]) + ", \"" + line_json["category"] + "\"), "
    sql += s
resource.close()
# removes the last ", " from sql
sql = sql[:-2]

out = open(out_dir + "SQL_insert_category.sql", "w")
out.write(sql)
out.close()

# optimization of emoji ------------------------------------------------------------------------------------------------
sql = "INSERT INTO Emoji (id, code, name, short_name, has_tone, tone, emoji_order, category_id) VALUES "

resource = open(resources_dir + "emoji.txt")

for line in resource:
    line_json = json.loads(line)
    s = "( " + str(line_json["id"]) + \
        ", \"" + line_json["code"] + "\"" + \
        ", \"" + line_json["name"] + "\"" + \
        ", \"" + line_json["short_name"] + "\"" + \
        ", " + str(int(line_json["has_tone"])) + \
        ", " + str(line_json["tone"]) + \
        ", " + str(line_json["emoji_order"]) + \
        ", " + str(line_json["category_id"]) + \
        "), "

    sql += s
resource.close()
# removes the last ", " from sql
sql = sql[:-2]

out = open(out_dir + "SQL_insert_emoji.sql", "w")
out.write(sql)
out.close()

# optimization of emoji_keyword ----------------------------------------------------------------------------------------
sql = "INSERT INTO Emoji_Keyword (emoji_id, keyword_id) VALUES "

resource = open(resources_dir + "emoji_keyword.txt")

for line in resource:
    line_json = json.loads(line)
    s = "( " + str(line_json["emoji_id"]) + ", " + str(line_json["keyword_id"]) + "), "
    sql += s
resource.close()
# removes the last ", " from sql
sql = sql[:-2]

out = open(out_dir + "SQL_insert_emoji_keyword.sql", "w")
out.write(sql)
out.close()

# optimization of keyword ----------------------------------------------------------------------------------------------
sql = "INSERT INTO Keyword (id, keyword) VALUES "

resource = open(resources_dir + "keyword.txt")

for line in resource:
    line_json = json.loads(line)
    s = "( " + str(line_json["id"]) + ", \"" + line_json["keyword"] + "\"), "
    sql += s
resource.close()
# removes the last ", " from sql
sql = sql[:-2]

out = open(out_dir + "SQL_insert_keyword.sql", "w")
out.write(sql)
out.close()
