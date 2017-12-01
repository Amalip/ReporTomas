package com.example.amalip.reportomas.Register

import com.facebook.AccessToken

/**
 * Created by Amalip on 11/29/2017.
 */
interface RegisterContract {

    interface Presenter {
        fun register(name : String, lastName : String, email : String, password : String, phone : String)
        fun registerFB()
    }

    interface Interactor {
        fun register(name : String, lastName : String, email : String, password : String, phone : String)
        fun registerFB(token : AccessToken)
    }
}