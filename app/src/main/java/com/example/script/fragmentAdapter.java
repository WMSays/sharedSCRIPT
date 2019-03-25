package com.example.script;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class fragmentAdapter extends FragmentPagerAdapter {
    public fragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new yourpaper();

            case 1:
                return new uploadfile();
            case 2:
                return new chooseSupervisor();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}

