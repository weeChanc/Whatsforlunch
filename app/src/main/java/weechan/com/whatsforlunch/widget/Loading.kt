//package weechan.com.whatsforlunch.widget
//
//import android.content.Context
//import android.util.Log
//import android.view.Gravity
//import android.view.LayoutInflater
//import android.view.View
//import android.view.View.*
//import android.view.ViewGroup
//import android.view.ViewGroup.LayoutParams.MATCH_PARENT
//import android.widget.FrameLayout
//import androidx.annotation.LayoutRes
//import weechan.com.whatsforlunch.R
//import weechan.com.whatsforlunch.widget.LoadingTag.*
//
///**
// *
// * @author 214652773@qq.com
// * @user c
// * @create 2018/11/7 12:58
// *
// */
//
//
//class Loading(private val loadingView : View) {
//
//    companion object {
//        private val MAGIC_NUMBER = R.id.loading_tag_state
//        private val loading = Loading()
//
//        fun load(target: View) = loading.load(target)
//        fun error(target: View) = loading.load(target)
//        fun retry(target: View) = loading.load(target)
//    }
//
//
//    fun load(@LayoutRes layoutId: Int, context: Context) {
//        val view = LayoutInflater.from(context).inflate(layoutId, null, false)
//    }
//
//    fun load(target: View) {
//        display(target, getCurrentSate(target), LOADING_STATE)
//    }
//
//    fun error(target: View) {
//        display(target, getCurrentSate(target), ERROR_STATE)
//    }
//
//    fun retry(target: View) {
//        display(target, getCurrentSate(target), ERROR_STATE)
//    }
//
//    private fun getCurrentSate(target: View): String {
//        var state = target.getTag(MAGIC_NUMBER) as String?
//        if (state == null) {
//            state = NONE_STATE
//        }
//        return state
//    }
//
//    private fun display(target: View, currentState: String, targetState: String) {
//        if (targetState == currentState) return
//
//        target.visibility = INVISIBLE
//        var wrapper = (target.parent as ViewGroup)
//                .findViewWithTag<FrameLayout>(WRAPPER_TAG + target.hashCode())
//
//        //wrapper不存在需要创建
//        if (wrapper == null) {
//            wrapper = LoadingWrapper(target.context, target.left, target.top, target.right, target.bottom).apply {
//                tag = WRAPPER_TAG + target.hashCode()
//            }
//            (target.parent as ViewGroup).addView(wrapper)
//        }
//
//        wrapper.visibility = INVISIBLE
//
//        with(wrapper) {
//            val viewToShow = findViewWithTag<View>(targetState)
//            val currentView = findViewWithTag<View>(currentState)
//
//            currentView?.visibility = GONE
//            viewToShow.visibility = VISIBLE
//
//            //改变当前状态为目标状态
//            target.setTag(MAGIC_NUMBER, targetState)
//        }
//    }
//
//
//
//}
//
////class loading{
////
////        fun load(view: ViewGroup, loadingView: View) {
////            val viewWithTag = getInsertedView(view).findViewWithTag<View>("com.weechan.loading.loadTag")
////            if (viewWithTag != null) return
////
////            checkType(loadingView)
////
////            loadingView.apply { setTag("com.weechan.loading.loadTag") }
////            dismiss(view)
////            if (view is androidx.recyclerview.widget.RecyclerView || view is ListView) {
////                loadList(view.parent as ViewGroup, loadingView, view)
////            } else {
////                loadNormal(view, loadingView)
////            }
////        }
////
////        fun error(view: ViewGroup, errorView: View) {
////
////            val viewWithTag = getInsertedView(view).findViewWithTag<View>("com.weechan.loading.errorTag")
////            if (viewWithTag != null) return
////
////            checkType(errorView)
////
////            errorView.setTag("com.weechan.loading.errorTag")
////            dismiss(view)
////            if (view is androidx.recyclerview.widget.RecyclerView || view is ListView) {
////                loadList(view.parent as ViewGroup, errorView, view)
////            } else {
////                loadNormal(view, errorView)
////            }
////        }
////
////        fun finish(view: ViewGroup) {
////            dismiss(view)
////            if (view is androidx.recyclerview.widget.RecyclerView || view is ListView) {
////                view.visibility = View.VISIBLE
////            } else {
////                val container = view.parent as ViewGroup
////                container.forEachChild {
////                    it.visibility = View.VISIBLE
////                }
////            }
////        }
////
////        private fun checkType(view: View) {
////            assert(view is RelativeView)
////        }
////
////        private fun dismiss(view: ViewGroup) {
////            val container = getInsertedView(view)
////            arrayOf("com.weechan.loading.loadTag", "com.weechan.loading.errorTag").forEach {
////                container.findViewWithTag<View>(it)?.let { existView ->
////                    container.removeView(existView)
////                }
////            }
////        }
////
////        private fun getInsertedView(view: ViewGroup): ViewGroup {
////            if (view is androidx.recyclerview.widget.RecyclerView || view is ListView) {
////                return view.parent as ViewGroup
////            }
////            return view;
////        }
////
////        private fun loadList(parent: ViewGroup, loadingView: View, listView: ViewGroup) {
////            listView.visibility = View.INVISIBLE
////            val left = listView.left
////            val top = listView.top
////            loadingView.layoutParams = ViewGroup.MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT)
////            loadingView.measure(0, 0)
////            val width = loadingView.measuredWidth;
////            val height = loadingView.measuredHeight
////
////            val loadingViewLeft = left + (listView.width - width) / 2
////            val loadingViewTop = top + (listView.height - height) / 2
////
////            parent.addView(loadingView)
////            parent.post {
////                (loadingView as RelativeView).absLayout(loadingViewLeft, loadingViewTop,
////                        loadingViewLeft + width, loadingViewTop + height)
////            }
////        }
////
////        private fun loadNormal(parent: ViewGroup, loadingView: View) {
////
////            for (index in 0 until parent.childCount) {
////                parent.getChildAt(index).visibility = View.INVISIBLE
////            }
////
////            val params = ViewGroup.MarginLayoutParams(WRAP_CONTENT, WRAP_CONTENT)
////            val (left, top) = arrayOf((parent.width - loadingView.measuredWidth) / 2,
////                    (parent.height - loadingView.measuredHeight) / 2)
////            loadingView.measure(0, 0)
////            loadingView.layoutParams = params
////            parent.addView(loadingView)
////
////            val (halfWidth, halfHeight) = arrayOf(loadingView.measuredWidth / 2, loadingView.measuredHeight / 2)
////            loadingView.post {
////                (loadingView as RelativeView).absLayout(left - halfWidth, top - halfHeight,
////                        left + halfWidth, top + halfHeight)
////            }
////        }
//
////}