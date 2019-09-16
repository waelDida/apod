package com.projects.mycompany.apod_app.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.projects.mycompany.apod_app.data.Apod
import com.projects.mycompany.apod_app.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.mainViewModel = viewModel
        binding.setLifecycleOwner(this)
        val mainAdapter = MainAdapter(MainAdapter.OnClickListener{
            viewModel.onNavigate(it)
        })

        viewModel.navigateToSelectedApod.observe(this, Observer {
            it?.let{
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailsFragment(it))
                viewModel.onNvigateTerminated()
            }
        })

        viewModel.response.observe(this, Observer {
            it?.let {
                val list = mutableListOf<Apod>()
                list.add(it)
                list.add(it)
                mainAdapter.submitList(list)
                binding.recycler.adapter = mainAdapter
            }
        })

        return binding.root
    }


}
