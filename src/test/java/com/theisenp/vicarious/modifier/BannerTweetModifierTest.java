package com.theisenp.vicarious.modifier;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import twitter4j.Status;

/**
 * Unit tests for {@link BannerTweetModifier}
 * 
 * @author patrick.theisen
 */
public class BannerTweetModifierTest {

	// Data
	private BannerTweetModifier modifier;

	@Before
	public void setUp() {
		modifier = new BannerTweetModifier();
	}

	@After
	public void tearDown() {
		modifier = null;
	}

	@Test
	public void testModifySingleSentence() {
		String input = "TEST SENTENCE.";
		String expected = "Test sentence.";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyMultipleSentencesWithPeriods() {
		String input = "TEST SENTENCE. TEST SENTENCE.";
		String expected = "Test sentence. Test sentence.";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyMultipleSentencesWithExclamationPoint() {
		String input = "TEST SENTENCE! TEST SENTENCE!";
		String expected = "Test sentence! Test sentence!";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyMultipleSentencesWithQuestionMark() {
		String input = "TEST SENTENCE? TEST SENTENCE?";
		String expected = "Test sentence? Test sentence?";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyMultipleSentencesWithEllipsis() {
		String input = "TEST SENTENCE... TEST SENTENCE...";
		String expected = "Test sentence... Test sentence...";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyMultipleSentencesWithWhitespace() {
		String input = "   \tTEST SENTENCE...    \tTEST SENTENCE...";
		String expected = "   \tTest sentence...    \tTest sentence...";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyUser() {
		String input = "TEST @TEST_USER";
		String expected = "Test @TEST_USER";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyLink() {
		String input = "TEST http://t.co/ABC";
		String expected = "Test http://t.co/ABC";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyOnlySymbols() {
		String input = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
		String expected = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyHulkBeginningOfTweet() {
		String input = "HULK TEST SENTENCE.";
		String expected = "Bruce test sentence.";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyHulkBeginningOfSentence() {
		String input = "TEST SENTENCE. HULK TEST SENTENCE.";
		String expected = "Test sentence. Bruce test sentence.";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyHulkMiddleOfSentence() {
		String input = "TEST HULK SENTENCE.";
		String expected = "Test bruce sentence.";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyHulkPlural() {
		String input = "HULKS TEST SENTENCE.";
		String expected = "Bruces test sentence.";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyHulkPossessive() {
		String input = "HULK'S TEST SENTENCE.";
		String expected = "Bruce's test sentence.";

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		String actual = modifier.modify(tweet).getStatus();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	public void testModifyHulkTooLong() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < 136; i++) {
			builder.append("A");
		}
		builder.append("HULK");
		String input = builder.toString();

		Status tweet = mock(Status.class);
		when(tweet.getText()).thenReturn(input);

		assertThat(modifier.modify(tweet)).isNull();
	}

	// @formatter:off
	private static final String[] HULK_TWEETS = {
		"HULK HAD CRAZY DOUBTS GOING IN, BUT, FOR WHATEVER IT'S WORTH, HOW I MET YOUR MOTHER IS CURRENTLY TURNING OUT THEIR BEST SEASON YET.",
		"CLARIFICATION: THIS IS PROBABLY THE MOST CONSISTENT, SOLID SEASON THE SHOW HAS DONE SO FAR. WHICH IS BEYOND ADMIRABLE FOR A FINAL, 9TH YEAR.",
		"THE WHOLE \"HOLLYWOOD SHUTS DOWN AT THE END OF SEPTEMBER\" THING IS GREAT... WHEN THAT'S ACTUALLY THE CASE FOR YOU #GRUMPYHULK",
		"SOMETIMES HULK FEELS LIKE THE SMARTEST DECISION HULK EVER MADE WAS NOT TO START COLLECTING BLU-RAYS. IT FEELS LIKE FREEDOM.",
		"MORE TO THE POINT, INSTEAD OF POPPING IN AN OLD STANDBYE, HULKS FORCED TO FIND SOMETHING NEW.",
		"OR WHEN A MUST-SEE DISC COMES OUT, HULKS MORE APT TO GATHER WITH A FRIEND WHO JUST GOT IT. IT ALL WIN.",
		"HULK'S NOT REALLY INTO PUNK ROCK CINEMATIC AFFECTATION, BUT IT'S KIND OF DELIGHTFUL HOW MANY PEOPLE HATE THE WOLF OF WALL STREET.",
		"SORRY TO BE RATHER FORWARD TODAY W/R/T WOLF OF WALL STREET, BUT THE POPULAR UNDERSTANDING OF THINGS IS SOMETHING YOU ALWAYS FIGHT FOR.",
		"IF YOU THINK THE ENDING OF THE GRADUATE WAS SOMETHING THE PUBLIC JUST ALL GOT IMMEDIATELY, YOU'D BE MISTAKEN.",
		"TATER TOTS AND A STONE IPA AT A MOVIE THEATER IS A NOVEL REVELATION.",
	};
	
	private static final String[] BRUCE_TWEETS = {
		"Bruce had crazy doubts going in, but, for whatever it's worth, how i met your mother is currently turning out their best season yet.",
		"Clarification: this is probably the most consistent, solid season the show has done so far. Which is beyond admirable for a final, 9th year.",
		"The whole \"hollywood shuts down at the end of september\" thing is great... When that's actually the case for you #grumpybruce",
		"Sometimes bruce feels like the smartest decision bruce ever made was not to start collecting blu-rays. It feels like freedom.",
		"More to the point, instead of popping in an old standbye, bruces forced to find something new.",
		"Or when a must-see disc comes out, bruces more apt to gather with a friend who just got it. It all win.",
		"Bruce's not really into punk rock cinematic affectation, but it's kind of delightful how many people hate the wolf of wall street.",
		"Sorry to be rather forward today w/r/t wolf of wall street, but the popular understanding of things is something you always fight for.",
		"If you think the ending of the graduate was something the public just all got immediately, you'd be mistaken.",
		"Tater tots and a stone ipa at a movie theater is a novel revelation.",
	};
	// @formatter:on

	@Test
	public void testRealTweets() {
		BannerTweetModifier modifier = new BannerTweetModifier();
		for(int i = 0; i < HULK_TWEETS.length; i++) {
			String hulkTweet = HULK_TWEETS[i];
			String expected = BRUCE_TWEETS[i];
			String actual = modifier.modifyText(hulkTweet);
			assertThat(actual).isEqualTo(expected);
		}
	}
}
