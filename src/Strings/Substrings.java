package Strings;

import java.util.ArrayList;
import java.util.List;

public class Substrings {

    public static void generate(String s) {
        generateSets(s, new ArrayList<>());
    }

    private static void generateSets(String remainingString, List<String> strings) {
        // if we are at the end of the string, print the set and return
        if(remainingString.equals("") || remainingString == null) {
            for(String s: strings) {
                System.out.print(s + ",");
            }
            return;
        }

        // generate all substrings of remainingString
        // for each substring, add it to the strings list
        // when backtracking to a previous stack frame, don't forget to remove the one you added
        for(int i = 1; i < remainingString.length(); i++) {
            // strings
            strings.add(0,remainingString.substring(0, i));
            generateSets(remainingString.substring(i+1), strings);
            strings.remove(0);
        }
    }
}


//    String testString = "hello";
//        System.out.println(testString.substring(3));
//        if(testString.substring(5,5).equals("")) {
//        //System.out.println("it returns empty string");
//    }
