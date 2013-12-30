package com.theisenp.vicarious.modifier;

import com.theisenp.vicarious.utils.BannerUtils;

/**
 * TODO
 * 
 * @author patrick.theisen
 */
public class BannerTweetModifier extends BaseTweetTextModifier {

	// Constants
	private static final String HULK_NAME = "HULK";
	private static final String BRUCE_NAME = "BRUCE";

	@Override
	protected String modifyText(String text) {
		String bruceText = text.replace(HULK_NAME, BRUCE_NAME);
		return BannerUtils.fixCase(bruceText);
	}
}
