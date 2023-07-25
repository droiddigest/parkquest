package com.park.quest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.park.quest.database.Park
import com.park.quest.routes.AppRoutes
import com.park.quest.viewmodels.LocalAppViewModel
import com.park.quest.viewmodels.SearchViewModel

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@Composable
fun Search() {
    val searchViewModel: SearchViewModel = hiltViewModel()

    var searchTerm by remember { mutableStateOf("") }

    val state by searchViewModel.searchViewState.collectAsState()

    val parks by remember(state) {
        derivedStateOf { state.sortedBy { it.name } }
    }

    val navController = LocalAppViewModel.current.appViewModel?.navController

    Column(
        modifier = Modifier
            .padding(
                horizontal = 12.dp,
                vertical = 24.dp
            )
    ) {
        SearchBar(
            query = searchTerm,
            onTextChanged = {
                searchTerm = it
                searchViewModel.searchParks(it)
            }
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
                .background(Color.White)
        )
        SearchItems(parks, navController)
    }
}

@Composable
fun SearchItems(parks: List<Park>, navController: NavController?) {
    LazyColumn {
        items(parks) { park ->
            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { navController?.navigate("${AppRoutes.STAMPS}/${park.pageId - 1}/0") }) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = park.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Left
                )
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                        .background(Color.White)
                )
            }
        }
    }
}

@Composable
fun SearchBar(query: String, onTextChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = query,
        onValueChange = { onTextChanged(it) },
        placeholder = {
            Text(
                "National Parks",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.alpha(0.7f)
            )
        },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(
                Icons.Filled.Search,
                contentDescription = "search",
                tint = Color.White,
                modifier = Modifier.alpha(0.7f)
            )
        },
        trailingIcon = {
            when {
                query.isNotEmpty() -> Icon(
                    Icons.Filled.Close,
                    contentDescription = "close",
                    tint = Color.White,
                    modifier = Modifier
                        .alpha(0.7f)
                        .clickable { onTextChanged("") }
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            disabledContainerColor = Color.DarkGray,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
        ),
    )
}