package weechan.com.common.utils.loading;

import android.content.Context;
import android.view.View;

import androidx.annotation.LayoutRes;
import weechan.com.common.R;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/9 10:40
 */

public class LoadingBuilder {

    private @LayoutRes int loadingView,errorView,retryView;
    private Runnable onRetryClick,onLoadingClick,onErrorClick;
    private View target;

    public LoadingBuilder(Context context){
        this.loadingView = R.layout.loading_view;
        this.errorView = R.layout.error_view;
        this.retryView = R.layout.retry_view;
    }

    public LoadingBuilder setErrorView(@LayoutRes int errorView) {
        this.errorView = errorView;
        return this;
    }

    public LoadingBuilder setLoadingView(@LayoutRes  int loadingView) {
        this.loadingView = loadingView;
        return this;
    }

    public LoadingBuilder setRetryView(@LayoutRes int retryView) {
        this.retryView = retryView;
        return this;
    }

    public int getLoadingView() {
        if(loadingView == 0 ) return R.layout.loading_view;
        return loadingView;
    }

    public int getErrorView() {
        if(errorView == 0) return R.layout.error_view;
        return errorView;
    }

    public int getRetryView() {
        if(retryView == 0 ) return R.layout.retry_view;
        return retryView;
    }



    public LoadingBuilder setOnRetryClickListener(Runnable onRetryClick) {
        this.onRetryClick = onRetryClick;
        return this;
    }

    public LoadingBuilder setOnErrorClickListener(Runnable onErrorClick) {
        this.onErrorClick = onErrorClick;
        return this;
    }

    public LoadingBuilder setOnLoadingClickListener(Runnable onLoadingClick) {
        this.onLoadingClick = onLoadingClick;
        return this;
    }

    Runnable getOnLoadingClick() {
        return onLoadingClick;
    }

    Runnable getOnErrorClick() {
        return onErrorClick;
    }

    Runnable getOnRetryClick() {
        return onRetryClick;
    }

    public View getTarget(){
        return target;
    }

    public Loading build(View target){
        this.target = target;
        return new Loading(this);
    }
}
