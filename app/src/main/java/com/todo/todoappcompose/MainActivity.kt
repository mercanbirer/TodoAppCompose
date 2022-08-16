package com.todo.todoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.todo.todoappcompose.ui.theme.TodoAppComposeTheme
import com.todo.todoappcompose.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppComposeTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main_screen"){
                    composable("main_screen"){
                        MainScreen()
                    }
                }
            }
        }
    }
}
