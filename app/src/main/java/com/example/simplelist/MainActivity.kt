package com.example.simplelist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.screens.NewsDetailScreen
import com.example.screens.NewsListScreen
import com.example.simplelist.model.NewsData
import com.example.simplelist.remote.RetrofitInstance
import com.example.simplelist.repository.NewsRepository
import com.example.simplelist.ui.theme.SimpleListTheme
import com.example.viewmodel.NewsViewModel

class MainActivity : ComponentActivity() {

    private lateinit var newsRepository: NewsRepository
    private lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsRepository = NewsRepository(RetrofitInstance.getRetrofit())
        viewModel = NewsViewModel(newsRepository)
        setContent {
            SimpleListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(viewModel)
                }
            }
        }
    }
}

@Composable
fun App(viewModel: NewsViewModel) {
    val navController = rememberNavController()
    var newData: NewsData? = null
    NavHost(navController = navController, startDestination = "news") {
        composable(route = "news") {
            NewsListScreen(viewModel = viewModel) { newsData ->
                newData = newsData
                navController.navigate("detail")
            }
        }
        composable(
            route = "detail", arguments = listOf(
            )
        ) {
            newData?.let { it1 -> NewsDetailScreen(it1) }
        }
    }
}
