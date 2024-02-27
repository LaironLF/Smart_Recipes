package com.laironlf.smartRecipes.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.addCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.NavHostFragment
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragmentContainer) as NavHostFragment).navController
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)


        binding = ActivityMainBinding.inflate(layoutInflater)
        navController.addOnDestinationChangedListener{_, destination, _ ->
            binding.tvDestinationTitle.text = destination.label
        }
        onBackPressedDispatcher.addCallback(this){
            if(binding.drawer.isOpen) binding.drawer.close()
            else if(!navController.popBackStack()) finish()
        }
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setupDrawerLayout()
        setupInsets()
    }



    private fun setupInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.navHostFragmentContainer){v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams>{
                bottomMargin = insets.bottom
            }
            WindowInsetsCompat.CONSUMED

        }
    }

    private fun setupDrawerLayout() {
        val toggle = ActionBarDrawerToggle(this, binding.drawer, binding.toolbar,
            R.string.nav_open,
            R.string.nav_close
        )
        binding.drawer.addDrawerListener(toggle)
        binding.circleMenu.setupWithNavController(navController)
        toggle.syncState()
    }


}