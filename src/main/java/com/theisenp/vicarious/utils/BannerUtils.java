package com.theisenp.vicarious.utils;

import java.util.StringTokenizer;

/**
 * Static utility functions for manipulating tweet content
 * 
 * @author patrick.theisen
 */
public class BannerUtils {

	// Constants
	private static final String WHITESPACE = " \t\n\r\f";
	private static final String PATTERN_WHITESPACE = "\\s*";
	private static final String PATTERN_USER = "^@.*";
	private static final String PATTERN_LINK = "^http://t.co/.*";
	private static final String PATTERN_SENTENCE_BREAK = ".*[.?!]$";

	/**
	 * @param hulk
	 * The input string with it's case in the Hulk format
	 * @return The input string with it's case in the Banner format
	 */
	public static String bannerize(String hulk) {
		StringBuilder builder = new StringBuilder(hulk.length());
		StringTokenizer words = new StringTokenizer(hulk, WHITESPACE, true);

		boolean firstWord = true;
		while(words.hasMoreTokens()) {
			String word = words.nextToken();

			// Append the word, modifying it if necessary
			boolean isLink = word.matches(PATTERN_LINK);
			boolean isUser = word.matches(PATTERN_USER);
			boolean isWhitespace = word.matches(PATTERN_WHITESPACE);
			if(isLink || isUser || isWhitespace) {
				builder.append(word);
			}
			else if(firstWord) {
				firstWord = false;
				builder.append(word.charAt(0));
				builder.append(word.substring(1, word.length()).toLowerCase());
			}
			else {
				builder.append(word.toLowerCase());
			}

			// Check for sentence breaks
			if(word.matches(PATTERN_SENTENCE_BREAK)) {
				firstWord = true;
			}
		}

		return builder.toString();
	}
}
