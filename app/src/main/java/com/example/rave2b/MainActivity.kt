package com.example.rave2b

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rave2b.screens.AppLoginScreen
import com.example.rave2b.screens.AppSignUpScreen
import com.example.rave2b.screens.AppUserScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val myNavController = rememberNavController()
            //if we logged in app will save that shit making that false from beginning bitch mr white
            val sharedPref = applicationContext.getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)
            val startDestination = if (isLoggedIn) "AppUserScreen" else "AppLogInScreen"

            NavHost(navController = myNavController, startDestination = startDestination)
            {
                composable(
                    route = "AppLogInScreen",
                    exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            tween(500)
                        )
                    },
                    popEnterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            tween(500)
                        )
                    },
                )
                {
                    AppLoginScreen(myNavController)
                }
                //sign up screen here
                composable(
                    route = "AppSignUpScreen",
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            tween(500)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            tween(500)
                        )
                    })
                {
                    AppSignUpScreen()
                }
                //user screen here
                composable(
                    route = "AppUserScreen",
                    enterTransition = {
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Left,
                            tween(500)
                        )
                    },
                    popExitTransition = {
                        slideOutOfContainer(
                            AnimatedContentTransitionScope.SlideDirection.Right,
                            tween(500)
                        )
                    })
                {
                    AppUserScreen()
                }
            }
        }
    }
}