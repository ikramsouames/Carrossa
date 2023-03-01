package com.example.car

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrossa.R
import com.example.carrossa.databinding.ActivityMainBinding
import com.example.carrossa.databinding.CarLayoutBinding
import com.example.carrossa.url

class MyAdapter(val context: Context, var data:List<Car>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    lateinit var binding: ActivityMainBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(CarLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            marque.text = data[position].marque
            modele.text = data[position].modele
            /*moteur.text = data[position].type_moteur*/
            Glide.with(context).load(url+data[position].photo).into(photo)
            tarif.text = data[position].tarif.toString()
            dispo.text = data[position].disponibilite

        }

        holder.itemView.setOnClickListener { view: View ->
            val data = bundleOf("data" to data[position])
            view.findNavController().navigate(R.id.action_fragment1_to_detailFragment,data)


        }


    }


        class MyViewHolder(val binding: CarLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        }

}