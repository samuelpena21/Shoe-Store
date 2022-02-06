package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.viewmodel.UserViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: UserViewModel by activityViewModels()

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
            val email = binding.emailEdit.text.toString()
            val pass = binding.passwordEdit.text.toString()

            viewModel.login(email = email, password = pass)
        }

        binding.createAccountTv.setOnClickListener {
            navigateToCreateAccountScreen()
        }

        viewModel.loginSuccessEvent.observe(viewLifecycleOwner) { successEvent ->
            if (successEvent == true) {
                findNavController().popBackStack()
            }
        }
    }

    private fun navigateToCreateAccountScreen() {
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCreateAccountFragment())
    }
}