package com.example.myviewerpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val NUM_ITEMS = 3

    override fun getCount(): Int {
        return  NUM_ITEMS
    }


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FirstFragment(0, "Page # 1")
            1 -> FirstFragment(1, "Page # 2")
            else -> SecondFragment(2, "Page # 3")
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

}