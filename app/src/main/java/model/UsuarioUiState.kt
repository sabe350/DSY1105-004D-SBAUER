package model

data class UsuarioUiState(
    val correo: String = "",
    val clave: String = "",
    val tipo: String = "",
    val errores: UsuarioErrores = UsuarioErrores()
)
