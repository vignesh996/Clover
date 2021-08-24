package com.example.clover

import android.accounts.Account
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clover.connector.sdk.v3.PaymentConnector
import com.clover.sdk.util.CloverAccount
import com.clover.sdk.util.CloverAuth
import com.clover.sdk.v3.connector.ExternalIdUtils
import com.clover.sdk.v3.remotepay.SaleRequest
import com.example.clover.adapder.Adapter
import com.example.clover.model.ModelClass
import com.example.clover.model.MyDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class InvoiceListActivity : AppCompatActivity(), Adapter.OnItemClickListener {

    lateinit  var invoiceList :List<ModelClass>
    lateinit var paymentConnector :PaymentConnector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invoice_list)
        var recyclerView : RecyclerView = findViewById(R.id.recycler_view)

        invoiceList = generateList()
        recyclerView.adapter = Adapter(invoiceList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        // Get the Clover account that will be used with the service; uses the GET_ACCOUNTS permission
        var cloverAccount = CloverAccount.getAccount(this)
        if (cloverAccount == null){
            Log.d("TAG", "onItemClick: account  not initialized")
        }
        Log.d("TAG", "onItemClick: account initialized")
        queryWebService(cloverAccount)

        paymentConnector = initializePaymentConnector(cloverAccount)




    }

    private fun queryWebService(cloverAccount: Account) {
        object : AsyncTask<Void?, String?, Void?>() {
            override fun doInBackground(vararg params: Void?): Void? {
                try {
                    val authResult = CloverAuth.authenticate(this@InvoiceListActivity, cloverAccount)
                    Log.d("TAG", "onItemClick: Successfully authenticated as " + cloverAccount + ".  authToken=" + authResult.authToken + ", authData=" + authResult.authData)

                    if (authResult.authToken != null && authResult.baseUrl != null) {
                        val retrofit = Retrofit.Builder()
                            .baseUrl(authResult.baseUrl.toString())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                        val service = retrofit.create(ApiStories::class.java)
                        val call = service.getResponse()
                            call.enqueue(
                            object : Callback<MyDataItem> {
                                override fun onResponse(
                                    call: Call<MyDataItem>?,
                                    response: Response<MyDataItem>?
                                ) {
                                    if (response != null) {
                                        if (response.isSuccessful) {
                                            Log.d("TAG", "onItemClick:  retrofit Successful")
                                            val data = response.body()
                                            Log.d("TAG", "onItemClick:  retrofit Successful ${data.merchantId} name ${data.name}")
                                        }
                                    }
                                }

                                override fun onFailure(call: Call<MyDataItem>?, t: Throwable?) {

                                }

                            }
                        )

                    }

                }catch (e: Exception) {
                    Log.d("TAG", "onItemClick: exception" + e)
                }
                Log.d("TAG", "onItemClick: queryWebService inside try end")
                return null
            }

    }.execute()
    }

    fun generateList(): List<ModelClass>{
        val invoiceList= ArrayList<ModelClass>()
        invoiceList.add(ModelClass("1", "Vignesh", "1234", "100"))
        invoiceList.add(ModelClass("2", "Jaya", "12758", "200"))
        invoiceList.add(ModelClass("3", "Arun", "15564", "1000"))
        invoiceList.add(ModelClass("4", "Suresh", "1354", "100"))
        invoiceList.add(ModelClass("5", "Kumar", "12234", "400"))
        invoiceList.add(ModelClass("6", "Mahi", "18934", "600"))
        invoiceList.add(ModelClass("7", "Partha", "134", "200"))
        invoiceList.add(ModelClass("8", "Vinai", "13864", "900"))
        return invoiceList
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item ${position + 1} is clicked", Toast.LENGTH_SHORT).show()
        Log.d("TAG", "onItemClick: ${invoiceList[position].id}")
        Log.d("TAG", "onItemClick: ${invoiceList[position].amount}")

        var saleRequest : SaleRequest = setupSaleRequest(invoiceList[position].amount)

        paymentConnector.sale(saleRequest)

        Log.d("TAG", "onItemClick: finish")


    }

    private fun initializePaymentConnector(cloverAccount: Account): PaymentConnector {
        // Set your RAID as the remoteApplicationId
        val remoteApplicationId = "J3HG8P5ZJDRPA.Z70ZEZC3A2ABR"

        var paymentConnectorListener = com.example.clover.MyPaymentConnector(this).paymentConnectorMethods()

        // Create the PaymentConnector with the context, account, listener, and RAID
        return PaymentConnector(this, cloverAccount, paymentConnectorListener, remoteApplicationId)
    }

    private fun setupSaleRequest(amount: String): SaleRequest {
        // Create a new SaleRequest and populate the required request fields
        val saleRequest = SaleRequest()
        saleRequest.externalId = ExternalIdUtils.generateNewID() //required, but can be any string
        saleRequest.amount = 2000L


        return saleRequest
    }



}