SELECT Emoji.code, Emoji.has_tone, Emoji.tone, Emoji.emoji_order, Category.category
FROM Emoji JOIN Category ON (Emoji.category_id = Category.id)
