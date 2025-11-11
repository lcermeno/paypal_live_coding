package com.lcermeno.reddit.paypal.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lcermeno.reddit.paypal.data.repository.AuthRepositoryImpl
import com.lcermeno.reddit.paypal.domain.model.Credentials
import com.lcermeno.reddit.paypal.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val repository: AuthRepository = AuthRepositoryImpl()

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState>
        get() = _uiState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?>
        get() = _errorState.asStateFlow()

    private val _navigate = MutableStateFlow(false)
    val navigate: StateFlow<Boolean>
        get() = _navigate.asStateFlow()

    fun login() {
        val credentials = Credentials(
            username = _uiState.value.username,
            password = _uiState.value.password
        )
        val credentialsError = credentials.hasError()
        if (credentialsError == null) {
            repository
                .login(credentials).onEach { result ->
                    if (result) {
                        _navigate.update { true }
                    } else {
                        _errorState.update {
                            "Invalid credentials"
                        }
                    }
                }.launchIn(viewModelScope)
        } else {
            _errorState.update {
                credentialsError
            }
        }
    }

    fun onUsernameChanged(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun onPasswordChanged(password: String) {
        _uiState.update { it.copy(password = password) }
    }
}

private fun Credentials.hasError(): String? {
    return when {
        username.isBlank() -> "username is empty"
        password.isBlank() -> "password is empty"
        else -> null
    }
}
