// Raika Roy Choudhury, APCS Peterson #2
// Chapter 7 Programming Project;
//
// This program adds together large integers using arrays, ignoring whitespace and utilizing leading zeroes
import java.util.*;
import java.io.*;

public class Sum {
		
	// class constant for length of array
	public static final int LENGTH = 25;
	
	// This method is the main method; it adds the integers together from each line of the file
	// using the other static methods to rid of leading zeros, etc.
	public static void main(String[] args) throws FileNotFoundException {
		Scanner file = new Scanner(new File("sum.txt"));
		int lines = 0;
		while (file.hasNextLine()) {
			String line = file.nextLine();
			lines ++;
			String output = addArr(sumByLine(line));
			System.out.println(ridLeadingZero(output));
		}
		System.out.println("Total lines = " + lines);
	}
	
	// This method removes any leading zeros from the numbers in the file
		public static String ridLeadingZero(String num) { 
			int index = 0;
			for (int i = 0; i < num.length(); i++) {
				if (num.charAt(i) != '0') {
					index = i;
					break;
				}
				if (i == num.length() - 1) {
					return "-1";
				}
			}
			return num.substring(index);
		}
	
	// This method turns an integer array into a string
	public static String toString(int[] array) { 
		String str = "";
		for (int i = 0; i < array.length; i++) {
			str += array[i]; 
		}
		return str;
	}
	
	// This method converts strings into arrays; storing each character as a new element
		public static int[] toArray(String num) { 
			int numlength = num.length();
			int[] newarray = new int[LENGTH];
			char[] prevarray = new char[numlength];
			for (int i = 0; i < numlength; i++) {
				prevarray[i] = num.charAt(i);
			}
			for (int i = 0; i < LENGTH - numlength; i++) {
				newarray[i] = 0;
			}
			for (int i = LENGTH - numlength; i < 25; i++) {
				char x = prevarray[i + numlength - LENGTH];
				newarray[i] = Character.getNumericValue(x);
			}
			
			return newarray;
		}
	
	// This method turns lines from the file into 2D arrays to sum
	public static int[][] sumByLine(String line) { 
		Scanner console = new Scanner(line);
		int total = 0;
		while(console.hasNext()) {
			console.next();
			total++;
		}
		
		Scanner consolerestart = new Scanner(line); // new scanner to keep better organization/restart place of scan
		int[][] array = new int[total][LENGTH];
		for (int i = 0; i < total; i++) {
			String val = consolerestart.next();
			System.out.print(val + " ");
			if (i == total - 1) {
				System.out.print("= ");
			} else {
				System.out.print("+ ");
			}
			
			array[i] = toArray(val);
		}
		
		return array;
	}
	
	// This method adds the subarrays within a 2D integer array and returns the sum as a String
		public static String addArr(int[][] sub) { 
			int[] sum = new int[LENGTH];
			int constant = 0;
			for (int i = LENGTH - 1; i >= 0; i--) {
				int whole = constant;
				for (int j = 0; j < sub.length; j++) {
					whole += sub[j][i];
				}
				sum[i] = whole % 10;
				constant = whole / 10;
			}
			return toString(sum);
		}

}
