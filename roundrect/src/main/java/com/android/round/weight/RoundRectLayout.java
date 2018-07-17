package com.android.round.weight;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.android.round.R;

/**
 * Created by xiangyi.wu on 2018/7/16.
 *
 * @descripyion:
 */

public class RoundRectLayout extends RelativeLayout {

    private RectF mRectF;//绘制画布的大小
    public float[] mRadii = new float[8];//绘制眼角矩形所需的8个角
    private boolean mCircle;//是否是绘制圆形
    private int mRectCorner; //圆角弧度
    private int mStrokeColor;// 描边颜色
    private int mStrokeWidth;// 描边宽度

    public RoundRectLayout(Context context) {
        this(context, null);
    }

    public RoundRectLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundRectLayout);
        mCircle = array.getBoolean(R.styleable.RoundRectLayout_round_rect_circle, false);
        mRectCorner = array.getDimensionPixelSize(R.styleable.RoundRectLayout_round_rect_corner, 0);
        int roundRectTopLeft = array.getDimensionPixelSize(R.styleable.RoundRectLayout_round_rect_corner_top_to_left, -1);
        int roundRectTopRight = array.getDimensionPixelSize(R.styleable.RoundRectLayout_round_rect_corner_top_to_right, -1);
        int roundRectBottomLeft = array.getDimensionPixelSize(R.styleable.RoundRectLayout_round_rect_corner_bottom_to_left, -1);
        int roundRectBottomRight = array.getDimensionPixelSize(R.styleable.RoundRectLayout_round_rect_corner_bottom_to_right, -1);

        mRadii[0] = roundRectTopLeft == -1 ? mRectCorner : roundRectTopLeft;
        mRadii[1] = roundRectTopLeft == -1 ? mRectCorner : roundRectTopLeft;
        mRadii[2] = roundRectTopRight == -1 ? mRectCorner : roundRectTopRight;
        mRadii[3] = roundRectTopRight == -1 ? mRectCorner : roundRectTopRight;
        mRadii[4] = roundRectBottomLeft == -1 ? mRectCorner : roundRectBottomLeft;
        mRadii[5] = roundRectBottomLeft == -1 ? mRectCorner : roundRectBottomLeft;
        mRadii[6] = roundRectBottomRight == -1 ? mRectCorner : roundRectBottomRight;
        mRadii[7] = roundRectBottomRight == -1 ? mRectCorner : roundRectBottomRight;

        mStrokeColor = array.getColor(R.styleable.RoundRectLayout_round_rect_stroke_color, Color.WHITE);
        mStrokeWidth = array.getDimensionPixelSize(R.styleable.RoundRectLayout_round_rect_stroke_width, 0);
        array.recycle();

        mRectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.left = getPaddingLeft();
        mRectF.top = getPaddingTop();
        mRectF.right = w - getPaddingRight();
        mRectF.bottom = h - getPaddingBottom();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(mRectF, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        Path path = new Path();
        path.reset();
        //判断是否绘制圆
        if (mCircle) {
            float width = (mRectF.right - mRectF.left) > (mRectF.bottom - mRectF.top) ? mRectF.bottom - mRectF.top : mRectF.right - mRectF.left;
            path.addCircle((mRectF.right - mRectF.left) / 2, (mRectF.bottom - mRectF.top) / 2, (width - mStrokeWidth) / 2, Path.Direction.CW);
        } else {
            path.addRoundRect(mRectF, mRadii, Path.Direction.CW);
        }


        Paint paint = new Paint();
        paint.setAntiAlias(true);

        //绘制描边
        if (mStrokeWidth > 0) {
            paint.setColor(mStrokeColor);
            paint.setStrokeWidth(mStrokeWidth);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(path, paint);
        }

        //剪切圆角矩形
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
    }
}
