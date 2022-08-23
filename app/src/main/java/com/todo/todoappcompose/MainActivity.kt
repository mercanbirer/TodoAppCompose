package com.todo.todoappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.todo.todoappcompose.ui.theme.TodoAppComposeTheme
import com.todo.todoappcompose.view.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var mInstal: InterstitialAd? = null

        setContent {
            val adRequest = AdRequest.Builder().build()
            InterstitialAd.load(this,"", adRequest,object : InterstitialAdLoadCallback(){
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    mInstal = null
                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    mInstal = p0
                    mInstal!!.show(this@MainActivity)
                }
            })
            TodoAppComposeTheme {
                Scaffold(bottomBar = {
                    AndroidView(factory = {
                        AdView(it).apply {
                            this.setAdSize(AdSize.BANNER)
                            adUnitId = "ca-app-pub-7156643920028424/3403006779"
                            loadAd(AdRequest.Builder().build())
                        }
                    })
                }) {

                }
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
