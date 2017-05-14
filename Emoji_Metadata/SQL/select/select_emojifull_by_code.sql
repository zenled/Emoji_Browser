SELECT  Emoji.code, Emoji.code_point, Emoji.name, Emoji.short_name, Emoji.has_tone, Emoji.tone, Emoji.emoji_order, Category.category, Keyword.keyword
FROM Category JOIN Emoji ON (Category.id = Emoji.category_id) 
		JOIN Emoji_Keyword ON (Emoji.id = Emoji_Keyword.emoji_id)
		JOIN Keyword ON (Emoji_Keyword.keyword_id = Keyword.id)
WHERE Emoji.code = "?"
