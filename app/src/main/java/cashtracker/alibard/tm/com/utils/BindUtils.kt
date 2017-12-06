package cashtracker.alibard.tm.com.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


class BindUtils {

    companion object {

        @BindingAdapter("imageUrl")
        @JvmStatic
        fun imageLoad(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}