class Emoji:
    def __init__(self):
        self.id = None
        self.code = None
        self.name = None
        self.short_name = None
        self.has_tone = False
        self.tone = 0
        self.emoji_order = None
        self.keywords = set()
        self.category = None