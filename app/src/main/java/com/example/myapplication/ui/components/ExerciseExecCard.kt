package com.example.myapplication.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.ui.classes.Exercise
import com.example.myapplication.ui.theme.Green
import com.example.myapplication.ui.activities.thirdactivity.ExecuteRoutineViewModel
import com.example.myapplication.ui.classes.CyclesExercise
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun RecomposingTitle(exercise: MutableStateFlow<CyclesExercise>) {
    val text by exercise.collectAsState()
    Text(text = text.cycle?:"", fontSize = 20.sp, color = Green)

    Column( verticalArrangement = Arrangement.SpaceEvenly,horizontalAlignment = Alignment.CenterHorizontally) {
        if(text.isExercise) {
            Text(text = text.name, fontSize = 30.sp, textAlign = TextAlign.Center)
            Text(text = text.detail, fontSize = 15.sp, textAlign = TextAlign.Center, color = Color.White)
        }
        else
            Text(text = stringResource(id = R.string.rest), fontSize = 30.sp)
    }


}

@Composable
fun ExerciseExecCard(viewModel: ExecuteRoutineViewModel, actualExercise: MutableStateFlow<CyclesExercise>){
    val exercise by actualExercise.collectAsState()
    LinearProgressIndicator(
        progress = exercise.index?.div((viewModel.exerciseCount * 1f)) ?: 0f,
        modifier = Modifier.padding(30.dp)
    )

    Card(backgroundColor = Gray,shape = RoundedCornerShape(30.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .fillMaxWidth(0.8F)
            .fillMaxHeight(0.8F), verticalArrangement = Arrangement.SpaceEvenly){
            RecomposingTitle(exercise = actualExercise)
            Row(verticalAlignment = Alignment.CenterVertically) {
                if(exercise.isExercise) {
                    RepsCards(
                        name = stringResource(R.string.reps),
                        actualExercise = actualExercise, viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    WeightCards(
                        name = stringResource(R.string.weight),
                        actualExercise = actualExercise, viewModel = viewModel
                    )

                }else{
                    Timer(
                        totalTime = exercise.duration * 1000L,
                        handleColor = Green,
                        inactiveBarColor = Color.DarkGray,
                        activeBarColor = Green,
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
            if(exercise.duration != 0 && exercise.isExercise) {
                Timer(
                    totalTime = exercise.duration * 1000L,
                    handleColor = Green,
                    inactiveBarColor = Color.DarkGray,
                    activeBarColor = Green,
                    modifier = Modifier.size(200.dp)
                )
            }
        }

    }
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun WeightCards(name : String, actualExercise: MutableStateFlow<CyclesExercise>, viewModel: ExecuteRoutineViewModel){
    val exercise by actualExercise.collectAsState()
    var sliderValueRaw by remember { mutableStateOf(exercise.weight) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    val isInteracting = isPressed || isDragged
    val sliderValue by derivedStateOf {
        if (isInteracting) {
            sliderValueRaw
        } else {
            exercise.weight
        }
    }

    Card(backgroundColor = Color.LightGray, elevation = 6.dp, modifier = Modifier
        .width(120.dp)
        .fillMaxHeight(0.4F),shape = RoundedCornerShape(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = name, fontSize = 25.sp)
            Text(text = sliderValue.toInt().toString(), fontSize = 20.sp)
            Slider(
                value = sliderValue, // using calculated sliderValue here from above
                onValueChange = {
                    sliderValueRaw = it
                },
                onValueChangeFinished = {
                    viewModel.setWeight(sliderValue)
                },
                interactionSource = interactionSource,
                valueRange = 0f..150f,
                colors = SliderDefaults.colors(
                    activeTickColor = Green,
                    inactiveTickColor = Green,
                    inactiveTrackColor = Color.DarkGray,
                    activeTrackColor = Green,
                    thumbColor = Green
                ),
            )


        }


    }
}
@SuppressLint("UnrememberedMutableState")
@Composable
fun RepsCards(name : String, actualExercise: MutableStateFlow<CyclesExercise>, viewModel: ExecuteRoutineViewModel){
    val exercise by actualExercise.collectAsState()
    var sliderValueRaw by remember { mutableStateOf(exercise.repetitions) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val isDragged by interactionSource.collectIsDraggedAsState()
    val isInteracting = isPressed || isDragged
    val sliderValue by derivedStateOf {
        if (isInteracting) {
            sliderValueRaw
        } else {
            exercise.repetitions
        }
    }

    Card(backgroundColor = Color.LightGray, elevation = 6.dp, modifier = Modifier
        .width(120.dp)
        .fillMaxHeight(0.4F),shape = RoundedCornerShape(20.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = name, fontSize = 25.sp)
            Text(text = sliderValue.toInt().toString(), fontSize = 20.sp)
            Slider(
                value = sliderValue, // using calculated sliderValue here from above
                onValueChange = {
                    sliderValueRaw = it
                },
                onValueChangeFinished = {
                    viewModel.setReps(sliderValue)
                },
                interactionSource = interactionSource,
                valueRange = 0f..40f,
                colors = SliderDefaults.colors(
                    activeTickColor = Green,
                    inactiveTickColor = Green,
                    inactiveTrackColor = Color.DarkGray,
                    activeTrackColor = Green,
                    thumbColor = Green
                ),
            )


        }


    }
}