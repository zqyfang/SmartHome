package com.tjs.smarthome.factory;

import android.support.v4.util.SparseArrayCompat;
import com.tjs.smarthome.base.BaseFragment;
import com.tjs.smarthome.fragment.AnFangFragment;
import com.tjs.smarthome.fragment.HomeFragment;
import com.tjs.smarthome.fragment.QuYuFragment;
import com.tjs.smarthome.fragment.SheBeiFragment;

/**
 * fragment的工厂类
 */

public class FragmentFactory {
    static SparseArrayCompat<BaseFragment> cachesChartFragment = new SparseArrayCompat<>();

    /**
     * @param position
     * @return
     */
    public static BaseFragment getChartFragment(int position) {
        BaseFragment fragment = null;
        BaseFragment tempFragment = cachesChartFragment.get(position);
        if (tempFragment != null) {
            fragment = tempFragment;
            return fragment;
        }
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new QuYuFragment();
                break;
            case 2:
                fragment = new SheBeiFragment();
                break;
            case 3:
                fragment = new AnFangFragment();
                break;
        }
        cachesChartFragment.put(position, fragment);
        return fragment;
    }
}
