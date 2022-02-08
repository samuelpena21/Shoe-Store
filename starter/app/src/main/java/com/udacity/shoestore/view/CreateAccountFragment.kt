package com.udacity.shoestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.SharePrefs
import com.udacity.shoestore.databinding.FragmentCreateAccountBinding
import com.udacity.shoestore.models.User

class CreateAccountFragment : Fragment() {

    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            createUserBtn.setOnClickListener {
                if (validatePassword()) {
                    createUser()
                    showUserHasBeenCreatedMessage()
                    navigateBackToLogin()
                } else {
                    showPasswordsMatchError()
                }
            }
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.passwordEdit.text.toString()
        val repeatedPassword = binding.repeatPasswordEdit.text.toString()

        return password == repeatedPassword
    }

    private fun createUser() {
        val email = binding.emailEdit.text.toString()
        val password = binding.passwordEdit.text.toString()

        val user = User(email, password)
        SharePrefs.saveUser(user, requireContext())
    }

    private fun navigateBackToLogin() {
        findNavController().popBackStack()
    }

    private fun showPasswordsMatchError() {
        Snackbar.make(binding.root, "Passwords needs to be the same!", Snackbar.LENGTH_LONG).show()
    }

    private fun showUserHasBeenCreatedMessage() {
        Snackbar.make(binding.root, "User Created correctly", Snackbar.LENGTH_SHORT).show()
    }
}