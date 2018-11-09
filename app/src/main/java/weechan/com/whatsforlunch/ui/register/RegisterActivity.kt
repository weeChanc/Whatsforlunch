package weechan.com.whatsforlunch.ui.register

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.selector
import weechan.com.common.utils.album.AlbumPicker
import weechan.com.common.utils.album.AlbumPickerActivity
import weechan.com.common.utils.showToast
import weechan.com.common.utils.Maps
import weechan.com.whatsforlunch.R
import java.io.File

class RegisterActivity : AlbumPickerActivity(), RegisterContract.View {

    override lateinit var presenter: RegisterContract.Presenter

    private var dialog: ProgressDialog? = null

    private var yyzz: String? = null
    private var sfz: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setSupportActionBar(register_toolbar)

        presenter = RegisterContract.Presenter(this)

        setupListener()

    }

    override fun showUploadDialog() {
        if(dialog == null){
            dialog = indeterminateProgressDialog("注册中,请稍等").apply { this.setCancelable(false) }
        }else{
            dialog?.show()
        }

    }

    override fun closeUploadDialog() {
        dialog?.dismiss()
    }

    private fun setupListener() {
        city.setOnClickListener {
            val cityList = presenter.getCityList()
            selector("请选择城市", cityList) { _, position ->
                city.setText(cityList.get(position))
            }
        }

        image_sfz.setOnClickListener {
            AlbumPicker.with(this).selectedPicAndHandle { path ->
                if (path != null) {
                    sfz = path
                    Glide.with(this@RegisterActivity).load(path).into(it as ImageView)
                }
            }
        }

        image_yyzz.setOnClickListener {
            AlbumPicker.with(this).selectedPicAndHandle { path ->
                if (path != null) {
                    showToast("load")
                    yyzz = path
                    Glide.with(this@RegisterActivity).load(path).into(it as ImageView)
                }
            }
        }

        submit.setOnClickListener {
            if (yyzz == null || sfz == null) {
                showToast("请填写完整信息")
                return@setOnClickListener
            }
            presenter.register(File(yyzz), File(sfz), Maps.buildMap {
                "sellerName" - name.text.toString()
                "storeName" - register_shopname.text.toString()
                "phone" - phone.text.toString()
                "idcardNumber" - "441900199239111438"
                "position" - "广东工业大学南塘村"
            })
        }
    }
}
