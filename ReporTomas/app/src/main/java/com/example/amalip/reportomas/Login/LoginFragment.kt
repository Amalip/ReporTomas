package com.example.amalip.reportomas.Login

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.example.amalip.reportomas.R
import com.example.amalip.reportomas.Utilities.BaseActivity
import com.facebook.login.widget.LoginButton
import kotterknife.bindView

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {
    val btnLogin : Button by bindView(R.id.btnLogin)
    val btnSignUp : Button by bindView(R.id.btnSignUp)
    val edtUsername : EditText by bindView(R.id.edtUsername)
    val edtPassword : EditText by bindView(R.id.edtPassword)
    val btnLoginFb : LoginButton by bindView(R.id.login_button)
    lateinit var activity : BaseActivity
    lateinit var presenter : LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_login, container, false)

        activity = context as BaseActivity
        presenter = LoginPresenter(activity, this)

        return view
    }

    override fun onResume() {
        super.onResume()
        btnLogin.setOnClickListener { presenter.login(edtUsername.text.toString(), edtPassword.text.toString()) }
        btnSignUp.setOnClickListener { presenter.signUp() }
        btnLoginFb.setReadPermissions("email", "public_profile")
    }
}