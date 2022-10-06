package com.example.leboncoin.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.leboncoin.AlbumsAdapter
import com.example.leboncoin.R
import com.example.leboncoin.data.Album
import com.example.leboncoin.databinding.ActivityMainBinding
import com.example.leboncoin.utils.RequestResult
import com.example.leboncoin.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureUI()
        observeAlbumsData()
    }

    private fun configureUI() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.recyclerView
            .apply {
                layoutManager = LinearLayoutManager(context)
                adapter = AlbumsAdapter(emptyList())
                addItemDecoration(DividerItemDecoration(context, VERTICAL))
            }

        binding.retryButton.setOnClickListener {
            binding.retryButton.visibility = View.GONE
            binding.progressCircular.visibility = View.VISIBLE
            viewModel.fetchAlbums()
        }

        binding.swipeRefresh.setOnRefreshListener {
            binding.retryButton.visibility = View.GONE
            viewModel.fetchAlbums()
        }
    }

    private fun observeAlbumsData() {
        viewModel.albumsLiveData.observe(this) {
            when (it) {
                is RequestResult.Success -> { displayAlbums(it.result) }
                is RequestResult.Error -> { showErrorState(it.errorResponse) }
            }
        }
    }

    private fun showErrorState(errorMessage: String) {
        Log.d("MainActivity", "showErrorState: $errorMessage")
        binding.progressCircular.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
        binding.retryButton.visibility = View.VISIBLE
    }

    private fun displayAlbums(albums: List<Album>) {
        binding.progressCircular.visibility = View.GONE
        binding.swipeRefresh.isRefreshing = false
        binding.retryButton.visibility = View.GONE
        (binding.recyclerView.adapter as AlbumsAdapter).apply {
            this.albums = albums
            this.notifyDataSetChanged()
        }
    }
}
