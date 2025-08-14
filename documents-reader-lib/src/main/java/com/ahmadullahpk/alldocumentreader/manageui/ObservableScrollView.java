package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.widget.NestedScrollView;

import java.util.ArrayList;
import java.util.List;

public class ObservableScrollView extends NestedScrollView {
    private final List<OnScrollViewListener> viewListenerList = new ArrayList<>();
    private final List<OnScrollStateListener> onScrollStateListeners = new ArrayList<>();

    public interface OnScrollStateListener {
        void onScroll(ObservableScrollView observableScrollView);
    }

    public interface OnScrollViewListener {
        void onScrollChanged(
                ObservableScrollView observableScrollView,
                int i,
                int i2,
                int i3,
                int i4
        );
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        sizeOnScroll(i, i2, i3, i4);
        if (Math.abs(i2 - i4) < 2) {
            sizeOnScroll();
        }
        super.onScrollChanged(i, i2, i3, i4);
    }

    private void sizeOnScroll() {
        for (int idx = onScrollStateListeners.size() - 1; idx >= 0; idx--) {
            onScrollStateListeners.get(idx).onScroll(this);
        }
    }

    private void sizeOnScroll(int i, int i2, int i3, int i4) {
        for (int idx = viewListenerList.size() - 1; idx >= 0; idx--) {
            viewListenerList.get(idx).onScrollChanged(this, i, i2, i3, i4);
        }
    }

    @Override
    public void requestChildFocus(View child, View focused) {
        super.requestChildFocus(child, focused);
    }
}
