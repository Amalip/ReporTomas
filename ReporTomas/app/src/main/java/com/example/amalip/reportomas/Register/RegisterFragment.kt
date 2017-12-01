package com.example.amalip.reportomas.Register

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
class RegisterFragment : Fragment() {
    val edtName : EditText by bindView(R.id.edtName)
    val edtLastName : EditText by bindView(R.id.edtLastName)
    val edtEmail : EditText by bindView(R.id.edtEmail)
    val edtPassword : EditText by bindView(R.id.edtPassword)
    val edtPhone : EditText by bindView(R.id.edtPhone)
    val btnSignUp : Button by bindView(R.id.btnSignR)
    val btnLoginFb : LoginButton by bindView(R.id.login_button)
    lateinit var presenter : RegisterPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_register, container, false)

        val activity = context as BaseActivity
        presenter = RegisterPresenter(activity, this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSignUp.setOnClickListener { presenter.register(edtName.text.toString(), edtLastName.text.toString()
                , edtEmail.text.toString(), edtPassword.text.toString(), edtPhone.text.toString()) }
        btnLoginFb.setReadPermissions("email", "public_profile")

    }

}