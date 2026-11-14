import model.UsuarioUiState

data class LoginState (
    val loginStateType: LoginStateType = LoginStateType.LOGGED_OUT,
    val uiState: UsuarioUiState? = null,
    val errorMessage : String? = null
) {
    enum class LoginStateType{
        LOGGED_IN, LOGGED_OUT, LOADING, ERROR
    }
}