package com.quin.android.play.ui.login

/**
 * Data validation state of the onLoginClicked form.
 */
data class LoginFormState(val usernameError: Int? = null,
                          val passwordError: Int? = null,
                          val isDataValid: Boolean = false)
