package com.example.javafxdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BruteForce {

    public static List<String> bruteForceStringMatcher(File file, String pattern) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);
        String text = "";

        while(scanner.hasNextLine())
        {
             text += scanner.nextLine();
        }

        char[] textArray = text.toCharArray();
        char[] patternArray = pattern.toCharArray();

        int textLength = textArray.length;
        int patternLength = patternArray.length;

        List<String> matchedIndexes = new ArrayList<>();

        int textIndex = 0;
        String str = "";            //This will store the word if found

        for(textIndex = 0; textIndex < textLength; textIndex++){
            str = "";           //Reset str after every iteration
            int textIndexLocal = textIndex;
            Boolean match = true;
            int matchedIndex = textIndex;
            for(int patternIndex = 0; patternIndex < patternLength; patternIndex++){
                if(textArray[textIndexLocal] != patternArray[patternIndex]) {
                    match = false;
                    break;
                }
                str += textArray[textIndexLocal];
                textIndexLocal = textIndexLocal + 1;
            }
            if(match)
                matchedIndexes.add(str);
        }
        return matchedIndexes;
    }


    public static List<String> bruteForceStringMatcherCaseFree(File file, String pattern) throws FileNotFoundException {

        char ch1;
        char ch2;

        Scanner scanner = new Scanner(file);
        String text = "";

        while(scanner.hasNextLine())
        {
            text += scanner.nextLine();
        }

        char[] textArray = text.toCharArray();
        char[] patternArray = pattern.toCharArray();

        int textLength = textArray.length;
        int patternLength = patternArray.length;

        List<String> matchedIndexes = new ArrayList<>();

        int textIndex = 0;
        String str = "";            //This will store the word if found

        for(textIndex = 0; textIndex < textLength; textIndex++){
            str = "";           //Reset str after every iteration
            int textIndexLocal = textIndex;
            Boolean match = true;
            int matchedIndex = textIndex;
            for(int patternIndex = 0; patternIndex < patternLength; patternIndex++){
//                if(textArray[textIndexLocal] != patternArray[patternIndex])
                if((textArray[textIndexLocal] == patternArray[patternIndex]) || ((ch1 =
                        Character.toUpperCase(patternArray[patternIndex])) == (ch2 =
                        Character.toUpperCase(textArray[textIndex]))))
                {
                    str += textArray[textIndexLocal];
                    textIndexLocal = textIndexLocal + 1;

                }
                else
                {
                    match = false;
                    break;
                }

            }
            if(match)
                matchedIndexes.add(str);
        }
        return matchedIndexes;
    }
}
