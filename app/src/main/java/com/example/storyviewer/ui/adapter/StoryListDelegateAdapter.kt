package com.example.storyviewer.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.delegateadapter.delegate.KDelegateAdapter
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.example.storyviewer.R
import com.example.storyviewer.ui.adapter.StoryListViewModel
import kotlinx.android.synthetic.main.fragment_stories.*

class StoryListDelegateAdapter(
    private val diffAdapter: DiffUtilCompositeAdapter,
    private val rvLayoutManager: RecyclerView.LayoutManager
) :
    KDelegateAdapter<StoryListViewModel>(){

    override fun getLayoutId() = R.layout.fragment_stories

    override fun isForViewType(items: MutableList<*>, position: Int) =
        items[position] is StoryListViewModel

    override fun onBind(item: StoryListViewModel, viewHolder: KViewHolder) {
        viewHolder.story_recycler.apply {
            adapter = diffAdapter
            layoutManager = rvLayoutManager
        }
        diffAdapter.swapData(item.list)
    }
}