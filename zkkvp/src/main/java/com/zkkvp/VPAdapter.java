package com.zkkvp;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 作者:dick
 * 邮箱:dick.zhang@inibiru.com
 * 创建日期: 2017/4/20
 * 描述:
 */

public class VPAdapter extends PagerAdapter {

    public List<ZkkDataBean> datas;
    private Context context;
    private int defualtImage;

    public void setDefualtImage(int defualtImage) {
        this.defualtImage = defualtImage;
    }

    public VPAdapter(List<ZkkDataBean> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getCount() {
        return datas != null ? Integer.MAX_VALUE : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        position = position % datas.size();
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);//设置图片填充的方式
        //获取网络加载图片的路径

        Picasso.with(context)
                .load(datas.get(position).getImgUrl())
                .error(defualtImage != 0 ? defualtImage : R.drawable.defualt)
                .into(imageView);

        container.addView(imageView);
        return imageView;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
