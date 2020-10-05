package com.example.storyviewer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storyviewer.adapter.StoryAdapter
import com.example.storyviewer.model.Story
import androidx.lifecycle.Observer
import com.example.storyviewer.R
import com.example.storyviewer.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val storyListModel: StoryViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getRecycle()
    }

    private fun getRecycle(){
        recycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        storyListModel.getStories()
        storyListModel.listofStories.observe(
            this,
            Observer(
                function = fun(storyList: List<Story>?) {
                    storyList?.let {
                        if (storyList.isNotEmpty()) {
                            val storyListAdapter = StoryAdapter(storyList)
                            recycler.adapter = storyListAdapter
                        } else {
                            print("Story list is empty!!!")
                        }
                    }
                })
        )
    }
}