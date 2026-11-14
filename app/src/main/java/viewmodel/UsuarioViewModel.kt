package viewmodel

import model.UsuarioErrores
import model.UsuarioUiState
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update


class UsuarioViewModel: ViewModel() {


    private val _estado = MutableStateFlow(UsuarioUiState())

    val estado: StateFlow<UsuarioUiState> = _estado


    fun onCorreoChange(valor: String){
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }


    fun onAceptarTerminosChange(valor: Boolean){
        _estado.update { it.copy(aceptaTerminos = valor) }
    }

    fun onClaveChange(valor: String){
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    fun onClave2Change(valor: String){
        _estado.update { it.copy(clave2 = valor, errores = it.errores.copy(clave = null)) }
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

    fun validarCredenciales(onExito: (String) -> Unit) {
        if (!validarInicioSesion()) return

        val estadoActual = _estado.value

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(estadoActual.correo.toString(), estadoActual.clave.toString())
            .addOnCompleteListener { tarea ->
                if (tarea.isSuccessful){
                    _estado.update { it.copy(errores = UsuarioErrores(correo = null, clave = null)) }
                    onExito(tipoInicio())
                }else
                    _estado.update {
                        it.copy(
                            errores = UsuarioErrores(
                                correo = "Credenciales invalidas",
                                clave = "Credenciales invalidas"
                            )
                        )
                    }
            }
    }

    fun tipoInicio(): String{
        return  when (_estado.value.correo){
            "admin@guardian.net" -> "admin"
            "supervisor@guardian.net" -> "supervisor"
            "operador@guardian.net" -> "operador"
            else -> "admin"
        }

    }

    fun validarFomulario(): Boolean {
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            correo = if (estadoActual.correo.isBlank()) "Debe rellenar este campo" else if (!estadoActual.correo.contains("@")) "Correo Invalido" else null,
            clave = if (estadoActual.clave.isBlank()) "Debe rellenar este campo" else if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else if (estadoActual.clave != estadoActual.clave2) "Contraseña no coincide" else null,
            clave2 = if (estadoActual.clave.isBlank()) "Debe rellenar este campo" else if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else if (estadoActual.clave2 != estadoActual.clave) "Contraseña no coincide" else null,
        )

        val hayErrores = listOfNotNull(
            errores.correo,
            errores.clave,
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return !hayErrores
    }

    fun logOut(){
        _estado.value = UsuarioUiState()
    }
    fun Regsitro(){
        _estado.value = UsuarioUiState()
    }

}