package com.zkkviewpager;

import com.zkkvp.ZkkDataBean;

/**
 * 作者:dick
 * 邮箱:dick.zhang@inibiru.com
 * 创建日期: 2017/5/2
 * 描述:
 */

public class VPBean implements ZkkDataBean {

    private String title;
    private String imgUrl;
    private int size;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
