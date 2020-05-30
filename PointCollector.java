package net.mobilcoder.notebook;

import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PointCollector implements View.OnTouchListener {
    private PointCollectorListener listener;
    private List<Point> points=new ArrayList<Point>();
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int x=Math.round(event.getX());
        int y=Math.round(event.getY());
        String message =String.format("Condinates :(%d,%d)",x,y);
        Log.d(Notebook.DEBUGTAG, message);
        points.add(new Point(x,y));
        if (points.size()==4)
        {
            if (listener!=null){
                listener.pointCollected(points);
                points.clear();
            }

        }
        return false;
    }

    public void setListener(PointCollectorListener listener) {
        this.listener = listener;
    }
}
