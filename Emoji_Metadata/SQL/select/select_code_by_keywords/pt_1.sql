SELECT Emoji.id
FROM Emoji JOIN Emoji_Keyword ON (Emoji.id = Emoji_Keyword.emoji_id)
		JOIN Keyword ON (Emoji_Keyword.keyword_id = Keyword.id)
WHERE Keyword.keyword LIKE "
