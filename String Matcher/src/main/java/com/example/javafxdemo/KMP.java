package com.example.javafxdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KMP {

    public static List<String> KMPSearch(File file, String pat) throws FileNotFoundException {
        String txt = "";
        char ch1;
        char ch2;

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine())
        {
            txt += scanner.nextLine();
        }

        List<String> matchedIndexes = new ArrayList<>();            //Contains the matched strings

        String str = "";            //To store the matched word

        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while ((N - i) >= (M - j)) {

            if ((pat.charAt(j) == txt.charAt(i)) || ((ch1 = Character.toUpperCase(pat.charAt(j))) == (ch2 =
                    Character.toUpperCase(txt.charAt(i)))))
            {
                str += txt.charAt(i);
                j++;
                i++;
            }
            if (j == M) {
//                System.out.println("Found pattern at index " + (i - j));
                matchedIndexes.add(str);
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                str = "";           //Reset the string if it mismatches during search

                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }

        return matchedIndexes;
    }

    public static List<String> KMPSearchMatchCase(File file, String pat) throws FileNotFoundException {
        String txt = "";
        char ch1;
        char ch2;

        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine())
        {
            txt += scanner.nextLine();
        }

        List<String> matchedIndexes = new ArrayList<>();            //Contains the matched strings

        String str = "";            //To store the matched word

        int M = pat.length();
        int N = txt.length();

        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]

        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);

        int i = 0; // index for txt[]
        while ((N - i) >= (M - j)) {

            if (pat.charAt(j) == txt.charAt(i))
            {
                str += txt.charAt(i);
                j++;
                i++;
            }
            if (j == M) {
//                System.out.println("Found pattern at index " + (i - j));
                matchedIndexes.add(str);
                j = lps[j - 1];
            }

            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                str = "";           //Reset the string if it mismatches during search

                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }

        return matchedIndexes;
    }

    static void computeLPSArray(String pat, int M, int lps[]) {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];

                    // Also, note that we do not increment
                    // i here
                } else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }
}
