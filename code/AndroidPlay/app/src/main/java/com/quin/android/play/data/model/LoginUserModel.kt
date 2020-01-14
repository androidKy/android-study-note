package com.quin.android.play.data.model

/**
 * Description:登录用户数据用例
 * Created by Quinin on 2020-01-04.
 **/
data class LoginUserModel(val userName: String?, val userPsw: String?) {

    fun isUserNameValid(): Boolean {
        return !userName.isNullOrBlank()
    }

    fun isPswValid(): Boolean {
        return !userPsw.isNullOrEmpty() && userPsw.length > 5
    }
}