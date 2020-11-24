package com.example.pts3;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.pts3.Group_fragment.GroupesFragment;
import com.example.pts3.Quizz_fragment.QuizzFragment;
import com.example.pts3.Student_fragment.EtudiantsFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){

        super(fm);
        this.numOfTabs = numOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new EtudiantsFragment();
            case 1:
                return new GroupesFragment();
            case 2:
                return new QuizzFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
