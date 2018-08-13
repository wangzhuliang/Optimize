package wang.cn.com.optimize.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import wang.cn.com.optimize.ui.widget.imagewatcher.ImageWatcher;

/**
 * @author: wangZL
 * @description:
 * @projectName: Optimize
 * @date: 2018-08-09
 * @time: 13:02
 */
public class GlideImageWatcherLoader implements ImageWatcher.Loader{

    @Override
    public void load(Context context, Uri uri, final ImageWatcher.LoadCallback lc) {
        Glide.with(context).load(uri).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                lc.onResourceReady(resource);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                lc.onLoadFailed(errorDrawable);
            }

            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                lc.onLoadStarted(placeholder);
            }
        });
    }
}
