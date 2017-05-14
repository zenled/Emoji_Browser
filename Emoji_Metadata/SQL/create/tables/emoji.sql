CREATE TABLE "Emoji" (
	`id`	INTEGER NOT NULL UNIQUE,
	`code`	TEXT NOT NULL UNIQUE,
	`code_point`	TEXT NOT NULL UNIQUE,
	`name`	TEXT NOT NULL,
	`short_name`	TEXT,
	`has_tone`	INTEGER NOT NULL,
	`tone`	INTEGER,
	`emoji_order`	INTEGER NOT NULL UNIQUE,
	`category_id`	INTEGER NOT NULL,
	PRIMARY KEY(id)
)
