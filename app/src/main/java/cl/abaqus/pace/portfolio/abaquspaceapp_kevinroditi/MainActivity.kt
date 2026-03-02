package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Navigation.AppNavHost
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Navigation.Screen
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Portfolio.PortfolioBottomNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbaqusPaceApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbaqusPaceApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val screenTitle = when (currentRoute) {
        Screen.Portfolio.route -> "Patrimonio"
        Screen.Search.route -> "Buscar"
        Screen.Trade.route -> "Trade"
        Screen.Messages.route -> "Mensajes"
        Screen.Account.route -> "Cuenta"
        else -> "Patrimonio"
    }

    Scaffold(
        topBar = {
            if (currentRoute != Screen.Portfolio.route) {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = screenTitle,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Color(0xFF121212)
                    )
                )
            }
        },
        bottomBar = {
            PortfolioBottomNavigation(
                currentScreen = when (currentRoute) {
                    Screen.Portfolio.route -> "Wallet"
                    Screen.Search.route -> "Buscar"
                    Screen.Trade.route -> "Trade"
                    Screen.Messages.route -> "Mensajes"
                    Screen.Account.route -> "Cuenta"
                    else -> "Wallet"
                },
                onItemSelected = { item ->
                    val route = when (item) {
                        "Trade" -> Screen.Trade.route
                        "Wallet" -> Screen.Portfolio.route
                        "Buscar" -> Screen.Search.route
                        "Mensajes" -> Screen.Messages.route
                        "Cuenta" -> Screen.Account.route
                        else -> Screen.Portfolio.route
                    }
                    
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        },
        containerColor = Color(0xFF121212)
    ) { paddingValues ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(paddingValues)
        )
    }
}
