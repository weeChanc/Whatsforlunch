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

        fun buildForm() = MultipartBody.Builder().setType(MultipartBody.FORM)

//        MultipartBody.Builder().setType(MultipartBody.FORM)
//        .addFormDataPart("name", dish.productName)
//        .addFormDataPart("description", dish.productDescription)
//        .addFormDataPart("icon", File(dish.productIcon).name,
//        RequestBody.create(MediaType.parse("image/*"), File(dish.productIcon)))
//        .addFormDataPart("category_id", id.toString())
    }
}