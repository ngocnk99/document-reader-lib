package com.ahmadullahpk.alldocumentreader.manageui.backgroundshadow;

import android.graphics.LinearGradient;
import com.ahmadullahpk.alldocumentreader.R;

public class ShadowOpacity {
    
    public static class Builder {
        private int mColor;
        private int mShadowColor;
        private int shadowColor;
        private int[] mGradientColorArray = null;
        private float[] mGradientPositions = null;
        private final LinearGradient mLinearGradient = null;
        private int mRadius;
        private int mShadowRadius;
        private int mOffsetX;
        private int mOffsetY;
        
        public Builder() {
            mShadowColor = -1;
            mOffsetX = 0;
            mOffsetY = 0;
            mColor = R.color.primary_material_dark;
            shadowColor = R.color.card_default_shadow_color;
            mRadius = 10;
            mShadowRadius = 16;
            mOffsetX = 0;
            mOffsetY = 0;
            mShadowColor = R.color.card_default_shadow_color;
            mShadowColor = -1;
        }
        
        public Builder setShadowColor(int i) {
            mShadowColor = i;
            return this;
        }

        public Builder setColor(int i) {
            mColor = i;
            return this;
        }

        public Builder setOShadowColor(int i) {
            if (i != -1) {
                shadowColor = i;
            }
            return this;
        }

        public Builder setGradientColorArray(int[] iArr) {
            mGradientColorArray = iArr;
            return this;
        }

        public Builder setRadius(int i) {
            mRadius = i;
            return this;
        }

        public Builder setShadowRadius(int i) {
            mShadowRadius = i;
            return this;
        }

        public Builder setOffsetX(int i) {
            mOffsetX = i;
            return this;
        }

        public Builder setOffsetY(int i) {
            mOffsetY = i;
            return this;
        }

        public CustomBackgroundShadow build() {
            return new CustomBackgroundShadow(
                mColor,
                mGradientColorArray,
                mGradientPositions,
                shadowColor,
                mLinearGradient,
                mRadius,
                mShadowRadius,
                mOffsetX,
                mOffsetY,
                mShadowColor
            );
        }
    }
}