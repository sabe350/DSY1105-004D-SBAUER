package model

data class UsuarioUiState(
    val correo: String = "",
    var clave: String = "",
    val tipo: String = "",
    val errores: UsuarioErrores = UsuarioErrores()
)
