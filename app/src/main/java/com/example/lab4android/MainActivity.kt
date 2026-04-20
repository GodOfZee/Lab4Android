package com.example.lab4android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab4android.navigation.ScreenDestinations
import com.example.lab4android.ui.components.AppDrawer
import com.example.lab4android.ui.components.BottomBar
import com.example.lab4android.ui.components.TopBar
import com.example.lab4android.ui.screens.*
import com.example.lab4android.ui.theme.Lab4AndroidTheme
import com.example.lab4android.viewmodel.CityAppViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4AndroidTheme {
                CityApp(modifier = Modifier)
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun CityApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val viewModel: CityAppViewModel = viewModel()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                currentRoute = navController.currentBackStackEntry?.destination?.route,
                onDestinationSelected = { category ->
                    scope.launch { drawerState.close() }
                    navController.navigate("${ScreenDestinations.CATEGORY.route}/$category")
                },
                onAboutClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(ScreenDestinations.ABOUT.route)
                },
                onSettingsClick = {
                    scope.launch { drawerState.close() }
                    navController.navigate(ScreenDestinations.SETTINGS.route)
                }
            )
        },
        modifier = modifier
    ) {
        Scaffold(
            topBar = {
                TopBar(
                    modifier = Modifier,
                    titleRes = R.string.app_name,
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    }
                )
            },
            bottomBar = {
                BottomBar(
                    modifier = Modifier,
                    navController = navController
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = ScreenDestinations.HOME.route,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(ScreenDestinations.HOME.route) {
                    HomeScreen(
                        modifier = Modifier,
                        viewModel = viewModel,
                        onCategoryClick = { category ->
                            navController.navigate("${ScreenDestinations.CATEGORY.route}/$category")
                        },
                        onRecommendationClick = { recommendationId ->
                            navController.navigate("${ScreenDestinations.DETAILS.route}/$recommendationId")
                        }
                    )
                }

                composable(ScreenDestinations.CATEGORY.route + "/{category}") { stackEntry ->
                    val category = stackEntry.arguments?.getString("category") ?: return@composable
                    CategoryScreen(
                        modifier = Modifier,
                        category = category,
                        viewModel = viewModel,
                        onRecommendationClick = { recommendationId ->
                            navController.navigate("${ScreenDestinations.DETAILS.route}/$recommendationId")
                        }
                    )
                }

                composable(ScreenDestinations.DETAILS.route + "/{recommendationId}") { stackEntry ->
                    val recommendationId = stackEntry.arguments?.getString("recommendationId")?.toIntOrNull() ?: return@composable
                    DetailsScreen(
                        modifier = Modifier,
                        recommendationId = recommendationId,
                        viewModel = viewModel
                    )
                }

                composable(ScreenDestinations.ABOUT.route) {
                    AboutScreen(modifier = Modifier)
                }

                composable(ScreenDestinations.SETTINGS.route) {
                    SettingsScreen(modifier = Modifier)
                }

                composable(ScreenDestinations.ABOUT.route) {
                    AboutScreen(modifier = Modifier)
                }

                composable(ScreenDestinations.SETTINGS.route) {
                    SettingsScreen(modifier = Modifier)
                }
            }
        }
    }
}