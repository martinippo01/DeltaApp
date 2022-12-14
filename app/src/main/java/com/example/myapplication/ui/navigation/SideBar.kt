package com.example.myapplication.ui.navigation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.R
import com.example.myapplication.ui.theme.Green

@Composable
fun SideBar(navController: NavController) {
    val items = listOf(
        NavBarScreen.Routines,
        NavBarScreen.Progress,
        NavBarScreen.Explore,
        NavBarScreen.Profile
    )

    NavigationRail(backgroundColor = Green, contentColor = MaterialTheme.colors.onPrimary) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationRailItem(
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        text = if(item == NavBarScreen.Explore) stringResource(id = R.string.explore_title)
                        else if(item == NavBarScreen.Progress) stringResource(id = R.string.progress_title)
                        else if(item == NavBarScreen.Routines) stringResource(id = R.string.routines_title)
                        else stringResource(id = R.string.profile),
                        color = Color.Gray
                    )
                },
                alwaysShowLabel = true,
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screenRoute ->
                            popUpTo(screenRoute) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
