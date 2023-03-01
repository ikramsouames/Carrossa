package com.example.carrossa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.car.Car
import com.example.carrossa.databinding.FragmentFragment21Binding


class Fragment2_1 : Fragment() {
    lateinit var binding: FragmentFragment21Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragment21Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ret.setOnClickListener{
            view.findNavController().navigate(R.id.action_fragment2_1_to_fragment2)

        }
        val reservation = arguments?.getSerializable("data") as ReservationCar
        if (reservation != null){
            binding.datedebut.text = reservation.datedebut
            binding.timedebut.text = reservation.timedebut
        }

    }
}