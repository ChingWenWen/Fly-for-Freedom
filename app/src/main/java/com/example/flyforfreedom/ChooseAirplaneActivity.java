package com.example.flyforfreedom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ChooseAirplaneActivity extends AppCompatActivity {

    private ViewPager viewPager;

    //三個view
    private View view1;
    private View view2;
    private View view3;

    //用來存放view並傳遞給viewPager的介面卡。
    private ArrayList<View> pageview;


    //用來存放圓點，沒有寫第四步的話，就不要定義一下三個變量了。
    private ImageView[] tips = new ImageView[3];

    private ImageView imageView;

    //圓點組的物件
    private ViewGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_airplane);
        //將view加進pageview中
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        view1 = getLayoutInflater().inflate(R.layout.view1, null);
        view2 = getLayoutInflater().inflate(R.layout.view2, null);
        view3 = getLayoutInflater().inflate(R.layout.view3, null);
        pageview = new ArrayList<View>();
        pageview.add(view1);
        pageview.add(view2);
        pageview.add(view3);

        //viewPager下面的圓點，ViewGroup
        group = (ViewGroup) findViewById(R.id.viewGroup);
        tips = new ImageView[pageview.size()];
        for (int i = 0; i < pageview.size(); i++) {
            imageView = new ImageView(ChooseAirplaneActivity.this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageView.setPadding(20, 0, 20, 0);
            tips[i] = imageView;

            //預設第一張圖顯示為選中狀態
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.bluepoint);
            } else {
                tips[i].setBackgroundResource(R.drawable.graypoint);
            }

            group.addView(tips[i]);
        }
        //這裡的mypagerAdapter是第三步定義好的。
        viewPager.setAdapter(new mypagerAdapter(pageview));
        //這裡的GuiPageChangeListener是第四步定義好的。
        viewPager.addOnPageChangeListener(new GuidePageChangeListener());
    }

    class mypagerAdapter extends PagerAdapter {
        private ArrayList<View> pageview1;

        public mypagerAdapter(ArrayList<View> pageview1) {
            this.pageview1 = pageview1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d("MainActivityDestroy", position + "");
            if (pageview1.get(position) != null) {
                container.removeView(pageview1.get(position));
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pageview1.get(position));
            Log.d("MainActivityInstanti", position + "");
            return pageview1.get(position);
        }

        @Override
        public int getCount() {
            return pageview1.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return object == view;
        }

    }

    class GuidePageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }


        @Override
        //切換view時，下方圓點的變化。
        public void onPageSelected(int position) {
            tips[position].setBackgroundResource(R.drawable.bluepoint);
            //這個圖片就是選中的view的圓點
            for (int i = 0; i < pageview.size(); i++) {
                if (position != i) {
                    tips[i].setBackgroundResource(R.drawable.graypoint);
                    //這個圖片是未選中view的圓點
                }
            }
        }
    }
}
