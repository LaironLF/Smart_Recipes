package com.laironlf.circlemenu

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.PopupMenu
import androidx.annotation.MenuRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavOptions


@SuppressLint("Recycle")
class CircleMenu @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private var mMenuViewHolder: MenuViewHolder
    private var mMenuDiameter: Float = 250.dpToPx(context.resources.displayMetrics).toFloat()
    private val mOptionBackgroundResId: Int
    private var mNavController: NavController? = null

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleMenu)
        val menuResId = typedArray.getResourceId(R.styleable.CircleMenu_menu, 0)
        val circleMenuBackground = typedArray.getResourceId(R.styleable.CircleMenu_circleMenuBackground, R.drawable.circleback)
        mMenuDiameter = typedArray.getDimension(R.styleable.CircleMenu_circleMenuDiameter, mMenuDiameter)
        val mOptionDiameter = typedArray.getDimension(R.styleable.CircleMenu_optionDiameter, 0f)
        val mOptionRadius = typedArray.getDimension(R.styleable.CircleMenu_circleMenuOptionRadius, mMenuDiameter/2 + 100)
        mOptionBackgroundResId = typedArray.getResourceId(R.styleable.CircleMenu_optionBackground, 0)

        val rootView: ViewGroup = inflate(context, R.layout.circle_menu, this) as ViewGroup
        rootView.findViewById<ImageView>(R.id.imageView).setImageResource(circleMenuBackground)
        mMenuViewHolder = MenuViewHolder(rootView, mOptionRadius.toInt(), mOptionDiameter.toInt())
        inflateMenu(menuResId)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val drawer = parent as BetterDrawerLayout
        drawer.setOnTouchListener { v, event ->
            if (drawer.isOpen) drawer.close()
            false
        }
        (parent as? BetterDrawerLayout)?.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                mMenuViewHolder.animateOptionsAngle(slideOffset)
            }

            override fun onDrawerOpened(drawerView: View) {
//                TODO("Not yet implemented")
            }

            override fun onDrawerClosed(drawerView: View) {
//                TODO("Not yet implemented")
            }

            override fun onDrawerStateChanged(newState: Int) {
//                TODO("Not yet implemented")
            }

        })

    }

    fun setupWithNavController(navController: NavController){
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            mNavController = controller
            mMenuViewHolder.menuItems.forEach { item ->
                item.isChecked = destination.id == item.id
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun inflateMenu(@MenuRes menuRes: Int) {
        val p = PopupMenu(context, null)
        val menu = p.menu
        MenuInflater(context).inflate(menuRes, menu)
        for (i in 0 until menu.size()) {
            val item = menu.getItem(i)
            val itemView = CircleMenuOptionView(context)
            itemView.setBackgroundResource(mOptionBackgroundResId)
            itemView.setDrawable(item.icon)
            if(item.itemId != 0){
                itemView.id = item.itemId
                itemView.setOnClickListener{
                    itemClicked(itemView)
                }

            }
//            Log.d("MyLog", "inflateMenu: ${item.itemId}")
            itemView.targetAngle = (180/(menu.size()-1) * i).toFloat()
            mMenuViewHolder.addCircleMenuOption(itemView)
        }
    }

    private fun itemClicked(view: View){
        if(mNavController?.currentDestination?.id == view.id) return
        mNavController?.navigate(view.id, null, NavOptions.Builder()
            .setPopUpTo(mNavController?.graph!!.id, false)
            .setEnterAnim(R.anim.enter_from_up_anim)
            .setExitAnim(R.anim.exit_to_down_anim)
            .build())
        val drawer = parent as BetterDrawerLayout
        drawer.close()
//        Log.d("TAG", "itemClicked: ${view.isSelected}")
    }

    private class MenuViewHolder(rootView: ViewGroup, val optionRadius: Int, val optionDiameter: Int) {
        val mMenuOptions: ConstraintLayout = rootView.findViewById(R.id.menuOptions)
        val menuItems: ArrayList<CircleMenuOptionView> = ArrayList()
        fun animateOptionsAngle(slideK: Float){
            for (i in 0 until mMenuOptions.childCount){
                val view: Any = mMenuOptions[i]
                if (view is CircleMenuOptionView) {
                    view.setConstraintCircleAngle(view.targetAngle * slideK*slideK)
                }
            }
        }
        fun addCircleMenuOption(menuOption: CircleMenuOptionView){
            val params = ConstraintLayout.LayoutParams(optionDiameter, optionDiameter)

            params.circleAngle = menuOption.targetAngle
            params.circleRadius = optionRadius
            params.circleConstraint = R.id.center
            menuOption.layoutParams = params
            menuItems.add(menuOption)
            mMenuOptions.addView(menuOption)
        }
    }


}