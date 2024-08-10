package com.fransbudikashira.suitmediatest

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.fransbudikashira.suitmediatest.ui.component.MyTopAppBar
import com.fransbudikashira.suitmediatest.ui.navigation.Screen
import com.fransbudikashira.suitmediatest.ui.screen.First.FirstScreen
import com.fransbudikashira.suitmediatest.ui.screen.Second.SecondScreen
import com.fransbudikashira.suitmediatest.ui.screen.Third.ThirdScreen
import com.fransbudikashira.suitmediatest.ui.theme.SuitmediaTestTheme

@OptIn(ExperimentalCoilApi::class)
@ExperimentalPagingApi
@Composable
fun SuitMediaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            when(currentRoute) {
                Screen.Second.route -> {
                    MyTopAppBar(
                        title = stringResource(R.string.second_screen),
                        onBackClick = { navController.navigateUp() },
                        modifier = Modifier
                    )
                }
                Screen.Third.route -> {
                    MyTopAppBar(
                        title = stringResource(R.string.third_screen),
                        onBackClick = { navController.navigateUp() },
                        modifier = Modifier
                    )
                }
            }
        },
        modifier = Modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.First.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.First.route) {
                FirstScreen(
                    onNextClick = { name ->
                        navController.navigate(Screen.Second.createRoute(name))
                    }
                )
            }
            composable(
                route = Screen.Second.route,
                arguments = listOf(navArgument("name") { type = NavType.StringType })
            ) {
                val name = it.arguments?.getString("name") ?: ""
                SecondScreen(
                    name = name,
                    onChooseUserClick = {
                        navController.navigate(Screen.Third.route)
                    }
                )
            }
            composable(Screen.Third.route) {
                ThirdScreen(
                    modifier = Modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalPagingApi::class)
@Preview(showBackground = true)
@Composable
fun SuitMediaAppPreview() {
    SuitmediaTestTheme {
        SuitMediaApp()
    }
}