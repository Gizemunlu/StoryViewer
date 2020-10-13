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
import com.example.storyviewer.ui.adapter.PostItemDelegateAdapter
import com.example.storyviewer.viewmodel.PostListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_posts.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostListFragment : Fragment(){

    private val viewModel: PostListViewModel by viewModel()

    private val diffAdapter by lazy {
        DiffUtilCompositeAdapter.Builder()
            .add(PostItemDelegateAdapter())
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

        /*  viewModel.event.observe(viewLifecycleOwner, Observer{
              it.getContentIfNotHandled()?.let { event ->
                  if(event == PostListViewModel.ViewEvent.ERROR){
                      showText("sorryyyy")
                  }
              }
          })*/

    }

    private fun initViews() {
        viewModel.state.observe(viewLifecycleOwner, Observer{
            /* when(it){
                 PostListViewModel.ViewState.DEFAULT ->progress_bar.visibility = View.GONE
                 PostListViewModel.ViewState.LOADING -> progress_bar.visibility = View.VISIBLE
                 else -> null
             }*/
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
