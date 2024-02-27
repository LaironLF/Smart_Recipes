package com.laironlf.smartRecipes.presentation.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

fun TabLayout.bindTwoWay(
    liveData: MutableLiveData<Int>, lifecycleOwner: LifecycleOwner,
    callback: (() -> Unit)? = null) {
    this.addOnTabSelectedListener(object : OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            liveData.value = tab?.position
            callback?.let { it() }
        }
        override fun onTabUnselected(tab: TabLayout.Tab?) {}
        override fun onTabReselected(tab: TabLayout.Tab?) {}
    })
    liveData.observe(lifecycleOwner) { pos ->
        if(this.selectedTabPosition == pos) return@observe
        this.selectTab(this.getTabAt(pos))
    }
}