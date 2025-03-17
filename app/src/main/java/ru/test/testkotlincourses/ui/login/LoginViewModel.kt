package ru.test.testkotlincourses.ui.login

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModel
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _isButtonEnabled = MutableStateFlow(false)
    val isButtonEnabled = _isButtonEnabled.asStateFlow()

    private val emailState = MutableStateFlow("")
    private val passState = MutableStateFlow("")

    fun setupInputValidation(
        inputField: TextInputEditText,
        setInput: (String) -> Unit,
        validateInput: () -> Unit
    ) {

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val inputTxt = inputField.inputToString()
                setInput(inputTxt)
                validateInput()
            }
        }

        inputField.addTextChangedListener(textWatcher)
    }


    fun setEmail(email: String) {
        emailState.value = email
    }

    fun setPass(pass: String) {
        passState.value = pass
    }

    fun validateInput() {
        _isButtonEnabled.value = isEmailValid(emailState.value) && passState.value.length >= 6
    }


    fun isEmailValid(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}

fun TextInputEditText.inputToString() = this.text.toString()
