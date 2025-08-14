package com.ahmadullahpk.alldocumentreader.manageui.backgroundshadow;

import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import android.graphics.Shader.TileMode;

public class CustomBackgroundShadow extends Drawable {
    @ColorInt
    private final int mColor;
    private final int[] mGradientColorArrays;
    private final float[] mGradientPositions;
    @ColorInt
    private final int mShadowColor;
    private final LinearGradient mLinearGradient;
    private final int mShadowRadius;
    private final int mOffsetX;
    private final int mOffsetY;
    private final int offset;
    private final int mRadius;
    
    private RectF mRectF = null;
    private Paint mPaint = null;
    private Paint paint = null;

    public CustomBackgroundShadow(@ColorInt int mColor, int[] mGradientColorArrays, 
            float[] mGradientPositions, @ColorInt int mShadowColor, 
            LinearGradient mLinearGradient, int mShadowRadius, int mOffsetX, 
            int mOffsetY, int offset, int mRadius) {
        this.mColor = mColor;
        this.mGradientColorArrays = mGradientColorArrays;
        this.mGradientPositions = mGradientPositions;
        this.mShadowColor = mShadowColor;
        this.mLinearGradient = mLinearGradient;
        this.mShadowRadius = mShadowRadius;
        this.mOffsetX = mOffsetX;
        this.mOffsetY = mOffsetY;
        this.offset = offset;
        this.mRadius = mRadius;
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void draw(Canvas canvas) {
        if (mRectF == null) {
            Rect bounds = getBounds();
            mRectF = new RectF(
                (float)(bounds.left + mOffsetX - mOffsetY),
                (float)(bounds.top + mOffsetX - offset),
                (float)(bounds.right - mOffsetX - mOffsetY),
                (float)(bounds.bottom - mOffsetX - offset)
            );
        }
        if (mPaint == null) {
            getDefaultGradient();
        }
        RectF rectF = mRectF;
        int i = mShadowRadius;
        canvas.drawRoundRect(rectF, (float)i, (float)i, mPaint);
        if (mRadius != -1) {
            if (paint == null) {
                initPaint();
            }
            RectF rectF2 = mRectF;
            int i2 = mShadowRadius;
            canvas.drawRoundRect(rectF2, (float)i2, (float)i2, paint);
        }
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(mRadius);
        paint.setStrokeWidth((float)setDisplayMetrics(1));
    }

    @Override
    public void setAlpha(int i) {
        mPaint.setAlpha(i);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    private void getDefaultGradient() {
        mPaint = new Paint();
        boolean z = true;
        mPaint.setAntiAlias(true);
        mPaint.setShadowLayer(
            (float)mOffsetX,
            (float)mOffsetY,
            (float)offset,
            mShadowColor
        );
        int[] iArr = mGradientColorArrays;
        if (iArr == null || iArr.length <= 1) {
            mPaint.setColor(mColor);
            return;
        }
        float[] fArr = mGradientPositions;
        if (fArr == null || fArr.length <= 0 || fArr.length != iArr.length) {
            z = false;
        }
        Paint paint = mPaint;
        LinearGradient linearGradient = mLinearGradient;
        if (linearGradient == null) {
            linearGradient = new LinearGradient(
                mRectF.left,
                Float.intBitsToFloat(1),
                mRectF.right,
                Float.intBitsToFloat(1),
                mGradientColorArrays,
                z ? mGradientPositions : null,
                TileMode.CLAMP
            );
        }
        paint.setShader(linearGradient);
    }

    public static int setDisplayMetrics(int i) {
        return (int)((float)i * Resources.getSystem().getDisplayMetrics().density);
    }
}