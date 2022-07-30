package com.example.farmbee

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_customer_registration.*
import kotlinx.android.synthetic.main.activity_register.*

class Customer_registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_registration)

        CSP_user.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(CSP_user.text.toString()).matches()){
                    Cbtn_signup.isEnabled = true
                }
                else{
                    Cbtn_signup.isEnabled = false
                    CSP_user.setError("Invalid Email")
                }
            }
        })


        Cbtn_signup.setOnClickListener() {

            //fire base data base
            var database = FirebaseDatabase.getInstance().getReference("USERINFO")
            var category = "CUSTOMER"
            var F_NAME =  CET_Fname.text.toString()
            var L_NAME = CET_Lname.text.toString()
            var PHONE_NO = CET_Pnumber.text.toString()
            var USERID = CSP_user.text.toString()
            var PASSWORD = CSP_pass.text.toString()

            if (CSP_pass.text.toString() == CET_confirmPass.text.toString())
            {
                //Firebase code
                var user = UserInfo(category,USERID,F_NAME,L_NAME,PHONE_NO,PASSWORD)
                database.push().setValue(user).addOnSuccessListener {
                    Toast.makeText(this,"User Created", Toast.LENGTH_LONG).show()
                }
                    .addOnFailureListener {
                        Toast.makeText(this,"Failed", Toast.LENGTH_LONG).show()
                    }
                clearfilds()
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(Intent(this, Login::class.java))
            }
            else
            {
                Toast.makeText(this, "Enter similar Password", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "User not created!! ", Toast.LENGTH_SHORT).show()
            }
        }

        Clogin_here.setOnClickListener{
            startActivity(Intent(this,Login::class.java))
        }
    }
    fun clearfilds(){
        CSP_user.setText("")
        CSP_pass.setText("")
        CET_confirmPass.setText("")
        CET_Fname.setText("")
        CET_Lname.setText("")
        CET_Pnumber.setText("")
    }
}