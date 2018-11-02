package weechan.com.whatsforlunch.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import weechan.com.whatsforlunch.R;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2018/10/31 21:12
 */

public class ImageText extends RelativeLayout {

    public ImageText(Context context) {
        super(context);
    }

    public ImageText(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.ImageText);
        int image = typedArray.getResourceId(R.styleable.ImageText_image,0);
        String text = typedArray.getString(R.styleable.ImageText_text);
        float textSize = typedArray.getDimension(R.styleable.ImageText_textSize,12);
        float imageSize = typedArray.getDimensionPixelSize(R.styleable.ImageText_imageSize,120);
        typedArray.recycle();
        int imageId = View.generateViewId();
        ImageView imageView = new ImageView(context);
        imageView.setId(imageId);
        TextView textView = new TextView(context);

        RelativeLayout.LayoutParams imageParams = new RelativeLayout.LayoutParams
                ((int)imageSize,(int)imageSize);
        RelativeLayout.LayoutParams textParams = new RelativeLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        imageParams.addRule(CENTER_IN_PARENT);
        textParams.addRule(CENTER_IN_PARENT);
        textParams.addRule(BELOW,imageId);

        imageParams.bottomMargin = 16;

        imageView.setLayoutParams(imageParams);
        textView.setLayoutParams(textParams);
        imageView.setImageDrawable(context.getDrawable(image));
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

        imageView.setTranslationY(-imageSize/2);
        textView.setTranslationY(-imageSize/2);

        addView(imageView);
        addView(textView);
    }

    public ImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



}
