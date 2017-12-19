package cashtracker.alibard.tm.com.utils.extention

import android.widget.ImageView
import com.bumptech.glide.Glide


fun ImageView.loadBigImage(url: String) {
    Glide.with(context).load(url).into(this)
}