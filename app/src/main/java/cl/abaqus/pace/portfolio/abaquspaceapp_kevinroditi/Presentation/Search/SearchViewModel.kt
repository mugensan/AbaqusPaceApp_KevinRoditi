package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PortfolioRepository
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    val results: StateFlow<List<Position>> =
        _query
            .debounce(300)
            .flatMapLatest { repository.searchPositions(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun onQueryChanged(value: String) {
        _query.value = value
    }
}
