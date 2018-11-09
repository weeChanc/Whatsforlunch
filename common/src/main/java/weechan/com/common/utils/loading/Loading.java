package weechan.com.common.utils.loading;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import weechan.com.common.R;

import static android.view.View.INVISIBLE;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static weechan.com.common.utils.loading.LoadingTag.ERROR_STATE;
import static weechan.com.common.utils.loading.LoadingTag.LOADING_STATE;
import static weechan.com.common.utils.loading.LoadingTag.NONE_STATE;
import static weechan.com.common.utils.loading.LoadingTag.RETRY_STATE;
import static weechan.com.common.utils.loading.LoadingTag.WRAPPER_TAG;


/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/9 10:45
 */

public class Loading {

    private static final int MAGIC_NUMBER = R.id.loading_tag_state;

    private LoadingBuilder mBuilder;

    private View target;
//    private static Loading mLoading = new Loading();

    public Loading(LoadingBuilder loadingBuilder) {
        mBuilder = loadingBuilder;
        target = mBuilder.getTarget();
    }

    public void load() {
        display(target, getCurrentSate(target), LOADING_STATE);
    }

    public void error() {
        display(target, getCurrentSate(target), ERROR_STATE);
    }

    public void retry() {
        display(target, getCurrentSate(target), RETRY_STATE);
    }

    public void finish() {
        display(target, getCurrentSate(target), NONE_STATE);
    }


    public <T> T findViewById(@IdRes int id){
        LoadingWrapper wrapper = ((ViewGroup) target.getParent())
                .findViewWithTag(WRAPPER_TAG + target.hashCode());
        return (T)wrapper.findViewById(id);
    }

    private int getCurrentSate(View target) {
        Integer state = (Integer) target.getTag(MAGIC_NUMBER);
        if (state == null) {
            state = NONE_STATE;
        }
        return state;
    }

    private void display(final View target, final int currentState, int targetState) {
        Log.e("STATE", currentState + "  " + targetState);
        if (targetState == currentState) return;

        LoadingWrapper wrapper = ((ViewGroup) target.getParent())
                .findViewWithTag(WRAPPER_TAG + target.hashCode());

        if (targetState == NONE_STATE) {
            wrapper.activeViewByState(targetState);
            wrapper.setVisibility(View.GONE);
            target.setVisibility(View.VISIBLE);
            target.setTag(MAGIC_NUMBER, NONE_STATE);
            return;
        }

        //避免移动的
        target.setVisibility(INVISIBLE);

        //wrapper不存在需要创建
        if (wrapper == null) {
            final Rect border = new Rect(target.getLeft(), target.getTop(),
                    target.getRight(), target.getBottom());

            wrapper = new LoadingWrapper(target.getContext(), border,
                    mBuilder.getLoadingView(),
                    mBuilder.getErrorView(),
                    mBuilder.getRetryView());
           // wrapper.setLayoutParams(new ViewGroup.LayoutParams(0,0));

            wrapper.setTag(WRAPPER_TAG + target.hashCode());

            wrapper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBuilder == null) return;
                    switch (getCurrentSate(target)) {
                        case LOADING_STATE:
                            if (mBuilder.getOnLoadingClick() != null)
                                mBuilder.getOnLoadingClick().run();
                            break;
                        case LoadingTag.ERROR_STATE:
                            if (mBuilder.getOnErrorClick() != null)
                                mBuilder.getOnErrorClick().run();
                            break;
                        case LoadingTag.RETRY_STATE:
                            if (mBuilder.getOnRetryClick() != null)
                                mBuilder.getOnRetryClick().run();
                            break;
                    }
                }
            });

            ((ViewGroup) target.getParent()).addView(wrapper);
        }

        wrapper.setVisibility(INVISIBLE);

        wrapper.activeViewByState(targetState);

        //改变当前状态为目标状态
        target.setTag(MAGIC_NUMBER, targetState);

        final LoadingWrapper finalWrapper = wrapper;
        wrapper.post(new Runnable() {
            @Override
            public void run() {
                finalWrapper.setVisibility(View.VISIBLE);
                ViewGroup.LayoutParams params = finalWrapper.getLayoutParams();

//                finalWrapper.setLayoutParams(params);
            }
        });

    }
}
