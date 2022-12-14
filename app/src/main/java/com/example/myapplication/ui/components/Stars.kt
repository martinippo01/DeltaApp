package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.classes.Routines
import kotlinx.coroutines.flow.update


@Composable
fun Stars(routine: Routines, clickable: Boolean) {

   val selectedStars by routine.points.collectAsState()

    Row()
    {
        for (starIndex in 1..5) {
            IconButton(onClick = { if(clickable) routine.points.update { starIndex } }, modifier = Modifier.padding(0.dp)) {
                Icon(
                    if (starIndex <= selectedStars) Icons.Default.Star
                    else Icons.Default.StarBorder,
                    contentDescription = "bordered star", tint = White, modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}
