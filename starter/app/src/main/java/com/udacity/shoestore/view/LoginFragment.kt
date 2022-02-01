package com.udacity.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener {
            val email = binding.emailEdit.text
            val pass = binding.passwordEdit.text

            if (!email.isNullOrEmpty() && !pass.isNullOrEmpty()) {
                goToWelcomeScreen()
            }
        }
    }

    private fun goToWelcomeScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToWelcomeFragment())
    }

}