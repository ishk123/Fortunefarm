package com.example.farmbee

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class Login : AppCompatActivity() {

    private lateinit var dbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var splogin = getSharedPreferences("splogin", Context.MODE_PRIVATE)

        //validation
        ET_user.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if(android.util.Patterns.EMAIL_ADDRESS.matcher(ET_user.text.toString()).matches()){
                    btn_login.isEnabled = true
                }
                else{
                    btn_login.isEnabled = false
                    ET_user.setError("Invalid Email")
                }
            }
        })

        btn_login.setOnClickListener {

            var adddata = splogin.edit()
            adddata.putString("email", ET_user.text.toString())
            adddata.commit()

            getUserDetail()
        }

        register.setOnClickListener {
            startActivity(Intent(this, LaunchPage::class.java))
        }

    }



    private fun getUserDetail(){
        dbref = FirebaseDatabase.getInstance().getReference("USERINFO")
        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(usernapshot in snapshot.children){
                        val user = usernapshot.getValue(User_fetch::class.java)
                        val category = user!!.category
                        val userid = user!!.userid
                        val userpass = user!!.password
                        Log.d("Test",userid)
                        Log.d("Test",category)
                        Log.d("Test",userpass)
                        var id = ET_user.text.toString()
                        var pass = ET_pass.text.toString()
                        if (id==userid && pass == userpass){
                            if(category == "FARMER"){
                                startActivity(Intent(this, Home::class.java))
                                clearfilds()
                            }
                            else{
                                startActivity(Intent2(this, Customer_Home::class.java))
                                clearfilds()
                            }
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun Intent2(valueEventListener: ValueEventListener, java: Class<Customer_Home>): Intent? {
        startActivity(Intent(applicationContext,Customer_Home::class.java))
        return null
    }

    private fun Intent(valueEventListener: ValueEventListener, java: Class<Home>): Intent? {
        startActivity(Intent(applicationContext,Home::class.java))
        return null
    }

    fun clearfilds(){

        ET_user.setText("")
        ET_pass.setText("")
    }

}