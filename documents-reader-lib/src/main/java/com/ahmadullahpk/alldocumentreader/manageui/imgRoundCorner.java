package com.ahmadullahpk.alldocumentreader.manageui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import com.ahmadullahpk.alldocumentreader.R;

public class imgRoundCorner extends AppCompatImageView {
    public static final float intBitsToFloat = Float.intBitsToFloat(1);
    public static final double longBitsToDouble = Double.longBitsToDouble(1);
    
    private static final ScaleType[] SCALE_TYPES = {
        ScaleType.MATRIX,
        ScaleType.FIT_XY,
        ScaleType.FIT_START,
        ScaleType.FIT_CENTER,
        ScaleType.FIT_END,
        ScaleType.CENTER,
        ScaleType.CENTER_CROP,
        ScaleType.CENTER_INSIDE
    };

    private int res = 0;
    private ScaleType mScaleType = null;
    private float cornerRadius = 0f;
    private float borderWidth = 0f;
    private ColorStateList borderColors = null;
    private boolean en = false;
    private Drawable image = null;
    private float[] dimens = null;

    static class SelectableRoundedCornerDrawable extends Drawable {
        private final RectF rectF2 = new RectF();
        private int scaleWidth = 0;
        private int scaleHeight = 0;
        private final Paint paint;
        private final Paint paint1;
        private final RectF rectF = new RectF();
        private final RectF rectF1 = new RectF();
        private final BitmapShader shader;
        private final float[] radius = {
            intBitsToFloat, intBitsToFloat, intBitsToFloat, intBitsToFloat,
            intBitsToFloat, intBitsToFloat, intBitsToFloat, intBitsToFloat
        };
        private final float[] radius1 = {
            intBitsToFloat, intBitsToFloat, intBitsToFloat, intBitsToFloat,
            intBitsToFloat, intBitsToFloat, intBitsToFloat, intBitsToFloat
        };
        private boolean rounded = false;
        private float conversion = intBitsToFloat;
        private ColorStateList colorStateList = ColorStateList.valueOf(Color.BLACK);
        private ScaleType scaleType = ScaleType.FIT_CENTER;
        private final Path path = new Path();
        private boolean state = false;
        private final Bitmap bitmap;

        public SelectableRoundedCornerDrawable(Bitmap bitmap, Resources resources) {
            this.bitmap = bitmap;
            this.shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
            
            if (bitmap != null) {
                scaleWidth = bitmap.getScaledWidth(resources.getDisplayMetrics());
                scaleHeight = bitmap.getScaledHeight(resources.getDisplayMetrics());
            } else {
                scaleHeight = -1;
                scaleWidth = -1;
            }
            
            rectF2.set(intBitsToFloat, intBitsToFloat, (float) scaleWidth, (float) scaleHeight);
            
            paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setStyle(Paint.Style.FILL);
            paint.setShader(shader);
            
            paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint1.setStyle(Paint.Style.STROKE);
            paint1.setColor(colorStateList.getColorForState(getState(), Color.BLACK));
            paint1.setStrokeWidth(conversion);
        }

        @Override
        public boolean isStateful() {
            return colorStateList.isStateful();
        }

        @Override
        protected boolean onStateChange(int[] state) {
            int colorForState = colorStateList.getColorForState(state, 0);
            if (paint1.getColor() == colorForState) {
                return super.onStateChange(state);
            }
            paint1.setColor(colorForState);
            return true;
        }

        private void setMatrixScale(Canvas canvas) {
            Rect clipBounds = canvas.getClipBounds();
            Matrix matrix = canvas.getMatrix();
            
            if (ScaleType.CENTER == scaleType) {
                rectF.set(clipBounds);
            } else if (ScaleType.CENTER_CROP == scaleType) {
                setMatrixScale(matrix);
                rectF.set(clipBounds);
            } else if (ScaleType.FIT_XY == scaleType) {
                Matrix matrix2 = new Matrix();
                matrix2.setRectToRect(rectF2, new RectF(clipBounds), ScaleToFit.FILL);
                shader.setLocalMatrix(matrix2);
                rectF.set(clipBounds);
            } else if (ScaleType.FIT_START == scaleType || ScaleType.FIT_END == scaleType || 
                       ScaleType.FIT_CENTER == scaleType || ScaleType.CENTER_INSIDE == scaleType) {
                setMatrixScale(matrix);
                rectF.set(rectF2);
            } else if (ScaleType.MATRIX == scaleType) {
                setMatrixScale(matrix);
                rectF.set(rectF2);
            }
        }

