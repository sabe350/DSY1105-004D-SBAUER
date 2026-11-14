package com.example.InsertCode

import android.app.Activity
import android.content.Context
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.compose.runtime.Composable
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import model.Resultado
import model.UsuarioUiState


class AuthManager (private val context: Context) {
    private val firebaseAuth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val credentialManager: CredentialManager by lazy { CredentialManager.create(context) }
    private val getGoogleIdOption: GetGoogleIdOption by lazy {
        GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId("849718066612-uctb4r8v5ih37936n9dcmht0hm1b5oec.apps.googleusercontent.com")
            .build()
    }
    private val getCredentialRequest: GetCredentialRequest by lazy {
        GetCredentialRequest.Builder()
            .addCredentialOption(getGoogleIdOption)
            .build()

    }

    suspend fun signInWithGoogle(): Resultado {
        try {
            val credential =
                credentialManager
                    .getCredential(context, getCredentialRequest)
                    .credential
            if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                val idToken = googleIdTokenCredential.idToken

                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                firebaseAuth.signInWithCredential(firebaseCredential).await()

                googleIdTokenCredential.apply {
                    val usuarioUiState = UsuarioUiState(correo = id)
                    val resultado = Resultado(usuarioUiState)
                    return resultado

                }
            } else {
                return Resultado(errorMessage = "Error de Google")

            }
        } catch (e: Exception) {
            return Resultado(errorMessage = e.toString())
        }
    }

    suspend fun signOut() {
        firebaseAuth.signOut()
        credentialManager.clearCredentialState(ClearCredentialStateRequest())
    }
}

