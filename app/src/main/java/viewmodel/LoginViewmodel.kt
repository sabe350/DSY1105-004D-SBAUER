package viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import LoginState
import android.content.Context
import androidx.credentials.exceptions.NoCredentialException
import androidx.lifecycle.viewModelScope
import com.example.InsertCode.AuthManager
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewmodel(private val application: Application) : AndroidViewModel(application) {

    private val _loginState : MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginState : StateFlow<LoginState> = _loginState.asStateFlow()

    private val authManager : AuthManager by lazy { AuthManager(application.applicationContext) }

    fun login(activityContext: Context){
        viewModelScope.launch {
            val result = authManager.signInWithGoogle()
            result.errorMessage?.let {
                _loginState.value = LoginState(loginStateType = LoginState.LoginStateType.ERROR, errorMessage = it)
                return@launch
            }

            _loginState.value = LoginState(loginStateType = LoginState.LoginStateType.LOGGED_IN, result.UsuarioUiState)
        }
    }

    fun logOut(){
        viewModelScope.launch {
            authManager.signOut()
            _loginState.value = LoginState(LoginState.LoginStateType.LOGGED_OUT)
        }
    }

}