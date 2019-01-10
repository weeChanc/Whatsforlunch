package weechan.com.whatsforlunch.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

import weechan.com.whatsforlunch.R
import weechan.com.whatsforlunch.ui.main.MainActivity
import weechan.com.whatsforlunch.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override lateinit var presenter: LoginContract.Presenter

    override fun startLogin() {
        progressBar.visibility = View.VISIBLE
    }

    override fun endLogin() {
        progressBar.visibility = View.GONE
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginContract.Presenter(this)
        setupListener()
    }

    private fun setupListener() {

        register.setOnClickListener {

            launch {
                register.isClickable = false
                delay(300)
                register.isClickable = true
            }
            startActivity(Intent(this, RegisterActivity::class.java))

        }

        login.setOnClickListener {
            presenter.login(account.editableText.toString(), password.editableText.toString())

        }

    }
}
