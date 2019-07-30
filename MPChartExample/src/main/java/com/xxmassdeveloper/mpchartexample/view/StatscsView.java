package com.xxmassdeveloper.mpchartexample.view;

/**
 * Created by Administrator on 2015/11/12.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class StatscsView extends View {

    public StatscsView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub

        init(context, null);
    }

    public StatscsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(context, attrs);
    }

    /**
     * 左边距
     */
    private int leftPadding = 10;
    //  坐标轴 轴线 画笔：
    private Paint axisLinePaint;
    //  坐标轴水平内部 柱形图画笔
    private Paint rectPaint;
    //  绘制属性文本的画笔
    private Paint titlePaint;
    //绘制数据文本的画笔
    private Paint dataPaint;
    //起始X坐标
    private float startX=200;
    //起始Y坐标
    public float startY=100;
    //柱形图上下右坐标
    private float columnTop;
    private float columnBelow;
    private float columnRight;
    //画布宽度
    private int width ;
    //画布高度
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }public void setHeight(int height) {
        this.height = height;
    }
    //字体宽度
    private float textWidth=100;

//    private int ss=(height-20)/5;

    //数据
    private int[] AirIndex=new int[]{1,2,3,4,5};

    private void init(Context context, AttributeSet attrs)
    {

        axisLinePaint = new Paint();
        rectPaint = new Paint();
        titlePaint = new Paint();
        dataPaint = new Paint();

        dataPaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextAlign(Paint.Align.RIGHT);

        dataPaint.setColor(Color.BLACK);
        axisLinePaint.setColor(Color.DKGRAY);
        rectPaint.setColor(Color.RED);
        titlePaint.setColor(Color.BLACK);

        axisLinePaint.setAntiAlias(true);

    }


    public void uodateAirIndex(int[] airIndex){
        AirIndex=airIndex;
        this.postInvalidate();
    }

    private String[] property =
            new String[]{"温度[℃]","湿度[RH%]","VOC[%]","CO2[PPH]","PM2.5[μg/m3]"};

    private String state="";


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        columnTop=10;
        columnBelow=0;
        columnRight=0;

        width=getWidth();
        height=getHeight();
        startX=width/8;
        //画布空间
        int drawHeight=height-20;
        int drawWidth=width-110;
        int ss=drawHeight/5;
        titlePaint.setTextSize(ss / 2);
        Log.i("提示", "columnTop:" + columnTop + "  ss:" + width);
        // 1 绘制坐标线：Y轴
        //canvas.drawLine(startX, startY, startX, AirIndex.length*80, axisLinePaint);

        int leftHeight = ss;// 左侧外周的 需要划分的高度：

        int hPerHeight = leftHeight/4;

        //绘制柱状图
        rectPaint.setTextAlign(Paint.Align.CENTER);
        for(int i=0;i<property.length;i++)
        {

//            columnTop=10 + i * hPerHeight;
//            columnBelow=60 + i * hPerHeight;
            columnRight=AirIndex[i] * 200 + startX;

            if (AirIndex[i]<=1) {
                state="优秀";
                rectPaint.setColor(Color.parseColor("#00c0cd"));
            } else if (AirIndex[i]<=2) {
                state="良好";
                rectPaint.setColor(Color.parseColor("#76b800"));
            } else if (AirIndex[i]<=3) {
                state="一般";
                rectPaint.setColor(Color.parseColor("#f2a301"));
            } else if (AirIndex[i]<=4) {
                state="差劲";
                rectPaint.setColor(Color.parseColor("#e84700"));
            } else {
                state="恶劣";
                rectPaint.setColor(Color.parseColor("#746dcd"));
            }
            columnBelow=columnTop+ss/2;
            //绘制柱形图
            if(columnRight>(width-startX)){
                columnRight=width-startX;
            }else{
                columnRight=AirIndex[i] * 200 + startX;
            }
            canvas.drawRect(startX, columnTop, columnRight, columnBelow, rectPaint);
            columnTop=columnTop+ss;
            textWidth=startX/8;
            dataPaint.setTextSize(textWidth);
            titlePaint.setTextSize(textWidth);

            //绘制数据
            canvas.drawText(AirIndex[i] + "", (columnRight-startX)/2+startX, columnBelow-(columnTop-columnBelow)/2 , dataPaint);
            //绘制数据评价
            canvas.drawText(state, columnRight + 3*textWidth, columnBelow-10, titlePaint);
            //绘制Y轴坐标
            canvas.drawText(property[i], startX-10, columnBelow-10, titlePaint);
        }
    }
}
