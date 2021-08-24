package com.example.clover.adapder


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.clover.R
import com.example.clover.model.ModelClass

class Adapter(private val invoiceList : List<ModelClass>,
              private val listener : OnItemClickListener) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return  ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = invoiceList[position]
        holder.id.text = currentItem.id
        holder.cusName.text = currentItem.name
        holder.productCode.text = currentItem.productCode
        holder.amount.text = currentItem.amount
    }

    override fun getItemCount(): Int = invoiceList.size


    inner class ViewHolder(itemView :View) : RecyclerView.ViewHolder(itemView),View.OnClickListener {

        val id :TextView = itemView.findViewById(R.id.invoice_id)
        val date :TextView = itemView.findViewById(R.id.invoice_date)
        val amount :TextView = itemView.findViewById(R.id.amount)
        val cusName :TextView = itemView.findViewById(R.id.customer_name)
        val productCode :TextView = itemView.findViewById(R.id.product_code)
        val payBtn :Button = itemView.findViewById(R.id.pay_btn)

        init {
            itemView.setOnClickListener(this)
            payBtn.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }


}