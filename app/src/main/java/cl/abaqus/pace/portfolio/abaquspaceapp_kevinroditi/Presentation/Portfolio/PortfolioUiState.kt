package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Portfolio

import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position

sealed interface PortfolioUiState {
    object Loading : PortfolioUiState
    data class Success(
        val portfolio: Portfolio? = null,
        val positions: List<Position> = emptyList()
    ) : PortfolioUiState
    data class Error(val message: String) : PortfolioUiState
}
