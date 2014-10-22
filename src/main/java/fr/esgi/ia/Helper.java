package fr.esgi.ia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Helper class. It's main role is to work with strings.
 * 
 * @author Cédric TESNIERE
 */
public class Helper {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	/**
	 * Return the number representing the position on the chessboard from a
	 * string. Use only the first char of the string.
	 * 
	 * @param location The string representing the move
	 * @return The number representing the position on the chessboard
	 */
	public static int getXfromString(String location) {
		if ((location.charAt(0) == 'a')) {
			return 0;
		}
		if ((location.charAt(0) == 'b')) {
			return 1;
		}
		if ((location.charAt(0) == 'c')) {
			return 2;
		}
		if ((location.charAt(0) == 'd')) {
			return 3;
		}
		if ((location.charAt(0) == 'e')) {
			return 4;
		}
		if ((location.charAt(0) == 'f')) {
			return 5;
		}
		if ((location.charAt(0) == 'g')) {
			return 6;
		}
		if ((location.charAt(0) == 'h')) {
			return 7;
		}
		return -1;
	}

	/**
	 * Return the number representing the position on the chessboard from a
	 * string. Use only the second char of the string.
	 * 
	 * @param location Représente une position sur l'echiquier
	 * @return int Un nombre representant l'echiquier
	 */
	public static int getYfromString(String location) {
		if ((location.charAt(1) == '1')) {
			return 0;
		}
		if ((location.charAt(1) == '2')) {
			return 1;
		}
		if ((location.charAt(1) == '3')) {
			return 2;
		}
		if ((location.charAt(1) == '4')) {
			return 3;
		}
		if ((location.charAt(1) == '5')) {
			return 4;
		}
		if ((location.charAt(1) == '6')) {
			return 5;
		}
		if ((location.charAt(1) == '7')) {
			return 6;
		}
		if ((location.charAt(1) == '8')) {
			return 7;
		}
		return -1;
	}

	/**
	 * Read a string from input.
	 * 
	 * @return The string read
	 */
	public static String readFromInput() {

		final InputStreamReader ISR = new InputStreamReader(System.in);
		final BufferedReader BR = new BufferedReader(ISR);
		String input;

		try {
			input = BR.readLine();
		} catch (IOException e) {
			input = null;
		}

		return input;
	}

	/**
	 * Use the first char of the input to return the profoundity of the
	 * algorithm. Accept only number from 1 to 5.
	 * 
	 * @param input The first input of main
	 * @return The profoundity
	 */
	public static int returnProf(String[] input) {

		final int ret;
		switch (input[0].charAt(0)) {
			case ('1'):
				ret = 1;
				break;
			case ('2'):
				ret = 2;
				break;
			case ('3'):
				ret = 3;
				break;
			case ('4'):
				ret = 4;
				break;
			case ('5'):
				ret = 5;
				break;
			default:
				ret = 2;
		}
		return ret;
	}

