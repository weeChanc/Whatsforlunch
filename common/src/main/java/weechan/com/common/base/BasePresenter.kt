package weechan.com.common.base

import android.arch.lifecycle.*

/**
 *
 * @author 214652773@qq.com
 * @user c
 * @onCreate 2018/10/21 16:31
 *
 */

abstract class BasePresenter<V:BaseView<IPresenter>>(var view : V?) : IPresenter{

    init {
       view?.lifecycle?.addObserver(Listener())
    }

    inner class Listener : LifecycleObserver{

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate(owner: LifecycleOwner) {
            onCreate()
        }


        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {
            onDestory()
            //及时设空,防止内存泄漏
            view = null;
            System.gc()
        }
        
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        fun onStop(owner: LifecycleOwner){
            onStop()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume(owner: LifecycleOwner){
            onResume()
        }
    }


    fun onDestory(){
        // Overwrite it if you need
    }

    fun onCreate(){
        // Overwrite it if you need
    }

    fun onStop(){
        // Overwrite it if you need
    }

    fun onResume(){
        // Overwrite it if you need
    }
}