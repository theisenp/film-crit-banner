package com.theisenp.vicarious.utils;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Unit tests for {@link BannerUtils}
 * 
 * @author patrick.theisen
 */
public class BannerUtilsTest {

	@Test
	public void testFixCaseSingleSentence() {
		String input = "TEST SENTENCE.";
		String expected = "Test sentence.";
		assertThat(BannerUtils.fixCase(input)).isEqualTo(expected);
	}

	@Test
	public void testFixCaseMultipleSentencesWithPeriods() {
		String input = "TEST SENTENCE. TEST SENTENCE.";
		String expected = "Test sentence. Test sentence.";
		assertThat(BannerUtils.fixCase(input)).isEqualTo(expected);
	}

	@Test
	public void testFixCaseMultipleSentencesWithExclamationPoint() {
		String input = "TEST SENTENCE! TEST SENTENCE!";
		String expected = "Test sentence! Test sentence!";
		assertThat(BannerUtils.fixCase(input)).isEqualTo(expected);
	}

	@Test
	public void testFixCaseMultipleSentencesWithQuestionMark() {
		String input = "TEST SENTENCE? TEST SENTENCE?";
		String expected = "Test sentence? Test sentence?";
		assertThat(BannerUtils.fixCase(input)).isEqualTo(expected);
	}

	@Test
	public void testFixCaseMultipleSentencesWithEllipsis() {
		String input = "TEST SENTENCE... TEST SENTENCE...";
		String expected = "Test sentence... Test sentence...";
		assertThat(BannerUtils.fixCase(input)).isEqualTo(expected);
	}

	@Test
	public void testFixCaseMultipleSentencesWithWhitespace() {
		String input = "   \tTEST SENTENCE...    \tTEST SENTENCE...";
		String expected = "   \tTest sentence...    \tTest sentence...";
		assertThat(BannerUtils.fixCase(input)).isEqualTo(expected);
	}

	@Test
	public void testFixCaseOnlySymbols() {
		String input = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
		String expected = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
		assertThat(BannerUtils.fixCase(input)).isEqualTo(expected);
	}
}
