package com.theisenp.vicarious.modifier;

import java.util.StringTokenizer;

/**
 * Implementation of {@link BaseTweetTextModifier} that uses 'Bruce' instead of
 * 'Hulk' and uses normal case instead of all-caps
 * 
 * @author patrick.theisen
 */
public class BannerTweetModifier extends BaseTweetTextModifier {

	// Constants
	private static final String HULK_NAME = "HULK";
	private static final String BRUCE_NAME = "BRUCE";
	private static final String WHITESPACE = " \t\n\r\f";

	// Patterns
	private static final String LINK_PATTERN = "^http://t.co/.*";
	private static final String SENTENCE_BREAK_PATTERN = ".*[.?!]$";
	private static final String USER_PATTERN = "^@.*";
	private static final String WHITESPACE_PATTERN = "\\s*";

	@Override
	protected String modifyText(String text) {
		return bannerize(text.replace(HULK_NAME, BRUCE_NAME));
	}

	/**
	 * @param hulk
	 * The input string with it's case in the Hulk format
	 * @return The input string with it's case in the Banner format
	 */
	private static String bannerize(String hulk) {
		StringBuilder builder = new StringBuilder(hulk.length());
		StringTokenizer words = new StringTokenizer(hulk, WHITESPACE, true);

		boolean firstWord = true;
		while(words.hasMoreTokens()) {
			String word = words.nextToken();

			// Append the word, modifying it if necessary
			boolean isLink = word.matches(LINK_PATTERN);
			boolean isUser = word.matches(USER_PATTERN);
			boolean isWhitespace = word.matches(WHITESPACE_PATTERN);
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
			if(word.matches(SENTENCE_BREAK_PATTERN)) {
				firstWord = true;
			}
		}

		return builder.toString();
	}
}
