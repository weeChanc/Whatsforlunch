package weechan.com.common.utils.permission


import android.app.Activity

import androidx.core.app.ActivityCompat

import java.util.*
import kotlin.concurrent.thread


/**
 * Created by jimji on 2017/9/12.
 */
class PermissionMan(private var ctx: Activity) {
    private val queue: Queue<PermissionTask> by lazy { ArrayDeque<PermissionTask>() }
    private var permissionGetting: PermissionTask? = null

    fun onResult() {
        if (permissionGetting == null) {
            return
        }
        if (permissionGetting!!.permission.has()) {
             permissionGetting!!.callBack.onPassed()

        } else {
             permissionGetting!!.callBack.onDenied()
        }
        queue.poll()
        permissionGetting = null
    }

    private fun loop() {
        while (queue.isNotEmpty()) {
            while (permissionGetting != null) {
                //blocking the thread
            }

            if (queue.peek() == null) {
                return
            }
            permissionGetting = queue.peek()
            if (!permissionGetting!!.permission.has()) {
                ActivityCompat.requestPermissions(ctx, arrayOf(permissionGetting!!.permission.value), 1)
            } else {
                onResult()
            }
        }


    }

    fun queue(task: PermissionTask) {
        if (queue.isEmpty()) {
            queue.offer(task)
            thread { loop() }
        } else {
            queue.offer(task)
        }
    }

}
