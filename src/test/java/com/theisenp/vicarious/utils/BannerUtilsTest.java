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
	public void testBannerizeSingleSentence() {
		String input = "TEST SENTENCE.";
		String expected = "Test sentence.";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeMultipleSentencesWithPeriods() {
		String input = "TEST SENTENCE. TEST SENTENCE.";
		String expected = "Test sentence. Test sentence.";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeMultipleSentencesWithExclamationPoint() {
		String input = "TEST SENTENCE! TEST SENTENCE!";
		String expected = "Test sentence! Test sentence!";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeMultipleSentencesWithQuestionMark() {
		String input = "TEST SENTENCE? TEST SENTENCE?";
		String expected = "Test sentence? Test sentence?";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeMultipleSentencesWithEllipsis() {
		String input = "TEST SENTENCE... TEST SENTENCE...";
		String expected = "Test sentence... Test sentence...";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeMultipleSentencesWithWhitespace() {
		String input = "   \tTEST SENTENCE...    \tTEST SENTENCE...";
		String expected = "   \tTest sentence...    \tTest sentence...";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeUser() {
		String input = "TEST @TEST_USER";
		String expected = "Test @TEST_USER";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeLink() {
		String input = "TEST http://t.co/ABC";
		String expected = "Test http://t.co/ABC";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}

	@Test
	public void testBannerizeOnlySymbols() {
		String input = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
		String expected = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
		assertThat(BannerUtils.bannerize(input)).isEqualTo(expected);
	}
}
