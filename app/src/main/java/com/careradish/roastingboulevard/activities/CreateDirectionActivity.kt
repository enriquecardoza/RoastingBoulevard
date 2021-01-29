package com.careradish.roastingboulevard.activities

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.careradish.roastingboulevard.R

class CreateDirectionActivity : AppCompatActivity() {

    lateinit var editTextLabel:EditText
    lateinit var editTextName:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_address)
    }
}