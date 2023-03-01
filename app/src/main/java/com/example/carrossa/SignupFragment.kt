package com.example.carrossa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.carrossa.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {

    lateinit var binding: FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button3.setOnClickListener { view: View ->
            if (binding.editTextPhone.text.toString().length < 10) {
                // Show an error message to the user
                binding.editTextPhone.error = "Enter 10 characters"
            }
            if (binding.editTextTextPersonName.text.length == 0) {
                // Show an error message to the user
                binding.editTextTextPersonName.error = "Enter your name"
            }
            if (binding.editTextNumber.text.length == 0) {
                // Show an error message to the user
                binding.editTextNumber.error = "Enter your credit card number"
            }
            if (binding.editTextExpirationdate.text.length == 0) {
                // Show an error message to the user
                binding.editTextExpirationdate.error = "Enter date expiration"
            }
            val user_name = binding.editTextTextPersonName.text.toString()
            val phone_number = binding.editTextPhone.text.toString()

            val credit_card = binding.editTextNumber.text.toString().toInt()
            val date_expiration = binding.editTextExpirationdate.text.toString()

            if (binding.editTextPhone.text.toString().length == 10 && binding.editTextTextPersonName.text.length > 0 &&
                binding.editTextNumber.text.length > 0 && binding.editTextExpirationdate.text.length > 0) {
                val user = User(-1,user_name, phone_number,null,credit_card,date_expiration, null,"")
                val data = bundleOf("data" to user)
                view.findNavController().navigate(R.id.action_signupFragment_to_signup2Fragment, data)
            }
        }

    }
}