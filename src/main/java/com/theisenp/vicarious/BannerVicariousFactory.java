package com.theisenp.vicarious;

import java.io.File;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import com.theisenp.vicarious.logger.CompositeTweetLogger;
import com.theisenp.vicarious.logger.ConsoleTweetLogger;
import com.theisenp.vicarious.logger.FileTweetLogger;
import com.theisenp.vicarious.logger.TweetLogger;
import com.theisenp.vicarious.modifier.BannerTweetModifier;
import com.theisenp.vicarious.modifier.TweetModifier;
import com.theisenp.vicarious.provider.BaseTweetProvider;
import com.theisenp.vicarious.provider.FileUserTweetsFetcher;
import com.theisenp.vicarious.provider.TweetFetcher;
import com.theisenp.vicarious.provider.TweetProvider;
import com.theisenp.vicarious.publisher.BlockingTweetPublisher;
import com.theisenp.vicarious.publisher.TweetPublisher;

/**
 * Implementation of {@link VicariousFactory} for FilmCritBanner
 * 
 * @author patrick.theisen
 */
public class BannerVicariousFactory implements VicariousFactory {

	// Constants
	private static final String USER = "FilmCritHULK";
	private static final String FILE_PATH = ".lastTweet";

	// Data
	private final TweetProvider provider;
	private final TweetModifier modifier;
	private final TweetPublisher publisher;
	private final TweetLogger logger;

	/**
	 * 
	 */
	public BannerVicariousFactory() {
		Twitter twitter = TwitterFactory.getSingleton();
		File file = new File(FILE_PATH);

		// Create the provider
		TweetFetcher fetcher = new FileUserTweetsFetcher(USER, file);
		provider = new BaseTweetProvider(twitter, fetcher);

		// Create the modifier
		modifier = new BannerTweetModifier();

		// Create the publisher
		publisher = new BlockingTweetPublisher(twitter);

		// Create the logger
		TweetLogger fileLogger = new FileTweetLogger(file);
		TweetLogger consoleLogger = new ConsoleTweetLogger();
		logger = new CompositeTweetLogger(fileLogger, consoleLogger);
	}

	@Override
	public TweetProvider getTweetProvider() {
		return provider;
	}

	@Override
	public TweetModifier getTweetModifier() {
		return modifier;
	}

	@Override
	public TweetPublisher getTweetPublisher() {
		return publisher;
	}

	@Override
	public TweetLogger getTweetLogger() {
		return logger;
	}
}
