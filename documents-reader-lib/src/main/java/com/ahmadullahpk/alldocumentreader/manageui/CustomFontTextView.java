package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.appcompat.widget.AppCompatTextView;
import com.ahmadullahpk.alldocumentreader.R;
import android.graphics.Typeface;
import android.util.AttributeSet;
import com.ahmadullahpk.alldocumentreader.util.FontCache;

public class CustomFontTextView extends AppCompatTextView {
    
    public CustomFontTextView(Context context) {
        super(context);
    }

    public CustomFontTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AppTextView, 0, 0);
            try {
                setFont(context, obtainStyledAttributes.getInt(R.styleable.AppTextView_textStyle, 0));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public CustomFontTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setFont(Context context, int i) {
        Typeface typeface;
        switch (i) {
            case 0:
                typeface = FontCache.setFontStyle(context, "fonts/gothamssm_bold.otf");
                break;
            case 1:
                typeface = FontCache.setFontStyle(context, "fonts/gothamssm_light.otf");
                break;
            case 2:
                typeface = FontCache.setFontStyle(context, "fonts/gothamssm_medium.otf");
                break;
            case 4:
                typeface = FontCache.setFontStyle(context, "fonts/gothamssm_black.otf");
                break;
            case 5:
                typeface = Typeface.create(FontCache.setFontStyle(context, "fonts/gothamssm_book.otf"), Typeface.ITALIC);
                break;
            case 3:
            default:
                typeface = FontCache.setFontStyle(context, "fonts/gothamssm_book.otf");
                break;
        }
        setTypeface(typeface);
    }
}