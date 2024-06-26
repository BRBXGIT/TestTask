package com.example.foodies.auth.presentation

import androidx.lifecycle.ViewModel
import com.example.foodies.auth.google_auth.SignInResult
import com.example.foodies.auth.google_auth.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInGoogleVM: ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInErrorMessage = result.errorMessage
        ) }
    }

    fun resetState() {
        _state.update { SignInState() }
    }
}