package com.example.storyviewer.ui.adapter.post

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.storyviewer.R
import com.example.storyviewer.data.model.PostItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_post.*
import org.koin.core.KoinComponent
import org.koin.core.inject

open class PostItemDelegateAdapter :
    KDelegateAdapter<PostItemViewModel>(), KoinComponent {

    private val picasso by inject<Picasso>()

    override fun getLayoutId() = R.layout.item_post

    override fun isForViewType(items: MutableList<*>, position: Int) =
        items[position] is PostItemViewModel

    override fun onBind(item: PostItemViewModel, viewHolder: KViewHolder) {
        with(viewHolder) {
            item_post_title.text = item.post.title
            item_post_description.text = item.post.description
            picasso.load(item.post.thumb).into(image_profile)
        }
    }
}