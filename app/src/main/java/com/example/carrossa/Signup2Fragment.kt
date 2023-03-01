package com.example.carrossa


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.Context
import android.content.pm.PackageManager
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.contentValuesOf
import androidx.core.content.edit
import com.example.car.Car
import com.example.carrossa.databinding.FragmentSignup2Binding
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class Signup2Fragment : Fragment() {
    lateinit var binding: FragmentSignup2Binding
    private val image = 100
    private val photo = 200
    var bool =0
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    val requestCode = 400
    lateinit var imageBitmap:Bitmap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignup2Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//camera

            activityResultLauncher=
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                    val intent = result.data
                    if (result.resultCode == RESULT_OK && intent != null) {
                        imageBitmap = intent.extras?.get("data") as Bitmap
                        val resizedBitmap = Bitmap.createScaledBitmap(imageBitmap, 300, 300, true)
                        binding.imagedriving.setImageBitmap(resizedBitmap)
                    }
                }



        binding.takephoto.setOnClickListener{
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED)  {
                openCameraIntent()
            }
            else {
                checkPermission()
            }

        }





        binding.signup.setOnClickListener {
            binding.signup.isEnabled  = false
            val user = arguments?.getSerializable("data") as User
            // convert Bitmap to File
            val filesDir = requireActivity().getApplicationContext().getFilesDir()
            val file = File(filesDir, "image" + ".png")
            val bos = ByteArrayOutputStream()
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, bos)
            val bitmapdata = bos.toByteArray()
            val fos = FileOutputStream(file)
            fos.write(bitmapdata)
            fos.flush()
            fos.close()
            val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
            val image = MultipartBody.Part.createFormData("image", file.getName(), reqFile)
            val userBody =  MultipartBody.Part.createFormData("user", Gson().toJson(user))
              binding.progressBar.visibility = View.VISIBLE
            addUser(image,userBody)

        }
    }

    fun openCameraIntent() {
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        bool=1
        activityResultLauncher.launch(pictureIntent)

    }




    // Request permission

    private fun checkPermission() {
        val perms = arrayOf(Manifest.permission.CAMERA)

        ActivityCompat.requestPermissions(requireActivity(),perms, requestCode)

    }

    override fun onRequestPermissionsResult(permsRequestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(permsRequestCode, permissions, grantResults)
        if (permsRequestCode == requestCode && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCameraIntent()
        }

    }
    private fun addUser(body: MultipartBody.Part, user:MultipartBody.Part) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitService.endpoint.adduser(body,user)
            val pref = requireActivity().getSharedPreferences("user_db", Context.MODE_PRIVATE)
            withContext(Dispatchers.Main) {
                binding.signup.isEnabled  = true

                if (response.isSuccessful) {
                    pref.edit {
                        putBoolean("connected", true)

                    }
                    val intent = Intent(requireActivity(), MainActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
                else {

                    Toast.makeText(requireContext(),"Error",Toast.LENGTH_SHORT).show()
                }


            }



        }


    }

}

