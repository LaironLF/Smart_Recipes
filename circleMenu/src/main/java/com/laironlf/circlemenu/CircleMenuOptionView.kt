package com.laironlf.circlemenu

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Checkable
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.constraintlayout.widget.ConstraintLayout

@SuppressLint("Recycle", "CustomViewStyleable")
class CircleMenuOptionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs), Checkable {

    private var mOptionViewHolder: OptionViewHolder
    private var mChecked = false
    private val CHECKED_STATE_SET = intArrayOf(android.R.attr.state_checked)
    var targetAngle: Float = 0F
//    val rad: RadioButton

    init {
        val rootView: ViewGroup = inflate(context, R.layout.circle_menu_option, this) as ViewGroup
        mOptionViewHolder = OptionViewHolder(rootView)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleMenuOption)
        val iconResId = typedArray.getResourceId(R.styleable.CircleMenuOption_drawable, 0)
        mOptionViewHolder.icon.setImageResource(iconResId)
    }

    fun setConstraintCircleAngle(angle: Float){
        post(
            kotlinx.coroutines.Runnable {
                layoutParams = (layoutParams as ConstraintLayout.LayoutParams).apply { circleAngle = angle }
            }
        )
    }

    fun setDrawable(resId: Int) {
        mOptionViewHolder.icon.setImageResource(resId)

    }

    fun setDrawable(drawable: Drawable?){
        mOptionViewHolder.icon.setImageDrawable(drawable)
    }

    private class OptionViewHolder(val rootView: ViewGroup) {
        val icon: ImageView = rootView.findViewById(R.id.icon)
    }

    override fun setChecked(checked: Boolean) {
        if(mChecked != checked){
//            post(kotlinx.coroutines.Runnable {
////                val twidth = if(checked) 20 else -20;
////                val theight = if(checked) 20 else -20;
////                val tcircleRadius = if(checked) 20 else -20;
////
////                while ()
//                layoutParams = (layoutParams as ConstraintLayout.LayoutParams).apply {
//                    width += if(checked) 20 else -20;
//                    height += if(checked) 20 else -20;
//                    circleRadius += if(checked) 20 else -20;
//                }
//            })
            layoutParams = (layoutParams as ConstraintLayout.LayoutParams).apply {
                width += if(checked) 20 else -20;
                height += if(checked) 20 else -20;
                circleRadius += if(checked) 35 else -35;
            }
        }
        mChecked = checked

        refreshDrawableState()
    }

    override fun isChecked(): Boolean = mChecked

    override fun toggle() {
        isChecked = !mChecked
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray? {
        val drawableState = super.onCreateDrawableState(extraSpace + 1)
        if (isChecked) {
            mergeDrawableStates(drawableState, CHECKED_STATE_SET)
        }
        return drawableState
    }
}