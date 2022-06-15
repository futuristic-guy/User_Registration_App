package com.example.userregistrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ShowData : AppCompatActivity() {

    lateinit var nameD: TextView
    lateinit var phoneD: TextView
    lateinit var emailD: TextView
    lateinit var genderD: TextView
    lateinit var ageD: TextView
    lateinit var pD: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_data)
        initObj()


        val bundle = intent.getBundleExtra("Bundle")
        nameD.text = "${bundle?.getString("firstName")} ${bundle?.getString("lastName")}"
        phoneD.text = "${bundle?.getString("phone")}"
        emailD.text = "${bundle?.getString("email")}"
        genderD.text = "${bundle?.getString("gender")}"
        ageD.text = "${bundle?.getString("age")}"
        pD.text = "${bundle?.getString("password")}"

    }


    private fun initObj() {
        nameD = findViewById(R.id.nameData)
        phoneD = findViewById(R.id.phoneData)
        emailD = findViewById(R.id.emailData)
        genderD = findViewById(R.id.genderData)
        ageD = findViewById(R.id.ageData)
        pD = findViewById(R.id.pData)
    }

}