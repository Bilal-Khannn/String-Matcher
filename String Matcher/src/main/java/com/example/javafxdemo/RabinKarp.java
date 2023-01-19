package com.example.javafxdemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RabinKarp {
    public final static int d = 10;
    public final static int z = 10;

    public static List<String> search(File file, String pattern, int q) throws FileNotFoundException {
        List<String> matchedIndexes = new ArrayList<>();
        String str = "";                //To store the matched word and add it to the list
        String txt = "";                     //Stores the content of the file where the word is to be searched

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            txt += scanner.nextLine();
        }

        int m = pattern.length();
        int n = txt.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;

        // Calculate hash value for pattern and text
        for (i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        // Find the match
        for (i = 0; i <= n - m; i++)
        {
            str = "";
            if (p == t)
            {
                str = "";
                for (j = 0; j < m; j++)
                {
                    if (txt.charAt(i + j) != pattern.charAt(j))
                    {
                        break;
                    }
                    else
                    {
                        str += txt.charAt(i+j);
                    }
                }
                if (j == m)
                    matchedIndexes.add(str);
//                    System.out.println("Pattern is found at position: " + (i + 1));
            }

            if (i < n - m) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
        return matchedIndexes;
    }


    public static List<String> searchCaseFree(File file, String pattern, int q) throws FileNotFoundException {
        List<String> matchedIndexes = new ArrayList<>();
        String str = "";                //To store the matched word and add it to the list
        String txt = "";                     //Stores the content of the file where the word is to be searched

        char ch1;
        char ch2;

        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            txt += scanner.nextLine();
        }

        int m = pattern.length();
        int n = txt.length();
        int i, j;
        int p = 0;
        int t = 0;
        int h = 1;

        for (i = 0; i < m - 1; i++)
            h = (h * z) % q;

        // Calculate hash value for pattern and text
        for (i = 0; i < m; i++) {
            p = (z * p + pattern.charAt(i)) % q;
            t = (z * t + txt.charAt(i)) % q;
        }

        // Find the match
        for (i = 0; i <= n - m; i++)
        {
            str = "";
            if (p == t)
            {
                str = "";
                for (j = 0; j < m; j++)
                {
                    if ((txt.charAt(i + j) != pattern.charAt(j)) && ((ch1 = Character.toUpperCase(txt.charAt(i + j))) != (ch2 =
                            Character.toUpperCase(pattern.charAt(j)))) )
                    {
                        break;
                    }
                    else
                    {
                        str += txt.charAt(i+j);
                    }
                }
                if (j == m)
                    matchedIndexes.add(str);
//                    System.out.println("Pattern is found at position: " + (i + 1));
            }

            if (i < n - m) {
                t = (z * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
                if (t < 0)
                    t = (t + q);
            }
        }
        return matchedIndexes;
    }
}
