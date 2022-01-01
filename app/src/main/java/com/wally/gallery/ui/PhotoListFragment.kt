package com.wally.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wally.gallery.databinding.FragmentPhotoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PhotoListFragment : Fragment() {
    private lateinit var binding: FragmentPhotoListBinding
    private val photoViewModel: PhotoViewModel by viewModels()
    private val adapter: PhotoPagingDataAdapter by lazy { PhotoPagingDataAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        initRecyclerView()
        collectFlow()
    }

    private fun setAdapter() {
        binding.rvPhoto.adapter = adapter
    }

    private fun initRecyclerView() {
        binding.rvPhoto.setHasFixedSize(true)
        binding.rvPhoto.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun collectFlow() {
        lifecycleScope.launchWhenStarted {
            photoViewModel.photoFlow.collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }
    }
}