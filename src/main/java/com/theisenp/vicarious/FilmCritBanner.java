package com.theisenp.vicarious;

import java.io.IOException;


/**
 * Entry point for FilmCritBanner
 * 
 * @author patrick.theisen
 */
public class FilmCritBanner {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		VicariousRunner.run(new BannerVicariousFactory());
	}
}
