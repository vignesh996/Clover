package com.example.clover

import android.R
import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.clover.sdk.v3.connector.IPaymentConnectorListener
import com.clover.sdk.v3.remotepay.*


class MyPaymentConnector(private val context: Context) {

        lateinit var paymentConnectorListener :IPaymentConnectorListener

        fun paymentConnectorMethods() :IPaymentConnectorListener {
        //Implement the interface
          paymentConnectorListener = object : IPaymentConnectorListener {

                override fun onSaleResponse(response: SaleResponse) {
                    Log.d("TAG", "onItemClick: onSaleResponse called")
                    val result: String
                    result = if (response.success) {
                        "Sale was successful"
                    } else {
                        "Sale is unsuccessful" + response.reason + ":" + response.message
                    }
                    Toast.makeText(
                        context.applicationContext.getApplicationContext(),
                        result,
                        Toast.LENGTH_LONG
                    ).show()
                }

                // Implement the other IPaymentConnector listener methods

                override fun onDeviceDisconnected() {
                    Log.d("TAG", "onItemClick:onDeviceDisconnected:")
                }

                override fun onDeviceConnected() {

                        Log.d("TAG", "onItemClick: onDeviceConnected")

                }

                override fun onPreAuthResponse(response: PreAuthResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onAuthResponse(response: AuthResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onTipAdjustAuthResponse(response: TipAdjustAuthResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onCapturePreAuthResponse(response: CapturePreAuthResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onVerifySignatureRequest(request: VerifySignatureRequest?) {
                    TODO("Not yet implemented")
                }

                override fun onConfirmPaymentRequest(request: ConfirmPaymentRequest?) {
                    TODO("Not yet implemented")
                }

                override fun onManualRefundResponse(response: ManualRefundResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onRefundPaymentResponse(response: RefundPaymentResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onTipAdded(tipAdded: TipAdded?) {
                    TODO("Not yet implemented")
                }

                override fun onVoidPaymentResponse(response: VoidPaymentResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onVaultCardResponse(response: VaultCardResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onRetrievePendingPaymentsResponse(retrievePendingPaymentResponse: RetrievePendingPaymentsResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onReadCardDataResponse(response: ReadCardDataResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onCloseoutResponse(response: CloseoutResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onRetrievePaymentResponse(response: RetrievePaymentResponse?) {
                    TODO("Not yet implemented")
                }

                override fun onVoidPaymentRefundResponse(response: VoidPaymentRefundResponse?) {
                    TODO("Not yet implemented")
                }
            }
         return paymentConnectorListener

    }
}