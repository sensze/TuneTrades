package com.ifkusyoba.tunetrades.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ifkusyoba.tunetrades.R
import com.ifkusyoba.tunetrades.di.Injection
import com.ifkusyoba.tunetrades.navigation.ItemNavigation
import com.ifkusyoba.tunetrades.navigation.Screen
import com.ifkusyoba.tunetrades.ui.theme.TuneTradesTheme
import com.ifkusyoba.tunetrades.ui.views.account.AccountScreen
import com.ifkusyoba.tunetrades.ui.views.checkout.CheckoutScreen
import com.ifkusyoba.tunetrades.ui.views.detail.DetailScreen
import com.ifkusyoba.tunetrades.ui.views.home.HomeScreen
import com.ifkusyoba.tunetrades.viewmodel.HomeViewModel
import com.ifkusyoba.tunetrades.viewmodel.ViewModelFactory

@Composable
fun TuneTradesApp(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    modifier: Modifier = Modifier,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailProduct.route) {
                BottomAppBar(navController)
            }
        }, modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { productId ->
                    navController.navigate(Screen.DetailProduct.createRoute(productId))
                }, onCartClicked = {
                    navController.navigate(Screen.Cart.route)
                })
            }
            composable(Screen.Account.route) {
                AccountScreen(onBackClick = { navController.navigateUp() })
            }
            composable(Screen.Cart.route) {
                CheckoutScreen(onCheckoutButtonClicked = {
                    viewModel.clearCart()
                    navController.navigateUp()
                })
            }
            composable(route = Screen.DetailProduct.route, arguments = listOf(navArgument("id") {
                type = NavType.LongType
            })) { backStackEntry ->
                val productId = backStackEntry.arguments?.getLong("id") ?: -1L
                DetailScreen(id = productId,
                    navigateBack = { navController.navigateUp() },
                    navigateToCart = {
                        navController.popBackStack()
                        navController.navigate(Screen.Cart.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}


@Composable
private fun BottomAppBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            ItemNavigation(
                title = stringResource(id = R.string.home),
                icon = Icons.Default.Home,
                route = Screen.Home
            ), ItemNavigation(
                title = stringResource(id = R.string.account),
                icon = Icons.Default.Person,
                route = Screen.Account
            )
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(icon = {
                    Icon(
                        imageVector = item.icon, contentDescription = item.title
                    )
                },
                    label = { Text(text = item.title) },
                    selected = currentRoute == item.route.route,
                    onClick = {
                        navController.navigate(item.route.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TuneTradesAppPreview() {
    TuneTradesTheme {
        TuneTradesApp()
    }
}