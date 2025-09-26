package com.diva.basics.stringdemo;

import java.util.Arrays;

public class StringExamples {

    public static void main(String[] args) {
        reverseWord("hello");
        
        System.out.println(isPalindrome("ammaa")); 

        nameFormatter("dhivakar");

        countWordFrequency("INFO Start ERROR Connection ERROR Timeout");
    }


    public static String reverseWord(String word) {
        int sLen = word.length()-1;
        String reversed = "";
        for(int i = sLen; i >=0; i--){
            reversed += word.charAt(i);
        }
        return reversed;
    }

    public static boolean isPalindrome(String str) {
        boolean isPalindrome = true;
        for (int i = 0, j = str.length() - 1; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    public static void countVowelsAndConstants(String word) {
        int vowels = 0; int Consonants = 0;
        word = word.toLowerCase();
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if("aeiou".indexOf(ch) != -1){
                vowels++;
            }else if(ch >= 'a' && ch <= 'z'){
                Consonants++;
            }
        }
        System.out.println("Vowels :: " +vowels+","+"Consonants :: " +Consonants );
    }

    private static void wordsCount(){
        String s = "Java is fun and powerful";
        String[] sa = s.split(" ");

        int count = 0;
        for(int i=0; i < sa.length; i++){
            if(!sa[i].isEmpty()){
                count++;
            }
        }
        System.out.println("No. of words :: " +count);
    }

    private static boolean isStrongPassword(String pwd) {
        boolean hasDigit = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasSpecial = false;

        for(int i = 0; i < pwd.length(); i++) {
            char ch = pwd.charAt(i);

            if(Character.isUpperCase(ch)){
                hasUpper = true;
            } else if(Character.isLowerCase(ch)) {
                hasLower = true;
            } else if(Character.isDigit(ch)){
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }

        if(hasUpper && hasLower && hasDigit && hasSpecial && pwd.length() > 8){
            System.out.println("Strong passord");
            return true;
        } else{
            System.out.println("Weak password");
            return false;
        }

    }

    private static void repeatedWords(){
        String s = "INFO Start ERROR Connection ERROR Timeout";
        String[] arr = s.split(" "); 

        int errorCount = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i].equalsIgnoreCase("ERROR")){
                errorCount++;
            }
        }
        System.out.println("ERROR Count :: " +errorCount);
    }

    private static void nameFormatter(String name) {
       String[] words = name.split(" ");

        String formattedName = "";
        for(int i=0; i<words.length; i++){
            if(!words[i].isEmpty()){
                String firstLetter = words[i].substring(0,1).toUpperCase();
                String rest = words[i].substring(1).toLowerCase();
                formattedName += firstLetter + rest + " ";
            }
        }

        formattedName = formattedName.trim();
        System.out.println("Fully formmatted name :: " +formattedName);
    }

    private static void countWordFrequency(String sentence) {
        String words[] = sentence.split(" ");

        System.out.println(Arrays.asList(words).stream().count()); 

        for(int i=0; i < words.length; i++){
            int count = 0;
            for(int j=0; j < words.length; j++){
                if(words[i].equalsIgnoreCase(words[j])){
                    count++;
                }
            }
            System.out.println(words[i] + "->" + count);
        }
    }
}
