package weechan.com.whatsforlunch.utils

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/31 18:44
 *
 */


class Oks{
    companion object {

        fun formFilePart(key : String , file : File): MultipartBody.Part {
            val requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            return MultipartBody.Part.createFormData(key,file.name,requestBody)
        }
    }
}