package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Portfolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Portfolio
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.use_case.GetPortfolioUseCase
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.use_case.SearchPositionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PortfolioViewModel @Inject constructor(
    private val getPortfolioUseCase: GetPortfolioUseCase,
    private val searchPositionsUseCase: SearchPositionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<PortfolioUiState>(PortfolioUiState.Loading)
    val uiState: StateFlow<PortfolioUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getPortfolioUseCase().collect { (positions, portfolio) ->
                _uiState.value = PortfolioUiState.Success(positions = positions)
            }
        }
    }

    fun refresh() {
        viewModelScope.launch {
            getPortfolioUseCase.refresh()
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            searchPositionsUseCase(query).collect { positions ->
                val currentState = _uiState.value
                if (currentState is PortfolioUiState.Success) {
                    _uiState.value = currentState.copy(positions = positions)
                }
            }
        }
    }
}
