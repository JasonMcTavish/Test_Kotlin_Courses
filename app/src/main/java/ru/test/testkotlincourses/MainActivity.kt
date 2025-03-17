package ru.test.testkotlincourses

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import ru.test.testkotlincourses.databinding.ActivityMainBinding
import ru.test.testkotlincourses.ui.onboarding.OnBoardingActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getPrefs = PreferenceManager.getDefaultSharedPreferences(baseContext)

        val FIRST_START = null
        val isFirstStart = getPrefs.getBoolean(FIRST_START, true)

        if (isFirstStart) {
            val i = Intent(this@MainActivity, OnBoardingActivity::class.java)
            startActivity(i)
            val e = getPrefs.edit()
            e.putBoolean(FIRST_START, false)
            e.apply()
        }
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val navView: BottomNavigationView = binding.navView
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_login -> hideBottomNav()
                else -> showBottomNav()
            }

        }
        navView.setupWithNavController(navController)
        setContentView(binding.root)

    }

    private fun showBottomNav() {
        binding.navView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        binding.navView.visibility = View.GONE
    }
}