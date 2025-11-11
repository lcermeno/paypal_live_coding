package com.lcermeno.reddit.paypal.presentation.login

data class LoginState(
    val username: String = "",
    val password: String = "",
    val errorMessage: String? = null
)