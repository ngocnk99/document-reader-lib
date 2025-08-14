package com.ahmadullahpk.alldocumentreader.util;

import android.os.Handler;
import android.widget.TextView;

public class TextAnimation {

    public static void marqueeAfterDelay(int delayMillis, TextView textView) {
        new Handler().postDelayed(() -> textView.setSelected(true), delayMillis);
    }
}