package com.example.farmbee

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)



       SP_user.addTextChangedListener(object : TextWatcher {
           override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }

           override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
           }

           override fun afterTextChanged(p0: Editable?) {
               if(android.util.Patterns.EMAIL_ADDRESS.matcher(SP_user.text.toString()).matches()){
                   btn_signup.isEnabled = true
               }
               else{
                   btn_signup.isEnabled = false
                   SP_user.setError("Invalid Email")
               }
           }

       })

       btn_signup.setOnClickListener() {


            //fire base data base
            var database = FirebaseDatabase.getInstance().getReference("USERINFO")
            var category = "FARMER"
            var F_NAME =  ET_Fname.text.toString()
            var L_NAME = ET_Lname.text.toString()
            var PHONE_NO = ET_Pnumber.text.toString()
            var USERID = SP_user.text.toString()
            var PASSWORD = SP_pass.text.toString()

            if (SP_pass.text.toString() == ET_confirmPass.text.toString())
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
            else {
                Toast.makeText(this, "Enter similar Password", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "User not created!! ", Toast.LENGTH_SHORT).show()
            }

        }

        login_here.setOnClickListener {

            startActivity(Intent(this, Login::class.java))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
    fun clearfilds(){
        SP_user.setText("")
        SP_pass.setText("")
        ET_confirmPass.setText("")
        ET_Fname.setText("")
        ET_Lname.setText("")
        ET_Pnumber.setText("")
    }

}
