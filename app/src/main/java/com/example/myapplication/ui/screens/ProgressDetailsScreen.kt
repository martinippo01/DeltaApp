package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.data.Routines
import com.example.myapplication.ui.components.BackButton
import com.example.myapplication.ui.components.Button1
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.viewmodel.RoutinesViewModel


@Composable
fun ProgressDetailScreen(viewModel: RoutinesViewModel, viewRoutineHandler: () -> Unit, routineId: String?, backButtonHandler: () -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.fillMaxWidth()) {
                BackButton(handler = backButtonHandler)
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth(0.7f)) {

                val id = routineId?.substringAfter('}')?.toInt() ?: -1
                val routine: Routines = viewModel.routine(id)!!

                Text(
                    text = routine.routineProgress.progressTile(),
                    style = MaterialTheme.typography.h1,
                )

                Slider(
                    value = routine.routineProgress.agreggatePerformance,
                    enabled = false, onValueChange = {}, valueRange = 0f..100f,
                    modifier = Modifier.clip(RoundedCornerShape(20.dp)).background(
                        Green))

                Text(
                    text = routine.routineProgress.progressDescription(),
                    fontSize = 30.sp,
                    color = Color.White
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(verticalAlignment = Alignment.CenterVertically){
            Button1(fontSize = 20, text = stringResource(R.string.see_routine_details), handler = viewRoutineHandler)
        }
    }
}