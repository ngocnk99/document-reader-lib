package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import com.ahmadullahpk.alldocumentreader.R;
import com.ahmadullahpk.alldocumentreader.util.ImageBitmaps;
import com.ahmadullahpk.alldocumentreader.util.ViewUtils;

public class CustomFrameLayout extends FrameLayout {
    private CustomFontTextView customFontTextViewTitle = null;
    public CustomFontTextView customFontTextViewDescription;
    public FrameLayout btnBackNav;
    public FrameLayout optionalNav;
    public FrameLayout optionalNav2;
    public FrameLayout optionalNav3;
    private CustomButton roundNavButton = null;
    private CustomButton roundedOptionalNav = null;
    private CustomButton roundedOptionalNav2 = null;
    private CustomButton roundedOptionalNav3 = null;
    private View viewToolbar = null;
    private LinearLayout customView = null;
    private final boolean isActive = true;

    public static class Builder {
        private final CustomFrameLayout customFrameLayout;
        private final Context context;
        private final int toolBarNavHeight;

        public Builder(CustomFrameLayout customFrameLayout, Context context) {
            this.customFrameLayout = customFrameLayout;
            this.context = context;
            this.toolBarNavHeight = context.getResources().getDimensionPixelSize(R.dimen.toolbar_nav_height);
        }

        public Builder setToolbarTitle(int i, int i2) {
            customFrameLayout.customFontTextViewTitle.setTextColor(ContextCompat.getColor(context, i2));
            customFrameLayout.customFontTextViewTitle.setText(context.getString(i));
            return this;
        }

        public Builder setToolbarTitle(String str, int i) {
            if (customFrameLayout.customFontTextViewTitle.getVisibility() == GONE) {
                customFrameLayout.customFontTextViewTitle.setVisibility(VISIBLE);
            }
            customFrameLayout.customFontTextViewTitle.setTextColor(ContextCompat.getColor(context, i));
            customFrameLayout.customFontTextViewTitle.setText(str);
            return this;
        }

        public Builder setToolbarDescription(String str, int i) {
            customFrameLayout.customFontTextViewDescription.setTextColor(ContextCompat.getColor(context, i));
            customFrameLayout.customFontTextViewDescription.setText(str);
            customFrameLayout.customFontTextViewDescription.setVisibility(VISIBLE);
            return this;
        }

        public Builder setUpToolbarButtons(int i, int i2, OnClickListener onClickListener) {
            customFrameLayout.roundNavButton.setDrawable(createDrawable(i, i2), toolBarNavHeight);
            customFrameLayout.btnBackNav.setOnClickListener(onClickListener);
            customFrameLayout.btnBackNav.setVisibility(VISIBLE);
            return this;
        }

        public Builder setBackArrow(int i, int i2, int i3, OnClickListener onClickListener) {
            customFrameLayout.roundNavButton.setDrawable(
                new BitmapDrawable(
                    context.getResources(), 
                    ImageBitmaps.setBitmapGradient(
                        ViewUtils.createDrawableFromDrawable(
                            AppCompatResources.getDrawable(context, i)
                        ), i2, i3
                    )
                ), toolBarNavHeight
            );
            customFrameLayout.btnBackNav.setVisibility(VISIBLE);
            customFrameLayout.btnBackNav.setOnClickListener(onClickListener);
            return this;
        }

        private Drawable createDrawable(int i, int i2) {
            Drawable b = AppCompatResources.getDrawable(context, i);
            if (i2 != -1) {
                b.setColorFilter(i2, PorterDuff.Mode.SRC_IN);
            }
            return b;
        }

        public Builder setUpToolbarButtons(int i, int i2, int i3, OnClickListener onClickListener) {
            customFrameLayout.roundedOptionalNav.setDrawable(
                new BitmapDrawable(
                    context.getResources(), 
                    ImageBitmaps.setBitmapGradient(
                        ViewUtils.createDrawableFromDrawable(
                            AppCompatResources.getDrawable(context, i)
                        ), i2, i3
                    )
                ), toolBarNavHeight
            );
            LayoutParams layoutParams = (LayoutParams) customFrameLayout.optionalNav.getLayoutParams();
            layoutParams.width = context.getResources().getDimensionPixelSize(R.dimen.toolbar_nav_height);
            customFrameLayout.optionalNav.setLayoutParams(layoutParams);
            customFrameLayout.optionalNav.setVisibility(VISIBLE);
            customFrameLayout.optionalNav.setOnClickListener(onClickListener);
            return this;
        }

