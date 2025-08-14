package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.ahmadullahpk.alldocumentreader.R;
import com.ahmadullahpk.alldocumentreader.manageui.backgroundshadow.ShadowOpacity;
import com.ahmadullahpk.alldocumentreader.manageui.backgroundshadow.ShadowSetup;
import com.ahmadullahpk.alldocumentreader.util.FontCache;

public class CustomButton extends AppCompatButton {
    public static String textColor = "#ffffff";
    
    public int textStartColor = -1;
    public int textEndColor = -1;
    protected int startColor = -1;
    private int boarderColor = -1;
    protected int endColor = -1;
    private int cornerRadius = 0;
    protected int gradientType = 0;
    private int icon = 0;
    private GradientStrokeDrawable buttonStrokeDrawable = null;
    protected boolean roundedCorner = false;
    protected boolean enableShadow = false;
    private int buttonShadowColor = -1;
    public int shadowOffsetX = 0;
    public int shadowOffsetY = 0;
    public int buttonElevation = 0;
    private boolean adjustDrawableLeft = false;
    private boolean iEnabled = false;

    public CustomButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.appButtonView, 0, 0);
            try {
                enableShadow = obtainStyledAttributes.getBoolean(R.styleable.appButtonView_enableShadow, false);
                enableShadow();
                setFontStyle(context, obtainStyledAttributes.getInt(R.styleable.appButtonView_textStyle, 0));
                startColor = obtainStyledAttributes.getResourceId(R.styleable.appButtonView_startColor, -1);
                textStartColor = obtainStyledAttributes.getResourceId(R.styleable.appButtonView_textStartColor, -1);
                textEndColor = obtainStyledAttributes.getResourceId(R.styleable.appButtonView_textEndColor, -1);
                endColor = obtainStyledAttributes.getResourceId(R.styleable.appButtonView_endColor, -1);
                icon = obtainStyledAttributes.getResourceId(R.styleable.appButtonView_icon, -1);
                gradientType = obtainStyledAttributes.getInt(R.styleable.appButtonView_gradientType, 0);
                adjustDrawableLeft = obtainStyledAttributes.getBoolean(R.styleable.appButtonView_adjustDrawableLeft, false);
                roundedCorner = obtainStyledAttributes.getBoolean(R.styleable.appButtonView_roundedCorner, false);
                
                if (roundedCorner) {
                    cornerRadius = (int) getContext().getResources().getDimension(R.dimen.round_button_corner_radius);
                } else {
                    cornerRadius = (int) obtainStyledAttributes.getDimension(R.styleable.appButtonView_cornerRadius,
                            getContext().getResources().getDimension(R.dimen.button_corner_radius));
                }
                
                if (!(startColor == -1 || endColor == -1)) {
                    setGradientStyle(getColor(startColor), getColor(endColor), enableShadow);
                }
                
                buttonShadowColor = obtainStyledAttributes.getResourceId(R.styleable.appButtonView_shadowColor, -1);
                if (buttonShadowColor != -1) {
                    buttonShadowColor = getColor(buttonShadowColor);
                }
                
                boarderColor = obtainStyledAttributes.getResourceId(R.styleable.appButtonView_borderColor, -1);
                if (boarderColor != -1) {
                    boarderColor = getColor(boarderColor);
                }
                
                if (enableShadow) {
                    setPadding();
                    initShadow();
                } else if (!(startColor == -1 || endColor == -1)) {
                    setBackgroundGradient();
                }
                
                if (adjustDrawableLeft) {
                    setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
                }
                
                if (icon != 0 && !TextUtils.isEmpty(getText())) {
                    setIconLeft(icon);
                } else if (icon != 0 && TextUtils.isEmpty(getText())) {
                    setIcon(icon);
                }
                
                if (!(textStartColor == -1 || textEndColor == -1)) {
                    getGradient();
                }
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
    }

    public CustomButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        enableShadow();
    }

    @Override
    public void setText(CharSequence charSequence, BufferType bufferType) {
        super.setText(charSequence, bufferType);
        if (!TextUtils.isEmpty(charSequence)) {
            setTag(charSequence);
        }
    }

    private void enableShadow() {
        if (enableShadow) {
            enableShadowOffset();
        }
    }

    private void enableShadowOffset() {
        shadowOffsetX = (int) getContext().getResources().getDimension(R.dimen.button_shadow_offset_x);
        shadowOffsetY = (int) getContext().getResources().getDimension(R.dimen.button_shadow_offset_y);
        buttonElevation = (int) getContext().getResources().getDimension(R.dimen.button_elevation);
    }

    protected void setPadding() {
        setPadding(
            getPaddingLeft() + buttonElevation - shadowOffsetX,
            getPaddingTop() + buttonElevation - shadowOffsetY,
            getPaddingRight() + shadowOffsetX + buttonElevation,
            getPaddingBottom() + shadowOffsetY + buttonElevation
        );
    }

    private void initShadow() {
        int[] iArr = new int[0];
        int i = startColor;
        if (i != -1) {
            int i2 = endColor;
            if (i2 != -1) {
                iArr = new int[]{i, i2};
            }
        }
        
        if (buttonShadowColor == -1) {
            int i3 = startColor;
            if (i3 != -1) {
                buttonShadowColor = ColorUtils.setAlphaComponent(i3, 120);
            }
        }
        
        if (boarderColor == -1) {
            int i4 = startColor;
            if (i4 != -1) {
                boarderColor = ColorUtils.setAlphaComponent(i4, 80);
            }
        }
        
        Drawable background = getBackground();
        ShadowOpacity.Builder b = new ShadowOpacity.Builder()
                .setGradientColorArray(iArr)
                .setColor(background instanceof ColorDrawable ? ((ColorDrawable) background).getColor() : -1);
        
        int i5 = buttonShadowColor;
        if (i5 == -1) {
            i5 = getColor(R.color.card_default_shadow_color);
        }
        
        ShadowOpacity.Builder c = b.setOShadowColor(i5);
        int i6 = boarderColor;
        if (i6 == -1) {
            i6 = getColor(R.color.card_default_border_color);
        }
        
        ShadowSetup.setUpShadow(this, c.setShadowColor(i6)
                .setRadius(cornerRadius)
                .setShadowRadius(buttonElevation)
                .setOffsetX(shadowOffsetX)
                .setOffsetY(shadowOffsetY));
    }

    public void setFontStyle(Context context, int i) {
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

    private void setBackgroundCompat(Drawable drawable) {
        setBackground(drawable);
    }

    private void setBackgroundGradient() {
        setBackgroundResource(0);
        buttonStrokeDrawable = setStrokeGradient(startColor, endColor, cornerRadius, gradientType);
        setBackgroundCompat(buttonStrokeDrawable.getGradientDrawable());
    }

    private int getColor(int i) {
        return getResources().getColor(i);
    }

    protected void setIcon(int i) {
        if (i != -1) {
            post(() -> {
                Drawable b = AppCompatResources.getDrawable(CustomButton.this.getContext(), i);
                int measuredWidth = (CustomButton.this.getMeasuredWidth() - b.getIntrinsicWidth()) / 2;
                CustomButton.this.setCompoundDrawablesWithIntrinsicBounds(b, null, null, null);
                CustomButton appButton = CustomButton.this;
                appButton.setPadding(measuredWidth, -(appButton.buttonElevation + shadowOffsetY) / 2, 0, 0);
            });
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            post(() -> {
                int measuredWidth = (CustomButton.this.getMeasuredWidth() - drawable.getIntrinsicWidth()) / 2;
                CustomButton.this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                CustomButton appButton = CustomButton.this;
                appButton.setPadding(measuredWidth, -(appButton.buttonElevation + shadowOffsetY) / 2, 0, 0);
            });
        }
    }

    public void setDrawable(Drawable drawable, int i) {
        if (drawable != null) {
            post(() -> {
                int intrinsicWidth = (i - drawable.getIntrinsicWidth()) / 2;
                CustomButton.this.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                CustomButton appButton = CustomButton.this;
                appButton.setPadding(intrinsicWidth, -(appButton.buttonElevation + shadowOffsetY) / 2, 0, 0);
            });
        }
    }

    public void setText(Drawable drawable, int i, String str) {
        if (TextUtils.isEmpty(str)) {
            setDrawable(drawable, i);
        } else if (drawable != null) {
            post(() -> {
                int dimensionPixelSize = CustomButton.this.getResources().getDimensionPixelSize(R.dimen.margin_normal);
                CustomButton.this.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
                CustomButton.this.setText(str);
                CustomButton appButton = CustomButton.this;
                appButton.setTextColor(ContextCompat.getColor(appButton.getContext(), R.color.title_info_color));
                CustomButton appButton2 = CustomButton.this;
                appButton2.setPadding(dimensionPixelSize, -(appButton2.buttonElevation + shadowOffsetY) / 2, dimensionPixelSize, 0);
                CustomButton.this.setCompoundDrawablePadding(dimensionPixelSize);
            });
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (adjustDrawableLeft) {
            float width = (float) (getWidth() - getPaddingLeft() - getPaddingRight());
            float measureText = getPaint().measureText(
                    (getTransformationMethod() != null ? 
                            getTransformationMethod().getTransformation(getText(), this) : getText()).toString()
            );
            int i = 0;
            Drawable drawable = getCompoundDrawables()[0];
            int intrinsicWidth = drawable != null ? drawable.getIntrinsicWidth() : 0;
            if (measureText > Float.intBitsToFloat(1) && drawable != null) {
                i = getCompoundDrawablePadding();
            }
            canvas.translate(
                    (width - (measureText + (float) intrinsicWidth + (float) i)) / 2.0f,
                    Float.intBitsToFloat(1)
            );
        }
        super.onDraw(canvas);
    }

    protected GradientStrokeDrawable setStrokeGradient(int i, int i2, int i3, int i4) {
        GradientStrokeDrawable strokeGradientDrawable = new GradientStrokeDrawable(new GradientDrawable());
        strokeGradientDrawable.getGradientDrawable().setShape(GradientDrawable.RECTANGLE);
        strokeGradientDrawable.setGradient(i, i2, i4);
        strokeGradientDrawable.setGradientCornerRadius((float) i3);
        return strokeGradientDrawable;
    }

    protected void setIconLeft(int i) {
        if (i != -1) {
            setCompoundDrawablesWithIntrinsicBounds(i, 0, 0, 0);
        }
    }

    public void setGradientColors(int i, int i2) {
        setGradientStyle(i, i2, true);
    }

    public void setGradientStyle(int startColor, int endColor, boolean enableShadow) {
        this.startColor = ColorUtils.blendARGB(startColor, Color.parseColor(textColor), 0.1f);
        this.endColor = ColorUtils.blendARGB(endColor, Color.parseColor(textColor), 0.1f);
        boarderColor = -1;
        if (this.enableShadow != enableShadow) {
            enableShadowOffset();
            setPadding();
        }
        this.enableShadow = enableShadow;
        if (this.enableShadow) {
            initShadow();
        } else {
            setBackgroundGradient();
        }
        invalidate();
    }

    public void setBackgroundBorderColor(int i) {
        boarderColor = i;
        if (!enableShadow) {
            enableShadowOffset();
            setPadding();
        }
        initShadow();
        invalidate();
    }

    public void setUpShadow() {
        enableShadow = true;
        initShadow();
        invalidate();
    }

    public void disableShadow() {
        setBackgroundDrawable(null);
        enableShadow = false;
    }

    public void setCornerRadius(int i) {
        cornerRadius = i;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (enableShadow) {
            initShadow();
        }
    }

    private void getGradient() {
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
        setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        LinearGradient linearGradient = new LinearGradient(
                Float.intBitsToFloat(1),
                Float.intBitsToFloat(1),
                f2,
                f,
                textStartColor,
                textEndColor,
                TileMode.CLAMP
        );
        getPaint().setShader(linearGradient);
    }

    public void setTextGradient(int i, int i2) {
        textStartColor = i;
        textEndColor = i2;
        getGradient();
        invalidate();
    }

    public void setCollapsible(boolean z) {
        iEnabled = z;
    }

    public GradientStrokeDrawable getButtonStrokeDrawable() {
        return buttonStrokeDrawable;
    }

    public boolean isIEnabled() {
        return iEnabled;
    }
}