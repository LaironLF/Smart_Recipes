package com.laironlf.smartRecipes.presentation.utils


import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.textfield.TextInputEditText
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetIngredients.SheetDialogIngredients.Companion.TAG
import com.shawnlin.numberpicker.NumberPicker

fun TabLayout.bindTwoWay(
    liveData: MutableLiveData<Int>,
    lifecycleOwner: LifecycleOwner,
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
fun TextInputEditText.bindTwoWay(
    liveData: MutableLiveData<String>,
    lifecycleOwner: LifecycleOwner,
    callback: ((String) -> Unit)? = null
){
    this.addTextChangedListener {
        callback?.invoke(it.toString())
        liveData.value = it.toString()
    }
    liveData.observe(lifecycleOwner) { text ->
        if (this.text.toString() == text) return@observe
        this.setText(text)
    }
}

fun NumberPicker.bindTwoWay(
    liveData: MutableLiveData<Int>,
    lifecycleOwner: LifecycleOwner,
    callback: ((Int) -> Unit)? = null
) {
    this.setOnValueChangedListener { _, _, newVal ->
        liveData.value = newVal
        callback?.invoke(newVal)
    }
    liveData.observe(lifecycleOwner) { value ->
        if(this.value == value) return@observe
        this.value = value
    }
}

fun SearchView.bindTwoWay(
    liveData: MutableLiveData<String>,
    lifecycleOwner: LifecycleOwner,
    callback: ((String) -> Unit)? = null
){
    Log.d(TAG, "bind Query: ")

    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            callback?.let { it(newText?: "") }
            liveData.value = newText ?: ""
            Log.d(TAG, "onQueryTextChange: ")
            return true
        }
    })
    liveData.observe(lifecycleOwner) { text ->
        if (this.query == text) return@observe
        this.setQuery(text, false)
    }
}