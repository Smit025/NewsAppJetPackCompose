package com.example.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.simplelist.Util.makeLink
import com.example.simplelist.model.NewsData
import com.example.simplelist.ui.theme.SimpleListTheme
import com.example.viewmodel.NewsViewModel

@Composable
fun NewsListScreen(viewModel: NewsViewModel, onClick: (NewsData) -> Unit) {

    val newState by viewModel.newsState.collectAsState()
    SimpleListTheme {
        when {
            newState.isEmpty() -> {
                // Empty state
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Loading...",
                        style = TextStyle(fontWeight = FontWeight.Bold, color = Color.Black),
                        fontSize = 20.sp
                    )
                }
            }

            else -> {
                // Rendering UI
                NewsListContainer(newState as List<NewsData>) {
                    onClick(it)
                }
            }
        }
    }
}


@Composable
fun NewsListContainer(
    list: List<NewsData>,
    modifier: Modifier = Modifier,
    onClick: (NewsData) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxSize()
    ) {
        items(list.size) { index ->
            NewsInfoCard(
                imageUrl = list[index].imageUrl,
                title = list[index].title ?: "",
                description = list[index].description ?: "",
                date = list[index].time ?: ""
            ) {
                onClick(list[index])
            }
        }
    }
}

@Composable
fun NewsInfoCard(
    imageUrl: String?,
    title: String,
    description: String,
    date: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val painter = rememberAsyncImagePainter(model = imageUrl?.makeLink())

    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .padding(8.dp)
            .fillMaxSize(),
        onClick = {
            onClick()
        }
    ) {
        Column {
            Row(modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .then(Modifier.clip(RoundedCornerShape(100.dp)))
                )
                Column(
                    modifier = modifier
                        .weight(1f)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineLarge,
                        maxLines = 1,
                        fontSize = 16.sp
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2,
                        fontSize = 10.sp
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(2.dp)
            ) {
                Text(
                    text = date,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodyMedium, fontSize = 10.sp,
                    modifier = modifier.wrapContentWidth()
                )
            }
        }
    }
}

