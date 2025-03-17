package ru.test.testkotlincourses.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.test.testkotlincourses.R
import ru.test.testkotlincourses.databinding.FragmentLoginBinding


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private val viewModel by viewModels<LoginViewModel>()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupInputValidation()
        setupButtonState()
        setupNavigation()
    }

    private fun setupInputValidation() {
        binding.apply {
            viewModel.setupInputValidation(
                emailInput,
                { viewModel.setEmail(emailInput.inputToString()) },
                {
                    viewModel.validateInput()
                    validateEmailField(emailInput.inputToString())
                }
            )
            viewModel.setupInputValidation(
                passwordInput,
                { viewModel.setPass(passwordInput.inputToString()) },
                { viewModel.validateInput() },

                )
        }

    }

    private fun validateEmailField(emailInput: String) {
        if (emailInput.isNotEmpty() && !viewModel.isEmailValid(emailInput)) {
            binding.emailInputLayout.error = "Некорректный email"
        } else {
            binding.emailInputLayout.error = null
        }
    }


    private fun setupButtonState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isButtonEnabled.collectLatest { isEnabled ->
                binding.loginButton.isEnabled = isEnabled
            }


        }
    }

    private fun setupNavigation() {
        binding.loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_home)
        }
        binding.vkButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://vk.com/"))
            startActivity(intent)
        }
        binding.okButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ok.ru/"))
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}