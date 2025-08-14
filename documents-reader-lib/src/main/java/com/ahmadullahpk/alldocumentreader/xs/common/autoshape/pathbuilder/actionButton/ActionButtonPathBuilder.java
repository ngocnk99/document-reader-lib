package com.ahmadullahpk.alldocumentreader.xs.common.autoshape.pathbuilder.actionButton;

import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.ahmadullahpk.alldocumentreader.xs.common.autoshape.ExtendPath;
import com.ahmadullahpk.alldocumentreader.xs.common.bg.BackgroundAndFill;
import com.ahmadullahpk.alldocumentreader.xs.common.shape.AutoShape;
import com.ahmadullahpk.alldocumentreader.xs.common.shape.ShapeTypes;
import com.ahmadullahpk.alldocumentreader.xs.ss.util.ColorUtil;
import java.util.ArrayList;
import java.util.List;

public class ActionButtonPathBuilder {
    private static final RectF tempRect = new RectF();
    private static final List<ExtendPath> pathExList = new ArrayList<>(2);

    // picture fill color
    private static final int PICTURECOLOR = 0x8F454544;
    private static final int PICTURECOLOR_LIGHT = 0x8F555554;
    private static final int PICTURECOLOR_DARK = 0x8FCDCDCC;
    private static final float TINT_LIGHT1 = -0.3f;
    private static final float TINT_LIGHT2 = 0.6f;
    private static final float TINT_DARK = -0.5f;

    /**
     * get action button path
     * @param shape
     * @param rect
     * @return
     */
    public static List<ExtendPath> getActionButtonExtendPath(AutoShape shape, Rect rect) {
        pathExList.clear();
        switch (shape.getShapeType()) {
            case ShapeTypes.ActionButtonBackPrevious:
                return getBackPreviousPath(shape, rect);
            case ShapeTypes.ActionButtonForwardNext:
                return getForwardNextPath(shape, rect);
            case ShapeTypes.ActionButtonBeginning:
                return getBeginningPath(shape, rect);
            case ShapeTypes.ActionButtonEnd:
                return getEndPath(shape, rect);
            case ShapeTypes.ActionButtonHome:
                return getHomePath(shape, rect);
            case ShapeTypes.ActionButtonInformation:
                return getInformationPath(shape, rect);
            case ShapeTypes.ActionButtonReturn:
                return getReturnPath(shape, rect);
            case ShapeTypes.ActionButtonMovie:
                return getMoviePath(shape, rect);
            case ShapeTypes.ActionButtonDocument:
                return getDocumentPath(shape, rect);
            case ShapeTypes.ActionButtonSound:
                return getSoundPath(shape, rect);
            case ShapeTypes.ActionButtonHelp:
                return getHelpPath(shape, rect);
            case ShapeTypes.ActionButtonBlank:
                return getBlankPath(shape, rect);
        }
        return null;
    }

