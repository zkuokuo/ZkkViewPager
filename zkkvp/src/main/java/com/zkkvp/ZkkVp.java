package com.zkkvp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * 作者:dick
 * 邮箱:dick.zhang@inibiru.com
 * 创建日期: 2017/5/2
 * 描述:实现自动轮播的广告条
 * 可以点击触发点击事件,长按停止轮播,松开继续轮播
 */
public class ZkkVp<T> {
    private Context context;
    private ZkkViewPager vp;
    private List<ZkkDataBean> datas;
    private TextView tvTitle;
    private LinearLayout llDot;
    private VPAdapter vpAdapter;
    private static final int SHOW_NEXTPAGER = 0;

    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SHOW_NEXTPAGER:
                    shownewPager();
                    break;
            }
        }
    };

    public  static  ZkkVp builder() {
        ZkkVp zkkVp=new ZkkVp();
        return zkkVp;
    }

    public ZkkVp setContext(Context context) {
        this.context = context;
        return this;
    }

    public ZkkVp setDatas(List<ZkkDataBean> datas) {

        this.datas = datas;

        return this;
    }

    public ZkkVp setView(ZkkViewPager vp, LinearLayout llDot, TextView tvTitle) {
        this.vp = vp;
        this.tvTitle = tvTitle;
        this.llDot = llDot;
        initVp();
        return this;
    }

    public ZkkVp setDefualtImage(int imageid) {
        if (vpAdapter != null) {
            vpAdapter.setDefualtImage(imageid);
        }
        return this;
    }

    /**
     * @des 初始化广告条
     */
    protected void initVp() {
        if (vp == null) {
            return;
        }
        try {
            vpAdapter = new VPAdapter(datas, context);
            vp.setAdapter(vpAdapter);
            //初始化小圆点
            initPoint();
            //改变小圆点
            changeDot(0);
            //给viewpager设置页面改变监听事件
            vp.setOnPageChangeListener(mOnPageChangeListener);
            //设置最初显示的小圆点的位置
            vp.setCurrentItem(10000);
            handler.removeCallbacksAndMessages(null);
            handler.sendEmptyMessageDelayed(SHOW_NEXTPAGER, 3000);
            vp.setZkkVp(ZkkVp.this);
            //设置一个点击回调
            vp.setOnPagerItemClickListener(new ZkkViewPager.OnPagerItemClickListener() {
                @Override
                public void onclick() {
                    int currentItem = vp.getCurrentItem();
                    if (datas != null && datas.size() > 0) {
                        int position = currentItem % datas.size();
                        //点击viewpager跳转页面
                        if (listener != null) {
                            listener.onclick(position);
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public OnClickListener listener;

    public ZkkVp setOnPageClickListener(OnClickListener listener) {
        this.listener = listener;
        return this;
    }

    public interface OnClickListener {
        void onclick(int position);
    }

    /**
     * @des 显示下一页
     */
    protected void shownewPager() {
        if (vp != null) {
            int currentItem = vp.getCurrentItem();
            vp.setCurrentItem(currentItem + 1);
            handler.sendEmptyMessageDelayed(SHOW_NEXTPAGER, 3000);
        }
    }

    /**
     * @des 给viewpager设置监听
     */
    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        //当页面滚动的时候调用的方法
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        //当页面被选择时调用的方法
        @Override
        public void onPageSelected(int position) {
            position = position % datas.size();
            changeDot(position);
        }

        //当页面滚动状态发生改变的时候调用的方法
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * @des 初始化viewpager的小圆点
     */
    protected void initPoint() {
        llDot.removeAllViews();
        for (int i = 0; i < datas.size(); i++) {
            View dot = new View(context);
            dot.setBackgroundResource(R.drawable.selector_dot);
            int dotSize = DensityUtil.dip2px(context, 8);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotSize, dotSize);
            params.leftMargin = i == 0 ? 0 : 10;
            dot.setLayoutParams(params);
            llDot.addView(dot);
        }
    }

    /**
     * @des 改变小圆点
     */
    protected void changeDot(int position) {
        tvTitle.setText(datas.get(position).getTitle() + "");
        // 把position位置的点设置为selected状态
        for (int i = 0; i < llDot.getChildCount(); i++) {
            llDot.getChildAt(i).setSelected(i == position);
        }
    }

    /**
     * 停止轮播
     */
    protected void stopMove() {
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 开始轮播
     */
    protected void startMove() {
        handler.sendEmptyMessageDelayed(SHOW_NEXTPAGER, 1000);
    }

}
