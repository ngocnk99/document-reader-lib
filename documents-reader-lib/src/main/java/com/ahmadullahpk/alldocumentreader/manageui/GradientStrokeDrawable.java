package com.ahmadullahpk.alldocumentreader.manageui;

import android.graphics.drawable.GradientDrawable;

public class GradientStrokeDrawable {
    private GradientDrawable gradientDrawable;

    public GradientStrokeDrawable(GradientDrawable gradientDrawable) {
        this.gradientDrawable = gradientDrawable;
    }

    public GradientDrawable getGradientDrawable() {
        return gradientDrawable;
    }

    public void setGradientCornerRadius(float angle) {
        gradientDrawable.setCornerRadius(angle);
    }

    public void setGradient(int i, int i2, int i3) {
        gradientDrawable.setColors(new int[]{i, i2});
        gradientDrawable.setOrientation(
            i3 == 0 ? GradientDrawable.Orientation.LEFT_RIGHT 
                    : GradientDrawable.Orientation.TOP_BOTTOM
        );
    }
}