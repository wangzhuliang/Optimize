package wang.cn.com.optimize.ui.widget.imagewatcher;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-09
 * @time: 16:07
 */
public class WangRecyclerView extends RecyclerView {

    public WangRecyclerView(Context context) {
        this(context,null);
    }

    public WangRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WangRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_MOVE:
                if (x != 0){
                    return false;
                }
                return true;
            default:
                break;
        }
        return super.onInterceptTouchEvent(e);
    }
}
