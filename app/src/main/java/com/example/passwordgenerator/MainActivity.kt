package com.example.passwordgenerator

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnGenerate: Button = findViewById(R.id.btn_generate)
        val btnClear: Button = findViewById(R.id.btn_clear)
        val passwordField: TextInputEditText = findViewById(R.id.password_field)

        val lengthField: TextInputEditText = findViewById(R.id.length_field)

        val checkLowercase: CheckBox = findViewById(R.id.check_lowercase)
        val checkUppercase: CheckBox = findViewById(R.id.check_uppercase)
        val checkNumbers: CheckBox = findViewById(R.id.check_numbers)
        val checkSymbols: CheckBox = findViewById(R.id.check_symbols)

        btnGenerate.setOnClickListener {
            if(lengthField.text.isNullOrEmpty()) {
                Toast.makeText(this, "Define a length", Toast.LENGTH_LONG).show()
            }
            else {
                try {
                    val length: Long = lengthField.text.toString().toLong()
                    val password = generatePassword(length,
                        checkLowercase.isChecked,
                        checkUppercase.isChecked,
                        checkNumbers.isChecked,
                        checkSymbols.isChecked)
                    passwordField.setText(password)
                } catch(e: NumberFormatException) {
                    Toast.makeText(this, "Invalid length", Toast.LENGTH_LONG).show()
                }
            }
        }

        btnClear.setOnClickListener {
            passwordField.setText("")
        }

    }

    fun generatePassword(length: Long,
                         hasLowercase: Boolean,
                         hasUppercase: Boolean,
                         hasNumbers: Boolean,
                         hasSymbols: Boolean): String {
        if(!hasLowercase && !hasUppercase && !hasNumbers && !hasSymbols) {
            return "peido"
        }
        val letters: String = "abcdefghijklmnopqrstuvwxyz"
        val numbers: String = "123456789"
        val symbols: String = "!@#$%&*()_"
        var password: String = ""
        var i: Int = 0

        var type: Int
        while(i < length) {
            type = Random.nextInt(0, 4)
            if(type == 0 && hasLowercase) {
                password += letters[Random.nextInt(0, letters.length)]
                i++
            }
            else if(type == 1 && hasUppercase) {
                password += letters[Random.nextInt(0, letters.length)].uppercase()
                i++
            }
            else if(type == 2 && hasNumbers) {
                password += numbers[Random.nextInt(0, numbers.length)]
                i++
            }
            else if(type == 3 && hasSymbols) {
                password += symbols[Random.nextInt(0, symbols.length)]
                i++
            }
        }
        return password
    }
}