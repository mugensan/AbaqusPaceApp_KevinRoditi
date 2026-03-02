package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Account

import androidx.lifecycle.ViewModel
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase.GetAccountDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getAccountDataUseCase: GetAccountDataUseCase
) : ViewModel() {
    // Implement ViewModel logic here
}
