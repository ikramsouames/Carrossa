package com.example.car

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.carrossa.R

import com.example.carrossa.databinding.FragmentDetailBinding
import com.example.carrossa.url
import java.util.*

lateinit var binding: FragmentDetailBinding
lateinit var carModel:CarModel

class detailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        carModel = ViewModelProvider(requireActivity()).get(CarModel::class.java)
        val car = arguments?.getSerializable("data") as Car
        if (car != null) {
            //binding.photo2.setImageResource(car.photo)
            //faut utiliser le glide
            binding.dispo2.text=car.disponibilite
            binding.marque2.text=car.marque
            binding.moteur2.text=car.type_moteur
            binding.tarif2.text=car.tarif.toString()
            binding.fuel.text=car.carburant
            binding.modele2.text=car.modele
            binding.year.text=car.year.toString()
            binding.Capacity.text=car.capacity.toString()
            Glide.with(requireContext()).load(url +car.photo).into(binding.photo2)
            binding.imageView8.setOnClickListener() {
                val labelLocation = "The car is right here"
                val urlAddress = "http://maps.google.com/maps?q="+ car.latitude.toString() +"," + car.longitude +"("+ labelLocation + ")&iwloc=A&hl=es"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlAddress))
                startActivity(intent)
            }
        }
        binding.button.setOnClickListener {
            val data = bundleOf("data" to car.matricule)
            view.findNavController().navigate(R.id.action_detailFragment_to_bookingFragment,data)
        }


    }
}