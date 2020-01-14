package com.quin.android.play.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.quin.android.play.data.LoginRepository
import com.quin.android.play.data.Result
import com.quin.android.play.data.model.LoggedInUser
import com.quin.android.play.data.model.LoginUserModel
import java.io.IOException
import java.util.*

class LoginViewModel : ViewModel() {

    private val loginUserModelLiveData = MutableLiveData<LoginUserModel>()
    val loginUserNameLiveData = MutableLiveData<String>()
    val loginUserPswLiveData = MutableLiveData<String>()

    fun getLoginUserModel(): MutableLiveData<LoginUserModel> {

        return loginUserModelLiveData
    }

    /*  private val _loginForm = MutableLiveData<LoginFormState>()
      val loginFormState: LiveData<LoginFormState> = _loginForm

      private val _loginResult = MutableLiveData<LoginResult>()
      val loginResult: LiveData<LoginResult> = _loginResult*/

    /**
     * 登录按钮被点击，loginUserModelLiveData数据更新
     */
    fun onLoginClicked() {
        val loginUserModel = LoginUserModel(loginUserNameLiveData.value, loginUserPswLiveData.value)
        loginUserModelLiveData.value = loginUserModel
    }

    fun login():Result<LoggedInUser>{
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = LoggedInUser(UUID.randomUUID().toString(), loginUserNameLiveData.value!!)
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout(){

    }

    fun onLoginClicked(username: String, password: String) {
        // can be launched in a separate asynchronous job
        /*val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            // _loginResult.value = LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
        } else {
            // _loginResult.value = LoginResult(error = R.string.login_failed)
        }*/
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            //_loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            // _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            //_loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
