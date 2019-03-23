package main;

import java.util.Random;


public class Vigenere {
	
    public static String encrypt(String text,String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z' || c == 'Ë')
                continue;
            res += (char) ((c + key.charAt(j) - 2 * 'A') % 27 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
 
    public static String decrypt(String text,String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z' || c == 'Ë')
                continue;
            res += (char) ((c - key.charAt(j) + 27) % 27 + 'A');
            j = ++j % key.length();
        }
        return res;
    }
    static String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static Random rng = new Random();
    public static String generateString(int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rng.nextInt(characters.length()));
        }
        return new String(text);
    }
}
