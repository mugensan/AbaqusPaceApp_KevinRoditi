package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.core.result.DomainResult
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase.GetPortfolioUseCase
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getPortfolioUseCase: GetPortfolioUseCase,
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PortfolioUiState>(PortfolioUiState.Loading)
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

    init {
        loadPortfolio()
    }

    fun loadPortfolio() {
        viewModelScope.launch {
            _uiState.value = PortfolioUiState.Loading
            when (val result = getPortfolioUseCase.execute()) {
                is DomainResult.Success -> {
                    _uiState.value = PortfolioUiState.Success(
                        portfolio = result.data,
                        positions = result.data.positions
                    )
                }
                is DomainResult.Failure -> {
                    _uiState.value = PortfolioUiState.Error(
                        message = result.error.toString()
                    )
                }
            }
        }
    }

    fun search(query: String) {
        if (query.isBlank()) {
            loadPortfolio()
            return
        }
        viewModelScope.launch {
            searchUseCase(query).collect { positions ->
                val currentState = _uiState.value
                if (currentState is PortfolioUiState.Success) {
                    _uiState.value = currentState.copy(positions = positions)
                }
            }
        }
    }
}
