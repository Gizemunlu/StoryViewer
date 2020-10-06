package com.example.storyviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyviewer.adapter.PostAdapter
import com.example.storyviewer.model.Post
import androidx.lifecycle.Observer
import com.example.storyviewer.R
import com.example.storyviewer.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val postListModel: PostViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRecycle()
    }

    private fun getRecycle(){
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        postListModel.getPost()
        postListModel.listofPost.observe(
            this,
            Observer(
                function = fun(postList: List<Post>?) {
                    postList?.let {
                        if (postList.isNotEmpty()) {
                            val postListAdapter = PostAdapter(postList)
                            recycler.adapter = postListAdapter
                        } else {
                            print("Post list is empty!!!")
                        }
                    }
                })
        )
    }
}