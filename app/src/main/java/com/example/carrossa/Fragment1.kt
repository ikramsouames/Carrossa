package com.example.carrossa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.car.CarModel
import com.example.car.MyAdapter
import com.example.carrossa.databinding.Fragment1Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment1 : Fragment() {
    lateinit var binding: Fragment1Binding
    lateinit var carModel: CarModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = Fragment1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCar()
        binding.imageView10.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_fragment1_to_fragment4)


        }


      //  carModel = ViewModelProvider(requireActivity()).get(CarModel::class.java)


    }

    private fun getCar() {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getcars()
            withContext(Dispatchers.Main) {
               // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val cars = response.body()
                    binding.recycler.layoutManager = GridLayoutManager(requireActivity(),resources.getInteger(R.integer.col))
                    binding.recycler.adapter = MyAdapter(requireActivity(),cars!!)

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur",Toast.LENGTH_SHORT).show()}
            }

        }
    }

}