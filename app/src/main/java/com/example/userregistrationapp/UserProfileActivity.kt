package com.example.userregistrationapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class UserProfileActivity : AppCompatActivity() {

    lateinit var imageV: ImageView
    lateinit var userText: TextView
    lateinit var phoneText: TextView
    lateinit var emailText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        initView()

        actionBar?.title = "User Profile"
        supportActionBar?.title = "User Profile"
        var gender = intent.getStringExtra("uGender")
        if(gender == "Male"){
            imageV.setImageResource(R.drawable.male)
        }else {
            imageV.setImageResource(R.drawable.female)
        }

        userText.text = intent.getStringExtra("uName")
        phoneText.text = "+91 " + intent.getStringExtra("uPhone")
        emailText.text = intent.getStringExtra("uEmail")
    }

    private fun initView() {
        imageV = findViewById(R.id.imageView)
        userText = findViewById(R.id.profile_name_text)
        phoneText = findViewById(R.id.profile_phone_text)
        emailText = findViewById(R.id.profile_email_text)
    }

}