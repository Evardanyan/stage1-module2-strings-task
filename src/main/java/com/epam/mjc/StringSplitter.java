package com.epam.mjc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> newDelimiters = new ArrayList<>(delimiters);
        String result = "";
        for (int i = 0; i < delimiters.size(); i++) {
            result = source.replaceAll(newDelimiters.get(i), " ");
            source = result;
        }
        String [] tempRes = source.split(" ");
        return Arrays.asList(tempRes);
    }
}
