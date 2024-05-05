package com.example.moviesapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.moviesapp.model.Boss
import com.example.moviesapp.navigation.MoviesScreens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.moviesapp.model.view.MainViewModel
import com.example.moviesapp.util.toJson
import java.net.URLEncoder

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(topBar = {
        Title(title = "Movies", onNavigationClick = {}, onSettingClick = {})
    }) {
        Content(navController)
    }
}

////////////////////////
//     UI Elements     //
////////////////////////

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Title(title: String,
          onNavigationClick: () -> Unit,
          onSettingClick: () -> Unit) {
    TopAppBar(title = { Text(text = "Elder Ring") }
        , navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Navigation icon"
                )
            }
        }
        ,actions = {
            IconButton(onClick = onSettingClick) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            }
        }
        , colors = TopAppBarDefaults.topAppBarColors( containerColor = Color.Gray)
        , modifier = Modifier
            .padding(all = 4.dp)
            .border(BorderStroke(1.dp, Color.Black), shape = RoundedCornerShape(8.dp))
    )
}

@Composable
fun Content(navController: NavController) {


    val stateViewModel: MainViewModel = viewModel()
    val viewState by stateViewModel.bossesState



    Surface(modifier = Modifier.padding(top = 70.dp), color = MaterialTheme.colorScheme.background
        , contentColor = MaterialTheme.colorScheme.onBackground) {

        when{
            viewState.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.width(20.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,)
            }
            viewState.error != null -> {
                Text(text = viewState.error!!, modifier = Modifier.padding(16.dp), color = Color.Red, style = MaterialTheme.typography.labelMedium)
            }
            else ->{
                LazyColumn {
                    items(items = viewState.list) {
                        MovieRow(ele = it) {
                            val jsonString = URLEncoder.encode(it.toJson(), "utf-8")
                            navController.navigate(route = MoviesScreens.DetailsScreen.name + "/$jsonString")
                        }
                    }
                }
            }
        }


    }
}

@Composable
fun MovieRow(ele: Boss, onClickItem: () -> Unit) {

    var expanded = remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable(onClick = onClickItem)
        , shape = RoundedCornerShape(8.dp)
        , elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically
            , horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(8.dp)
                .size(100.dp)
                , shape = RectangleShape
            ){
                Image(
                    painter = rememberAsyncImagePainter(ele.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                    )
            }
            Column(horizontalAlignment = Alignment.Start
                , verticalArrangement = Arrangement.Center) {
                Text(text = ele.name
                    , fontWeight = FontWeight.Bold
                    , style = MaterialTheme.typography.bodyLarge)
                Icon(imageVector = Icons.Filled.ArrowDropDown
                    , contentDescription = "Row Down"
                    , modifier = Modifier
                        .padding(top = 2.dp)
                        .clickable { expanded.value = !expanded.value })
                AnimatedVisibility(visible = expanded.value) {
                    Column(modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize()) {
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("description: ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                                append(ele.description)
                            }
                        })
                        Divider(modifier = Modifier.padding(8.dp))
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("region: ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                                append(ele.region)
                            }
                        })
                        Divider(modifier = Modifier.padding(8.dp))
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("location: ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                                append(ele.location)
                            }
                        })
                        Divider(modifier = Modifier.padding(8.dp))
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("drops: ")
                            }
                            withStyle(style = SpanStyle(color = Color.Black , fontStyle =  FontStyle.Normal)) {
                                append(ele.drops.joinToString(","))
                            }
                        })
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesAppPreview() {
    MovieRow(ele = Boss(""
        , "Elden Ring"
        , ""
        , ""
        , ""
        , ""
        , emptyList()
        ,""), onClickItem = {})
}