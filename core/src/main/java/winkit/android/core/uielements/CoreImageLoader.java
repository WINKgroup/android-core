package winkit.android.core.uielements;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.AnimRes;
import android.support.annotation.DrawableRes;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import winkit.android.core.R;

public class CoreImageLoader {

    private static ImageLoader appImageLoader;

    private String url;
    private @DrawableRes int defaultImageResId = 0, errorImageResId = 0;
    private boolean animated;
    private Animation animation;
    private @AnimRes int animationRes;

    private CoreImageLoader (String url) {
        this.url = url;
    }

    public static CoreImageLoader load(String url) {
        return new CoreImageLoader(url);
    }

    public CoreImageLoader withDefault (@DrawableRes int defaultImageResId) {
        this.defaultImageResId = defaultImageResId;
        return this;
    }

    public CoreImageLoader withError (@DrawableRes int errorImageResId) {
        this.errorImageResId = errorImageResId;
        return this;
    }

    public CoreImageLoader animated (boolean animated){
        this.animated = animated;
        return this;
    }

    public CoreImageLoader animated (Animation animation){
        this.animated = true;
        this.animationRes = 0;
        this.animation = animation;
        return this;
    }

    public CoreImageLoader animated (@AnimRes int animation){
        this.animated = true;
        this.animationRes = animation;
        this.animation = null;
        return this;
    }

    public void into (final ImageView imageView) {
        setLoadingResource(url, imageView);
        into(imageView.getContext(), new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if(!isLoadingResource(url, imageView)) return;

                if(!ViewCompat.isAttachedToWindow(imageView) && !isImmediate) return;

                if (response.getBitmap() != null) {
                    imageView.setImageBitmap(response.getBitmap());
                    if(!isImmediate) animate(imageView);
                } else if (defaultImageResId != 0) {
                    imageView.setImageResource(defaultImageResId);
                    if(!isImmediate) animate(imageView);
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                if(!ViewCompat.isAttachedToWindow(imageView) || !isLoadingResource(url, imageView)) return;

                if (errorImageResId != 0) {
                    imageView.setImageResource(errorImageResId);
                    animate(imageView);
                }
            }
        });
    }

    public void into (Context c, final ImageLoader.ImageListener listener) {
        getImageLoader(c).get(url, listener);
    }

    private void animate (ImageView view){
        if(!animated) return;
        Animation a = this.animation;
        if(a == null) {
            a = AnimationUtils.loadAnimation(view.getContext(), animationRes != 0? animationRes : android.R.anim.fade_in);
        }
        view.startAnimation(a);
    }

    private static synchronized ImageLoader getImageLoader (Context c) {
        if(appImageLoader == null) {
            appImageLoader = new ImageLoader(Volley.newRequestQueue(c.getApplicationContext()),
                    new ImageLoader.ImageCache() {
                        private final LruCache<String, Bitmap> cache = new LruCache<>(20);
                        @Override
                        public Bitmap getBitmap(String url) {
                            return cache.get(url);
                        }

                        @Override
                        public void putBitmap(String url, Bitmap bitmap) {
                            cache.put(url, bitmap);
                        }
                    });
        }
        return appImageLoader;
    }

    private static void setLoadingResource (String url, ImageView view){
        view.setTag(R.string.loading_url_tag, url);
    }

    private static boolean isLoadingResource (String url, ImageView view) {
        Object tag = view.getTag(R.string.loading_url_tag);
        return tag != null && tag.equals(url);
    }
}