        public Builder setUpToolbarButtons(int i, int i2, int i3, OnClickListener onClickListener, 
                String str, boolean z) {
            customFrameLayout.roundedOptionalNav.setText(
                new BitmapDrawable(
                    context.getResources(), 
                    ImageBitmaps.setBitmapGradient(
                        ViewUtils.createDrawableFromDrawable(
                            AppCompatResources.getDrawable(context, i)
                        ), i2, i3
                    )
                ), toolBarNavHeight, str
            );
            LayoutParams layoutParams = (LayoutParams) customFrameLayout.optionalNav.getLayoutParams();
            int i4 = (str != null) ? -2 : context.getResources().getDimensionPixelSize(R.dimen.toolbar_nav_height);
            layoutParams.width = i4;
            customFrameLayout.optionalNav.setLayoutParams(layoutParams);
            customFrameLayout.optionalNav.setVisibility(VISIBLE);
            customFrameLayout.optionalNav.setOnClickListener(onClickListener);
            customFrameLayout.roundedOptionalNav.setCollapsible(z);
            return this;
        }

        public Builder setRightNavButton(int i, int i2, OnClickListener onClickListener) {
            customFrameLayout.roundedOptionalNav.setDrawable(createDrawable(i, i2), toolBarNavHeight);
            customFrameLayout.optionalNav.setVisibility(VISIBLE);
            customFrameLayout.optionalNav.setOnClickListener(onClickListener);
            return this;
        }

        public Builder setNextToRightNavButton(int i, int i2, OnClickListener onClickListener) {
            customFrameLayout.roundedOptionalNav2.setDrawable(createDrawable(i, i2), toolBarNavHeight);
            customFrameLayout.optionalNav2.setVisibility(VISIBLE);
            customFrameLayout.optionalNav2.setOnClickListener(onClickListener);
            return this;
        }

        public Builder setNextToRightNavButton3(int i, int i2, OnClickListener onClickListener) {
            customFrameLayout.roundedOptionalNav3.setDrawable(createDrawable(i, i2), toolBarNavHeight);
            customFrameLayout.optionalNav3.setVisibility(VISIBLE);
            customFrameLayout.optionalNav3.setOnClickListener(onClickListener);
            return this;
        }

        public Builder setOptionalNavButtons(int i, int i2) {
            customFrameLayout.roundedOptionalNav.setDrawable(createDrawable(i, i2), toolBarNavHeight);
            customFrameLayout.optionalNav.setVisibility(VISIBLE);
            return this;
        }

        public Builder hideOptionalNav() {
            if (customFrameLayout.optionalNav2.getVisibility() == VISIBLE) {
                customFrameLayout.optionalNav.setVisibility(GONE);
            } else {
                customFrameLayout.optionalNav.setVisibility(INVISIBLE);
            }
            return this;
        }

        public Builder showOptionalNav() {
            customFrameLayout.optionalNav.setVisibility(VISIBLE);
            return this;
        }

        public Builder setRoundedOptionalNav2(int drawable, int startColor, int endColor, 
                OnClickListener onClickListener, String height, boolean isCollapsable) {
            customFrameLayout.roundedOptionalNav2.setText(
                new BitmapDrawable(
                    context.getResources(), 
                    ImageBitmaps.setBitmapGradient(
                        ViewUtils.createDrawableFromDrawable(
                            AppCompatResources.getDrawable(context, drawable)
                        ), startColor, endColor
                    )
                ), toolBarNavHeight, height
            );
            LayoutParams layoutParams = (LayoutParams) customFrameLayout.optionalNav2.getLayoutParams();
            int i4 = (height != null) ? -2 : context.getResources().getDimensionPixelSize(R.dimen.toolbar_nav_height);
            layoutParams.width = i4;
            customFrameLayout.optionalNav2.setLayoutParams(layoutParams);
            customFrameLayout.optionalNav2.setVisibility(VISIBLE);
            customFrameLayout.optionalNav2.setOnClickListener(onClickListener);
            customFrameLayout.roundedOptionalNav2.setCollapsible(isCollapsable);
            return this;
        }

        public Builder hideOptionalNav2() {
            customFrameLayout.optionalNav2.setVisibility(GONE);
            return this;
        }

        public Builder hideBackNav() {
            customFrameLayout.btnBackNav.setVisibility(GONE);
            return this;
        }
    }

