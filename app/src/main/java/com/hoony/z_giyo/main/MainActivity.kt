package com.hoony.z_giyo.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hoony.z_giyo.R
import com.hoony.z_giyo.db.entity.Item
import com.hoony.z_giyo.viewer.ViewerActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener,
    View.OnClickListener {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        setObserver()
        setListener()
    }

    private fun initRecyclerView() {
        rvList.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

            adapter = RecyclerViewAdapter(this@MainActivity)
        }
    }

    private fun setObserver() {
        viewModel.itemLiveData.observe(
            this,
            Observer {
                (rvList.adapter as RecyclerViewAdapter).submitList(it)
            }
        )
    }

    private fun setListener() {
        btAdd.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.btAdd -> {
                    val title = etTitle.editableText?.toString()
                    if (!title.isNullOrBlank()) {
                        viewModel.insertItem(Item(title))
                        etTitle.setText("")
                    }
                }
            }
        }
    }

    override fun onItemClick(position: Int) {
        val item = viewModel.getItem(position)

        val intent = Intent(this, ViewerActivity::class.java)
        intent.putExtra("title", item.text)
        startActivity(intent)
    }

    override fun onItemLongClick(position: Int) {
        viewModel.deleteItem(position)
    }
}
