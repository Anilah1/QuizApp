package com.example.quizapp.uiA

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quizapp.R

@Composable
fun ResultScreen(score: Int, onRepeat: () -> Unit, onHome: () -> Unit) {
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
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Quiz Completed!", fontSize = 24.sp, color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Your Score: $score", fontSize = 20.sp, color = Color.Yellow)
            Spacer(modifier = Modifier.height(24.dp))
            Button(onClick = onRepeat) {
                Text("Try Again")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onHome) {
                Text("Back to Home")
            }
        }
    }
}






