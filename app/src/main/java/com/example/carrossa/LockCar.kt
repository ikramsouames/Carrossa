package com.example.carrossa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.carrossa.databinding.FragmentLockCarBinding
import com.example.carrossa.databinding.FragmentUnlockCarBinding

class LockCar : Fragment() {
    lateinit var binding: FragmentLockCarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockCarBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.unlock.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_lockCar_to_unlockCar)
        }
        binding.EndTrip.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_lockCar_to_fragment3)
        }
    }
}