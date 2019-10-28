package com.hizz.graph;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Drawing extends View implements View.OnClickListener {

    private Paint mPaint, mPaintBackground, mPaintText;
    private Paint mLinesPaint;
    private Path path;
//    int width, height;


    Point p0 = new Point();
    Point p1 = new Point();
    Point p2 = new Point();
    Point p3 = new Point();
    Point p4 = new Point();
    Point p5 = new Point();
    Point p6 = new Point();


    public Drawing(Context context) {
        this(context, null);
        init (null);
    }

    public Drawing(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init (attrs);
    }

    public Drawing(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        init (attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Drawing(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        //you can also init your attributes here (if you have any)

        init (attrs);
    }

    private void init (AttributeSet set){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinesPaint = new Paint();
        mPaintBackground = new Paint();
        mPaintBackground.setColor(Color.GRAY);
        mPaintText = new Paint();
        mPaintText .setColor(Color.DKGRAY);
        path = new Path();

        p0.set(0,800);
        p1.set(180,400);
        p2.set(360,1000);
        p3.set(540,600);
        p4.set(720,800);
        p5.set(900,1000);
        p6.set(1080,800);

    }

    public void swapColor(){
        mPaintBackground.setColor(mPaintBackground.getColor() == Color.GRAY ? Color.DKGRAY : Color.GRAY);
        mPaintText.setColor(mPaintText.getColor() == Color.DKGRAY ? Color.GRAY : Color.DKGRAY);
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {

        canvas.drawRect(0, 0, getWidth(), getHeight(), mPaintBackground); // Фон

        drawAngLines(canvas); //Нанесення косих ліній

        mPaintText.setTextSize(30);
        canvas.drawText(getWidth()+"x"+getHeight(), 20, 50, mPaintText); //Text (Розмір екрану)

// Створення кривої графіку

        mPaint.reset();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(5);

        path.moveTo(p0.x, p0.y);
        path.cubicTo((p0.x+p1.x)/2, p0.y, (p0.x+p1.x)/2, p1.y, p1.x, p1.y);
        path.cubicTo((p1.x+p2.x)/2, p1.y, (p1.x+p2.x)/2, p2.y, p2.x, p2.y);
        path.cubicTo((p2.x+p3.x)/2, p2.y, (p2.x+p3.x)/2, p3.y, p3.x, p3.y);
        path.cubicTo((p3.x+p4.x)/2, p3.y, (p3.x+p4.x)/2, p4.y, p4.x, p4.y);
        path.cubicTo((p4.x+p5.x)/2, p4.y, (p4.x+p5.x)/2, p5.y, p5.x, p5.y);
        path.cubicTo((p5.x+p6.x)/2, p5.y, (p5.x+p6.x)/2, p6.y, p6.x, p6.y);

        canvas.drawPath(path, mPaint);


// Нанесення точок на графік
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(p0.x, p0.y, 8f, mPaint);
        canvas.drawCircle(p1.x, p1.y, 8f, mPaint);
        canvas.drawCircle(p2.x, p2.y, 8f, mPaint);
        canvas.drawCircle(p3.x, p3.y, 8f, mPaint);
        canvas.drawCircle(p4.x, p4.y, 8f, mPaint);
        canvas.drawCircle(p5.x, p5.y, 8f, mPaint);
        canvas.drawCircle(p6.x, p6.y, 8f, mPaint);

        path.reset();
        mPaint.reset();

    }

//создание косых линий
    public void drawAngLines (Canvas canvas){
        for(int i = 0; i < getHeight() + getWidth(); i=i+20){
            canvas.drawLine(-getHeight() + i, 0, 0 + i, getHeight(), mLinesPaint);
        }
    }

//Обробка натисків
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:{
                return true;
            }
            case MotionEvent.ACTION_MOVE: {

                float x1 = event.getX();
                float y1 = event.getY();
                float x2 = event.getX();
                float y2 = event.getY();
                float x3 = event.getX();
                float y3 = event.getY();
                float x4 = event.getX();
                float y4 = event.getY();
                float x5 = event.getX();
                float y5 = event.getY();

                double dx1 = Math.pow(x1 - p1.x, 2);
                double dy1 = Math.pow(y1 - p1.y, 2);
                double dx2 = Math.pow(x2 - p2.x, 2);
                double dy2 = Math.pow(y2 - p2.y, 2);
                double dx3 = Math.pow(x3 - p3.x, 2);
                double dy3 = Math.pow(y3 - p3.y, 2);
                double dx4 = Math.pow(x4 - p4.x, 2);
                double dy4 = Math.pow(y4 - p4.y, 2);
                double dx5 = Math.pow(x5 - p5.x, 2);
                double dy5 = Math.pow(y5 - p5.y, 2);


                if (dx1 + dy1 < Math.pow(100, 2)){
                    p1.set(180, (int)y1);
                    invalidate();
                    return true;
                }

                if (dx2 + dy2 < Math.pow(100, 2)){
                    p2.set(360, (int)y2);
                    invalidate();
                    return true;
                }

                if (dx3 + dy3 < Math.pow(100, 2)){
                    p3.set(540, (int)y2);
                    invalidate();
                    return true;
                }

                if (dx4 + dy4 < Math.pow(100, 2)){
                    p4.set(720, (int)y2);
                    invalidate();
                    return true;
                }

                if (dx5 + dy5 < Math.pow(100, 2)){
                    p5.set(900, (int)y2);
                    invalidate();
                    return true;
                }

                return value;
            }
        }

        return value;
    }

    @Override
    public void onClick(View v) {

    }
}
