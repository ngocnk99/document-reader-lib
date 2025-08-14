package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import androidx.core.content.ContextCompat;
import com.ahmadullahpk.alldocumentreader.R;

public class CustomTextGradient extends CustomFontTextView {
    private int startColor = 0;
    private int endColor = 0;
    private int gradientType = 0;

    public CustomTextGradient(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(
                    attributeSet, R.styleable.gradientTextView, 0, 0);
            try {
                startColor = ContextCompat.getColor(
                    getContext(),
                    obtainStyledAttributes.getResourceId(
                        R.styleable.gradientTextView_startColor,
                        R.color.defaultStartColor
                    )
                );
                endColor = ContextCompat.getColor(
                    getContext(),
                    obtainStyledAttributes.getResourceId(
                        R.styleable.gradientTextView_endColor,
                        R.color.defaultEndColor
                    )
                );
                gradientType = obtainStyledAttributes.getInt(R.styleable.gradientTextView_gradientType, 0);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public CustomTextGradient(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setGradient(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        init();
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            init();
        }
    }

    private void init() {
        float f;
        float f2;
        float measureText = getPaint().measureText(getText().toString());
        if (gradientType == 0) {
            f2 = measureText;
            f = Float.intBitsToFloat(1);
        } else {
            f = (float) getHeight();
            f2 = Float.intBitsToFloat(1);
        }
        setTextColor(ContextCompat.getColor(getContext(), android.R.color.black));
        LinearGradient linearGradient = new LinearGradient(
            Float.intBitsToFloat(1),
            Float.intBitsToFloat(1),
            f2,
            f,
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        );
        getPaint().setShader(linearGradient);
    }
}