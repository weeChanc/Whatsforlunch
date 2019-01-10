package weechan.com.whatsforlunch.ui.register

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Base64
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.indeterminateProgressDialog
import weechan.com.common.utils.album.AlbumPicker
import weechan.com.common.utils.album.AlbumPickerActivity
import weechan.com.common.utils.showToast
import weechan.com.common.utils.Maps
import weechan.com.whatsforlunch.R
import java.io.File

class RegisterActivity : AlbumPickerActivity(), RegisterContract.View {
    override fun registerSuccess() {
        finish();
    }

    override lateinit var presenter: RegisterContract.Presenter

    private var dialog: ProgressDialog? = null

    private var shop_image: String? = null

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

        image_store.setOnClickListener {
            AlbumPicker.with(this).selectedPicAndHandle { path ->
                if (path != null) {
                    shop_image = path
                    Glide.with(this@RegisterActivity).load(path).into(it as ImageView)
                }
            }
        }


        submit.setOnClickListener {
            if (shop_image == null) {
                showToast("请填写完整信息")
                return@setOnClickListener
            }
            presenter.register(File(shop_image), Maps.buildMap {
                "sellername" - name.text.toString()
                "store_name" - register_shopname.text.toString()
                "phone" - phone.text.toString()
                "position" - position.text.toString()
                "password" - pass.text.toString()
                "idcard" - idcard.text.toString()
            })
        }
    }
}
