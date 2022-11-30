package com.murat.taskmanager.ui.auht

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController

import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


import com.murat.taskmanager.databinding.FragmentAuhtBinding
import java.util.concurrent.TimeUnit


class AuthFragment : Fragment() {
   lateinit var binding: FragmentAuhtBinding
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private val mAuth = Firebase.auth
    lateinit var verificationId : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuhtBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetCode.setOnClickListener {
            if (binding.editPhone.text.toString().isEmpty()){
                binding.editPhone.error = "Не верный номер"
            }else{
                val number  = "${binding.editPhoneLayout.prefixText}${binding.editPhone.text}"
                sendVerificationCode(number)
            }




    }

        binding.btnContinue.setOnClickListener {
            if (binding.editSMS.text.toString().isEmpty()){
                binding.editSMS.error = "Не верный код"
            }else{
                verifyCode(binding.editSMS.text.toString())
            }


        }

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                val code : String? = credential.smsCode
                if (code!= null){
                    verifyCode(code)
                }

            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(requireActivity(),"Проверка не удалась", Toast.LENGTH_SHORT).show()
                Log.d("codeError", e.message.toString())
            }

            override fun onCodeSent(
                s: String,
                token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(s, token)
                verificationId = s

                binding.editPhoneLayout.isVisible = false
                binding.btnGetCode.isVisible = false
                binding.btnContinue.isVisible = true
                binding.editSMS.isVisible = true

            }
        }




    }

    private fun sendVerificationCode(number: String) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber(number)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

    }



    private fun verifyCode(code : String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signinbyCredentials(credential)
    }

    private fun signinbyCredentials(credential: PhoneAuthCredential) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(requireActivity(), "Успешно", Toast.LENGTH_SHORT).show()

                    findNavController().navigateUp()
                }

            }


    }}
