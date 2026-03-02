package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Portfolio.PortfolioScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioScreen()
        }
    }
}
