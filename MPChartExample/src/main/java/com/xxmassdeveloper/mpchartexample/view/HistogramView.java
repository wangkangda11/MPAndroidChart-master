package com.xxmassdeveloper.mpchartexample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HistogramView  extends View {

    private static final String TAG = "HistogramView";

    /**
     * 柱状图L型线的画笔
     */
    private Paint linePaint = new Paint();
    /**
     * 柱状图数据的画笔
     */
    private Paint histogramPaint = new Paint();
    /**
     * 柱状图文字的画笔
     */
    private Paint textPaint = new Paint();

    /**
     * 柱状图数据
     */
    private List<Histogram> mList = new ArrayList<>();
    /**
     * 柱状图L型线的水平长度
     */
    private int horizontalLineLength = 0;
    /**
     * 柱状图L型线的垂直高度
     */
    private int verticalLineHight = 0;
    /**
     * 柱状图水平缩放比例：如果数据量大，按比例缩放显示
     */
    private float hScalSize = 1;
    /**
     * 柱状图垂直缩放比例：如果数据量大，按比例缩放显示
     */
    private float wScalSize = 1;

    public HistogramView(Context context) {
        super(context);
        init();
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //柱状图L型线的画笔初始设定
        linePaint.setColor(Color.GRAY);
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(2);

        //柱状图数据画笔初始设定
        histogramPaint.setAntiAlias(true);

        //柱状图文字的画笔初始设定
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);

        int width = 0;
        float sum = 0;
        for (Histogram histogram : mList) {
            Log.d(TAG, "histogram.spaceWidth:" + histogram.spaceWidth + "   histogram.valueWidth:" + histogram.valueWidth);
            horizontalLineLength += histogram.spaceWidth + histogram.valueWidth;
            width = histogram.spaceWidth;
            if (sum < histogram.value) {
                sum = histogram.value;
            }
        }
        //获取水平方向长度，并多增加一次间距，避免贴屏幕显示
        horizontalLineLength += width;
        //获取垂直方向高度，确定最大值，并增加1/4最大值，确定垂直线
        verticalLineHight += sum + sum / 4;
        Log.d(TAG, "horizontalLineLength:" + horizontalLineLength);
        Log.d(TAG, "verticalLineHight:" + verticalLineHight);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //获取当前宽高
        int width = getWidth();
        int hight = getHeight();
        Log.d(TAG, "width:" + width);
        Log.d(TAG, "hight:" + hight);
        //如果当前宽小于所有数据总宽度，则计算缩放比例
        if (width < horizontalLineLength) {
            wScalSize = (float) horizontalLineLength / (float) width;
        }
        //如果当前高小于计算高度，则计算缩放比例
        if (hight < verticalLineHight) {
            hScalSize = (float) verticalLineHight / (float) hight;
        }

        Log.d(TAG, "wScalSize:" + wScalSize);
        Log.d(TAG, "hScalSize:" + hScalSize);
        //确定L型线的起点 并按比例计算位置。初始位置默认为1/20
        int startX = (int) (width / 20 / wScalSize);
        int startY = (int) (hight / 20 / hScalSize);

        Log.d(TAG, "startX:" + startX);
        Log.d(TAG, "startY:" + startY);
        //垂直线
        canvas.drawLine(startX / wScalSize, startY / hScalSize, startX / wScalSize, (startY + verticalLineHight) / hScalSize, linePaint);
        //水平线
        canvas.drawLine(startX / wScalSize, (startY + verticalLineHight) / hScalSize, (startX + horizontalLineLength) / wScalSize, (startY + verticalLineHight) / hScalSize, linePaint);

        int pWidth = startX;
        //遍历数据，画出相应柱状图和文字描述
        for (Histogram histogram : mList) {
            Log.d(TAG, "pWidth1:" + pWidth);
            //获取随机颜色
            int red = (int) (Math.random() * 255);
            int green = (int) (Math.random() * 255);
            int blue = (int) (Math.random() * 255);
            @ColorInt int color = Color.rgb(red, green, blue);
            histogramPaint.setColor(histogram.color);
            textPaint.setColor(Color.parseColor("#000000"));
            histogramPaint.setStrokeWidth(histogram.valueWidth / wScalSize);

            if (pWidth == startX) {//第一次柱状图位置，宽度减半
                pWidth += histogram.spaceWidth + histogram.valueWidth / 2;
            } else
                pWidth += histogram.spaceWidth + histogram.valueWidth;

            Log.d(TAG, "pWidth2:" + pWidth);
            //柱状图
            canvas.drawLine(pWidth / wScalSize, (startY + verticalLineHight) / hScalSize, pWidth / wScalSize, (startY + verticalLineHight - histogram.value) / hScalSize, histogramPaint);
            //获取设置的文字大小
            float size = 20;//textPaint.getTextSize()
            //按比例缩放文字
            textPaint.setTextSize(size / wScalSize);
            //绘制底部文字
            canvas.drawText(histogram.valueName, pWidth / wScalSize, (startY + verticalLineHight) / hScalSize + 2 * (size / wScalSize), textPaint);
            //绘制顶部文字
            canvas.drawText(histogram.value+"", pWidth / wScalSize, (startY + verticalLineHight - histogram.value) / hScalSize - (size / wScalSize), textPaint);

            //柱状图2

            histogramPaint.setColor(Color.parseColor("#9538322B"));
            textPaint.setColor(Color.parseColor("#000000"));
            histogramPaint.setStrokeWidth(histogram.valueWidth / wScalSize);

//            if (pWidth == startX) {//第一次柱状图位置，宽度减半
//                pWidth += histogram.spaceWidth + histogram.valueWidth / 2;
//            } else
//                pWidth += histogram.spaceWidth + histogram.valueWidth;
            canvas.drawLine(pWidth / wScalSize, (startY + verticalLineHight - histogram.value) / hScalSize, pWidth / wScalSize, (startY + verticalLineHight- histogram.value1) / hScalSize, histogramPaint);
            //获取设置的文字大小
            float size1 = 20;//textPaint.getTextSize()
            //按比例缩放文字
            textPaint.setTextSize(size1 / wScalSize);
            //绘制底部文字
            canvas.drawText(histogram.valueName, pWidth / wScalSize, (startY + verticalLineHight) / hScalSize + 2 * (size1 / wScalSize), textPaint);

            NumberFormat nf = NumberFormat.getPercentInstance();

            //绘制顶部文字
            canvas.drawText(histogram.value1+"", pWidth / wScalSize, (startY + verticalLineHight - histogram.value1) / hScalSize - (size1 / wScalSize), textPaint);
            canvas.drawText("("+nf.format(histogram.value/histogram.value1)+")", pWidth / wScalSize, (startY + verticalLineHight - histogram.value1) / hScalSize - (size1 / wScalSize), textPaint);

        }
    }
    public void setTextSize(float size) {
        textPaint.setTextSize(size);
    }

    public void setData(List<Histogram> list) {
        mList.clear();
        mList.addAll(list);
        init();
    }

    public static class Histogram {
        /**
         * 柱状图高度数值
         */
        private float value = 0;

        /**
         * 柱状图高度预定数值
         */
        private float value1 = 0;



        /**
         * 柱状图数值对应名称
         */
        private String valueName = "ABC";
        /**
         * 柱状图宽度 不设置可使用默认值100
         */
        private int valueWidth = 100;
        /**
         * 柱状图间距 不设置可使用默认值50
         */
        private int spaceWidth = 50;
        /**
         * 颜色
         */
        private int color = 50;



        public Histogram() {

        }
        public Histogram(float value) {
            this.value = value;
        }

        public Histogram(float value, String valueName) {
            this.value = value;
            this.valueName = valueName;
        }

        public Histogram(float value, String valueName, int valueWidth) {
            this.value = value;
            this.valueName = valueName;
            this.valueWidth = valueWidth;
        }

        public Histogram(float value, String valueName, int valueWidth, int spaceWidth) {
            this.value = value;
            this.valueName = valueName;
            this.valueWidth = valueWidth;
            this.spaceWidth = spaceWidth;
        }

        public Histogram(float value, float value1, String valueName, int valueWidth, int spaceWidth, int color) {
            this.value = value;
            this.value1 = value1;
            this.valueName = valueName;
            this.valueWidth = valueWidth;
            this.spaceWidth = spaceWidth;
            this.color = color;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
        public String getValueName() {
            return valueName;
        }

        public void setValueName(String valueName) {
            this.valueName = valueName;
        }

        public int getValueWidth() {
            return valueWidth;
        }

        public void setValueWidth(int valueWidth) {
            this.valueWidth = valueWidth;
        }

        public int getSpaceWidth() {
            return spaceWidth;
        }

        public void setSpaceWidth(int spaceWidth) {
            this.spaceWidth = spaceWidth;
        }

        public float getValue1() {
            return value1;
        }

        public void setValue1(float value1) {
            this.value1 = value1;
        }


        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }


}

