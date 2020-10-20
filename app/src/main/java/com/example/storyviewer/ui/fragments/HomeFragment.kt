package com.example.storyviewer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.delegateadapter.delegate.diff.DiffUtilCompositeAdapter
import com.example.storyviewer.R
import com.example.storyviewer.ui.adapter.StoryListDelegateAdapter
import com.example.storyviewer.ui.adapter.story.StoryItemDelegateAdapter
import com.example.storyviewer.ui.adapter.post.PostItemDelegateAdapter
import com.example.storyviewer.viewmodel.HomeViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(){

    private val viewModel: HomeViewModel by viewModel()

    private val diffAdapter by lazy {
        DiffUtilCompositeAdapter.Builder()
            .add(PostItemDelegateAdapter())
            .add(StoryListDelegateAdapter(
                diffAdapter = DiffUtilCompositeAdapter.Builder()
                    .add(StoryItemDelegateAdapter {
                        showText(it)
                    })
                    .build(),
                rvLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                )
            )
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_posts, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()

        viewModel.event.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { event ->
                if (event == HomeViewModel.ViewEvent.ERROR) {
                    showText("Sorryy!")
                }
            }
        })
    }

    private fun initViews() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            when (it) {
                HomeViewModel.ViewState.DEFAULT -> progress_bar.visibility = View.GONE
                HomeViewModel.ViewState.LOADING -> progress_bar.visibility = View.VISIBLE
                else -> showText("Try Again!")
            }
        })
        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = diffAdapter
        }

        viewModel.contentList.observe(viewLifecycleOwner, Observer{
            diffAdapter.swapData(it)
        })
    }

    private fun showText(msg: String) {
        Snackbar.make(main_layout, msg, Snackbar.LENGTH_SHORT).show()
    }
}
