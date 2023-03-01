package com.example.car

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.carrossa.*
import com.example.carrossa.databinding.Fragment2Binding
import com.example.carrossa.databinding.ReservationLayoutBinding

// attention faut enlever abstract
class MyAdapter2(val context: Context, var data:List<ReservationCar>): RecyclerView.Adapter<MyAdapter2.MyViewHolder>() {
    lateinit var binding: Fragment2Binding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ReservationLayoutBinding.inflate(LayoutInflater.from(context), parent, false))

    }


    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        holder.binding.apply {
            dateDebut.text = data[position].datedebut
            timeDebut.text = data[position].timedebut
            modele.text = data[position].modele
            pin.text=data[position].pin
            matricule.text = data[position].matricule
            Glide.with(context).load(url +data[position].photo).into(photo)

            button7.setOnClickListener{ view: View ->
                val reservationpin = ReservationPin(data[position].idreservation,data[position].pin,data[position].state)
                val data = bundleOf("data" to reservationpin)
                view.findNavController().navigate(R.id.action_fragment2_to_listBluetooth,data)


            }
        }


    }



    class MyViewHolder(val binding: ReservationLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    }

}