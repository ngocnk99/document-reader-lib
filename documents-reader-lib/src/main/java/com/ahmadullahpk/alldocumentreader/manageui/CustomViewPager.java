package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import androidx.viewpager.widget.ViewPager;

public class CustomViewPager extends ViewPager {
    public static final float INT_BITS_TO_FLOAT = Float.intBitsToFloat(1);
    
    public float mStartDragX = 0f;
    private OnSwipeOutListener onSwipeOutListener = null;

    public interface OnSwipeOutListener {
        void onSwipeOutAtStart();
        void onSwipeOutAtEnd();
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnSwipeOutListener(OnSwipeOutListener onSwipeOutListener) {
        this.onSwipeOutListener = onSwipeOutListener;
    }

    private void onSwipeOutAtStart() {
        OnSwipeOutListener onSwipeOutListener = this.onSwipeOutListener;
        if (onSwipeOutListener != null) {
            onSwipeOutListener.onSwipeOutAtStart();
        }
    }

    private void onSwipeOutAtEnd() {
        OnSwipeOutListener onSwipeOutListener = this.onSwipeOutListener;
        if (onSwipeOutListener != null) {
            onSwipeOutListener.onSwipeOutAtEnd();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if ((ev.getAction() & 255) == 0) {
            mStartDragX = ev.getX();
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (getCurrentItem() == 0 || getCurrentItem() == getAdapter().getCount() - 1) {
            int action = ev.getAction();
            float x = ev.getX();
            if ((action & 255) == 1) {
                if (getCurrentItem() == 0 && x > mStartDragX) {
                    onSwipeOutAtStart();
                }
                if (getCurrentItem() == getAdapter().getCount() - 1 && x < mStartDragX) {
                    onSwipeOutAtEnd();
                }
            }
        } else {
            mStartDragX = INT_BITS_TO_FLOAT;
        }
        return super.onTouchEvent(ev);
    }
}