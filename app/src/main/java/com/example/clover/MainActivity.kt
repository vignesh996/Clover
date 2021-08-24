package com.example.clover

import android.accounts.Account
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.clover.sdk.util.CloverAccount
import com.clover.sdk.v1.BindingException
import com.clover.sdk.v1.ClientException
import com.clover.sdk.v1.ServiceException
import com.clover.sdk.v3.inventory.InventoryConnector
import com.clover.sdk.v3.inventory.Item


class MainActivity : AppCompatActivity() {

    lateinit var name : EditText
    lateinit var password : EditText
    lateinit var loginBtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.et_email)
        password = findViewById(R.id.et_pass)
        loginBtn = findViewById(R.id.login_btn)

        loginBtn.setOnClickListener {

        if (name.text.isNullOrEmpty() && password.text.isNullOrEmpty()){
            Toast.makeText(this,"Name and Password Mandatory",Toast.LENGTH_SHORT).show()
        }else if (!name.text.isNullOrEmpty() && password.text.isNullOrEmpty()){
            Toast.makeText(this,"Password Mandatory",Toast.LENGTH_SHORT).show()
        }else if (name.text.isNullOrEmpty() && (!password.text.isNullOrEmpty())){
            Toast.makeText(this,"Name Mandatory",Toast.LENGTH_SHORT).show()
        }else if (!name.text.isNullOrEmpty() && !password.text.isNullOrEmpty()){
            if (name.text.toString() == "vignesh" && password.text.toString() == "vignesh"){
                val intent = Intent(this, InvoiceListActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Account details not valid",Toast.LENGTH_SHORT).show()
            }



        }
        }

    }

    override fun onResume() {
        super.onResume()
    }


}