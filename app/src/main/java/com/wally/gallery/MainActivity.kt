package com.wally.gallery

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.wally.gallery.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: PhotoViewModel by viewModels()
    private val adapter: PhotoPagingDataAdapter by lazy { PhotoPagingDataAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvPhoto.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.photoFlow.collectLatest { photoUiState ->
                binding.rvPhoto.layoutManager = GridLayoutManager(this@MainActivity, 2)
                //binding.rvPhoto.adapter = PhotoAdapter(photoUiState.photo)
                adapter.submitData(photoUiState)
            }
        }
    }
}