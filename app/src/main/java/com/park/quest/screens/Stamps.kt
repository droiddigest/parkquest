package com.park.quest.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.park.quest.animations.SmallScaleAnimation
import com.park.quest.components.HorizontalPagerItem
import com.park.quest.components.VerticalPagerItem
import com.park.quest.routes.AppRoutes
import com.park.quest.screens.StampsUtility.PHOTOS_COUNT
import com.park.quest.viewmodels.LocalAppViewModel
import com.park.quest.viewmodels.ParksViewModel

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Stamps(horizontalPageIndex: Int = 0, verticalPageIndex: Int = 0) {
    val navController: NavHostController? = LocalAppViewModel.current.appViewModel?.navController

    val parkViewModel: ParksViewModel = hiltViewModel()

    val parks by parkViewModel.parksViewState.collectAsState()

    val horizontalPagerState = rememberPagerState(initialPage = horizontalPageIndex)

    var fabVisibility by remember {
        mutableStateOf(false)
    }

    Scaffold(
        floatingActionButton = {
            SmallScaleAnimation(fabVisibility) {
                FloatingActionButton(
                    modifier = Modifier.size(56.dp).offset((-20).dp),
                    onClick = { navController?.navigate(AppRoutes.SEARCH.name) },
                    containerColor = com.park.quest.Color.Orange
                ) {
                        Icon(
                            Icons.Filled.Search,
                            modifier = Modifier.size(40.dp),
                            contentDescription = "search",
                            tint = com.park.quest.Color.DarkGrey
                        )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        HorizontalPager(
            modifier = Modifier.background(com.park.quest.Color.DarkGrey),
            pageCount = parks.size,
            state = horizontalPagerState,
            beyondBoundsPageCount = 3
        ) {
            val park = parks[it]

            val verticalPagerState: PagerState = rememberPagerState()

            fabVisibility =
                verticalPagerState.currentPage != 0 && !verticalPagerState.isScrollInProgress

            VerticalPager(
                pageCount = PHOTOS_COUNT,
                state = verticalPagerState,
                beyondBoundsPageCount = 6
            ) { verticalPageIndex ->
                if (verticalPageIndex == 0) {
                    HorizontalPagerItem(park = park, pageIndex = it)
                } else {
                    VerticalPagerItem(park = park)
                }

            }
        }
    }
}

object StampsUtility {
    const val PHOTOS_COUNT = 10
    const val PHOTOS_ENDPOINT = "https://source.unsplash.com/random"
}