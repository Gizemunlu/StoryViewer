package com.example.storyviewer.ui.adapter.post

import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.storyviewer.R
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
            item.post.videos?.run {
                getOrNull(0)?.let { postItem ->
                    item_post_title.text = postItem.title
                    item_post_description.text = postItem.description
                    picasso.load(postItem.thumb).into(image_profile)
                }
            }

        }
    }
}