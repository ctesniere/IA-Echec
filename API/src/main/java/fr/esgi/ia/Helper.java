package fr.esgi.ia;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper class. It's main role is to work
 * with strings.
 * 
 * @author CŽdric TESNIERE
 */
public class Helper {
    
    /**
     * Return the number representing the position on the chessboard
     * from a string. Use only the first char of the string.
     * 
     * @param move The string representing the move
     * @return The number representing the position on the chessboard
     */
    public static int getXfromString (String move) {
    	
        int valueOfX = 0;
        if ((move.charAt(0) ==  'a')) valueOfX = 0;
        if ((move.charAt(0) ==  'b')) valueOfX = 1;
        if ((move.charAt(0) ==  'c')) valueOfX = 2;
        if ((move.charAt(0) ==  'd')) valueOfX = 3;
        if ((move.charAt(0) ==  'e')) valueOfX = 4;
        if ((move.charAt(0) ==  'f')) valueOfX = 5;
        if ((move.charAt(0) ==  'g')) valueOfX = 6;
        if ((move.charAt(0) ==  'h')) valueOfX = 7;
        return valueOfX;
    }
    
    /**
     * Return the number representing the position on the chessboard
     * from a string. Use only the second char of the string.
     * 
     * @param move The string representing the move
     * @return The number representing the position on the chessboard
     */
    public static int getYfromString (String move) {
    	
    	//TODO: Pass a char and use a case
        int valueOfY = 0;
        if ((move.charAt(1) ==  '1')) valueOfY = 0;
        if ((move.charAt(1) ==  '2')) valueOfY = 1;
        if ((move.charAt(1) ==  '3')) valueOfY = 2;
        if ((move.charAt(1) ==  '4')) valueOfY = 3;
        if ((move.charAt(1) ==  '5')) valueOfY = 4;
        if ((move.charAt(1) ==  '6')) valueOfY = 5;
        if ((move.charAt(1) ==  '7')) valueOfY = 6;
        if ((move.charAt(1) ==  '8')) valueOfY = 7;
        return valueOfY ;
    }
    
    /**
     * Read a string from input.
     * 
     * @return The string read
     */
    public static String readFromInput () {
    	
        InputStreamReader ISR = new InputStreamReader(System.in);
        BufferedReader BR = new BufferedReader(ISR);
        String input = new String();

        try {
        	input = BR.readLine();
        }
        catch (IOException e) {
        	input = null;
        };
        
        return input;
    }
    
    /**
     * Use the first char of the input to return the
     * profoundity of the algorithm. Accept only number from
     * 1 to 5.
     * 
     * @param input The first input of main
     * @return The profoundity
     */
    public static int returnProf (String[] input) {
        
        int ret;
        switch (input[0].charAt(0)){
            case ('1'): ret = 1; break;
            case ('2'): ret = 2; break;
            case ('3'): ret = 3; break;
            case ('4'): ret = 4; break;
            case ('5'): ret = 5; break;
            default: ret = 2;
        }
        return ret;
    }
    
    /**
     * Return true if the number is even.
     * 
     * @param number Number to test
     * @return True if even
     */
    public static boolean IsEven (int number) {
        int half = number / 2;
        if ((half * 2) == number) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Transform a set of numeric position in the
     * string equivalent.
     * 
     * @param x The numeric coordinate on the x axis
     * @param y The numeric coordinate on the y axis
     * @return The string representing the position
     */
    public static String getStringFromPosition (int x, int y) {
    	
        String string = "";
        if ((y==0)&&(x==0)) string = "a1";
        if ((y==1)&&(x==0)) string = "a2";
        if ((y==2)&&(x==0)) string = "a3";
        if ((y==3)&&(x==0)) string = "a4";
        if ((y==4)&&(x==0)) string = "a5";
        if ((y==5)&&(x==0)) string = "a6";
        if ((y==6)&&(x==0)) string = "a7";
        if ((y==7)&&(x==0)) string = "a8";
        if ((y==0)&&(x==1)) string = "b1";
        if ((y==1)&&(x==1)) string = "b2";
        if ((y==2)&&(x==1)) string = "b3";
        if ((y==3)&&(x==1)) string = "b4";
        if ((y==4)&&(x==1)) string = "b5";
        if ((y==5)&&(x==1)) string = "b6";
        if ((y==6)&&(x==1)) string = "b7";
        if ((y==7)&&(x==1)) string = "b8";
        if ((y==0)&&(x==2)) string = "c1";
        if ((y==1)&&(x==2)) string = "c2";
        if ((y==2)&&(x==2)) string = "c3";
        if ((y==3)&&(x==2)) string = "c4";
        if ((y==4)&&(x==2)) string = "c5";
        if ((y==5)&&(x==2)) string = "c6";
        if ((y==6)&&(x==2)) string = "c7";
        if ((y==7)&&(x==2)) string = "c8";
        if ((y==0)&&(x==3)) string = "d1";
        if ((y==1)&&(x==3)) string = "d2";
        if ((y==2)&&(x==3)) string = "d3";
        if ((y==3)&&(x==3)) string = "d4";
        if ((y==4)&&(x==3)) string = "d5";
        if ((y==5)&&(x==3)) string = "d6";
        if ((y==6)&&(x==3)) string = "d7";
        if ((y==7)&&(x==3)) string = "d8";
        if ((y==0)&&(x==4)) string = "e1";
        if ((y==1)&&(x==4)) string = "e2";
        if ((y==2)&&(x==4)) string = "e3";
        if ((y==3)&&(x==4)) string = "e4";
        if ((y==4)&&(x==4)) string = "e5";
        if ((y==5)&&(x==4)) string = "e6";
        if ((y==6)&&(x==4)) string = "e7";
        if ((y==7)&&(x==4)) string = "e8";
        if ((y==0)&&(x==5)) string = "f1";
        if ((y==1)&&(x==5)) string = "f2";
        if ((y==2)&&(x==5)) string = "f3";
        if ((y==3)&&(x==5)) string = "f4";
        if ((y==4)&&(x==5)) string = "f5";
        if ((y==5)&&(x==5)) string = "f6";
        if ((y==6)&&(x==5)) string = "f7";
        if ((y==7)&&(x==5)) string = "f8";
        if ((y==0)&&(x==6)) string = "g1";
        if ((y==1)&&(x==6)) string = "g2";
        if ((y==2)&&(x==6)) string = "g3";
        if ((y==3)&&(x==6)) string = "g4";
        if ((y==4)&&(x==6)) string = "g5";
        if ((y==5)&&(x==6)) string = "g6";
        if ((y==6)&&(x==6)) string = "g7";
        if ((y==7)&&(x==6)) string = "g8";
        if ((y==0)&&(x==7)) string = "h1";
        if ((y==1)&&(x==7)) string = "h2";
        if ((y==2)&&(x==7)) string = "h3";
        if ((y==3)&&(x==7)) string = "h4";
        if ((y==4)&&(x==7)) string = "h5";
        if ((y==5)&&(x==7)) string = "h6";
        if ((y==6)&&(x==7)) string = "h7";
        if ((y==7)&&(x==7)) string = "h8";
        return string;
    }

}