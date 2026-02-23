package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.model.Position
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.repository.PortfolioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PortfolioRepository
) : ViewModel() {

    private val query = MutableStateFlow("")

    val results: StateFlow<List<Position>> =
        query
            .debounce { 300 }
            .flatMapLatest { repository.(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            )

    fun onQueryChanged(value: String) {
        query.value = value

    }
}