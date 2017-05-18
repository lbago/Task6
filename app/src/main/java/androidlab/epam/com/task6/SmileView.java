package androidlab.epam.com.task6;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;


public class SmileView extends View {
    public int colorFace;
    public int colorEyeAndMouth;

    private Paint circlePaint;
    private Paint eyeAndMouthPaint;

    private boolean touchOn;

    private float centerX;
    private float centerY;
    private float radius;
    private float width;

    private RectF arcBounds = new RectF();

    public SmileView(Context context) {
        this(context, null);
    }

    public SmileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SmileView, 0, 0);
        try {
            colorFace = a.getColor(R.styleable.SmileView_colorFace, Color.GRAY);
            colorEyeAndMouth = a.getColor(R.styleable.SmileView_colorEyesAndMouth, Color.BLACK);
        } finally {
            a.recycle();
        }
        initPaints();
    }

    public SmileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //инициализация кистей
    private void initPaints() {
        circlePaint = new Paint();
        circlePaint.setColor(colorFace);
        eyeAndMouthPaint = new Paint();
        eyeAndMouthPaint.setColor(colorEyeAndMouth);
        eyeAndMouthPaint.setStrokeWidth(width);
    }

    // Размеры изображения
    //Необходимо для того,чтобы родительский контейнер мог корректо отобразить изображение
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(w, h);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // рисуем лицо
        canvas.drawCircle(centerX, centerY, radius, circlePaint);
        // рисуем глаза
        canvas.drawCircle(centerX - radius / 3f, centerY - radius / 3f, radius / 8f, eyeAndMouthPaint);
        canvas.drawCircle(centerX + radius / 3f, centerY - radius / 3f, radius / 8f, eyeAndMouthPaint);
        // рисуем рот
        if (touchOn == true) {
            arcBounds.set(radius / 3f, radius / 3f, radius * 2 - radius / 3f, radius * 2 - radius / 3f);
            canvas.drawArc(arcBounds, 45f, 90f, false, eyeAndMouthPaint);
        } else if (touchOn == false) {
            arcBounds.set(radius / 2f, centerY + radius / 4f, 3 * radius / 2, centerY + radius);
            canvas.drawArc(arcBounds, 180f, 180f, false, eyeAndMouthPaint);
        }
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        centerX = w / 2f;
        centerY = h / 2f;
        radius = Math.min(w, h) / 2f;
        width = w / 100f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (touchOn == true) touchOn = false;
            else if (touchOn == false) touchOn = true;
            invalidate();
        }
        return true;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putBoolean("Face", touchOn);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            touchOn = bundle.getBoolean("Face");
            state = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(state);
    }
}
