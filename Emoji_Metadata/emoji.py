class Emoji:
    def __init__(self):
        self.id = None
        self.code = None
        self.code_point = None
        self.name = None
        self.short_name = None
        self.has_tone = False
        self.tone = 0
        self.emoji_order = None
        self.keywords = set()
        self.category = None
        self.real_code = None

    def all_keywords_to_lover(self):
        new_keywords = set()
        for keyword in self.keywords:
            new_keywords.add(keyword.lower())

        self.keywords = new_keywords

    def remove_blank_keywords(self):
        if "" in self.keywords:
            self.keywords.remove("")
        if " " in self.keywords:
            self.keywords.remove(" ")

