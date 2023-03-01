package com.example.carrossa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.carrossa.databinding.FragmentNotrajetsBinding


class Notrajets : Fragment() {
    lateinit var binding: FragmentNotrajetsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentNotrajetsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttoncar2.setOnClickListener{
            view.findNavController().navigate(R.id.action_notrajets_to_fragment1)

        }
    }
}