package com.example.madopskrifter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Vi har lavet en klasse Helpers som kan vi bruges i andre metode i andre class
 */

public class Helpers {

    /**
     *
     * @param context
     * @param uri
     * @return
     */

    @Nullable
    private static ExifInterface getExifInterface(Context context, Uri uri) {
        try {
            String path = uri.toString();
            if (path.startsWith("file://")) {
                return new ExifInterface(path);
            }
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                if (path.startsWith("content://")) {
                    InputStream inputStream = context.getContentResolver().openInputStream(uri);
                    return new ExifInterface(inputStream);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *
     * @param context
     * @param uri
     * @return
     */
    public static float getExifAngle(Context context, Uri uri) {
        try {
            ExifInterface exifInterface = getExifInterface(context, uri);
            if (exifInterface == null) {
                return -1f;
            }
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return 90f;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return 180f;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return 270f;
                case ExifInterface.ORIENTATION_NORMAL:
                    return 0f;
                case ExifInterface.ORIENTATION_UNDEFINED:
                    return -1f;
                default:
                    return -1f;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1f;
        }
    }


    /**
     * Vi har lavet den metode at fjerne keyboard når vi færdig med skrive.
     * @param editText
     */
    public static void RemoveKeyboard(EditText editText) {
        InputMethodManager inputManager = (InputMethodManager) MainActivity.currentMainActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     *
     * @param event
     * Denne metode
     * @param keyCode
     * @return
     */
    public static Boolean KeyBoardEnterPressed(KeyEvent event, int keyCode) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            return true;
        } else {
            return false;
        }
    }

    public static byte[] ConvertImageViewToVarBinary(ImageView imageView)
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        return stream.toByteArray();
    }

    public static ImageView ConvertVarBinaryToImageView(byte[] imageByteArray, Context context, int width, int height)
    {
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageByteArray, 0,imageByteArray.length);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,width, height, false));
        return imageView;
    }
}