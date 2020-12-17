package com.abhi.electiondata

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import android.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var etSearch : TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etSearch=findViewById(R.id.etField)

        etSearch.addTextChangedListener {
            println(etSearch.text.toString())
        }
    }
}