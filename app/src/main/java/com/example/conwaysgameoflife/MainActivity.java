package com.example.conwaysgameoflife;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements ViewTreeObserver.OnGlobalLayoutListener,
        View.OnTouchListener {

    @BindView(R.id.canvasView) public ImageView imageView;
    @BindView(R.id.value) public TextView valueDisplay;

    private Bitmap mBitmap;
    private Canvas mCanvas;

    private SquareDrawingEngine engine;

    private float xDown;
    private float yDown;

    private float xUp;
    private float yUp;

    private float xMove;
    private float yMove;

    int SIZE;
    boolean[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        int cellSize = 20;
        cells = new boolean[cellSize][cellSize];
        for (int row = 0; row < cellSize; row ++) {
            for (int col = 0; col < cellSize; col++) {
                cells[row][col] = Math.random() < .5;
            }
        }

        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        float xx = motionEvent.getX();
        float yy = motionEvent.getY();

        String line1 = "size: " + SIZE + " x: " + ((int)Math.floor(xx)) + " y: " + ((int)Math.floor(yy));
        String line2 = "Col X = " + ((int)Math.floor(xx / SIZE)) + " \n Row Y = " + ((int)Math.floor(yy / SIZE));

        valueDisplay.setText(line1 + "\n" + line2);

        if (action == MotionEvent.ACTION_DOWN) {
            Log.d("ACTION", "down");
            xDown = xx;
            yDown = yy;

            return true;

        } else if (action == MotionEvent.ACTION_UP) {
            Log.d("ACTION", "up");
            xUp = xx;
            yUp = yy;

            return true;

        } else if (action == MotionEvent.ACTION_MOVE) {
            Log.d("ACTION", "move");
            xMove = xx;
            yMove = yy;
        }

        return false;
    }

    @Override
    public void onGlobalLayout() {
        initBitmap();
    }

    public void initBitmap () {
        imageView.setOnTouchListener(this);

        int width = imageView.getWidth();
        int height = imageView.getHeight();

        Log.d("DIMENSIONS", "" + width + "x" + height + "y");

        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);

        drawGrid();
    }

    public void drawGrid() {
        int height = imageView.getHeight();
        int width = imageView.getWidth();
        int smallest = Math.min(width, height);
        SIZE = smallest/cells.length;

        float x0 = 0;
        float y0 = 0;

        float x1 = SIZE;
        float y1 = SIZE;


        for (int row = 0; row < cells.length; row++) {
            x0 = 0;
            x1 = SIZE;

            for (int col = 0; col < cells[row].length; col++) {
                int color;

                if (cells[row][col] == true) {
                    //Steve has this one white
                    color = Color.BLACK;
                } else {
                    color = Color.WHITE;
                }

                Paint brush = new Paint(Paint.ANTI_ALIAS_FLAG);
                brush.setColor(color);
                mCanvas.drawRect(x0, y0, x1, y1, brush);

                //update to the next column
                x0 += SIZE;
                x1 += SIZE;
            }

            //update the row
            y0 += SIZE;
            y1 += SIZE;
        }
        imageView.setImageBitmap(mBitmap);
    }


}