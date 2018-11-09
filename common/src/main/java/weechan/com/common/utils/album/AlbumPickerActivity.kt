package weechan.com.common.utils.album

import android.content.Intent
import weechan.com.common.utils.album.AlbumPicker
import weechan.com.common.utils.permission.PermissionCompatActivity

/**
 * Created by 铖哥 on 2017/11/8.
 */
open class AlbumPickerActivity : PermissionCompatActivity(){

    var albumPicker : AlbumPicker?  = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        albumPicker?.onActivityResult(requestCode,resultCode,data)
    }
}