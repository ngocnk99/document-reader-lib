package com.ahmadullahpk.alldocumentreader.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

public class FontCache {
    private static final Map<String, Typeface> sCachedFonts = new HashMap<>();

    public static Typeface setFontStyle(Context context, String fontPath) {
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), fontPath);
        sCachedFonts.put(fontPath, createFromAsset);
        return createFromAsset;
    }
}