    private static List<ExtendPath> getBackPreviousPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);
        
        pathExtend = new ExtendPath();
        path = new Path();
        int arrowLen = Math.round(Math.min(rect.width(), rect.height()) * 0.75f);
        float off = (float) Math.round(Math.sqrt(3.0) / 4 * arrowLen);
        path.moveTo(rect.centerX() - off, rect.centerY());
        path.lineTo(rect.centerX() + off, rect.centerY() - arrowLen / 2f);
        path.lineTo(rect.centerX() + off, rect.centerY() + arrowLen / 2f);
        path.close();
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExtend.setPath(path);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getForwardNextPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);
        
        pathExtend = new ExtendPath();
        path = new Path();
        int arrowLen = Math.round(Math.min(rect.width(), rect.height()) * 0.75f);
        float off = (float) Math.round(Math.sqrt(3.0) / 4 * arrowLen);
        path.moveTo(rect.centerX() + off, rect.centerY());
        path.lineTo(rect.centerX() - off, rect.centerY() + arrowLen / 2f);
        path.lineTo(rect.centerX() - off, rect.centerY() - arrowLen / 2f);
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getBeginningPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);
        
        pathExtend = new ExtendPath();
        path = new Path();
        int len = Math.min(rect.width(), rect.height());
        path.addRect(
                rect.centerX() - len * 0.375f,
                rect.centerY() - len * 0.375f,
                rect.centerX() - len * (0.375f - 0.1f),
                rect.centerY() + len * 0.375f,
                Path.Direction.CW
        );
        path.moveTo(rect.centerX() - len * 3 / 16f, rect.centerY());
        path.lineTo(rect.centerX() + len * 0.375f, rect.centerY() - len * 0.375f);
        path.lineTo(rect.centerX() + len * 0.375f, rect.centerY() + len * 0.375f);
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getEndPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);
        
        pathExtend = new ExtendPath();
        path = new Path();
        int len = Math.min(rect.width(), rect.height());
        path.addRect(
                rect.centerX() + len * (0.375f - 0.1f),
                rect.centerY() - len * 0.375f,
                rect.centerX() + len * 0.375f,
                rect.centerY() + len * 0.375f,
                Path.Direction.CW
        );
        path.moveTo(rect.centerX() + len * 3 / 16f, rect.centerY());
        path.lineTo(rect.centerX() - len * 0.375f, rect.centerY() + len * 0.375f);
        path.lineTo(rect.centerX() - len * 0.375f, rect.centerY() - len * 0.375f);
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getHomePath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);

        // First path
        pathExtend = new ExtendPath();
        path = new Path();
        int len = Math.min(rect.width(), rect.height());
        path.addRect(
                rect.centerX() - len * 0.28f,
                rect.centerY(),
                rect.centerX() + len * 0.28f,
                rect.centerY() + len * 0.375f,
                Path.Direction.CW
        );
        path.moveTo(rect.centerX() + len * 0.14f, rect.centerY() - len * 0.33f);
        path.lineTo(rect.centerX() + len * 0.24f, rect.centerY() - len * 0.33f);
        path.lineTo(rect.centerX() + len * 0.24f, rect.centerY() - len * (0.375f - 0.24f));
        path.lineTo(rect.centerX() + len * 0.14f, rect.centerY() - len * (0.375f - 0.14f));
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_LIGHT1));
        } else {
            fill.setForegroundColor(PICTURECOLOR_LIGHT);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);

        // Second path
        pathExtend = new ExtendPath();
        path = new Path();
        path.moveTo(rect.centerX() - len * 0.375f, rect.centerY());
        path.lineTo(rect.centerX(), rect.centerY() - len * 0.375f);
        path.lineTo(rect.centerX() + len * 0.375f, rect.centerY());
        path.close();
        path.addRect(
                rect.centerX() - len * 0.05f,
                rect.centerY() + len * 0.18f,
                rect.centerX() + len * 0.05f,
                rect.centerY() + len * 0.375f,
                Path.Direction.CW
        );
        pathExtend.setPath(path);
        
        fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getInformationPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);

        // First path
        pathExtend = new ExtendPath();
        path = new Path();
        int len = Math.min(rect.width(), rect.height());
        path.addCircle(
                rect.centerX(),
                rect.centerY(),
                len * 0.375f,
                Path.Direction.CW
        );
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);

        // Second path
        pathExtend = new ExtendPath();
        path = new Path();
        path.addCircle(
                rect.centerX(),
                rect.centerY() - len * 0.22f,
                len * 0.06f,
                Path.Direction.CW
        );
        path.moveTo(rect.centerX() - len * 0.12f, rect.centerY() - len * 0.11f);
        path.lineTo(rect.centerX() + len * 0.06f, rect.centerY() - len * 0.11f);
        path.lineTo(rect.centerX() + len * 0.06f, rect.centerY() + len * 0.16f);
        path.lineTo(rect.centerX() + len * 0.12f, rect.centerY() + len * 0.16f);
        path.lineTo(rect.centerX() + len * 0.12f, rect.centerY() + len * 0.2f);
        path.lineTo(rect.centerX() - len * 0.12f, rect.centerY() + len * 0.2f);
        path.lineTo(rect.centerX() - len * 0.12f, rect.centerY() + len * 0.16f);
        path.lineTo(rect.centerX() - len * 0.06f, rect.centerY() + len * 0.16f);
        path.lineTo(rect.centerX() - len * 0.06f, rect.centerY() - len * 0.08f);
        path.lineTo(rect.centerX() - len * 0.12f, rect.centerY() - len * 0.08f);
        path.close();
        pathExtend.setPath(path);
        
        fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_LIGHT2));
        } else {
            fill.setForegroundColor(PICTURECOLOR_DARK);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getReturnPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);

        int len = Math.min(rect.width(), rect.height());
        pathExtend = new ExtendPath();
        path = new Path();
        path.moveTo(rect.centerX() - len * 0.4f, rect.centerY() - len * 0.2f);
        path.lineTo(rect.centerX() - len * 0.2f, rect.centerY() - len * 0.2f);
        path.lineTo(rect.centerX() - len * 0.2f, rect.centerY() + len * 0.1f);
        tempRect.set(rect.centerX() - len * 0.2f, rect.centerY(), 
                    rect.centerX(), rect.centerY() + len * 0.2f);
        path.arcTo(tempRect, 180f, -90f);
        path.lineTo(rect.centerX(), rect.centerY() + len * 0.2f);
        tempRect.set(rect.centerX() - len * 0.1f, rect.centerY(),
                    rect.centerX() + len * 0.1f, rect.centerY() + len * 0.2f);
        path.arcTo(tempRect, 90f, -90f);
        path.lineTo(rect.centerX() + len * 0.1f, rect.centerY() - len * 0.2f);
        path.lineTo(rect.centerX(), rect.centerY() - len * 0.2f);
        path.lineTo(rect.centerX() + len * 0.2f, rect.centerY() - len * 0.4f);
        path.lineTo(rect.centerX() + len * 0.4f, rect.centerY() - len * 0.2f);
        path.lineTo(rect.centerX() + len * 0.3f, rect.centerY() - len * 0.2f);
        tempRect.set(rect.centerX() - len * 0.3f, rect.centerY() - len * 0.2f,
                    rect.centerX() + len * 0.3f, rect.centerY() + len * 0.4f);
        path.arcTo(tempRect, 0f, 90f);
        tempRect.set(rect.centerX() - len * 0.4f, rect.centerY() - len * 0.2f,
                    rect.centerX() + len * 0.2f, rect.centerY() + len * 0.4f);
        path.arcTo(tempRect, 90f, 90f);
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getMoviePath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);

        int len = Math.min(rect.width(), rect.height());
        pathExtend = new ExtendPath();
        path = new Path();
        path.moveTo(rect.centerX() - len * 0.38f, rect.centerY() - len * 0.2f);
        path.lineTo(rect.centerX() - len * 0.31f, rect.centerY() - len * 0.2f);
        path.lineTo(rect.centerX() - len * 0.3f, rect.centerY() - len * 0.18f);
        path.lineTo(rect.centerX() + len * 0.18f, rect.centerY() - len * 0.18f);
        path.lineTo(rect.centerX() + len * 0.22f, rect.centerY() - len * 0.15f);
        path.lineTo(rect.centerX() + len * 0.22f, rect.centerY() - len * 0.12f);
        path.lineTo(rect.centerX() + len * 0.31f, rect.centerY() - len * 0.12f);
        path.lineTo(rect.centerX() + len * 0.34f, rect.centerY() - len * 0.15f);
        path.lineTo(rect.centerX() + len * 0.37f, rect.centerY() - len * 0.15f);
        path.lineTo(rect.centerX() + len * 0.37f, rect.centerY() + len * 0.15f);
        path.lineTo(rect.centerX() + len * 0.34f, rect.centerY() + len * 0.15f);
        path.lineTo(rect.centerX() + len * 0.29f, rect.centerY() + len * 0.12f);
        path.lineTo(rect.centerX() + len * 0.22f, rect.centerY() + len * 0.12f);
        path.lineTo(rect.centerX() + len * 0.22f, rect.centerY() + len * 0.16f);
        path.lineTo(rect.centerX() - len * 0.29f, rect.centerY() + len * 0.16f);
        path.lineTo(rect.centerX() - len * 0.29f, rect.centerY() - len * 0.06f);
        path.lineTo(rect.centerX() - len * 0.31f, rect.centerY() - len * 0.06f);
        path.lineTo(rect.centerX() - len * 0.32f, rect.centerY() - len * 0.04f);
        path.lineTo(rect.centerX() - len * 0.38f, rect.centerY() - len * 0.04f);
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getDocumentPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);

        int len = Math.min(rect.width(), rect.height());
        pathExtend = new ExtendPath();
        path = new Path();
        path.moveTo(rect.centerX() - len * 0.28f, rect.centerY() - len * 0.38f);
        path.lineTo(rect.centerX() + len * 0.1f, rect.centerY() - len * 0.38f);
        path.lineTo(rect.centerX() + len * 0.1f, rect.centerY() - len * 0.18f);
        path.lineTo(rect.centerX() + len * 0.28f, rect.centerY() - len * 0.18f);
        path.lineTo(rect.centerX() + len * 0.28f, rect.centerY() + len * 0.38f);
        path.lineTo(rect.centerX() - len * 0.28f, rect.centerY() + len * 0.38f);
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_LIGHT1));
        } else {
            fill.setForegroundColor(PICTURECOLOR_LIGHT);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);

        // Second path
        pathExtend = new ExtendPath();
        path = new Path();
        path.moveTo(rect.centerX() + len * 0.1f, rect.centerY() - len * 0.38f);
        path.lineTo(rect.centerX() + len * 0.1f, rect.centerY() - len * 0.18f);
        path.lineTo(rect.centerX() + len * 0.28f, rect.centerY() - len * 0.18f);
        path.close();
        pathExtend.setPath(path);
        
        fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getSoundPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);

        int len = Math.min(rect.width(), rect.height());
        pathExtend = new ExtendPath();
        path = new Path();
        path.moveTo(rect.centerX() - len * 0.38f, rect.centerY() - len * 0.14f);
        path.lineTo(rect.centerX() - len * 0.14f, rect.centerY() - len * 0.14f);
        path.lineTo(rect.centerX() + len * 0.1f, rect.centerY() - len * 0.38f);
        path.lineTo(rect.centerX() + len * 0.1f, rect.centerY() + len * 0.38f);
        path.lineTo(rect.centerX() - len * 0.14f, rect.centerY() + len * 0.14f);
        path.lineTo(rect.centerX() - len * 0.38f, rect.centerY() + len * 0.14f);
        path.close();
        
        float off = Math.min(5f, len * 0.01f);
        path.moveTo(rect.centerX() + len * 0.18f, rect.centerY() - len * 0.14f);
        path.lineTo(rect.centerX() + len * 0.38f, rect.centerY() - len * 0.28f);
        path.lineTo(rect.centerX() + len * 0.38f, rect.centerY() - len * 0.28f + off);
        path.lineTo(rect.centerX() + len * 0.18f, rect.centerY() - len * 0.14f + off);
        path.close();
        path.moveTo(rect.centerX() + len * 0.18f, rect.centerY());
        path.lineTo(rect.centerX() + len * 0.38f, rect.centerY());
        path.lineTo(rect.centerX() + len * 0.38f, rect.centerY() + off);
        path.lineTo(rect.centerX() + len * 0.18f, rect.centerY() + off);
        path.close();
        path.moveTo(rect.centerX() + len * 0.18f, rect.centerY() + len * 0.14f);
        path.lineTo(rect.centerX() + len * 0.38f, rect.centerY() + len * 0.28f);
        path.lineTo(rect.centerX() + len * 0.38f, rect.centerY() + len * 0.28f + off);
        path.lineTo(rect.centerX() + len * 0.18f, rect.centerY() + len * 0.14f + off);
        path.close();
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR_LIGHT);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getHelpPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);

        int len = Math.min(rect.width(), rect.height());
        pathExtend = new ExtendPath();
        path = new Path();
        path.moveTo(rect.centerX() - len * 0.2f, rect.centerY() - len * 0.16f);
        tempRect.set(rect.centerX() - len * 0.2f, rect.centerY() - len * 0.36f,
                    rect.centerX() + len * 0.2f, rect.centerY() + len * 0.04f);
        path.arcTo(tempRect, 180f, 240f);
        tempRect.set(rect.centerX() + len * 0.05f, rect.centerY() + len * 0.012f,
                    rect.centerX() + len * 0.15f, rect.centerY() + len * 0.112f);
        path.arcTo(tempRect, 270f, -90f);
        path.lineTo(rect.centerX() + len * 0.05f, rect.centerY() + len * 0.18f);
        path.lineTo(rect.centerX() - len * 0.05f, rect.centerY() + len * 0.18f);
        path.lineTo(rect.centerX() - len * 0.05f, rect.centerY() + len * 0.1f);
        tempRect.set(rect.centerX() - len * 0.05f, rect.centerY() - len * 0.073f,
                    rect.centerX() + len * 0.15f, rect.centerY() + len * 0.273f);
        path.arcTo(tempRect, 180f, 90f);
        tempRect.set(rect.centerX() - len * 0.1f, rect.centerY() - len * 0.26f,
                    rect.centerX() + len * 0.1f, rect.centerY() - len * 0.06f);
        path.arcTo(tempRect, 60f, -240f);
        path.close();
        path.addCircle(
                rect.centerX(),
                rect.centerY() + len * 0.28f,
                len * 0.08f,
                Path.Direction.CW
        );
        pathExtend.setPath(path);
        
        BackgroundAndFill fill = new BackgroundAndFill();
        fill.setFillType(BackgroundAndFill.FILL_SOLID);
        BackgroundAndFill shapeFill = shape.getBackgroundAndFill();
        if (shapeFill != null && shapeFill.getFillType() == BackgroundAndFill.FILL_SOLID) {
            fill.setForegroundColor(ColorUtil.getInstance()
                    .getColorWithTint(shapeFill.getForegroundColor(), (double) TINT_DARK));
        } else {
            fill.setForegroundColor(PICTURECOLOR_LIGHT);
        }
        pathExtend.setBackgroundAndFill(fill);
        pathExList.add(pathExtend);
        return pathExList;
    }

    private static List<ExtendPath> getBlankPath(AutoShape shape, Rect rect) {
        ExtendPath pathExtend = new ExtendPath();
        Path path = new Path();
        tempRect.set(rect);
        path.addRect(tempRect, Path.Direction.CW);
        pathExtend.setPath(path);
        pathExtend.setBackgroundAndFill(shape.getBackgroundAndFill());
        pathExList.add(pathExtend);
        return pathExList;
    }
}