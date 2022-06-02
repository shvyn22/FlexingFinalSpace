package shvyn22.flexingfinalspace.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.ActivityMainBinding
import shvyn22.flexingfinalspace.util.collectOnLifecycle

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNavView.setupWithNavController(navController)

        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.characterFragment, R.id.episodeFragment, R.id.quoteFragment)
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_app_bar, menu)

        viewModel.isDarkTheme.collectOnLifecycle(this) { isDarkTheme ->
            AppCompatDelegate.setDefaultNightMode(
                if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES
                else AppCompatDelegate.MODE_NIGHT_NO
            )

            menu?.findItem(R.id.menu_mode)?.apply {
                if (isDarkTheme) {
                    setIcon(R.drawable.ic_light_mode)
                    setTitle(R.string.mode_light)
                } else {
                    setIcon(R.drawable.ic_night_mode)
                    setTitle(R.string.mode_night)
                }
            }
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_mode)
            viewModel.editThemePreferences(
                AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO
            )
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}