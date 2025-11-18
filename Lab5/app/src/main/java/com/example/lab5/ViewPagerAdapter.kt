package com.example.lab5

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

// ViewPagerAdapter 繼承 FragmentStateAdapter 類別
// 傳遞 FragmentManager 和 Lifecycle 物件
class ViewPagerAdapter(
    fm: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    // 將要顯示的 Fragment 儲存在一個列表中
    private val fragments: List<Fragment> = listOf(
        FirstFragment(),
        SecondFragment(),
        ThirdFragment()
    )

    // 回傳 Fragment 的數量
    override fun getItemCount(): Int = fragments.size

    // 根據 position 位置回傳對應的 Fragment
    override fun createFragment(position: Int): Fragment = fragments[position]
}