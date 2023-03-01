package com.example.carrossa

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView.OnDateChangeListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.car.Car
import com.example.carrossa.databinding.FragmentBookingBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*


class BookingFragment : Fragment() {
    lateinit var binding: FragmentBookingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // read only
         binding.StartDate.inputType = InputType.TYPE_NULL
        binding.StartTime.inputType = InputType.TYPE_NULL

        binding.calendarView.setOnDateChangeListener(
                OnDateChangeListener { view, year, month, dayOfMonth ->
                    binding.StartDate.setText("")
                    val Date = (dayOfMonth.toString() + "-" + (month + 1) + "-"  + year.toString())
                    binding.StartDate.append(Date)
                })



        // Date
        binding.StartDate.setOnClickListener {
            val cal = Calendar.getInstance()
            val picker =  DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH,month)
                cal.set(Calendar.DAY_OF_MONTH,day)
                binding.StartDate.setText(SimpleDateFormat("dd-MM-yyyy").format(cal.time))
            }
            DatePickerDialog(requireActivity(), picker, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(
                Calendar.DAY_OF_MONTH)).show()

        }

        // TIme
        binding.StartTime.setOnClickListener {
            // Time
            val cal = Calendar.getInstance()
            val picker =  TimePickerDialog.OnTimeSetListener { timePicker, hour, time ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE,time)
                binding.StartTime.setText(SimpleDateFormat("HH:mm").format(cal.time))
            }

            TimePickerDialog(requireActivity(), picker, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()

        }

        binding.toreturn.setOnClickListener {
            view.findNavController().navigate(R.id.action_bookingFragment_to_detailFragment)
        }




        binding.button5.setOnClickListener {
            val matricule = arguments?.getSerializable("data") as String
            var datedeb=binding.StartDate.text.toString()
            //var datefi=binding.EndDate.text.toString()
            var timedeb=binding.StartTime.text.toString()
            val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
            val id=pref.getInt("idUser",0)
            var reservation = Reservation(null, datedeb, "", timedeb, "", 0, "", id, matricule)
            if(id==0)
            {getid(reservation)}
            else {
                //Toast.makeText(requireActivity(), datedeb, Toast.LENGTH_SHORT).show()
                addreservation(reservation)
                view.findNavController().navigate(R.id.action_bookingFragment_to_fragment2)
            }
        }
    }
    private fun getid(reservation:Reservation) {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.getid()
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    reservation.id= response.body()!!.id
                    addreservation(reservation)
                    findNavController().navigate(R.id.action_bookingFragment_to_fragment2)
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
    private fun addreservation(reservation: Reservation) {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.addreservation(reservation)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {

                    Toast.makeText(requireActivity(),"Your reservation has been added successfully", Toast.LENGTH_SHORT).show()

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

}