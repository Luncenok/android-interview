package com.goodylabs.android.interview.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.goodylabs.android.interview.R
import com.goodylabs.android.interview.databinding.ActivityMainBinding
import com.goodylabs.android.interview.util.hideSoftInput
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        initializeNavController()
    }

    private fun initializeNavController() {
        navController.addOnDestinationChangedListener { _, _, _ -> hideSoftInput() }
    }
}
