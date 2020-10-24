/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dragor.international.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dragor.international.R
import dragor.international.api.model.Medium
import dragor.international.api.model.User

/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("url")
    fun loadImageUrl(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .apply(
                RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(200, 200).priority(Priority.IMMEDIATE)
            )
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("avtar")
    fun loadExampleUrl(view: ImageView, url: List<User>?) {
        if (url?.get(0)?.avatar != null) {
            Glide.with(view.context)
                .load(url?.get(0)?.avatar)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(100, 100).priority(Priority.IMMEDIATE)
                )
                .apply(RequestOptions().circleCrop())
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("posts")
    fun loadPostUrl(view: ImageView, url: List<Medium>?) {
        if (url?.get(0)?.image != null) {
            Glide.with(view.context)
                .load(url?.get(0)?.image)
                .apply(
                    RequestOptions().placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .override(300, 300).priority(Priority.IMMEDIATE)
                )
                .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                .into(view)
        }
    }

    @JvmStatic
    @BindingAdapter("name")
    fun setUsers(view: TextView, url: List<User>?) {
        if (url?.get(0)?.name != null) {
            view.setText(url?.get(0)?.name.plus(" ").plus(url?.get(0)?.lastname))
        }
    }

    @JvmStatic
    @BindingAdapter("desig")
    fun setDesignation(view: TextView, url: List<User>?) {
        if (url?.get(0)?.designation != null) {
            view.setText(url?.get(0)?.designation)
        }
    }

    @JvmStatic
    @BindingAdapter("title")
    fun setTitle(view: TextView, url: List<Medium>?) {
        if (url?.get(0)?.title != null) {
            view.setText(url?.get(0)?.title)
        }
    }

    @JvmStatic
    @BindingAdapter("link")
    fun setLinks(view: TextView, url: List<Medium>?) {
        if (url?.get(0)?.url != null) {
            view.setText(url?.get(0)?.url)
        }
    }

    @JvmStatic
    @BindingAdapter("likes")
    fun setLikes(view: TextView, data: String?) {
        if (data!= null) {
            view.setText(data.plus(" Likes"))
        }
    }

    @JvmStatic
    @BindingAdapter("comments")
    fun setComment(view: TextView, data: String?) {
        if (data!= null) {
            view.setText(data.plus(" Comments"))
        }
    }
}
