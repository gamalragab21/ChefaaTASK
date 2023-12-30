package net.gamal.chefaatask.ui.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import net.gamal.chefaatask.R
import net.gamal.chefaatask.core.android.extension.onBackClicked
import net.gamal.chefaatask.core.android.extension.show
import net.gamal.chefaatask.core.android.helpers.viewModel.CurrentAction
import net.gamal.chefaatask.core.base.BaseActivity
import net.gamal.chefaatask.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavController.OnDestinationChangedListener {

    private lateinit var navController: NavController

    override fun onActivityReady(savedInstanceState: Bundle?) {

        onBackClicked {

        }
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        val navGraph =
            navHostFragment.navController.navInflater.inflate(R.navigation.main_nav_graph)
        navHostFragment.navController.graph = navGraph
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.fragment_container_view).navigateUp()

    override fun onLoading(loading: Boolean) {
        super.onLoading(loading)
        binding.includeLoading.root.show(loading)
    }

    override fun onDestinationChanged(
        controller: NavController, destination: NavDestination, arguments: Bundle?
    ) {
        // we can hide or show toolbar as u want based on destination id
    }

    override fun onDestroy() {
        navController.removeOnDestinationChangedListener(this)
        val fragmentList: List<Fragment> = supportFragmentManager.fragments
        for (f in fragmentList) {
            navController.popBackStack(f.id, true)
        }
        super.onDestroy()
    }

    override fun viewInit() {}
    override fun onRetryCurrentAction(currentAction: CurrentAction?, message: String) {}
}