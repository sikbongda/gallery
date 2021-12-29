package com.wally.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.wally.gallery.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy { PhotoViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            viewModel.photoUiState.collect { photoUiState ->
                when (photoUiState) {
                    is PhotoUiState.Error -> {}
                    PhotoUiState.Initial -> {}
                    PhotoUiState.Loading -> {}
                    is PhotoUiState.Success -> {
                        binding.rvPhoto.layoutManager = GridLayoutManager(this@MainActivity,2)
                        binding.rvPhoto.adapter =
                            PhotoAdapter(photoUiState.photo)
                    }
                }
            }
        }

        viewModel.getListImages()
    }
}