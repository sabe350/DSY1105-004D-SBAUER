package model

data class UsuarioUiState(
    val correo: String = "",
    var clave: String = "",
    var clave2: String = "",
    val tipo: String = "",
    val aceptaTerminos: Boolean = false,
    val errores: UsuarioErrores = UsuarioErrores()
)
