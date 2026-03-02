package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Portfolio

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.util.*

@Composable
fun PortfolioScreen(
    viewModel: PortfolioViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {
        PortfolioTopBar()

        when (val s = state) {
            is PortfolioUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFFADFF2F))
                }
            }
            is PortfolioUiState.Success -> {
                PortfolioContent(s.portfolio, s.positions)
            }
            is PortfolioUiState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = s.message, color = Color.Red, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun PortfolioTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = "Patrimonio",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "USD/CLP",
            color = Color.Gray,
            fontSize = 14.sp,
            textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
        )
    }
}

@Composable
fun PortfolioContent(portfolio: Portfolio?, positions: List<Position>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            PortfolioSummary(portfolio)
            Spacer(modifier = Modifier.height(24.dp))
            PortfolioChart()
            Spacer(modifier = Modifier.height(24.dp))
            BuyingPowerSection(portfolio)
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Posiciones",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "VALOR DEL DÍA ⌄",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        if (positions.isEmpty()) {
            item {
                Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                    Text(text = "No hay posiciones disponibles", color = Color.Gray)
                }
            }
        } else {
            items(positions) { position ->
                PositionItem(position)
                HorizontalDivider(
                    color = Color.DarkGray,
                    thickness = 0.5.dp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        }
    }
}

@Composable
fun PortfolioSummary(portfolio: Portfolio?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "USD $${portfolio?.totalValue?.format() ?: "0.00"}",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            val performance = portfolio?.performancePercentage ?: BigDecimal.ZERO
            val color = if (performance >= BigDecimal.ZERO) Color(0xFFADFF2F) else Color.Red
            val symbol = if (performance >= BigDecimal.ZERO) "▲" else "▼"
            
            Text(text = symbol, color = color, fontSize = 12.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "$7.06 (${performance.format()}%) Hoy",
                color = color,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
fun PortfolioChart() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val path = Path().apply {
                    moveTo(0f, size.height * 0.8f)
                    lineTo(size.width * 0.2f, size.height * 0.6f)
                    lineTo(size.width * 0.4f, size.height * 0.7f)
                    lineTo(size.width * 0.6f, size.height * 0.4f)
                    lineTo(size.width * 0.8f, size.height * 0.5f)
                    lineTo(size.width, size.height * 0.2f)
                }
                drawPath(
                    path = path,
                    color = Color(0xFFADFF2F),
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("1D", "1W", "1M", "3M", "1Y", "5Y", "YTD").forEach { period ->
                Text(
                    text = period,
                    color = if (period == "YTD") Color.White else Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .background(
                            if (period == "YTD") Color(0xFF1E1E1E) else Color.Transparent,
                            RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun BuyingPowerSection(portfolio: Portfolio?) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Poder de compra",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "US DOLLARS", color = Color.Gray, fontSize = 12.sp)
                Text(
                    text = "$${portfolio?.cashBalance?.format() ?: "0.00"}",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "PESO CHILENO", color = Color.Gray, fontSize = 12.sp)
                Text(
                    text = "$42.788,54", 
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun PositionItem(position: Position) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = position.symbol,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(text = position.name, color = Color.Gray, fontSize = 12.sp)
        }
        Text(
            text = "$${position.marketValue.setScale(2, RoundingMode.HALF_UP)}",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

data class BottomNavItem(val label: String, val icon: ImageVector)

@Composable
fun PortfolioBottomNavigation(
    currentScreen: String,
    onItemSelected: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem("Trade", Icons.Default.AutoGraph),
        BottomNavItem("Wallet", Icons.Default.AccountBalanceWallet),
        BottomNavItem("Buscar", Icons.Default.Search),
        BottomNavItem("Mensajes", Icons.Default.ChatBubbleOutline),
        BottomNavItem("Cuenta", Icons.Default.PersonOutline)
    )

    NavigationBar(
        containerColor = Color(0xFF1E1E1E),
        contentColor = Color.White,
        tonalElevation = 0.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.label == currentScreen,
                onClick = { onItemSelected(item.label) },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label, fontSize = 10.sp) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFADFF2F),
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFFADFF2F),
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

fun BigDecimal.format(): String {
    val formatter = NumberFormat.getInstance(Locale.US)
    formatter.minimumFractionDigits = 2
    formatter.maximumFractionDigits = 2
    return formatter.format(this)
}
