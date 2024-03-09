package com.example.wss;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class OnboardingFragment extends Fragment {
    //Класс-страница для Onboarding
    //09.03.2024
    //Нуркаев Альфир Х.
    View view;
    int img;
    String text;
    String text2;
    int page;

    public OnboardingFragment(int img, String text, String text2, int page) {
        this.img = img;
        this.text = text;
        this.text2 = text2;
        this.page = page;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.onboarding_fragment, container, false);
        ImageView iv = (ImageView) view.findViewById(R.id.onboarding_iv);
        TextView tv = (TextView) view.findViewById(R.id.onboarding_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.onboarding_tv2);
        iv.setImageResource(img);
        tv.setText(text);
        tv2.setText(text2);
        return view;
    }
}
