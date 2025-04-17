package com.example.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.uiA.CategoryScreen
import com.example.quizapp.uiA.HomeScreen
import com.example.quizapp.uiA.QuizScreen
import com.example.quizapp.uiA.ResultScreen
import com.example.quizapp.ui.theme.QuizAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                QuizNavGraph()
            }
        }
    }
}

@Composable
fun QuizNavGraph() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(onStartClick = {
                navController.navigate("category")
            })
        }
        composable("category") {
            CategoryScreen(onCategorySelected = {
                navController.navigate("quiz")
            })
        }
        composable("quiz") {
            QuizScreen(onQuizFinish = { score ->
                navController.navigate("result/$score")
            })
        }
        composable("result/{score}") { backStackEntry ->
            val score = backStackEntry.arguments?.getString("score")?.toInt() ?: 0

            ResultScreen(
                score = score,
                onRepeat = {
                    navController.navigate("quiz") {
                        popUpTo("quiz") { inclusive = true }
                    }
                },
                onHome = {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    }
}