	/**
	 * Transform a set of numeric position in the string equivalent.
	 * 
	 * @param x The numeric coordinate on the x axis
	 * @param y The numeric coordinate on the y axis
	 * @return The string representing the position
	 */
	public static String getStringFromPosition(int x, int y) {
		if ((y == 0) && (x == 0)) {
			return "a1";
		}
		if ((y == 1) && (x == 0)) {
			return "a2";
		}
		if ((y == 2) && (x == 0)) {
			return "a3";
		}
		if ((y == 3) && (x == 0)) {
			return "a4";
		}
		if ((y == 4) && (x == 0)) {
			return "a5";
		}
		if ((y == 5) && (x == 0)) {
			return "a6";
		}
		if ((y == 6) && (x == 0)) {
			return "a7";
		}
		if ((y == 7) && (x == 0)) {
			return "a8";
		}
		if ((y == 0) && (x == 1)) {
			return "b1";
		}
		if ((y == 1) && (x == 1)) {
			return "b2";
		}
		if ((y == 2) && (x == 1)) {
			return "b3";
		}
		if ((y == 3) && (x == 1)) {
			return "b4";
		}
		if ((y == 4) && (x == 1)) {
			return "b5";
		}
		if ((y == 5) && (x == 1)) {
			return "b6";
		}
		if ((y == 6) && (x == 1)) {
			return "b7";
		}
		if ((y == 7) && (x == 1)) {
			return "b8";
		}
		if ((y == 0) && (x == 2)) {
			return "c1";
		}
		if ((y == 1) && (x == 2)) {
			return "c2";
		}
		if ((y == 2) && (x == 2)) {
			return "c3";
		}
		if ((y == 3) && (x == 2)) {
			return "c4";
		}
		if ((y == 4) && (x == 2)) {
			return "c5";
		}
		if ((y == 5) && (x == 2)) {
			return "c6";
		}
		if ((y == 6) && (x == 2)) {
			return "c7";
		}
		if ((y == 7) && (x == 2)) {
			return "c8";
		}
		if ((y == 0) && (x == 3)) {
			return "d1";
		}
		if ((y == 1) && (x == 3)) {
			return "d2";
		}
		if ((y == 2) && (x == 3)) {
			return "d3";
		}
		if ((y == 3) && (x == 3)) {
			return "d4";
		}
		if ((y == 4) && (x == 3)) {
			return "d5";
		}
		if ((y == 5) && (x == 3)) {
			return "d6";
		}
		if ((y == 6) && (x == 3)) {
			return "d7";
		}
		if ((y == 7) && (x == 3)) {
			return "d8";
		}
		if ((y == 0) && (x == 4)) {
			return "e1";
		}
		if ((y == 1) && (x == 4)) {
			return "e2";
		}
		if ((y == 2) && (x == 4)) {
			return "e3";
		}
		if ((y == 3) && (x == 4)) {
			return "e4";
		}
		if ((y == 4) && (x == 4)) {
			return "e5";
		}
		if ((y == 5) && (x == 4)) {
			return "e6";
		}
		if ((y == 6) && (x == 4)) {
			return "e7";
		}
		if ((y == 7) && (x == 4)) {
			return "e8";
		}
		if ((y == 0) && (x == 5)) {
			return "f1";
		}
		if ((y == 1) && (x == 5)) {
			return "f2";
		}
		if ((y == 2) && (x == 5)) {
			return "f3";
		}
		if ((y == 3) && (x == 5)) {
			return "f4";
		}
		if ((y == 4) && (x == 5)) {
			return "f5";
		}
		if ((y == 5) && (x == 5)) {
			return "f6";
		}
		if ((y == 6) && (x == 5)) {
			return "f7";
		}
		if ((y == 7) && (x == 5)) {
			return "f8";
		}
		if ((y == 0) && (x == 6)) {
			return "g1";
		}
		if ((y == 1) && (x == 6)) {
			return "g2";
		}
		if ((y == 2) && (x == 6)) {
			return "g3";
		}
		if ((y == 3) && (x == 6)) {
			return "g4";
		}
		if ((y == 4) && (x == 6)) {
			return "g5";
		}
		if ((y == 5) && (x == 6)) {
			return "g6";
		}
		if ((y == 6) && (x == 6)) {
			return "g7";
		}
		if ((y == 7) && (x == 6)) {
			return "g8";
		}
		if ((y == 0) && (x == 7)) {
			return "h1";
		}
		if ((y == 1) && (x == 7)) {
			return "h2";
		}
		if ((y == 2) && (x == 7)) {
			return "h3";
		}
		if ((y == 3) && (x == 7)) {
			return "h4";
		}
		if ((y == 4) && (x == 7)) {
			return "h5";
		}
		if ((y == 5) && (x == 7)) {
			return "h6";
		}
		if ((y == 6) && (x == 7)) {
			return "h7";
		}
		if ((y == 7) && (x == 7)) {
			return "h8";
		}
		return null;
	}

	public static boolean isColorWhite(boolean color) {
		if (Algorithm.WHITE == color) {
			{
				return Algorithm.WHITE;
			}
		} else {
			{
				return Algorithm.BLACK;
			}
		}
	}
}
