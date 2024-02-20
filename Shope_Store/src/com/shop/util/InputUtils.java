package com.shop.util;

import java.util.Scanner;

public class InputUtils {
	
	 private static Scanner scan = new Scanner(System.in);
	 
	 public static int getInputNumber(String message) {
		 try {
			 return Integer.parseInt(getInputString(message));
		 }catch (NumberFormatException e) {
			 System.out.println("Please Input Digit Only ...");
		 }
		 return 0;
	 }
	 
	 public static String getInputString(String message) {
		 System.out.println(message);
		 return scan.nextLine();
	 }

}
