package com.xxmassdeveloper.mpchartexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


import com.xxmassdeveloper.mpchartexample.view.viewbeen.Wsbyinfo;

import java.util.ArrayList;

public class Forecast2WsView extends View {
    public Forecast2WsView(Context context) {
        super(context);
        init();
    }

    public Forecast2WsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Forecast2WsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        maxValue = 31021 - 645;
        textPaint = new TextPaint();
        textPaint.setColor(Color.WHITE);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
    }

    private int topTextSize;
    private int otherTextSize;

    private TextPaint textPaint;
    private Paint paint;


    //西北长度是否脱离比例
    private static final boolean xbIsDiff = true;
    //    private static final float wPx = 713;
//    private static final float hPx = 372;
    private static float wPx = 713;
    private static float hPx = 372;
    private static float beginX = 0;
    private static float beginY = 0;
    private float useWidth, useHeight;

    private float useHeightItem = 0.073f;
    private float itemTextBegn = 0.038f;
    private float itemSqreBegn = 0.116f;
    private float itemSqreUse = 0.817f;
    private double maxValue;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        wPx = getWidth();
        hPx = getHeight();
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        widthSize = widthSize - getPaddingLeft() - getPaddingRight();
        heightSize = heightSize - getPaddingTop() - getPaddingBottom();
        float pix = widthSize / wPx > heightSize / hPx ? heightSize / hPx : widthSize / wPx;
        useWidth = (pix * wPx);
        useHeight = (pix * hPx);
        beginX = (widthSize - useWidth) / 2;
        beginY = (heightSize - useHeight) / 2;
        topTextSize = (int) (useHeight * 0.063);
        otherTextSize = (int) (useHeight * 0.05);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw top text
        textPaint.setTextSize(topTextSize);
        canvas.drawText("最小出力", beginX + useWidth * TOP_TEXT[0][0], beginY + useHeight * TOP_TEXT[0][1], textPaint);
        canvas.drawText("实际出力", beginX + useWidth * TOP_TEXT[1][0], beginY + useHeight * TOP_TEXT[1][1], textPaint);
        canvas.drawText("最大出力", beginX + useWidth * TOP_TEXT[2][0], beginY + useHeight * TOP_TEXT[2][1], textPaint);
        textPaint.setTextSize(otherTextSize);
        //draw item
        float textHeightAdd = (useHeightItem * useHeight - otherTextSize) / 2 + otherTextSize;
        for (int i = 0; i < str.length; i++) {
            canvas.drawText(getItem(i), beginX + useWidth * itemTextBegn, (float) (beginY + useHeight * str[i][3] + textHeightAdd), textPaint);
            if (xbIsDiff) {
                if (i == 0) {
                    drawXb(canvas, i, textHeightAdd);
                } else {
                    drawItem(canvas, i, textHeightAdd);
                }
            } else {
                drawItem(canvas, i, textHeightAdd);
            }

        }
    }

    private static final int leftCOlor = Color.parseColor("#28A1A0");
    private static final int rightCOlor = Color.parseColor("#DD633A");

    private void drawXb(Canvas canvas, int i, float textHeightAdd) {
        //
        paint.setColor(leftCOlor);
        double maxValue = str[i][0] - str[i][1];
        float w2 = (float) ((str[i][0] - str[i][2]) / maxValue * useWidth * itemSqreUse);
        float w1 = (float) ((str[i][2] - str[i][1]) / maxValue * useWidth * itemSqreUse);
        canvas.drawRect(beginX + itemSqreBegn * useWidth,
                (float) str[i][3] * useHeight + beginY,
                beginX + itemSqreBegn * useWidth + w1,
                (float) (str[i][3] + useHeightItem) * useHeight + beginY, paint);
        paint.setColor(rightCOlor);
        canvas.drawRect(beginX + itemSqreBegn * useWidth + w1,
                (float) str[i][3] * useHeight + beginY,
                beginX + itemSqreBegn * useWidth + w1 + w2,
                (float) (str[i][3] + useHeightItem) * useHeight + beginY, paint);
        int otherTextSize = this.otherTextSize / 2;
        //left
        canvas.drawText((int) (str[i][1]) + "", beginX + itemSqreBegn * useWidth + otherTextSize,
                (float) str[i][3] * useHeight + beginY + textHeightAdd, textPaint);
        //end
        float nextBegin = beginX + itemSqreBegn * useWidth + w1 + otherTextSize >
                beginX + itemSqreBegn * useWidth + otherTextSize * 2 + textPaint.measureText((int) str[i][1] + "")
                ? beginX + itemSqreBegn * useWidth + w1 + otherTextSize : beginX + itemSqreBegn * useWidth +
                otherTextSize * 2 + textPaint.measureText((int) str[i][1] + "");
        //center
        if (w2 < textPaint.measureText((int) str[i][2] + "") + textPaint.measureText((int) str[i][0] + "")) {
            if (textPaint.measureText((int) str[i][1] + "") < w1) {
                nextBegin = beginX + itemSqreBegn * useWidth + otherTextSize * 2 + textPaint.measureText((int) str[i][1] + "");
            }
        }
        canvas.drawText((int) (str[i][2]) + "", nextBegin,
                (float) str[i][3] * useHeight + beginY + textHeightAdd, textPaint);
        //end
        nextBegin = nextBegin + textPaint.measureText((int) str[i][2] + "") + otherTextSize >
                beginX + itemSqreBegn * useWidth + w1 + w2 - otherTextSize - textPaint.measureText((int) str[i][0] + "")
                ? nextBegin + textPaint.measureText((int) str[i][2] + "") + otherTextSize :
                beginX + itemSqreBegn * useWidth + w1 + w2 - otherTextSize - textPaint.measureText((int) str[i][0] + "");

        //right
        canvas.drawText((int) (str[i][0]) + "", nextBegin,
                (float) str[i][3] * useHeight + beginY + textHeightAdd, textPaint);
    }

    private void drawItem(Canvas canvas, int i, float textHeightAdd) {
        //
        paint.setColor(leftCOlor);
        float w2 = (float) ((str[i][0] - str[i][2]) / maxValue * useWidth * itemSqreUse);
        float w1 = (float) ((str[i][2] - str[i][1]) / maxValue * useWidth * itemSqreUse);
        canvas.drawRect(beginX + itemSqreBegn * useWidth,
                (float) str[i][3] * useHeight + beginY,
                beginX + itemSqreBegn * useWidth + w1,
                (float) (str[i][3] + useHeightItem) * useHeight + beginY, paint);
        paint.setColor(rightCOlor);
        canvas.drawRect(beginX + itemSqreBegn * useWidth + w1,
                (float) str[i][3] * useHeight + beginY,
                beginX + itemSqreBegn * useWidth + w1 + w2,
                (float) (str[i][3] + useHeightItem) * useHeight + beginY, paint);
        int otherTextSize = this.otherTextSize / 2;
        //left
        canvas.drawText((int) (str[i][1]) + "", beginX + itemSqreBegn * useWidth + otherTextSize,
                (float) str[i][3] * useHeight + beginY + textHeightAdd, textPaint);
        //end
        float nextBegin = beginX + itemSqreBegn * useWidth + w1 + otherTextSize >
                beginX + itemSqreBegn * useWidth + otherTextSize * 2 + textPaint.measureText((int) str[i][1] + "")
                ? beginX + itemSqreBegn * useWidth + w1 + otherTextSize : beginX + itemSqreBegn * useWidth +
                otherTextSize * 2 + textPaint.measureText((int) str[i][1] + "");
        if (w2 < textPaint.measureText((int) str[i][2] + "") + textPaint.measureText((int) str[i][0] + "")) {
            if (textPaint.measureText((int) str[i][1] + "") < w1) {
                nextBegin = beginX + itemSqreBegn * useWidth + otherTextSize * 2 + textPaint.measureText((int) str[i][1] + "");
            }
        }
        //center
        canvas.drawText((int) (str[i][2]) + "", nextBegin,
                (float) str[i][3] * useHeight + beginY + textHeightAdd, textPaint);

        //end
        nextBegin = nextBegin + textPaint.measureText((int) str[i][2] + "") + otherTextSize >
                beginX + itemSqreBegn * useWidth + w1 + w2 - otherTextSize - textPaint.measureText((int) str[i][0] + "")
                ? nextBegin + textPaint.measureText((int) str[i][2] + "") + otherTextSize :
                beginX + itemSqreBegn * useWidth + w1 + w2 - otherTextSize - textPaint.measureText((int) str[i][0] + "");

        //right
        canvas.drawText((int) (str[i][0]) + "", nextBegin,
                (float) str[i][3] * useHeight + beginY + textHeightAdd, textPaint);
    }


    public void setTmpL(ArrayList<Wsbyinfo> tmpL) {
        if (tmpL == null) {
            return;
        }
        boolean hasXIBei = true;
        maxValue = 0;
        double left = 0, right = 0, center = 0;
        for (int i = 0; i < tmpL.size(); i++) {
            Wsbyinfo wsbyinfo = tmpL.get(i);

            boolean b = setItem(wsbyinfo.getFProvinceName(), wsbyinfo.getFMaximumOutput() + wsbyinfo.getFActualOutput(),
                    wsbyinfo.getFActualOutput() - wsbyinfo.getFMinimumOutput(), wsbyinfo.getFActualOutput());
            if (b) {
                left += wsbyinfo.getFActualOutput() - wsbyinfo.getFMinimumOutput();
                right += wsbyinfo.getFMaximumOutput() + wsbyinfo.getFActualOutput();
                center += wsbyinfo.getFActualOutput();
            } else {
                if ("西北".equals(wsbyinfo.getFProvinceName())) {
                    hasXIBei = false;
                }
            }
        }
        if (hasXIBei) {
            setItem("西北", right, left, center);
        }

        invalidate();
    }

    private String getItem(int i) {
        switch (i) {
            case 0:
                return "西北";
            case 1:
                return "陕西";
            case 2:
                return "甘肃";
            case 3:
                return "青海";
            case 4:
                return "宁夏";
            case 5:
                return "新疆";
            default:
                return "西北";
        }
    }

    private boolean setItem(String fProvinceName, double fMaximumOutput, double fMinimumOutput, double fActualOutput) {
        if (fProvinceName == null) {
            return false;
        }
        if (xbIsDiff) {
            if (!fProvinceName.equals("西北")) {
                maxValue = fMaximumOutput - fMinimumOutput > maxValue ? fMaximumOutput - fMinimumOutput : maxValue;
            }
        } else {
            maxValue = fMaximumOutput - fMinimumOutput > maxValue ? fMaximumOutput - fMinimumOutput : maxValue;
        }

        switch (fProvinceName) {
            case "西北":
                str[0][0] = fMaximumOutput;
                str[0][1] = fMinimumOutput;
                str[0][2] = fActualOutput;
                break;
            case "陕西":
                str[1][0] = fMaximumOutput;
                str[1][1] = fMinimumOutput;
                str[1][2] = fActualOutput;
                return true;
            case "甘肃":
                str[2][0] = fMaximumOutput;
                str[2][1] = fMinimumOutput;
                str[2][2] = fActualOutput;
                return true;
            case "青海":
                str[3][0] = fMaximumOutput;
                str[3][1] = fMinimumOutput;
                str[3][2] = fActualOutput;
                return true;
            case "宁夏":
                str[4][0] = fMaximumOutput;
                str[4][1] = fMinimumOutput;
                str[4][2] = fActualOutput;
                return true;
            case "新疆":
                str[5][0] = fMaximumOutput;
                str[5][1] = fMinimumOutput;
                str[5][2] = fActualOutput;
                return true;
        }
        return false;
    }

    private static final float[][] TOP_TEXT = {
            {0.101f, 0.09f},
            {0.393f, 0.09f},
            {0.766f, 0.09f},
    };

    private static double[][] str = {
            //西北
            // 最大，最下，出力，位置Height
            {27021, 345, 12345, 0.164},
            //陕西
            {31021, 645, 15345, 0.293},
            //甘肃
            {18876, 145, 245, 0.433},
            //青海
            {19876, 345, 845, 0.583},
            //宁夏
            {21876, 245, 345, 0.728},
            //新疆
            {26876, 545, 845, 0.860},
    };
}
