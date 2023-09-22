package com.wally.gallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.wally.gallery.databinding.FragmentPhotoDetailBinding

class PhotoDetailFragment : Fragment() {
    private var _binding: FragmentPhotoDetailBinding? = null
    private val binding get() = _binding!!

    private val args: PhotoDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoDetailBinding.inflate(layoutInflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        binding.url = args.photoUrl
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}