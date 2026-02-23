package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Outline
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {
    val results by viewModel.results.collectAsState()

    Column {
        OutlinedTextField(
            value = "",
            onValueChange = viewModel::onQueryChanged,
            label = {
                Text(
                    "Buscar activo"
                )
                LazyColumn {
                    items(results) { position ->
                        SearchItem(position)
                    }
                }
            }
        )
    }
}

