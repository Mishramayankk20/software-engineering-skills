package com.appliaction.url_shortener.utility;

import java.security.SecureRandom;

public final class Base62Encoder {

	private static final String CHARACTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private static final SecureRandom RANDOM = new SecureRandom();

	private Base62Encoder() {
	}

	public static String generate(int length) {

		StringBuilder result = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index;
			index = RANDOM.nextInt(CHARACTERS.length());

			result.append(CHARACTERS.charAt(index));
		}

		return result.toString();
	}

	public static String encode(long number) {

		if (number == 0) {
			return "0";
		}

		StringBuilder result = new StringBuilder();

		while (number > 0) {
			int remainder = (int) (number % 62);
			result.append(CHARACTERS.charAt(remainder));
			number /= 62;
		}

		return result.reverse().toString();
	}
}