        private void setMatrixScale(Matrix matrix) {
            float[] values = new float[9];
            matrix.getValues(values);
            for (int i = 0; i < radius.length; i++) {
                radius[i] = radius[i] / values[0];
            }
        }

        private void drawCanvas(Canvas canvas) {
            float[] values = new float[9];
            canvas.getMatrix().getValues(values);
            float scaleX = values[0];
            float scaleY = values[4];
            float translateX = values[2];
            float translateY = values[5];
            
            float width = rectF.width();
            float scaleFactorX = width / (width + conversion + conversion);
            float height = rectF.height();
            float scaleFactorY = height / (height + conversion + conversion);
            
            canvas.scale(scaleFactorX, scaleFactorY);
            
            if (ScaleType.FIT_START == scaleType || ScaleType.FIT_END == scaleType || 
                ScaleType.FIT_XY == scaleType || ScaleType.FIT_CENTER == scaleType || 
                ScaleType.CENTER_INSIDE == scaleType || ScaleType.MATRIX == scaleType) {
                canvas.translate(conversion, conversion);
            } else if (ScaleType.CENTER == scaleType || ScaleType.CENTER_CROP == scaleType) {
                canvas.translate(-translateX / (scaleFactorX * scaleX), -translateY / (scaleFactorY * scaleY));
                canvas.translate(-(rectF.left - conversion), -(rectF.top - conversion));
            }
        }

        private void drawCanvasFloat(Canvas canvas) {
            float[] values = new float[9];
            canvas.getMatrix().getValues(values);
            conversion = conversion * rectF.width() / (rectF.width() * values[0] - conversion * 2.0f);
            paint1.setStrokeWidth(conversion);
            rectF1.set(rectF);
            rectF1.inset(-conversion / 2.0f, -conversion / 2.0f);
        }

        private void convertTofloat() {
            for (int i = 0; i < radius.length; i++) {
                if (radius[i] > intBitsToFloat) {
                    radius1[i] = radius[i];
                    radius[i] = radius[i] - conversion;
                }
            }
        }

        @Override
        public void draw(Canvas canvas) {
            canvas.save();
            if (!state) {
                setMatrixScale(canvas);
                if (conversion > intBitsToFloat) {
                    drawCanvasFloat(canvas);
                    convertTofloat();
                }
                state = true;
            }
            
            if (rounded) {
                if (conversion > intBitsToFloat) {
                    drawCanvas(canvas);
                    path.addOval(rectF, Path.Direction.CW);
                    canvas.drawPath(path, paint);
                    path.reset();
                    path.addOval(rectF1, Path.Direction.CW);
                    canvas.drawPath(path, paint1);
                } else {
                    path.addOval(rectF, Path.Direction.CW);
                    canvas.drawPath(path, paint);
                }
            } else if (conversion > intBitsToFloat) {
                drawCanvas(canvas);
                path.addRoundRect(rectF, radius, Path.Direction.CW);
                canvas.drawPath(path, paint);
                path.reset();
                path.addRoundRect(rectF1, radius1, Path.Direction.CW);
                canvas.drawPath(path, paint1);
            } else {
                path.addRoundRect(rectF, radius, Path.Direction.CW);
                canvas.drawPath(path, paint);
            }
            canvas.restore();
        }

        public void castRadious(float[] radii) {
            if (radii != null) {
                if (radii.length == 8) {
                    System.arraycopy(radii, 0, radius, 0, radii.length);
                    return;
                }
                throw new ArrayIndexOutOfBoundsException("radii[] needs 8 values");
            }
        }

