package com.example.moviesapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.model.Boss
import com.example.moviesapp.navigation.MoviesScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, arg: Boss?) {

    Scaffold(topBar = {
        TitleDetails(title = "Movies", onNavigationClick = { navController.popBackStack() }, onSettingClick = {})
    }) {
        ContentDetails(navController, arg)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleDetails(title: String, onNavigationClick: () -> Unit, onSettingClick: () -> Unit)
{
    TopAppBar(
        title = { Text(text = "Elder Ring") },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Navigation icon"
                )
            }
        },
        actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray),
        modifier = Modifier
            .padding(all = 4.dp)
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
    )
}

@Composable
fun ContentDetails(navController: NavController, ele: Boss?) {
    Surface(modifier = Modifier.fillMaxSize()
        , color = Color.Transparent
        , ) {

        Card(modifier = Modifier
            .wrapContentWidth(Alignment.CenterHorizontally)
            .wrapContentHeight(Alignment.Top)
            .padding(top = 80.dp, start = 20.dp, end = 20.dp)
            , elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)){
            Column(modifier = Modifier
                .align(Alignment.CenterHorizontally),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Surface(modifier = Modifier
                    .padding(8.dp)
                    .size(200.dp)
                    , shape = RectangleShape
                ){
                    Image(
                        painter = rememberAsyncImagePainter(ele?.let{ it.image}),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                }
                Text( buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("description: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                        ele?.let { append(it.description) }
                    }
                })
                Divider(modifier = Modifier.padding(8.dp))
                Text( buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("region: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                        ele?.let { append(it.region) }
                    }
                })
                Divider(modifier = Modifier.padding(8.dp))
                Text( buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("location: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                        ele?.let { append(it.location) }
                    }
                })
                Divider(modifier = Modifier.padding(8.dp))
                Text( buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("drops: ")
                    }
                    withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                        ele?.let { append(it.drops.joinToString(",")) }
                    }
                })
                Spacer(modifier = Modifier.height(23.dp))
                Button(
                    onClick = { navController.navigate(route = MoviesScreens.HomeScreen.name) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Go Back")
                }
            }
        }


    }
}

@Preview
@Composable
fun PreviewDetailsScreen() {
    ContentDetails(navController = rememberNavController(), ele = Boss(""
        , "Elden Ring"
        , ""
        , ""
        , ""
        , ""
        , emptyList()
        ,""))
}