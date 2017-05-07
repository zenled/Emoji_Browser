package com.kukumoraketo.emojibrowser.Utils;

/**
 * Created by zed on 7.5.2017.
 */

public class StringUtils {

    private StringUtils(){
        // this is static only class
    }

    public static String toCamelCase(String s){
        StringBuilder r = new StringBuilder();

        boolean toUpper = true;
        for (char c : s.toCharArray()){
            if (c == ' '){
                toUpper = true;
                r.append(c);
                continue;
            }
            else{
                if (toUpper){
                    r.append(Character.toUpperCase(c));
                    toUpper = false;
                }
                else{
                    r.append(c);
                }
            }
        }

        return r.toString();
    }
}
