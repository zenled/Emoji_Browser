package com.kukumoraketo.emojibrowser.Emoji.Emoji;

/**
 * Created by zed on 29.4.2017.
 */

public enum EmojiCategory {

    NOT_VALID{
        @Override
        public String toString(){
            return "Not Valid";
        }
    },
    PEOPLE {
        @Override
        public String toString(){
            return "people";
        }
    },
    ACTIVITY {
        @Override
        public String toString(){
            return "activity";
        }
    },
    OBJECTS {
        @Override
        public String toString(){
            return "objects";
        }
    },
    TRAVEL {
        @Override
        public String toString(){
            return "travel";
        }
    },
    FOOD {
        @Override
        public String toString(){
            return "food";
        }
    },
    FLAGS {
        @Override
        public String toString(){
            return "flags";
        }
    },
    SYMBOLS {
        @Override
        public String toString(){
            return "symbols";
        }
    },
    NATURE {
        @Override
        public String toString(){
            return "nature";
        }
    },
    REGIONAL {
        @Override
        public String toString(){
            return "regional";
        }
    },
    MODIFIER {
        @Override
        public String toString(){
            return "modifier";
        }
    };

    public static EmojiCategory stringToCategory(String categoryString){
        switch (categoryString.toLowerCase()){
            case "people":
                return EmojiCategory.PEOPLE;
            case "activity":
                return EmojiCategory.ACTIVITY;
            case "objects":
                return EmojiCategory.OBJECTS;
            case "travel":
                return EmojiCategory.TRAVEL;
            case "food":
                return EmojiCategory.FOOD;
            case "flags":
                return EmojiCategory.FLAGS;
            case "symbols":
                return EmojiCategory.SYMBOLS;
            case "nature":
                return EmojiCategory.NATURE;
            case "regional":
                return EmojiCategory.REGIONAL;
            case "modifier":
                return EmojiCategory.MODIFIER;
            default:
                return EmojiCategory.NOT_VALID;
        }
    }

}
