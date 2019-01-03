package weechan.com.whatsforlunch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.test.*
import okhttp3.Call
import okhttp3.OkHttpClient
import rx.Observable
import weechan.com.common.utils.loading.Loading
import weechan.com.common.utils.loading.LoadingBuilder
import weechan.com.common.utils.showToast
import weechan.com.whatsforlunch.data.DishesCategory
import weechan.com.whatsforlunch.ui.dishes.DishesCategoryAdapter
import java.util.*
import kotlin.concurrent.thread

class Test : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test)


        recycler.adapter = DishesCategoryAdapter(mutableListOf(
                DishesCategory("1", 1, true),
                DishesCategory("1", 1, true),
                DishesCategory("1", 1, true),
                DishesCategory("1", 1, true),
                DishesCategory("1", 1, true)))
        recycler.layoutManager = LinearLayoutManager(this)

        val loading = LoadingBuilder(this).setOnRetryClickListener {
            showToast("click")
        }.build(recycler)

        retry.setOnClickListener {
            loading.retry()
            loading.findViewById<TextView>(R.id.retry_text).setText("GOOOD")
        }

        load.setOnClickListener {
            loading.load()
        }

        finish.setOnClickListener {
            loading.finish()
        }

        error.setOnClickListener {
            loading.error()
        }
\

//        butto2n.setOnClickListener {
//            startActivity(Intent(this, Test::class.java))
//        }
//
//
        Call
//
//        button.setOnClickListener {
//            //            Loading.defaultLoad(recycler)
//            loading.load()
//        }
//
////        butto2n.setOnClickListener{
//////            Loading.defaultError(recycler)
////            loading.finish(recycler)
////        }
////        butto2n.setOnClickListener {
////            Loading.error(recycler, ErrorView(this).apply { setImageResource(R.drawable.ic_menu_share) })
////        }
//
//        var a = true;
//        btn3.setOnClickListener {
//            if (a) {
//                loading.retry()
//                a = false
//            } else {
//                loading.finish(it)
//                a = true
//
//            }
//
//        }
    }
}