    public CustomFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initViews();
    }

    public CustomFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initViews();
    }

    private void initViews() {
        inflate(getContext(), R.layout.custom_toolbar, this);
        customFontTextViewTitle = findViewById(R.id.title);
        customFontTextViewDescription = findViewById(R.id.desc);
        btnBackNav = findViewById(R.id.backNav);
        viewToolbar = findViewById(R.id.toolbarView);
        optionalNav = findViewById(R.id.optionalNav);
        optionalNav.setVisibility(INVISIBLE);
        optionalNav2 = findViewById(R.id.optionalNav2);
        optionalNav2.setVisibility(GONE);
        optionalNav3 = findViewById(R.id.optionalNav3);
        optionalNav3.setVisibility(GONE);
        customFontTextViewDescription.setVisibility(GONE);
        customView = findViewById(R.id.customView);
        roundNavButton = btnBackNav.findViewById(R.id.roundedNavButton);
        roundedOptionalNav = optionalNav.findViewById(R.id.roundedNavButton);
        roundedOptionalNav2 = optionalNav2.findViewById(R.id.roundedNavButton);
        roundedOptionalNav3 = optionalNav3.findViewById(R.id.rectangleNavButton);
        setPadding();
        setAppToolBarAlpha(0);
    }

    private void setPadding() {
        if (Build.VERSION.SDK_INT >= 23) {
            viewToolbar.setPadding(0, ViewUtils.getDimensions(getResources()), 0, 0);
        }
    }

    public void setToolbarPadding() {
        if (Build.VERSION.SDK_INT >= 23) {
            viewToolbar.setPadding(0, 0, 0, 0);
        }
    }

    private void setHomeAsUpButtonIcon(Drawable drawable) {
        roundNavButton.setIcon(drawable);
    }

    public void setAppToolBarAlpha(int i) {
        if (isActive) {
            setTitleAlpha((float) i / 255.0f);
        }
        boolean nightMode = getResources().getBoolean(R.bool.night_mode);
        if (nightMode) {
            viewToolbar.setBackgroundColor(Color.argb((int)(i * 0.92), 61, 61, 61));
        } else {
            viewToolbar.setBackgroundColor(Color.argb((int)(i * 0.92), 255, 255, 255));
        }
        if (i >= 255) {
            setToolbarButtonText(roundedOptionalNav);
            setToolbarButtonText(roundNavButton);
            setToolbarButtonText(roundedOptionalNav2);
            setToolbarButtonText(roundedOptionalNav3);
        }
        if (i <= 0) {
            setAppBarButtonText(roundedOptionalNav);
            setAppBarButtonText(roundNavButton);
            setAppBarButtonText(roundedOptionalNav2);
            setAppBarButtonText(roundedOptionalNav3);
        }
    }

    public void setOptionalMenuDrawable(Drawable drawable) {
        roundedOptionalNav.setIcon(drawable);
    }

    public void setOptionalMenu2Drawable(Drawable drawable) {
        roundedOptionalNav2.setIcon(drawable);
    }

    public void setToolbarTitle(CharSequence charSequence) {
        if (customFontTextViewTitle.getVisibility() == GONE) {
            customFontTextViewTitle.setVisibility(VISIBLE);
        }
        customFontTextViewTitle.setText(charSequence);
    }

    public void setToolbarSubtitle(CharSequence charSequence) {
        customFontTextViewDescription.setText(charSequence);
    }

    public View getBackNavView() {
        return btnBackNav;
    }

    public View getOptionalNavView() {
        return optionalNav;
    }

    public View getOptionalNav2View() {
        return optionalNav2;
    }

    public void setTitleAlpha(float f) {
        customFontTextViewTitle.setAlpha(f);
        customFontTextViewDescription.setAlpha(f);
    }

    public void setRoundNavText() {
        setToolbarButtonText(roundNavButton);
    }

    public void setRoundedOptionalNav3Text() {
        setToolbarButtonText(roundedOptionalNav3);
    }

    public void setRoundedOptionalNavText() {
        setToolbarButtonText(roundedOptionalNav);
    }

    public void setCustomView(View view) {
        customView.addView(view);
        customView.setVisibility(VISIBLE);
    }

    public void hideAppToolBar() {
        customView.setVisibility(GONE);
        if (customView.getChildCount() > 0) {
            customView.removeAllViews();
        }
    }

    private void setToolbarButtonText(CustomButton customButton) {
        if (customButton.isIEnabled()) {
            customButton.setText("");
            setTitleAlpha(1.0f);
        }
        customButton.disableShadow();
    }

    private void setAppBarButtonText(CustomButton customButton) {
        if (customButton.isIEnabled()) {
            customButton.setText(customButton.getTag() != null ? customButton.getTag().toString() : "");
            setTitleAlpha(Float.intBitsToFloat(1));
        }
        customButton.setUpShadow();
    }
}