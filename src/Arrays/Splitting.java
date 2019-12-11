package Arrays;

import java.util.List;

public class Splitting {
    // generate all substrings
    public static void parts(String word, int beg, List<String> current) {
        if(beg >= word.length()) {
            for(String str: current) {
                System.out.print(str + ",");
            }
            System.out.println(" Done words");
            return;
        }

        //iterate over all endpoints from the beginning point
        for(int i = beg; i < word.length(); i++) {
            String temp = word.substring(beg, i+1);
            current.add(temp);
            parts(word, i + 1, current);
            current.remove(temp);
        }
    }
}
