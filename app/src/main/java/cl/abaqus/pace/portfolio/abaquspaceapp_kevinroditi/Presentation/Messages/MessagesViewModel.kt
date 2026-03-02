package cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.Presentation.Messages

import androidx.lifecycle.ViewModel
import cl.abaqus.pace.portfolio.abaquspaceapp_kevinroditi.domain.usecase.GetMessagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MessagesViewModel @Inject constructor(
    private val getMessagesUseCase: GetMessagesUseCase
) : ViewModel() {
    // Implement ViewModel logic here
}
