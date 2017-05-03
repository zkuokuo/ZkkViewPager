package com.zkkviewpager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zkkvp.ZkkViewPager;
import com.zkkvp.ZkkVp;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.viewPager)
    ZkkViewPager vp;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_dot)
    LinearLayout llDot;
    public List<VPBean> vpBeenlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        String path = "http://112.124.67.116:8080/NibiruVideo/download/banner/11669795400441702" +
                ".jpg";
        for (int i = 0; i < 5; i++) {
            VPBean vpBean = new VPBean();
            vpBean.setImgUrl(path);
            vpBean.setTitle("title: " + i);
            vpBeenlist.add(vpBean);
        }
        initView();
    }

    /**
     * 初始化自动轮播广告条
     */
    private void initView() {

           ZkkVp.builder()
                .setContext(this)
                .setDatas(vpBeenlist)
                .setView(vp, llDot, tvTitle)
                .setDefualtImage(R.drawable.defualt)
                .setOnPageClickListener(new ZkkVp.OnClickListener() {
                    @Override
                    public void onclick(int position) {
                        Toast.makeText(MainActivity.this, "我被点击了" + position, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }
}
