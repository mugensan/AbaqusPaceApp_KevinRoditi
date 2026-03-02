package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Trade

import androidx.lifecycle.ViewModel
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase.GetTradeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TradeViewModel @Inject constructor(
    private val getTradeDataUseCase: GetTradeDataUseCase
) : ViewModel() {
    // Implement ViewModel logic here
}
