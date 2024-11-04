package com.example.flagsandcapitals;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class SimpleFragmentPagerAdapter extends PagerAdapter {
    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

  /*  @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new FirstFragment();
        } else if (position == 1) {
            return new HomeFragment();
        } else {
            return new TheardFragment();
        }
    }*/

    @Override
    public int getCount() {
        return 3;
    }
}