/**
 * Copyright (c) 2016 Marco Velluto. All rights reserved.
 */
package it.reexon.lib.manipulation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;


/**
 * @author Marco Velluto
 * @since Java 1.8
 */
public class ManipulationUtils
{

    /**
     * Order randomly the characters of a String
     * <pre>
     * Example:
     *  shuffle("Hello") = hlleo
     *  shuffle("Hello") = llheo
     *  shuffle("Hello") = leohl
     *  shuffle("Hello") = lleho
     * </pre>
     * @param input String to be sorted randomly
     * @throws IllegalArgumentException if input string is null
     */
    public static String shuffle(String input)
    {
        if (input == null)
            throw new IllegalArgumentException("Input string cannot be null");

        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray())
        {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0)
        {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        return output.toString();
    }

    public static List<String> permutatios(String stringToPermutation)
    {
        if (stringToPermutation == null)
            throw new IllegalArgumentException("String to permutation cannot be null!");

        List<String> permutations = new LinkedList<>();
        for (char c : stringToPermutation.toCharArray())
        {
            StringBuilder sb = new StringBuilder(stringToPermutation);
            sb.deleteCharAt(0);
            sb.insert(0, c);
        }

        return null;
    }

    @Test
    public final void permutatiosTest()
    {
        permutatios("Ciao");
    }
}
