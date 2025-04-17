package com.example.quizapp.uiA

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.R

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

val sampleQuestions = listOf(
    Question("What is the correct syntax to declare a variable in C#?", listOf("var x = 5;", "x = 5;", "int 5 = x;", "int x;"), 0),
    Question("Which of the following is a value type in C#?", listOf("String", "Object", "Class", "Int32"), 3),
    Question("What is the default value of an uninitialized int variable in C#?", listOf("0", "null", "undefined", "1"), 0),
    Question("Which keyword is used to define a method in C#?", listOf("function", "define", "void", "method"), 2),
    Question("Which of the following is used for exception handling in C#?", listOf("try-catch", "do-catch", "error handling", "exception block"), 0)
)

@Composable
fun QuizScreen(onQuizFinish: (Int) -> Unit) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var timeLeft by remember { mutableStateOf(30L) }
    var showNext by remember { mutableStateOf(false) }

    // Start timer
    LaunchedEffect(currentQuestionIndex) {
        selectedOptionIndex = null
        showNext = false
        timeLeft = 30L
        object : CountDownTimer(30_000, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = millisUntilFinished / 1000
            }

            override fun onFinish() {
                showNext = true
            }
        }.start()
    }

    val question = sampleQuestions[currentQuestionIndex]

    Box(modifier = Modifier.fillMaxSize()) {

        // Background image
        Image(
            painter = painterResource(id = R.drawable.bg2),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Content over image
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                "Question ${currentQuestionIndex + 1} of ${sampleQuestions.size}",
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                question.text,
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Time Left: $timeLeft sec",
                fontSize = 16.sp,
                color = Color.Yellow
            )
            Spacer(modifier = Modifier.height(24.dp))

            question.options.forEachIndexed { index, option ->
                val backgroundColor = when {
                    selectedOptionIndex == null -> Color.White
                    index == question.correctAnswerIndex -> Color(0xFFAAF683)
                    index == selectedOptionIndex -> Color(0xFFFF686B)
                    else -> Color.White

                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(backgroundColor)
                        .clickable(enabled = selectedOptionIndex == null) {
                            selectedOptionIndex = index
                            if (index == question.correctAnswerIndex) score++
                            showNext = true
                        }
                        .padding(12.dp)
                ) {
                    Text(option, fontSize = 16.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (showNext) {
                Button(onClick = {
                    if (currentQuestionIndex < sampleQuestions.size - 1) {
                        currentQuestionIndex++
                    } else {
                        onQuizFinish(score)
                    }
                }) {
                    Text("Next")
                }
            }
        }
    }
}














