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
     * @param _move The string representing the move
     * @return The number representing the position on the chessboard
     */
    public static int getXfromString (String _move) {
        if ((_move.charAt(0) ==  'a')) return 0;
        if ((_move.charAt(0) ==  'b')) return 1;
        if ((_move.charAt(0) ==  'c')) return 2;
        if ((_move.charAt(0) ==  'd')) return 3;
        if ((_move.charAt(0) ==  'e')) return 4;
        if ((_move.charAt(0) ==  'f')) return 5;
        if ((_move.charAt(0) ==  'g')) return 6;
        if ((_move.charAt(0) ==  'h')) return 7;
		return -1;
    }
    
    /**
     * Return the number representing the position on the chessboard
     * from a string. Use only the second char of the string.
     * 
     * @param _move The string representing the move
     * @return The number representing the position on the chessboard
     */
    public static int getYfromString (String _move) {
        if ((_move.charAt(1) ==  '1')) return 0;
        if ((_move.charAt(1) ==  '2')) return 1;
        if ((_move.charAt(1) ==  '3')) return 2;
        if ((_move.charAt(1) ==  '4')) return 3;
        if ((_move.charAt(1) ==  '5')) return 4;
        if ((_move.charAt(1) ==  '6')) return 5;
        if ((_move.charAt(1) ==  '7')) return 6;
        if ((_move.charAt(1) ==  '8')) return 7;
		return -1;
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
     * @param _input The first input of main
     * @return The profoundity
     */
    public static int returnProf (String[] _input) {
        
        int ret;
        switch (_input[0].charAt(0)){
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
     * Transform a set of numeric position in the
     * string equivalent.
     * 
     * @param _x The numeric coordinate on the x axis
     * @param _y The numeric coordinate on the y axis
     * @return The string representing the position
     */
    public static String getStringFromPosition (int _x, int _y) {
        if ((_y==0)&&(_x==0)) return "a1";
        if ((_y==1)&&(_x==0)) return "a2";
        if ((_y==2)&&(_x==0)) return "a3";
        if ((_y==3)&&(_x==0)) return "a4";
        if ((_y==4)&&(_x==0)) return "a5";
        if ((_y==5)&&(_x==0)) return "a6";
        if ((_y==6)&&(_x==0)) return "a7";
        if ((_y==7)&&(_x==0)) return "a8";
        if ((_y==0)&&(_x==1)) return "b1";
        if ((_y==1)&&(_x==1)) return "b2";
        if ((_y==2)&&(_x==1)) return "b3";
        if ((_y==3)&&(_x==1)) return "b4";
        if ((_y==4)&&(_x==1)) return "b5";
        if ((_y==5)&&(_x==1)) return "b6";
        if ((_y==6)&&(_x==1)) return "b7";
        if ((_y==7)&&(_x==1)) return "b8";
        if ((_y==0)&&(_x==2)) return "c1";
        if ((_y==1)&&(_x==2)) return "c2";
        if ((_y==2)&&(_x==2)) return "c3";
        if ((_y==3)&&(_x==2)) return "c4";
        if ((_y==4)&&(_x==2)) return "c5";
        if ((_y==5)&&(_x==2)) return "c6";
        if ((_y==6)&&(_x==2)) return "c7";
        if ((_y==7)&&(_x==2)) return "c8";
        if ((_y==0)&&(_x==3)) return "d1";
        if ((_y==1)&&(_x==3)) return "d2";
        if ((_y==2)&&(_x==3)) return "d3";
        if ((_y==3)&&(_x==3)) return "d4";
        if ((_y==4)&&(_x==3)) return "d5";
        if ((_y==5)&&(_x==3)) return "d6";
        if ((_y==6)&&(_x==3)) return "d7";
        if ((_y==7)&&(_x==3)) return "d8";
        if ((_y==0)&&(_x==4)) return "e1";
        if ((_y==1)&&(_x==4)) return "e2";
        if ((_y==2)&&(_x==4)) return "e3";
        if ((_y==3)&&(_x==4)) return "e4";
        if ((_y==4)&&(_x==4)) return "e5";
        if ((_y==5)&&(_x==4)) return "e6";
        if ((_y==6)&&(_x==4)) return "e7";
        if ((_y==7)&&(_x==4)) return "e8";
        if ((_y==0)&&(_x==5)) return "f1";
        if ((_y==1)&&(_x==5)) return "f2";
        if ((_y==2)&&(_x==5)) return "f3";
        if ((_y==3)&&(_x==5)) return "f4";
        if ((_y==4)&&(_x==5)) return "f5";
        if ((_y==5)&&(_x==5)) return "f6";
        if ((_y==6)&&(_x==5)) return "f7";
        if ((_y==7)&&(_x==5)) return "f8";
        if ((_y==0)&&(_x==6)) return "g1";
        if ((_y==1)&&(_x==6)) return "g2";
        if ((_y==2)&&(_x==6)) return "g3";
        if ((_y==3)&&(_x==6)) return "g4";
        if ((_y==4)&&(_x==6)) return "g5";
        if ((_y==5)&&(_x==6)) return "g6";
        if ((_y==6)&&(_x==6)) return "g7";
        if ((_y==7)&&(_x==6)) return "g8";
        if ((_y==0)&&(_x==7)) return "h1";
        if ((_y==1)&&(_x==7)) return "h2";
        if ((_y==2)&&(_x==7)) return "h3";
        if ((_y==3)&&(_x==7)) return "h4";
        if ((_y==4)&&(_x==7)) return "h5";
        if ((_y==5)&&(_x==7)) return "h6";
        if ((_y==6)&&(_x==7)) return "h7";
        if ((_y==7)&&(_x==7)) return "h8";
        return null;
    }

}