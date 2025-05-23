package com.example.newsaggregator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsaggregator.ui.webview.WebViewScreen
import com.example.newsaggregator.ui.theme.NewsAggregatorTheme
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.net.URLEncoder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsaggregator.ui.news.NewsListRoute

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NewsAggregatorTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "news_list"
                ) {
                    composable("news_list") {
                        NewsListRoute(
                            onItemClick = { item ->
                                val encodedUrl = URLEncoder.encode(item.guid, "UTF-8")
                                navController.navigate("webview?url=$encodedUrl")
                            }
                        )
                    }

                    composable(
                        route = "webview?url={url}",
                        arguments = listOf(navArgument("url") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val url = URLDecoder.decode(
                            backStackEntry.arguments?.getString("url") ?: "",
                            "UTF-8"
                        )
                        WebViewScreen(
                            url = url,
                            onBackClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}

