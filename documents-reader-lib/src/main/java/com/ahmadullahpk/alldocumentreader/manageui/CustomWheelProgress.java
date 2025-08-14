package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.ahmadullahpk.alldocumentreader.R;

public class CustomWheelProgress extends View {
    private static final String TAG = CustomWheelProgress.class.getSimpleName();
    
    private int circleRadius = 80;
    private boolean fillRadius = false;
    private double timeStartGrowing = Double.longBitsToDouble(1);
    private double barSpinCycleTime = 1000.0;
    private float barExtraLength = Float.intBitsToFloat(1);
    private boolean barGrowingFromFront = true;
    private long pausedTimeWithoutGrowing = 0;
    private int barWidth = 5;
    private int rimWidth = 5;

    // Colors (with defaults)
    private int pBarColor = 0xAA000000;
    private int rimColor = 0x00FFFFFF;

    // Paints
    private final Paint barPaint = new Paint();
    private final Paint rimPaint = new Paint();
    private RectF circleBounds = new RectF();
    private float spinSpeed = 270.0f;
    private long current = 0;
    private float startAngle = 270.0f;
    private float f12959v = Float.intBitsToFloat(1);
    private boolean isSpinning = false;

    static class WheelSavedState extends BaseSavedState {
        float mProgress = 0f;
        float mTargetProgress = 0f;
        boolean isSpinning = false;
        float spinSpeed = 0f;
        int barWidth = 0;
        int barColor = 0;
        int rimWidth = 0;
        int rimColor = 0;
        int circleRadius = 0;

        WheelSavedState(Parcelable superState) {
            super(superState);
        }

        private WheelSavedState(Parcel parcel) {
            super(parcel);
            mProgress = parcel.readFloat();
            mTargetProgress = parcel.readFloat();
            isSpinning = parcel.readByte() != 0;
            spinSpeed = parcel.readFloat();
            barWidth = parcel.readInt();
            barColor = parcel.readInt();
            rimWidth = parcel.readInt();
            rimColor = parcel.readInt();
            circleRadius = parcel.readInt();
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            super.writeToParcel(parcel, flags);
            parcel.writeFloat(mProgress);
            parcel.writeFloat(mTargetProgress);
            parcel.writeByte((byte) (isSpinning ? 1 : 0));
            parcel.writeFloat(spinSpeed);
            parcel.writeInt(barWidth);
            parcel.writeInt(barColor);
            parcel.writeInt(rimWidth);
            parcel.writeInt(rimColor);
            parcel.writeInt(circleRadius);
        }

        public static final Parcelable.Creator<WheelSavedState> CREATOR = new Parcelable.Creator<WheelSavedState>() {
            @Override
            public WheelSavedState createFromParcel(Parcel parcel) {
                return new WheelSavedState(parcel);
            }

            @Override
            public WheelSavedState[] newArray(int size) {
                return new WheelSavedState[size];
            }
        };
    }

    public CustomWheelProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAppProgressWheel(context.obtainStyledAttributes(attributeSet, R.styleable.ProgressWheel));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int paddingLeft = circleRadius + getPaddingLeft() + getPaddingRight();
        int paddingTop = circleRadius + getPaddingTop() + getPaddingBottom();
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = MeasureSpec.getMode(heightMeasureSpec);
        int size2 = MeasureSpec.getSize(heightMeasureSpec);
        
        if (mode == MeasureSpec.EXACTLY) {
            paddingLeft = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            paddingLeft = Math.min(paddingLeft, size);
        }
        
        if (mode2 == MeasureSpec.EXACTLY || mode == MeasureSpec.EXACTLY) {
            paddingTop = size2;
        } else if (mode2 == MeasureSpec.AT_MOST) {
            paddingTop = Math.min(paddingTop, size2);
        }
        
