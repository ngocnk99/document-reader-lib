package com.ahmadullahpk.alldocumentreader.manageui.backgroundshadow;

import android.view.View;
import androidx.core.view.ViewCompat;

public class ShadowSetup {
    
    public static void setUpShadow(View view, ShadowOpacity.Builder builder) {
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, builder.build());
    }
}