package com.example.carrossa

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Delete
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.car.Car
import com.example.car.MyAdapter2
import com.example.carrossa.databinding.Fragment3Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Fragment3 : Fragment() {
    lateinit var binding: Fragment3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = Fragment3Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        val id=pref.getInt("idUser",0)

        val instance = Room.databaseBuilder(requireActivity(),AppDatabase::class.java, "trajets_db").allowMainThreadQueries().build()
        /*val trajet1 = Trajet( 1,"Lundi 12 Janvier 2023", "Dar El-Beida","Oued Smar","10:00", "12:00","200 DA",18)
        val trajet2 = Trajet( 2,"Dimanche 24 Février 2023", "Cité Bakir Tabal", "Faubourg","11:35", "13:20","550 DA",17)
       // val trajet3 = Trajet( 3,"Dimanche 24 Février 2023", "Cité Bakir Tabal", "Faubourg","11:35", "13:20","600 DA",16)
        //val trajet15 = Trajet( 12,"Lundi 12 Janvier 2023", "Dar El-Beida","Oued Smar","10:00", "12:00","2000 DA",16)*/
       val trajet16 = Trajet( 33,"Dimanche 24 Février 2023", "Cité Bakir Tabal", "Faubourg","11:35", "13:20","5502 DA",5,50)
        //val trajet19 = Trajet( 19,"Dimanche 24 Février 2023", "Cité Bakir Tabal", "Faubourg","11:35", "13:20","5692 DA",15,1)
        //instance.getTrajetDao().addTrajet(trajet19)
        //instance.getTrajetDao().addTrajet(trajet16)

       // instance.getTrajetDao().deleteTrajet(trajet16)
        //instance.getTrajetDao().deleteTrajet(trajet19)
        //instance.getTrajetDao().addTrajet(trajet16)
        var trajets=instance.getTrajetDao().getTrajetbyid(id)
        if (trajets.size != 0) {
            binding.recycler3.layoutManager = GridLayoutManager(requireActivity(), 1)
            binding.recycler3.adapter = MyAdapter3(requireActivity(), trajets!!)

        }
        else{
            findNavController()?.navigate(R.id.action_fragment3_to_notrajets)
        }




      /* Room.databaseBuilder(requireContext(), AppDatabase::class.java, "trajets_db")
            .fallbackToDestructiveMigration()
            .build()*/



    }


}