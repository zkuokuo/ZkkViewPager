
ZkkViewPager

    使用步骤:
    1.在工程gradle的allprojects节点下添加   maven { url 'https://jitpack.io' } 如下

    allprojects {

        repositories {
            jcenter()
            maven { url 'https://jitpack.io' }
        }
    }

    2.在项目目录的build.gradle下添加依赖

    compile 'com.github.zkuokuo:ZkkViewPager:1.0'

3.  简单几句话就可以实现无限轮播广告条,如下

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
            
        4.
         (1)setcontext()这个方法是设置上下文
         (2)setdatas()方法是设置数据,传递的是一个list集合,注意这个集合中的数据模型bean要实现ZkkDataBean
             示例如下:

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

      (3)setView(vp, llDot, tvTitle)方法是传递zkkViewPager,存放小圆点的容器linearlayout,还有设置标题的textview
      (4)setDefualtImage(R.drawable.defualt)设置的是默认加载的图片,网络加载失败显示的图片
      (5)setOnPageClickListener(new ZkkVp.OnClickListener(){...}是给广告条设置点击事件

      5.布局的实现示例代码:

         <com.zkkvp.ZkkViewPager
          android:id="@+id/viewPager"
          android:layout_width="match_parent"
          android:layout_height="150dp"/>
