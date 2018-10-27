package weechan.com.whatsforlunch.ui.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.ui.main.MainActivity
import weechan.com.whatsforlunch.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() , LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter

    override fun startLogin() {
        progressBar.visibility = View.VISIBLE
    }

    override fun endLogin() {
        progressBar.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginContract.Presenter(this)
        setupListener()
    }

    private fun setupListener(){

        register.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }

        login.setOnClickListener {
            presenter.login(account.editableText.toString(),password.editableText.toString())
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}
