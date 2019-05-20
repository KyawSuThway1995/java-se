package com.bookstore.util;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookUtils {

	private static Scanner sc = new Scanner(System.in);
	public static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private BookUtils() {
	}

	public static String readString(String message) {
		System.out.print(message);
		return sc.nextLine().trim();
	}

	public static int readInt(String message) {
		for (;;) {
			try {
				return Integer.parseInt(readString(message));
			} catch (NumberFormatException e) {
				message = "Please, Enter Valid Opearation Code!";
			}
		}

	}

	public static LocalDate readDate(String message) {
		try {
			return LocalDate.parse(readString(message), df);
		} catch (Exception e) {
			return null;
		}
	}

	public static Console getConsole() {
		return System.console();
	}

}
