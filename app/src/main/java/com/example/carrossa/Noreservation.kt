package com.example.carrossa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.carrossa.databinding.FragmentFragmentnoreservationBinding


class Noreservation : Fragment() {
    lateinit var binding: FragmentFragmentnoreservationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentFragmentnoreservationBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttoncar2.setOnClickListener{
            view.findNavController().navigate(R.id.action_noreservation_to_fragment1)

        }

    }
}