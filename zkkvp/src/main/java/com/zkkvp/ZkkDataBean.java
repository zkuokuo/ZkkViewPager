package com.zkkvp;

/**
 * 作者:dick
 * 邮箱:dick.zhang@inibiru.com
 * 创建日期: 2017/5/2
 * 描述:
 */

public interface ZkkDataBean {

    abstract String getTitle();

    abstract String getImgUrl();

    abstract void setTitle(String title);

    abstract void setImgUrl(String url);
}
