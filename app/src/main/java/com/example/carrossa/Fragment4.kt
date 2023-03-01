package com.example.carrossa

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.carrossa.databinding.Fragment4Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.MultipartBody.Part.createFormData
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream


class Fragment4 : Fragment() {
    lateinit var binding: Fragment4Binding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    lateinit var imageBitmap: Bitmap
    //lateinit var user: User
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = Fragment4Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
        val id=pref.getInt("idUser",0)
        if(id==0)
        {getid()}
        else{
        getUser(id)}
        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val intent = result.data
                if (result.resultCode == AppCompatActivity.RESULT_OK && intent != null) {
                    val selectedImageUri = intent.getData()
                    imageBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        val source = ImageDecoder.createSource(requireActivity().contentResolver, selectedImageUri!!)
                        ImageDecoder.decodeBitmap(source)
                    } else {
                        @Suppress("DEPRECATION")
                        MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImageUri)
                    }

                    binding.profilpic.setImageBitmap(imageBitmap)

                }
            }
        binding.addpic.setOnClickListener {
            imageChooser()
        }
        binding.imageView16.setOnClickListener{
            view.findNavController().navigate(R.id.action_fragment4_to_fragment1)
        }

        binding.exit.setOnClickListener {
            pref.edit {
                putBoolean("connected", false)

            }
            val intent = Intent(requireActivity(), login::class.java)
            this.startActivity(intent)
            requireActivity().finish()
        }


    }
    private fun getid() {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.getid()
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if (response.isSuccessful) {
                    val id= response.body()!!.id
                    getUser(id)

                }
                else {
                    Toast.makeText(requireActivity(),"une erreur", Toast.LENGTH_SHORT).show()}
            }

        }
    }
    private fun getUser(id:Int) {
        CoroutineScope(Dispatchers.IO).launch {

            val response =RetrofitService.endpoint.getuserbyid(id)
            withContext(Dispatchers.Main) {
                // binding.progressBar.visibility= View.INVISIBLE
                if(response.isSuccessful) {
                    val user = response.body()
                    if(user!=null) {

                        binding.fullname.text = user.full_name
                        binding.phonenumber.text = user.phone_number
                        binding.creditnardnumber.text=user.credit_card.toString()
                        binding.creditcardvalidity.text=user.validity_card
                        Glide.with(requireContext()).load(url+user.driving_licence).into(binding.driving)
                        //Glide.with(requireContext()).load(url+user.profil_pic).into(binding.profilpic)
                    }


                }
                else {
                    Toast.makeText(requireActivity(),"une erreur 200", Toast.LENGTH_SHORT).show()
                }




            }

        }


    }


    fun imageChooser() {

        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        activityResultLauncher.launch(intent)
    }
}