        setMeasuredDimension(paddingLeft, paddingTop);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setPadding(w, h);
        setUpPaints();
        invalidate();
    }

    private void setUpPaints() {
        barPaint.setColor(pBarColor);
        barPaint.setAntiAlias(true);
        barPaint.setStyle(Paint.Style.STROKE);
        barPaint.setStrokeWidth((float) barWidth);
        
        rimPaint.setColor(rimColor);
        rimPaint.setAntiAlias(true);
        rimPaint.setStyle(Paint.Style.STROKE);
        rimPaint.setStrokeWidth((float) rimWidth);
    }

    private void setPadding(int w, int h) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        
        if (!fillRadius) {
            int availableWidth = w - paddingLeft - paddingRight;
            int min = Math.min(
                Math.min(availableWidth, h - paddingBottom - paddingTop),
                circleRadius * 2 - barWidth * 2
            );
            int x = (availableWidth - min) / 2 + paddingLeft;
            int y = (h - paddingTop - paddingBottom - min) / 2 + paddingTop;
            int barWidthOffset = barWidth;
            circleBounds = new RectF(
                (float) (x + barWidthOffset),
                (float) (y + barWidthOffset),
                (float) (x + min - barWidthOffset),
                (float) (y + min - barWidthOffset)
            );
            return;
        }
        
        int barWidthOffset = barWidth;
        circleBounds = new RectF(
            (float) (paddingLeft + barWidthOffset),
            (float) (paddingTop + barWidthOffset),
            (float) (w - paddingRight - barWidthOffset),
            (float) (h - paddingBottom - barWidthOffset)
        );
    }

    private void initAppProgressWheel(TypedArray typedArray) {
        float displayDensity = getContext().getResources().getDisplayMetrics().density;
        barWidth = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            (float) barWidth,
            getContext().getResources().getDisplayMetrics()
        );
        rimWidth = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            (float) rimWidth,
            getContext().getResources().getDisplayMetrics()
        );
        
        circleRadius = (int) typedArray.getDimension(R.styleable.ProgressWheel_circleRadius, (float) circleRadius);
        fillRadius = typedArray.getBoolean(R.styleable.ProgressWheel_fillRadius, false);
        barWidth = (int) typedArray.getDimension(R.styleable.ProgressWheel_barWidth, (float) barWidth);
        rimWidth = (int) typedArray.getDimension(R.styleable.ProgressWheel_rimWidth, (float) rimWidth);
        spinSpeed = typedArray.getFloat(R.styleable.ProgressWheel_spinSpeed, spinSpeed / 360.0f) * 360.0f;
        barSpinCycleTime = (double) typedArray.getInt(R.styleable.ProgressWheel_barSpinCycleTime, (int) barSpinCycleTime);
        pBarColor = typedArray.getColor(R.styleable.ProgressWheel_pBarColor, pBarColor);
        rimColor = typedArray.getColor(R.styleable.ProgressWheel_rimColor, rimColor);
        
        if (typedArray.getBoolean(R.styleable.ProgressWheel_progressIndeterminate, false)) {
            upDateProgress();
        }
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(circleBounds, 360.0f, 360.0f, false, rimPaint);
        boolean shouldInvalidate = true;
        
        if (isSpinning) {
            long uptimeMillis = SystemClock.uptimeMillis() - current;
            float rotation = (float) uptimeMillis * spinSpeed / 1000.0f;
            updateBarLength(uptimeMillis);
            startAngle += rotation;
            
            if (startAngle > 360.0f) {
                startAngle = startAngle - 360.0f;
            }
            
            current = SystemClock.uptimeMillis();
            canvas.drawArc(
                circleBounds,
                startAngle - 90.0f,
                barExtraLength + 40.0f,
                false,
                barPaint
            );
        } else {
            if (startAngle != f12959v) {
                startAngle = Math.min(
                    startAngle + (SystemClock.uptimeMillis() - current) / 1000.0f * spinSpeed,
                    f12959v
                );
                current = SystemClock.uptimeMillis();
            } else {
                shouldInvalidate = false;
            }
            canvas.drawArc(circleBounds, -90.0f, startAngle, false, barPaint);
        }
        
        if (shouldInvalidate) {
            invalidate();
        }
    }

    private void updateBarLength(long deltaTimeInMilliSeconds) {
        if (pausedTimeWithoutGrowing >= 300) {
            timeStartGrowing += (double) deltaTimeInMilliSeconds;
            if (timeStartGrowing > barSpinCycleTime) {
                timeStartGrowing = Double.longBitsToDouble(1);
                if (!barGrowingFromFront) {
                    pausedTimeWithoutGrowing = 0;
                }
                barGrowingFromFront = !barGrowingFromFront;
            }
            
            float progress = (float) (Math.cos((timeStartGrowing / barSpinCycleTime + 1.0) * Math.PI) / 2.0f + 0.5f);
            
            if (barGrowingFromFront) {
                barExtraLength = progress * 230.0f;
                return;
            }
            
            float newLength = (1.0f - progress) * 230.0f;
            startAngle += barExtraLength - newLength;
            barExtraLength = newLength;
            return;
        }
        pausedTimeWithoutGrowing = pausedTimeWithoutGrowing + deltaTimeInMilliSeconds;
    }

    public void upDateProgress() {
        current = SystemClock.uptimeMillis();
        isSpinning = true;
        invalidate();
    }

    public void setInstantProgress(float progress) {
        if (isSpinning) {
            startAngle = Float.intBitsToFloat(1);
            isSpinning = false;
        }
        
        if (progress > 1.0f) {
            progress -= 1.0f;
        } else if (progress < Float.intBitsToFloat(1)) {
            progress = Float.intBitsToFloat(1);
        }
        
        if (Math.abs(progress - f12959v) >= 1.0E-4) {
            f12959v = Math.min(progress * 360.0f, 360.0f);
            startAngle = f12959v;
            current = SystemClock.uptimeMillis();
            invalidate();
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        WheelSavedState wheelSavedState = new WheelSavedState(super.onSaveInstanceState());
        wheelSavedState.mProgress = startAngle;
        wheelSavedState.mTargetProgress = f12959v;
        wheelSavedState.isSpinning = isSpinning;
        wheelSavedState.spinSpeed = spinSpeed;
        wheelSavedState.barWidth = barWidth;
        wheelSavedState.barColor = pBarColor;
        wheelSavedState.rimWidth = rimWidth;
        wheelSavedState.rimColor = rimColor;
        wheelSavedState.circleRadius = circleRadius;
        return wheelSavedState;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (!(state instanceof WheelSavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }
        
        WheelSavedState savedState = (WheelSavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        startAngle = savedState.mProgress;
        f12959v = savedState.mTargetProgress;
        isSpinning = savedState.isSpinning;
        spinSpeed = savedState.spinSpeed;
        barWidth = savedState.barWidth;
        pBarColor = savedState.barColor;
        rimWidth = savedState.rimWidth;
        rimColor = savedState.rimColor;
        circleRadius = savedState.circleRadius;
    }

    public float getProgress() {
        return isSpinning ? -1.0f : startAngle / 360.0f;
    }

    public void setProgress(float progress) {
        if (isSpinning) {
            startAngle = Float.intBitsToFloat(1);
            isSpinning = false;
        }
        
        if (progress > 1.0f) {
            progress -= 1.0f;
        } else if (progress < Float.intBitsToFloat(1)) {
            progress = Float.intBitsToFloat(1);
        }
        
        if (Math.abs(progress - f12959v) >= 1.0E-4f) {
            if (Math.abs(startAngle - f12959v) < 1.0E-4f) {
                current = SystemClock.uptimeMillis();
            }
            f12959v = Math.min(progress * 360.0f, 360.0f);
            invalidate();
        }
    }

    public int getCircleRadius() {
        return circleRadius;
    }

    public void setCircleRadius(int radius) {
        circleRadius = radius;
        if (!isSpinning) {
            invalidate();
        }
    }

    public int getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(int width) {
        barWidth = width;
        if (!isSpinning) {
            invalidate();
        }
    }

    public int getBarColor() {
        return pBarColor;
    }

    public void setBarColor(int color) {
        pBarColor = color;
        setUpPaints();
        if (!isSpinning) {
            invalidate();
        }
    }

    public int getRimColor() {
        return rimColor;
    }

    public void setRimColor(int color) {
        rimColor = color;
        setUpPaints();
        if (!isSpinning) {
            invalidate();
        }
    }

    public float getSpinSpeed() {
        return spinSpeed / 360.0f;
    }

    public void setSpinSpeed(float speed) {
        spinSpeed = speed * 360.0f;
    }

    public int getRimWidth() {
        return rimWidth;
    }

    public void setRimWidth(int width) {
        rimWidth = width;
        if (!isSpinning) {
            invalidate();
        }
    }
}