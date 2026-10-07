package viewmodel

import model.UsuarioErrores
import model.UsuarioUiState
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class UsuarioViewModel: ViewModel() {
    private val _estado = MutableStateFlow(UsuarioUiState())

    val estado: StateFlow<UsuarioUiState> = _estado


    fun onCorreoChange(valor: String){
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    fun onClaveChange(valor: String){
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }



    fun validarInicioSesion(): Boolean{
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            correo = if (estadoActual.correo.isBlank()) "Campo Obligatorio" else if (!estadoActual.correo.contains("@")) "Correo Invalido" else null,
            clave = if (estadoActual.clave.isBlank()) "Debe Rellenar este campo" else null
        )

        val hayErrores = listOfNotNull(
            errores.correo,
            errores.clave,
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores
    }

    fun validarCredenciales(): Boolean{
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            correo = if (estadoActual.correo == "admin@guardian.net" || estadoActual.correo == "supervisor@guardian.net" || estadoActual.correo == "operador@guardian.net") null else "Credenciales invalidas",
            clave = if (estadoActual.clave != "123456") "Credenciales invalidas" else null
        )

        val hayErrores = listOfNotNull(
            errores.correo,
            errores.clave
        ).isNotEmpty()

        _estado.update {  it.copy(errores = errores) }

        return !hayErrores
    }

    fun tipoInicio(): String{
        val estadoActual = _estado.value
        val tipoUser = UsuarioUiState(
            tipo = (if (estadoActual.correo == "admin@guardian.net") "admin" else
                if (estadoActual.correo == "supervisor@guardian.net") "supervisor" else
                    if (estadoActual.correo == "operador@guardian.net") "operador" else null).toString()
        )

        return tipoUser.tipo
    }

}