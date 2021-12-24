package com.yusuf.customerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class CustomerAdapter(customers: LiveData<List<Customer>>) :
    RecyclerView.Adapter<CustomerAdapter.ViewHolder>() {

    private var dataList: LiveData<List<Customer>>? = null

    init {
        setCustomerList(customers)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(com.yusuf.customerapp.R.layout.card_customer_layout, viewGroup, false)
        return ViewHolder(v)
    }

     fun setCustomerList(customers: LiveData<List<Customer>>) {
        this.dataList = customers
        notifyDataSetChanged()      // Fix Me: see if this is needed if using LiveData
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // add customerImage...
        val customerFirstName: TextView = itemView.findViewById(R.id.cust_first_name)
        val customerLastName: TextView = itemView.findViewById(R.id.cust_last_name)

        init {
            itemView.setOnClickListener { v: View ->
                var position: Int = getAdapterPosition()
                customerLastName.text.toString()

                Snackbar.make(
                    v, "Click detected on custmoer ${++position}",
                    Snackbar.LENGTH_LONG
                ).setAction("Action", null).show()

                // Display CustomerDetails

                v.findNavController()
                    .navigate(R.id.action_nav_customer_to_customerDetailsFragment)
                /***
                 * Fix me.
                 * Need to pass user ID (which I don't have yet) and then display detailed
                 * inforamtion about this customer
                 */
/*
                val nextFrag = CustomerDetailsFragment()
                    getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_customer, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit()
*/
            } // end of itemView Listner
        } // end init block of ViewHolder

    } // end ViewHolder class


    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<Customer>() {
            override fun areItemsTheSame(oldCustomer: Customer, newCustomer: Customer): Boolean {
                return oldCustomer === newCustomer
            }

            override fun areContentsTheSame(oldCustomer: Customer, newCustomer: Customer): Boolean {
                return (oldCustomer.lastName == newCustomer.lastName && oldCustomer.lastName == newCustomer.lastName)   //Fix Me
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, i: Int) {

        holder.customerFirstName.text = this.dataList?.value?.get(i)?.firstName.toString()
        holder.customerLastName.text = this.dataList?.value?.get(i)?.lastName.toString()

    } // end onBindViewHolder

    override fun getItemCount(): Int {
        return if (dataList!!.value == null) 0 else dataList!!.value!!.size     // may not return the right size????
    } // end getItemCount()
}

