package com.example.carrossa

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.car.Car
import com.example.car.binding
import com.example.carrossa.databinding.Fragment1Binding
import com.example.carrossa.databinding.FragmentListBluetoothBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

class ListBluetooth : Fragment() {
    var m_bluetoothAdapter: BluetoothAdapter? = null
    lateinit var m_pairedDevices: Set<BluetoothDevice>
    val REQUEST_ENABLE_BLUETOOTH = 1
    lateinit var binding: FragmentListBluetoothBinding
    companion object {
        val UUID_STRING: String = "00001101-0000-1000-8000-00805F9B34FB" // UUID for serial port profile (SPP)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBluetoothBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val reservationpin = arguments?.getSerializable("data") as ReservationPin



        @SuppressLint("MissingPermission")

        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (m_bluetoothAdapter == null) {
            Toast.makeText(requireActivity(), "This device doesn't support bluetooth", Toast.LENGTH_SHORT).show()
            return
        }

        if (!m_bluetoothAdapter!!.isEnabled) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
        }


        binding.searchbluetooth.setOnClickListener { pairedDeviceList() }
    }

        @SuppressLint("MissingPermission")
        private fun pairedDeviceList() {
            m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
            val list : ArrayList<BluetoothDevice> = ArrayList()
            val listName : ArrayList<String> = ArrayList()

            if (!m_pairedDevices.isEmpty()) {
                for (device: BluetoothDevice in m_pairedDevices) {
                    list.add(device)
                    listName.add(device.name)
                    Log.i("device", ""+device)
                }
            } else {
                Toast.makeText(requireActivity(), "no paired bluetooth devices found", Toast.LENGTH_SHORT).show()
            }

            val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, listName)


            binding.simpleListView.adapter = adapter
            binding.simpleListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                val device: BluetoothDevice = list[position]
                val address: String = device.address
                val name: String = device.name
                Toast.makeText(requireActivity(), "Connecting to $name", Toast.LENGTH_SHORT).show()
                connectToDevice(device)
            }
        }


        @SuppressLint("MissingPermission")
        private fun connectToDevice(device: BluetoothDevice) {
            val uuid = UUID.fromString(UUID_STRING)
            val socket: BluetoothSocket = device.createRfcommSocketToServiceRecord(uuid)
            try {
                val reservationpin = arguments?.getSerializable("data") as ReservationPin
                val PIN_CODE = reservationpin.pin
                val id_reservation = reservationpin.idreservation.toString()
                val message = PIN_CODE+id_reservation

                socket.connect()
                Log.i("Bluetooth", "Connected to device ${device.name}")
                val outputStream: OutputStream = socket.outputStream
                outputStream.write(message.toByteArray())
                //outputStream.write(id_reservation.toByteArray())

                Log.i("Bluetooth", "Sent PIN code $PIN_CODE to device ${device.name}")
                Toast.makeText(requireActivity(), "Sent PIN code $PIN_CODE  to device ${device.name}", Toast.LENGTH_SHORT).show()
                Thread.sleep(5000) //attendre jusqu'a ce que le systeme embarqué de la voiture change l'etat de la réservation
                getstate(reservationpin.idreservation)

            } catch (e: IOException) {
                Log.e("Bluetooth", "Error connecting to device ${device.name}: ${e.message}")
            }
        }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    if (m_bluetoothAdapter!!.isEnabled) {
                        // toast("Bluetooth has been enabled")
                    } else {
                        //toast("Bluetooth has been disabled")
                    }
                } else if (resultCode == AppCompatActivity.RESULT_CANCELED) {
                    //toast("Bluetooth enabling has been canceled")
                }
            }
        }
    private fun getstate(idreservation:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val response =RetrofitService.endpoint.getstate(idreservation)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    if (response.body()?.state=="verified") {
                        findNavController().navigate(R.id.action_listBluetooth_to_unlockCar)
                    }
                    else{ Toast.makeText(requireActivity(),"Votre réservation n'est pas vérifiée " , Toast.LENGTH_SHORT).show()}


                }
                else {
                    Toast.makeText(requireContext(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }

}
