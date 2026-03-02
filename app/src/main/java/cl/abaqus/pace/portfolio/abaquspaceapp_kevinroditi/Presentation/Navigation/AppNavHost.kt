package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Portfolio.PortfolioScreen
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Search.SearchScreen
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Trade.TradeScreen
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Messages.MessagesScreen
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Account.AccountScreen

sealed class Screen(val route: String) {
    object Portfolio : Screen("portfolio")
    object Search : Screen("search")
    object Trade : Screen("trade")
    object Messages : Screen("messages")
    object Account : Screen("account")
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Portfolio.route,
        modifier = modifier
    ) {
        composable(Screen.Portfolio.route) {
            PortfolioScreen(
                onNavigateToSearch = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
        composable(Screen.Search.route) {
            SearchScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Trade.route) {
            TradeScreen()
        }
        composable(Screen.Messages.route) {
            MessagesScreen()
        }
        composable(Screen.Account.route) {
            AccountScreen()
        }
    }
}
