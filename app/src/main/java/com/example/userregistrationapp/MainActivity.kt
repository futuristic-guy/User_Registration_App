package com.example.userregistrationapp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {


    lateinit var firstNameL: TextInputLayout
    lateinit var firstName: TextInputEditText
    lateinit var lastNameL: TextInputLayout
    lateinit var lastName: TextInputEditText
    lateinit var phoneL: TextInputLayout
    lateinit var phone: TextInputEditText
    lateinit var emailL: TextInputLayout
    lateinit var email: TextInputEditText
    lateinit var gender: RadioGroup
    lateinit var male: RadioButton
    lateinit var female: RadioButton
    lateinit var ageL: TextInputLayout
    lateinit var age: TextInputEditText
    lateinit var passwordL: TextInputLayout
    lateinit var password: TextInputEditText
    lateinit var addBtn: Button
    lateinit var showUserBtn: Button
    lateinit var genderTextV: TextView
    private var firstNameStr: String = ""
    private var lastNameStr: String = ""
    private var phoneStr: String = ""
    private var emailStr: String = ""
    private var ageStr: String = ""
    private var passwordStr: String = ""
    private var genderStr: String = ""
    private var userList = ArrayList<Model>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        assignViewObj()
        firstNameValidator()
        lastNameValidator()
        phoneNumberValidator()
        emailAddressValidator()
        ageValidator()
        passwordValidator()
        genderValidator()
        addDataHandler()
        showUsers()

    }


    private fun firstNameValidator() {

        firstName.setOnFocusChangeListener { view, isFocused ->

            if (!isFocused) {
                firstNameStr = firstName.text.toString().trim()

                if (firstNameStr.isEmpty()) {
                    firstNameL.helperText = "Required"
                } else if (firstNameStr.length <= 2) {
                    firstNameL.helperText = "Length must be greater than 2"
                } else {
                    firstNameL.helperText = null
                }


            }

        }


    }

    private fun lastNameValidator() {

        lastName.setOnFocusChangeListener { view, isFocused ->

            if (!isFocused) {
                lastNameStr = lastName.text.toString().trim()

                if (lastNameStr.isEmpty()) {
                    lastNameL.helperText = "Required"
                } else if (lastNameStr!!.length <= 2) {
                    lastNameL.helperText = "Length must be greater than 2"
                } else {
                    lastNameL.helperText = null
                }
            }

        }

    }

    private fun phoneNumberValidator() {


        phone.setOnFocusChangeListener { view, isFocused ->


            if (!isFocused) {
                phoneStr = phone.text.toString().trim()

                if (phoneStr.isEmpty()) {
                    phoneL.helperText = "Required"
                } else if (phoneStr.length != 10) {
                    phoneL.helperText = "Number must be of 10 digits"
                } else {
                    phoneL.helperText = null
                }

            }

        }


    }

    private fun emailAddressValidator() {

        email.setOnFocusChangeListener { view, isFocused ->

            if (!isFocused) {
                emailStr = email.text.toString().trim()

                emailL.helperText = if (emailStr.isEmpty()) {
                    "Required"
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
                    "Invalid Email Address"
                } else {
                    null
                }

            }

        }

    }

    private fun ageValidator() {

        age.setOnFocusChangeListener { view, isFocused ->

            if (!isFocused) {

                ageStr = age.text.toString().trim()

                ageL.helperText = if (ageStr.isEmpty()) {
                    "Required"
                } else if (ageStr.toInt() < 12) {
                    "Age above 11 allowed"
                } else {
                    null
                }

            }

        }

    }

    private fun passwordValidator() {

        password.setOnFocusChangeListener { view, isFocused ->

            if (!isFocused) {

                passwordStr = password.text.toString().trim()

                passwordL.helperText = if (passwordStr.isEmpty()) {
                    "Required"
                } else if (passwordStr.length < 8) {
                    "Minimum 8 character password"
                } else {
                    null
                }

            }

        }

    }

    private fun genderValidator() {

        gender.setOnCheckedChangeListener { radioGroup, checkedId ->


                var id = findViewById<RadioButton>(checkedId)
                if(id != null) {
                    genderStr = id.text.toString()
                }
        }

    }

    private fun addDataHandler() {

        addBtn.setOnClickListener {
            var temp = 1

            if ((firstNameL.helperText != null) || (lastNameL.helperText != null) || (phoneL.helperText != null) || (emailL.helperText != null) || (ageL.helperText != null) || (passwordL.helperText != null)) {
                temp = 0
            } else if (firstNameStr.isEmpty() || lastNameStr.isEmpty() || phoneStr.isEmpty() || emailStr.isEmpty() || ageStr.isEmpty() || passwordStr.isEmpty()) {
                temp = 0
                checkIfEmpty()
            } else {
                if (gender.checkedRadioButtonId == -1) {
                    temp = 0
                    genderTextV.setTextColor(Color.parseColor("#FF0000"))
                } else {
                    genderTextV.setTextColor(resources.getColor(R.color.black))
                }
            }

            //Passing Data
            if (temp == 1) {
                addData()
            } else {

            }

        }

    }

    private fun addData() {
        userList.add(Model("$firstNameStr $lastNameStr",phoneStr,emailStr,genderStr ))
        reset()
    }

    private fun reset() {
        firstNameL.helperText = null
        lastNameL.helperText = null
        phoneL.helperText = null
        emailL.helperText = null
        ageL.helperText = null
        passwordL.helperText = null
        firstName.text = null
        lastName.text = null
        phone.text = null
        email.text = null
        age.text = null
        password.text = null
        gender.clearCheck()
        firstNameStr = ""
        lastNameStr = ""
        phoneStr = ""
        emailStr = ""
        ageStr = ""
        ageStr = ""
        passwordStr = ""
        genderStr= ""
    }

    private fun showUsers(){

        showUserBtn.setOnClickListener {
            when {
                userList.size <= 0 -> {
                    Toast.makeText(this,"Please add atleast 5 user",Toast.LENGTH_SHORT).show()
                }
                userList.size in 1..4 -> {
                    Toast.makeText(this,"Please add ${5 - userList.size} more users", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    passData()
                }
            }
        }

    }

    private fun passData() {
        var intent = Intent(this, RecycleShowData::class.java)

        var bundle = Bundle()
        bundle.putParcelableArrayList("uList", userList)
        intent.putExtra("Bundle", bundle)

        startActivity(intent)
    }





    private fun checkIfEmpty() {
        if (firstNameStr.isEmpty()) {
            firstNameL.helperText = "Required"
        }
        if (lastNameStr.isEmpty()) {
            lastNameL.helperText = "Required"
        }
        if (phoneStr.isEmpty()) {
            phoneL.helperText = "Required"
        }
        if (emailStr.isEmpty()) {
            emailL.helperText = "Required"
        }
        if (ageStr.isEmpty()) {
            ageL.helperText = "Required"
        }
        if (passwordStr.isEmpty()) {
            passwordL.helperText = "Required"
        }
        if (gender.checkedRadioButtonId == -1) {
            genderTextV.setTextColor(Color.parseColor("#FF0000"))
        } else {
            genderTextV.setTextColor(resources.getColor(R.color.black))
        }
    }


    private fun assignViewObj(): Unit {
        firstNameL = findViewById(R.id.firstNameL)
        firstName = findViewById(R.id.firstName)
        lastNameL = findViewById(R.id.lastNameL)
        lastName = findViewById(R.id.lastName)
        phoneL = findViewById(R.id.userNumberL)
        phone = findViewById(R.id.userNumber)
        emailL = findViewById(R.id.userEmailL)
        email = findViewById(R.id.userEmail)
        gender = findViewById(R.id.radioGroup)
        male = findViewById(R.id.male)
        female = findViewById(R.id.female)
        genderTextV = findViewById(R.id.gender)
        ageL = findViewById(R.id.ageL)
        age = findViewById(R.id.age)
        passwordL = findViewById(R.id.passwordL)
        password = findViewById(R.id.password)
        addBtn = findViewById(R.id.addBtn)
        showUserBtn = findViewById(R.id.showUserBtn)
    }


}