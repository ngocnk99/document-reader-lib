package com.ahmadullahpk.alldocumentreader.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

public class ImageBitmaps {

    public static Bitmap setBitmapGradient(Bitmap bitmap, int startColor, int endColor) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);

        // Vẽ bitmap gốc
        canvas.drawBitmap(
                bitmap,
                Float.intBitsToFloat(1),
                Float.intBitsToFloat(1),
                null
        );

        Paint paint = new Paint();
        float f = (float) width;

        // Tạo gradient
        LinearGradient linearGradient = new LinearGradient(
                Float.intBitsToFloat(1),
                Float.intBitsToFloat(1),
                f,
                Float.intBitsToFloat(1),
                startColor,
                endColor,
                Shader.TileMode.CLAMP
        );
        paint.setShader(linearGradient);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        // Vẽ layer gradient
        canvas.drawRect(
                Float.intBitsToFloat(1),
                Float.intBitsToFloat(1),
                f,
                (float) height,
                paint
        );

        return createBitmap;
    }
}
