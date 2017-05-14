import json
import sqlite3

db = sqlite3.connect("./database/filled.db")

resources = "./parsed/"
sql_inserts = "./SQL/insert/"

# inserts categories
categories_resource = open(resources + "category.txt")
sql_res = open(sql_inserts + "insert_category.sql")

sql = sql_res.read()

sql_res.close()

purchases = []
for line in categories_resource:
    line_json = json.loads(line)
    purchases.append(tuple([line_json["id"], line_json["category"]]))

db.executemany(sql, purchases)

categories_resource.close()

# inserts keywords
keywords_resource = open(resources + "keyword.txt")
sql_res = open(sql_inserts + "insert_keyword.sql")

sql = sql_res.read()
sql_res.close()

purchases = []
for line in keywords_resource:
    line_json = json.loads(line)
    purchases.append(tuple([line_json["id"], line_json["keyword"]]))

db.executemany(sql, purchases)

# insert emoji_keyword
ek_resources = open(resources + "emoji_keyword.txt")
sql_res = open(sql_inserts + "insert_emoji_keyword.sql")

sql = sql_res.read()
sql_res.close()

purchases = []
for line in ek_resources:
    line_json = json.loads(line)
    purchases.append(tuple([line_json["emoji_id"], line_json["keyword_id"]]))

db.executemany(sql, purchases)

# insert emoji
emoji_resource = open(resources + "emoji.txt")
sql_res = open(sql_inserts + "insert_emoji.sql")

sql = sql_res.read()
sql_res.close()

purchases = []
for line in emoji_resource:
    line_json = json.loads(line)
    purchases.append(tuple([
        line_json["id"],
        line_json["code"],
        line_json["code_point"],
        line_json["name"],
        line_json["short_name"],
        line_json["has_tone"],
        line_json["tone"],
        line_json["emoji_order"],
        line_json["category_id"]
    ]))

db.executemany(sql, purchases)


# closes database
db.commit()
db.close()
