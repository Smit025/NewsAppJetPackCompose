package com.example.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.simplelist.Util.makeLink
import com.example.simplelist.model.NewsData

@Composable
fun NewsDetailScreen(
    newsData: NewsData,
    modifier: Modifier = Modifier
) {
    val painter = rememberAsyncImagePainter(model = newsData.imageUrl?.makeLink())
    Column(verticalArrangement = Arrangement.Top) {
        Image(
            painter = painter,
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        NewsDetails(newsData)
    }
}

@Composable
fun NewsDetails(newsData: NewsData, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = newsData.title?:"",
            style = MaterialTheme.typography.titleMedium.copy(
                Color.Black,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = newsData.description?:"",
            maxLines = 4,
            style = MaterialTheme.typography.titleSmall.copy(
                Color.Black,
                fontWeight = FontWeight.Light
            ),
        )
    }
}

