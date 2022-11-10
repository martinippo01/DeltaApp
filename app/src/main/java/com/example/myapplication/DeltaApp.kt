package com.example.myapplication

import android.graphics.Color
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.navigation.BottomBar
import com.example.myapplication.ui.navigation.NavGraph
import com.example.myapplication.viewmodel.RoutinesViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.ui.navigation.SideBar

@Composable
fun NavigationBar(navController: NavHostController, windowSize: WindowWidthSizeClass) {

     if (WindowWidthSizeClass.Compact == windowSize)
        BottomBar(navController = navController)
    else
        SideBar(navController = navController)
}

@Composable
fun DeltaApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
    viewModel: RoutinesViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    viewModel.setWidth(windowSize)

        Scaffold(
            modifier = modifier,
            bottomBar = { NavigationBar( navController, windowSize) },
        ) {
            NavGraph(navController = navController, viewModel = viewModel)
        }
}

