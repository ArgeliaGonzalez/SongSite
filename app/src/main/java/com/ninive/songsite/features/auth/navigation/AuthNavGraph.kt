package com.ninive.songsite.features.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.ninive.songsite.core.navigation.FeatureNavGraph
import com.ninive.songsite.core.navigation.Login
import com.ninive.songsite.core.navigation.Register
import com.ninive.songsite.features.auth.presentation.screens.LoginScreen
import com.ninive.songsite.features.auth.presentation.screens.RegisterScreen
import javax.inject.Inject

class AuthNavGraph @Inject constructor() : FeatureNavGraph {
    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavHostController
    ) {
        navGraphBuilder.composable<Login> {
            LoginScreen(
                onLoginSuccess = {
                    // Navegar a Home u otra pantalla principal
                    navController.navigate(com.ninive.songsite.core.navigation.Songs) {
                        popUpTo<Login> { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Register)
                }
            )
        }

        navGraphBuilder.composable<Register> {
            RegisterScreen(
                onRegisterSuccess = {
                    // Navegar a Home u otra pantalla principal
                    navController.navigate(com.ninive.songsite.core.navigation.Songs) {
                        popUpTo<Register> { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
    }
}
