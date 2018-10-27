package weechan.com.whatsforlunch.ui.register

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.selector
import weechan.com.whatsforlunch.R

class RegisterActivity : AppCompatActivity(), RegisterContract.View{

    override lateinit var presenter: RegisterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(register_toolbar)

        presenter = RegisterContract.Presenter(this)

        city.setOnClickListener {
            val cityList = presenter.getCityList()
            selector("请选择城市",cityList){
                _, position ->
                city.setText(cityList.get(position))
            }
        }

    }
}
