package com.example.passwordgenerator

import android.os.Bundle
import android.widget.Button
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

        btnGenerate.setOnClickListener {
            val password = generatePassword(16)
            passwordField.setText(generatePassword(16))
        }

        btnClear.setOnClickListener {
            passwordField.setText("")
        }

    }

    fun generatePassword(length: Int): String {
        val letters: String = "abcdefghijklmnopqrstuvwxyz"
        var password: String = ""
        var i: Int = 0
        while(i < length) {
            password += letters[Random.nextInt(0, 25)]
            i++
        }
        return password
    }
}