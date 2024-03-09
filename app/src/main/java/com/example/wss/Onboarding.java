package com.example.wss;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;
import java.util.List;

public class Onboarding extends AppCompatActivity {
    //Класс обертка для трех страниц
    //09.03.2024
    //Нуркаев Альфир Х.
    public static List<OnboardingFragment> list;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.onboarding);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        vp = (ViewPager) findViewById(R.id.onboarding_vp);
        Adapter adapter = new Adapter(getSupportFragmentManager());
        vp.setAdapter(adapter);

        RelativeLayout rl = (RelativeLayout) findViewById(R.id.onboarding_rl);
        LinearLayout ll = (LinearLayout) findViewById(R.id.onboarding_ll);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Проверка на то, какая сейчас страница. Тут скрываются и показываются нижние кнопки
                if (position == 2) {
                    rl.setVisibility(View.GONE);
                    ll.setVisibility(View.VISIBLE);
                } else {
                    rl.setVisibility(View.VISIBLE);
                    ll.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void skip(View view) {
        //Обработка нажатия на кнопку "skip"
        save();
        Intent i = new Intent(this, Holder.class);
        startActivity(i);
        finish();
    }

    public void next(View view) {
        //Обработка нажатия на кнопку "next"
        vp.setCurrentItem(vp.getCurrentItem() + 1);
    }

    public void sign_up(View view) {
        //Обработка нажатия на текст "sing_up"
        Intent i = new Intent(this, Holder.class);
        startActivity(i);
        finish();
    }

    public void sign_in(View view) {
        //Обработка нажатия на кнопку "sign_in"
        Intent i = new Intent(this, Holder.class);
        startActivity(i);
        finish();
    }

    public void save() {
        //Сохранение информации о том, что когда-то была нажата кнопка "skip"
        SharedPreferences sp = getSharedPreferences("wsr", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("is", 1);
        editor.apply();
    }


    public void fillList() {
        //Заполнение листа
        list = new ArrayList<>();
        list.add(new OnboardingFragment(R.drawable.onboarding_img_1, "Quick Delivery At Your Doorstep", "Enjoy quick pick-up and delivery to your destination", 0));
        list.add(new OnboardingFragment(R.drawable.onboarding_img_2, "Flexible Payment", "Different modes of payment either before and after delivery without stress", 1));
        list.add(new OnboardingFragment(R.drawable.onboarding_img_3, "Real-time Tracking", "Track your packages/items from the comfort of your home till final destination", 2));
    }

    class Adapter extends FragmentPagerAdapter {
        //Адаптер для ViewPager'a
        public Adapter(@NonNull FragmentManager fm) {
            super(fm);
            fillList();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
