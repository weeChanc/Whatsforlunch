package weechan.com.whatsforlunch.widget;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/11/7 22:06
 */

public interface RelativeView {
    void absLayout(int l ,int t ,int r, int b);

    //HAVE TO OVERWRITE AND RETURN DOTING NOTHING
    void layout(int l, int t ,int r, int b);
}
