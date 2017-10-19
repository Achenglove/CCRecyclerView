package com.ccr.ccrecyclerviewlibrary.util;

import java.util.regex.Pattern;

/**
 * Created by ewhale on 2015/6/10.
 */
public class PatternUtils {
    private static PatternUtils mPatternUtils;

    private PatternUtils() {
    }

    public static PatternUtils getInstance() {
        if (null == mPatternUtils) {
            mPatternUtils = new PatternUtils();
        }
        return mPatternUtils;
    }

    public boolean checkURL(String url) {
        String regular = "(http|https)://[\\S]*";
        return Pattern.matches(regular, url);
    }
}
