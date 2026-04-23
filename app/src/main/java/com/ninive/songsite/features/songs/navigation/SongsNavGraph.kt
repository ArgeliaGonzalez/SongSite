package com.ninive.songsite.features.songs.navigation

import com.ninive.songsite.core.navigation.FeatureNavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ninive.songsite.core.navigation.Songs
import com.ninive.songsite.features.songs.presentation.screens.SongsScreen
import javax.inject.Inject

class SongsNavGraph @Inject constructor() : FeatureNavGraph {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<Songs> {
            SongsScreen()
        }
    }
}