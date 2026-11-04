package model

data class UsuarioErrores(
    val correo: String? = null,
    val clave:  String? = null,
    val clave2:  String? = null,
    val check: Boolean? = false
)