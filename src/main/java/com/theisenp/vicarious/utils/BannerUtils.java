package com.theisenp.vicarious.utils;

import static java.lang.Character.toLowerCase;
import static java.util.Arrays.asList;

import java.util.List;

/**
 * Static utility functions for manipulating tweet content
 * 
 * @author patrick.theisen
 */
public class BannerUtils {

	// Constants
	private static final List<Character> SENTENCE_BREAKS =
			asList('.', '?', '!');

	/**
	 * @param uppercase
	 * @return The input string with all letters made
	 */
	public static String fixCase(String uppercase) {
		StringBuilder builder = new StringBuilder(uppercase.length());

		boolean newSentence = true;
		for(char current : uppercase.toCharArray()) {
			// Append the character
			builder.append(newSentence ? current : toLowerCase(current));

			// Update the new sentence state
			if(Character.isLetter(current)) {
				newSentence = false;
			}
			else if(SENTENCE_BREAKS.contains(current)) {
				newSentence = true;
			}
		}

		return builder.toString();
	}
}
