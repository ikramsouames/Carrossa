package com.example.carrossa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.carrossa.MyAdapter3
import com.example.carrossa.databinding.Fragment3Binding
import com.example.carrossa.databinding.ReservationLayoutBinding
import com.example.carrossa.databinding.TrajetLayoutBinding

class MyAdapter3(val context: Context, var data:List<Trajet>): RecyclerView.Adapter<MyAdapter3.MyViewHolder>() {
    lateinit var binding: Fragment3Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TrajetLayoutBinding.inflate(LayoutInflater.from(context), parent, false))
    }


    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.binding.apply {
            datetrip.text = data[position].date
            heured.text = data[position].timedebut
            heuref.text = data[position].timefin
            cost.text = data[position].cout
            adrdepart.text = data[position].adr_depart
            adrariv.text = data[position].adr_ariv
        }


    }



    class MyViewHolder(val binding: TrajetLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }

}