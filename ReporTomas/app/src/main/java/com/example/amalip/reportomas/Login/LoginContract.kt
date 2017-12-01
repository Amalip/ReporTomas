package com.example.amalip.reportomas.Login

import com.facebook.AccessToken

/**
 * Created by Amalip on 11/28/2017.
 */
interface LoginContract {

    interface View{

    }

    interface Presenter {
        fun login(username : String, password : String)
        fun signUp()
        fun facebook()
    }

    interface Interactor {
        fun login(username : String, password : String)
        fun facebookLogin(token : AccessToken)
    }
}