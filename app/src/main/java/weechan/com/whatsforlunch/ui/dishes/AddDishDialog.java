package weechan.com.whatsforlunch.ui.dishes;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoImpl;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.InvokeParam;
import org.devio.takephoto.model.TContextWrap;
import org.devio.takephoto.model.TResult;
import org.devio.takephoto.permission.InvokeListener;
import org.devio.takephoto.permission.PermissionManager;
import org.devio.takephoto.permission.TakePhotoInvocationHandler;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import weechan.com.whatsforlunch.R;

/**
 * @author 214652773@qq.com
 * @user c
 * @create 2019/1/3 14:29
 */

public class AddDishDialog extends DialogFragment implements TakePhoto.TakeResultListener, InvokeListener {

    private TakePhoto takePhoto;
    private InvokeParam invokeParam;
    private View view;

    private String photo = "";

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout((int) (dm.widthPixels * 0.95), ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }

        takePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false);
        return takePhoto;
    }

    @Override
    public void takeSuccess(TResult tResult) {
        photo = tResult.getImage().getCompressPath();
        if (photo == null) photo = "";
        ImageView image = view.findViewById(R.id.dishes_icon);
        Glide.with(view).load(new File(photo)).into(image);
    }

    @Override
    public void takeFail(TResult tResult, String s) {

    }

    @Override
    public void takeCancel() {

    }

    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    public interface OnSubmitListener {
        void onSubmit(String name, String description, String path, Map<?, ?> map);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_add_dish, container, false);
        getTakePhoto().onCreate(savedInstanceState);
        final EditText e1 = view.findViewById(R.id.dishes_name);
        final EditText e2 = view.findViewById(R.id.dishes_description);
        final EditText big = view.findViewById(R.id.big);
        final EditText medium = view.findViewById(R.id.meidum);
        final EditText small = view.findViewById(R.id.small);
        final ImageView image = view.findViewById(R.id.dishes_icon);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                Uri imageUri = Uri.fromFile(file);
                getTakePhoto().onPickFromCaptureWithCrop(imageUri, new CropOptions.Builder().setWithOwnCrop(true).create());
            }
        });
        view.findViewById(R.id.dishes_ensure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e2.getText().toString().isEmpty() || e1.getText().toString().isEmpty() || photo.isEmpty()) {
                    Toast.makeText(getActivity(), "请输入菜名,描述,以及图片", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String, String> map = new HashMap<String, String>();
                if (!big.getText().toString().trim().isEmpty()) {
                    map.put("big", big.getText().toString());
                }

                if (!medium.getText().toString().trim().isEmpty()) {
                    map.put("medium", medium.getText().toString());
                }

                if (!small.getText().toString().trim().isEmpty()) {
                    map.put("small", small.getText().toString());
                }
                ((OnSubmitListener) getActivity()).onSubmit(e1.getText().toString(), e2.getText().toString(), photo, map);
                dismiss();
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this.getActivity(), type, invokeParam, this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        getTakePhoto().onSaveInstanceState(outState);
    }
}