        @Override
        public int getOpacity() {
            return (bitmap == null || bitmap.hasAlpha() || paint.getAlpha() < 255) ? 
                PixelFormat.TRANSLUCENT : PixelFormat.OPAQUE;
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
            invalidateSelf();
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {
            paint.setColorFilter(colorFilter);
            invalidateSelf();
        }

        @Override
        public void setDither(boolean dither) {
            paint.setDither(dither);
            invalidateSelf();
        }

        @Override
        public void setFilterBitmap(boolean filter) {
            paint.setFilterBitmap(filter);
            invalidateSelf();
        }

        @Override
        public int getIntrinsicWidth() {
            return scaleWidth;
        }

        @Override
        public int getIntrinsicHeight() {
            return scaleHeight;
        }

        public void setBorderWidth(float borderWidth) {
            conversion = borderWidth;
            paint1.setStrokeWidth(borderWidth);
        }

        public void setColorState(ColorStateList colorStateList) {
            if (colorStateList == null) {
                conversion = intBitsToFloat;
                this.colorStateList = ColorStateList.valueOf(0);
                paint1.setColor(0);
                return;
            }
            this.colorStateList = colorStateList;
            paint1.setColor(this.colorStateList.getColorForState(getState(), Color.BLACK));
        }

        public void setOval(boolean oval) {
            rounded = oval;
        }

        public void setScaleType(ScaleType scaleType) {
            if (scaleType != null) {
                this.scaleType = scaleType;
            }
        }

        public static SelectableRoundedCornerDrawable fromBitmap(Bitmap bitmap, Resources resources) {
            return bitmap != null ? new SelectableRoundedCornerDrawable(bitmap, resources) : null;
        }

        public static Drawable fromDrawable(Drawable drawable, Resources resources) {
            if (drawable == null || drawable instanceof SelectableRoundedCornerDrawable) {
                return drawable;
            }
            
            if (drawable instanceof LayerDrawable) {
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                int numberOfLayers = layerDrawable.getNumberOfLayers();
                for (int i = 0; i < numberOfLayers; i++) {
                    layerDrawable.setDrawableByLayerId(
                        layerDrawable.getId(i),
                        fromDrawable(layerDrawable.getDrawable(i), resources)
                    );
                }
                return layerDrawable;
            }
            
            Bitmap bitmap = getBitmapFromDrawable(drawable);
            return bitmap != null ? new SelectableRoundedCornerDrawable(bitmap, resources) : drawable;
        }

        public static Bitmap getBitmapFromDrawable(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
            
            try {
                Bitmap bitmap = Bitmap.createBitmap(
                    Math.max(drawable.getIntrinsicWidth(), 2),
                    Math.max(drawable.getIntrinsicHeight(), 2),
                    Bitmap.Config.ARGB_8888
                );
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);
                return bitmap;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public imgRoundCorner(Context context) {
        super(context);
    }

    public imgRoundCorner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public imgRoundCorner(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        
        res = 0;
        mScaleType = ScaleType.FIT_CENTER;
        cornerRadius = intBitsToFloat;
        borderWidth = intBitsToFloat;
        borderColors = ColorStateList.valueOf(Color.BLACK);
        en = false;
        dimens = new float[]{
            intBitsToFloat, intBitsToFloat, intBitsToFloat, intBitsToFloat,
            intBitsToFloat, intBitsToFloat, intBitsToFloat, intBitsToFloat
        };
        
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(
            attributeSet, R.styleable.RoundedCornerImageView, defStyleAttr, 0);
        
        int scaleTypeIndex = obtainStyledAttributes.getInt(0, -1);
        if (scaleTypeIndex >= 0) {
            setScaleType(SCALE_TYPES[scaleTypeIndex]);
        }
        
        cornerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
            R.styleable.RoundedCornerImageView_left_top_corner_radius, 2);
        float rightTopRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
            R.styleable.RoundedCornerImageView_right_top_corner_radius, 2);
        float leftBottomRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
            R.styleable.RoundedCornerImageView_left_bottom_corner_radius, 2);
        float rightBottomRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
            R.styleable.RoundedCornerImageView_right_bottom_corner_radius, 2);
        float allCornersRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
            R.styleable.RoundedCornerImageView_corner_radius, 2);
        
        if (cornerRadius < intBitsToFloat || rightTopRadius < intBitsToFloat || 
            leftBottomRadius < intBitsToFloat || rightBottomRadius < intBitsToFloat) {
            throw new IllegalArgumentException("radius values cannot be negative.");
        }
        
        if (allCornersRadius >= longBitsToDouble) {
            if (allCornersRadius > intBitsToFloat) {
                cornerRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
                    R.styleable.RoundedCornerImageView_corner_radius, 0);
                rightTopRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
                    R.styleable.RoundedCornerImageView_corner_radius, 0);
                leftBottomRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
                    R.styleable.RoundedCornerImageView_corner_radius, 0);
                rightBottomRadius = (float) obtainStyledAttributes.getDimensionPixelSize(
                    R.styleable.RoundedCornerImageView_corner_radius, 0);
            }
            
            dimens = new float[]{
                cornerRadius, cornerRadius, rightTopRadius, rightTopRadius,
                rightBottomRadius, rightBottomRadius, leftBottomRadius, leftBottomRadius
            };
            
            borderWidth = (float) obtainStyledAttributes.getDimensionPixelSize(
                R.styleable.RoundedCornerImageView_border_width, 1);
            
            if (borderWidth >= intBitsToFloat) {
                borderColors = obtainStyledAttributes.getColorStateList(R.styleable.RoundedCornerImageView_border_color);
                if (borderColors == null) {
                    borderColors = ColorStateList.valueOf(Color.BLACK);
                }
                
                en = obtainStyledAttributes.getBoolean(R.styleable.RoundedCornerImageView_oval, false);
                obtainStyledAttributes.recycle();
                updateImage();
                return;
            }
            throw new IllegalArgumentException("border width cannot be negative.");
        } else {
            throw new IllegalArgumentException("radius values cannot be negative.");
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }

    @Override
    public ScaleType getScaleType() {
        return mScaleType;
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        super.setScaleType(scaleType);
        mScaleType = scaleType;
        updateImage();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        res = 0;
        image = SelectableRoundedCornerDrawable.fromDrawable(drawable, getResources());
        super.setImageDrawable(image);
        updateImage();
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        res = 0;
        image = SelectableRoundedCornerDrawable.fromBitmap(bitmap, getResources());
        super.setImageDrawable(image);
        updateImage();
    }

    @Override
    public void setImageResource(int resId) {
        if (res != resId) {
            res = resId;
            image = resolveResource();
            super.setImageDrawable(image);
            updateImage();
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        setImageDrawable(getDrawable());
    }

    private Drawable resolveResource() {
        Resources resources = getResources();
        if (resources == null) {
            return null;
        }
        
        Drawable drawable = null;
        if (res != 0) {
            try {
                drawable = resources.getDrawable(res);
            } catch (NotFoundException e) {
                res = 0;
            }
        }
        return SelectableRoundedCornerDrawable.fromDrawable(drawable, getResources());
    }

    private void updateImage() {
        if (image != null && image instanceof SelectableRoundedCornerDrawable) {
            SelectableRoundedCornerDrawable roundedDrawable = (SelectableRoundedCornerDrawable) image;
            roundedDrawable.setScaleType(mScaleType);
            roundedDrawable.castRadious(dimens);
            roundedDrawable.setBorderWidth(borderWidth);
            roundedDrawable.setColorState(borderColors);
            roundedDrawable.setOval(en);
        }
    }

    public void setCornerRadiiDP(float radiusDP) {
        float radiusPx = radiusDP * getResources().getDisplayMetrics().density;
        dimens = new float[]{radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx, radiusPx};
        updateImage();
    }

    public void setBorderWidthDP(float borderWidthDP) {
        float borderWidthPx = getResources().getDisplayMetrics().density * borderWidthDP;
        if (Math.abs(borderWidth - borderWidthPx) >= 1.0E-4) {
            borderWidth = borderWidthPx;
            updateImage();
            invalidate();
        }
    }

    public int getBorderColor() {
        return borderColors.getDefaultColor();
    }

    public void setBorderColor(int color) {
        setBorderColor(ColorStateList.valueOf(color));
    }

    public void setBorderColor(ColorStateList colorStateList) {
        if (borderColors != colorStateList) {
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(Color.BLACK);
            }
            borderColors = colorStateList;
            updateImage();
            if (borderWidth > intBitsToFloat) {
                invalidate();
            }
        }
    }

    public void setOval(boolean oval) {
        en = oval;
        updateImage();
        invalidate();
    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public float getBorderWidth() {
        return borderWidth;
    }

    public ColorStateList getBorderColors() {
        return borderColors;
    }
}