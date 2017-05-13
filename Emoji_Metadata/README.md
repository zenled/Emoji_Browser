# Emoji Metadata

All files for generating Database with emoji metadata.

## How to use

Just run the *generate_emoji_metadata.sh*

```bash
bash generate_emoji_metadata.sh
```

## Troubleshooting

The database */database/filled.db* must be empty in order for *generate_emoji_metadata.sh* to work. SQL for deleting data is in */SQL/delete/*

## How it works
*parse.py* parses *emoji.json* and creates files in */parsed/*

*fill_database.py* fills the database in */database/filled.db* this database is the exact copy of the database that will be used on a phone.

*optimize.py* creates .txt files where every column is in its own row.  
Optimized files are in */optimized/*

## Sources
All metadata is from [EmojiOne](https://github.com/Ranks/emojione) project, specifically from emoji.json file.


