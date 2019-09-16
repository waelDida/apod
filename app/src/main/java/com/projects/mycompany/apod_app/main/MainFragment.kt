package com.projects.mycompany.apod_app.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.projects.mycompany.apod_app.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater)

        val app = requireNotNull(activity).application
        val viewModelFactory = MainViewModelFactory(app)

        val viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)
        binding.mainViewModel = viewModel
        binding.setLifecycleOwner(this)
        val mainAdapter = MainAdapter(MainAdapter.OnClickListener{
            viewModel.onNavigate(it)
        })
        binding.recycler.adapter = mainAdapter

        viewModel.navigateToSelectedApod.observe(this, Observer {
            it?.let{
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(it))
                viewModel.onNvigateTerminated()
            }
        })

        viewModel.apods.observe(this, Observer {
            it.apply {
                mainAdapter.submitList(it)
            }
        })

        return binding.root
    }


}
