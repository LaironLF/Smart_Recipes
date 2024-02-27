package com.laironlf.circlemenu

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GravityCompat
import androidx.customview.widget.ViewDragHelper
import androidx.drawerlayout.widget.DrawerLayout
import kotlin.math.abs
import kotlin.math.max


class BetterDrawerLayout : DrawerLayout {

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    )
    private var mDragging = false
    private var cancelHandle = false
    private var initX = 0f
    private var initY = 0f
//    private lateinit var mDraggerLeft: ViewDragHelper

    init {
//        val leftDraggerField = DrawerLayout::class.java.getDeclaredField("mLeftDragger")
//        leftDraggerField.isAccessible = true
//        mDraggerLeft = leftDraggerField[this] as ViewDragHelper
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        when(ev?.action){
            MotionEvent.ACTION_DOWN ->{
                initX = ev.rawX
                initY = ev.rawY
            }
            MotionEvent.ACTION_MOVE -> {
//                Log.d(TAG, "onTouchEvent:$initX ${ev.rawX} $mDragging")
                if(abs(initY - ev.rawY) > 20 && !cancelHandle && !mDragging) cancelHandle = true
                if (cancelHandle) return super.onTouchEvent(ev)
                if (ev.rawX - initX > 60 && !mDragging) {
                    this.openDrawer(GravityCompat.START)
                    mDragging = true
                }
                if (mDragging) return super.onTouchEvent(ev)
                return true
            }
            else -> {
                mDragging = false
                cancelHandle = false
            }
        }
        return super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
         if(this.isOpen){
            for(i in 0 until childCount){
                val view = getChildAt(i)
                if(view is CircleMenu) return !view.dispatchTouchEvent(ev)
            }
            return true
        } else{
            return super.onInterceptTouchEvent(ev)
        }
    }


}