
/*
 * Name : Rahul Ganeshwar Patil
 * Date : 18/11/2024
 * Description : [Make a program that gives total count palindromes in a substring]
 */

import java.util.Scanner;

public class CountPalindromes {
    static String[] resultArray;
    final static Constant constant = new Constant();
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int i = 0;
        while(i < 5) {
        	System.out.println(constant.ENTER_INPUT);
            String mainString = input.nextLine();
            if (mainString == null || mainString.isEmpty() || mainString.length() > 25) {
                System.out.println(constant.EMPTY_STRING);
                i++;
                continue;
            }
            generateSubStrings(mainString,"", 0, 1);
            System.out.println(constant.TOTAL_PALINDROME + countPalindrome(resultArray, 0, 0));
            i++;
        }
    }

    //This function is used to take any string and convert it into substrings
    public static String generateSubStrings(String mainString,String result, int start, int end){
        if (start >= mainString.length() && end > mainString.length()) {
            return result;
        }else{
            String appendStringElement = mainString.substring(start, end);
            if (start >= 0 && end>1) {
                if (start == mainString.length()-1 && end == mainString.length()) {
                    if (!isFound(resultArray, appendStringElement, 0)) {
                        result += appendStringElement;
                        resultArray = result.split(",");
                    }
                }else{
                    if (!isFound(resultArray, appendStringElement, 0)) {
                        result += appendStringElement+",";
                        resultArray = result.split(",");
                    }
                }
            }else{
                result += appendStringElement+",";
                resultArray = result.split(",");
                }
            }
            
            if (end == mainString.length()) {
                return generateSubStrings(mainString, result, start+1, start+2);
            }
            return generateSubStrings(mainString, result, start, ++end);
        }
    
    //isFound function is used to check if the substring is unique
    public static boolean isFound(String[] result, String element, int index){
        if (index > result.length-1) {
            return false;
        }else if(result[index].equals(element) ){
            return true;
        }
        return isFound(result, element, ++index);
    }

    //isPalindrome function is used to check whether the given String is palindrome or not
    public static boolean isPalindrome(String mainString,char[] characterArray,int index, String reverseString) {
         if(index < 0) {
              if(reverseString.equals(mainString)) {
                   return true;
              }
              else {
                  return false;
              }
         }
         else {
             reverseString = reverseString + characterArray[index];
             index--;
             return isPalindrome(mainString, characterArray, index, reverseString);
         }
        }
    
    //Count palindrome function is used to count possible palindromes in given array of string 
    public static int countPalindrome(String[] resultArray, int index, int count){
          if (index > resultArray.length - 1) {
              return count;
          }else{
              if (isPalindrome(resultArray[index], resultArray[index].toCharArray(), resultArray[index].toCharArray().length-1, "")) {
                  System.out.print(resultArray[index] + " ");
            	  count++;
              }
              return countPalindrome(resultArray, ++index, count);
          }
        }
}
