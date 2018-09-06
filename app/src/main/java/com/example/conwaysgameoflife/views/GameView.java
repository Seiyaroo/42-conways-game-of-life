package com.example.conwaysgameoflife.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.example.conwaysgameoflife.GameWorld;
import com.example.conwaysgameoflife.models.Cell;

public class GameView extends SurfaceView implements Runnable {
    public static final int DEFAULT_SIZE = 100;
    //Default color is white for a living square
    public static final int DEFAULT_LIVING_COLOR = Color.RED;
    //Default color of a dead square.
    public static final int DEFAULT_DEAD_COLOR = Color.BLUE;

    private Thread thread;

    private boolean isRunning = false;
    private int columnWidth = 1;
    private int rowHeight = 1;
    private int nbColumns = 1;
    private int nbRows = 1;

    private GameWorld world;

    private Rect rect = new Rect();
    private Paint paint = new Paint();

    public GameView(Context context) {
        super(context);
        initWorld();
    }

    public GameView(Context context, AttributeSet attributes) {
        super(context, attributes);
        initWorld();
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!getHolder().getSurface().isValid())
                continue;

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
            }
            Canvas canvas = getHolder().lockCanvas();
            world.nextGen();
            drawCells(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        isRunning = false;

        while (true) {
            try {
                thread.join();
            } catch (InterruptedException e) {
            }

            break;
        }
    }

    private void initWorld() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);

        nbColumns = point.x / DEFAULT_SIZE;
        nbRows = point.y / DEFAULT_SIZE;

        columnWidth = point.x / nbColumns;
        rowHeight = point.y / nbRows;

        world = new GameWorld(nbColumns, nbRows);

    }

    private void drawCells(Canvas canvas) {
        for (int i = 0; i < nbColumns; i++) {
            for (int j = 0; j < nbRows; j++) {
                Cell cell = GameWorld.get(i, j);
                rect.set((cell.x * columnWidth) - 1, (cell.y * rowHeight) - 1,
                        (cell.x * columnWidth + columnWidth) - 1,
                        (cell.y * rowHeight + rowHeight) - 1);
                paint.setColor(cell.live ? DEFAULT_LIVING_COLOR : DEFAULT_DEAD_COLOR);
                canvas.drawRect(rect, paint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int i = (int) (event.getX() / columnWidth);
            int j = (int) (event.getY() / rowHeight);

            Cell cell = world.get(i, j);

            cell.inverted();

            invalidate();
        }

        return super.onTouchEvent(event);
    }
}
