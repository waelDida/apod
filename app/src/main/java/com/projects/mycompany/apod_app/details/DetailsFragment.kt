package com.projects.mycompany.apod_app.details


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.projects.mycompany.apod_app.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentDetailsBinding.inflate(inflater)

        val args = DetailsFragmentArgs.fromBundle(arguments!!).apodData
        val viewModelFactory = DetailsViewModelFactory(args)
        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(DetailsViewModel::class.java)
        binding.detailsViewModel = viewModel
        binding.setLifecycleOwner(this)

        return binding.root
    }


}
