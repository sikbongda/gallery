package com.wally.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.wally.gallery.databinding.FragmentPhotoListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class PhotoListFragment : Fragment() {
    private var _binding: FragmentPhotoListBinding? = null
    private val binding get() = _binding!!

    private val photoViewModel: PhotoViewModel by viewModels()
    private val adapter: PhotoPagingDataAdapter by lazy {
        PhotoPagingDataAdapter(PhotoClickListener { photo ->
            // when item clicked
            val directions = PhotoListFragmentDirections.actionPhotoDetails(photo.download_url)
            findNavController().navigate(directions)
        }, BookmarkListener { id, bookmarked ->
            // when book mark clicked
            photoViewModel.setBookmark(id, bookmarked)
        }).apply {
            addLoadStateListener { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        collectFlow()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initRecyclerView() {
        binding.rvPhoto.adapter = adapter
        binding.rvPhoto.setHasFixedSize(true)
        binding.rvPhoto.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun collectFlow() {
        lifecycleScope.launchWhenCreated {
            photoViewModel.photoFlow.collectLatest { pagedData ->
                adapter.submitData(pagedData)
            }
        }
    }
}