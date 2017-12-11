package com.example.bill.generateimage;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Bill on 2017/8/1.
 */

public class Utils {

    /**
     * 获取当前Window 的 DrawingCache 的方式,屏幕截图
     *
     * @param ctx
     * @return
     */
    public static Bitmap generateBitmap(Activity ctx) {
        View view = ctx.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bp = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(),
                view.getMeasuredHeight());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();

        return bp;
    }

    /**
     * 获取当前屏幕View截图
     *
     * @param view
     * @return
     */
    public static Bitmap generateBitmapByView(View view) {
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bp = Bitmap.createBitmap(view.getDrawingCache(), 0, 0, view.getMeasuredWidth(),
                view.getMeasuredHeight());
        view.setDrawingCacheEnabled(false);
        view.destroyDrawingCache();

        return bp;
    }

    /**
     * 根据LinearLayout生成图片
     *
     * @param activity
     * @param linearLayout
     * @return
     */
    public static Bitmap generateBitmapByLinearLayout(Activity activity, LinearLayout linearLayout) {
        int height = 0;
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            linearLayout.getChildAt(i).measure(0, 0);
            height += linearLayout.getChildAt(i).getMeasuredHeight();
        }
        linearLayout.measure(
                View.MeasureSpec.makeMeasureSpec(getWidthAndHeight(activity)[0], View.MeasureSpec.EXACTLY),
                height);
        linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(), linearLayout.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(linearLayout.getWidth(), linearLayout.getHeight(), Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        linearLayout.draw(canvas);
        return bitmap;
    }

    /**
     * 获取ScrollView截图，ScrollView已经绘制，没有绘制参考上面LinearLayout
     * @param scrollView
     * @return
     */
    public static Bitmap generateBitmapByScrollView(ScrollView scrollView) {
        int h = 0;
        Bitmap bitmap = null;
        for (int i = 0; i < scrollView.getChildCount(); i++) {
            h += scrollView.getChildAt(i).getHeight();
        }
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        return bitmap;
    }

    public static int[] getWidthAndHeight(Context context) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        return new int[]{width, height};
    }

    public static boolean saveBitmapToSD(Bitmap bt, String path) {
        Log.e("Bill", "bt:" + bt);
        if (bt == null)
            return false;
        boolean success = false;
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
            success = bt.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

}
