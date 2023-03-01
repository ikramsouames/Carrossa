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
import com.example.car.MyAdapter
import com.example.car.MyAdapter2
import com.example.carrossa.databinding.Fragment2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Fragment2 : Fragment() {
    lateinit var binding: Fragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = Fragment2Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        val id=pref.getInt("idUser",0)
        if(id==0)
        {getid()}
        else {
            getReservations(id)
        }
    }
    private fun getid() {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.getid()
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val id= response.body()!!.id
                    getReservations(id)

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
    private fun getReservations(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getreservations(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val reservations = response.body()
                    if (reservations != null) {
                        binding.recycler2.layoutManager = GridLayoutManager(
                            requireActivity(),
                            resources.getInteger(R.integer.col)
                        )
                        binding.recycler2.adapter = MyAdapter2(requireActivity(), reservations!!)
                    }
                    else {view?.findNavController()?.navigate(R.id.action_fragment2_to_noreservation)}
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
}