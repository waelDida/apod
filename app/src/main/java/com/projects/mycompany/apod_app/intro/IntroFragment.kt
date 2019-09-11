package com.projects.mycompany.apod_app.intro


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.projects.mycompany.apod_app.R
import com.projects.mycompany.apod_app.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentIntroBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_intro,container,false)
        val viewModel = ViewModelProviders.of(this).get(IntroViewModel::class.java)
        binding.introViewModel = viewModel

        viewModel.navigate.observe(this, Observer {
            if(it == true){
                findNavController().navigate(IntroFragmentDirections.actionIntroFragmentToMainFragment())
                viewModel.navigateTerminated()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.about_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (NavigationUI.onNavDestinationSelected(item,view!!.findNavController())
                || super.onOptionsItemSelected(item))
    }


}
