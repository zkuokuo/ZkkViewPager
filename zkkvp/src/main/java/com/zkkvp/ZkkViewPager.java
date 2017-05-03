package com.zkkvp;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者:zkk
 * 公司:itheima
 * 邮箱:15671555534@163.com
 * 描述:
 */

public class ZkkViewPager extends ViewPager {
    private int mDownx;
    private int mDowny;
    private ZkkVp zkkvp;

    public void setZkkVp(ZkkVp zkkvp) {
        this.zkkvp = zkkvp;
    }

    public ZkkViewPager(Context context) {
        this(context, null);
    }

    public ZkkViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private long downTime;
    private int pageInterval = 400;
    private long upTime;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downTime = System.currentTimeMillis();
                mDownx = (int) ev.getX();
                mDowny = (int) ev.getY();
                if (zkkvp != null) {
                    zkkvp.stopMove();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (ev.getX() - mDownx);
                int dy = (int) (ev.getY() - mDowny);
                if (Math.abs(dx) > Math.abs(dy)) {
                    //请求外层控件不要拦截事件
                    requestDisallowInterceptTouchEvent(true);
                }
                break;

            case MotionEvent.ACTION_UP:
                upTime = System.currentTimeMillis();
                if (zkkvp != null) {
                    zkkvp.startMove();
                }
                //点击事件
                int upx = (int) ev.getX();
                int upy = (int) ev.getY();
                if (mDownx == upx && mDowny == upy && upTime - downTime < pageInterval) {
                    if (listener != null) {
                        listener.onclick();
                    }
                    return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private OnPagerItemClickListener listener;

    public void setOnPagerItemClickListener(OnPagerItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnPagerItemClickListener {
        void onclick();
    }
}
