package com.example.carrossa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.carrossa.databinding.FragmentListBluetoothBinding
import com.example.carrossa.databinding.FragmentUnlockCarBinding


class UnlockCar : Fragment() {

    lateinit var binding: FragmentUnlockCarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUnlockCarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lock.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_unlockCar_to_lockCar)
        }
    }
}