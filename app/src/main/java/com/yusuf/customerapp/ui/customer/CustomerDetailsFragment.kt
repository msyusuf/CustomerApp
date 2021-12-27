package com.yusuf.customerapp.ui.customer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import com.yusuf.customerapp.Constants
import com.yusuf.customerapp.Customer
import com.yusuf.customerapp.CustomerApplication
import com.yusuf.customerapp.R
import com.yusuf.customerapp.databinding.FragmentCustomerBinding
import com.yusuf.customerapp.databinding.FragmentCustomerDetailsBinding

class CustomerDetailsFragment : Fragment() {

/*
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_customer_details, container, false)
        return inflater.inflate(R.layout.fragment_customer_details, container, false)
    }
*/


    val customerViewModel: CustomerViewModel by viewModels {
        CustomerViewModelFactory((activity?.application as CustomerApplication).repository)
    }
    // private var allCustomers: LiveData<List<Customer>>? = null

    private var _binding: FragmentCustomerDetailsBinding? = null
    private val binding get() = _binding!!

    /***
     * onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.i(Constants.LOG_TAG, "Customer Details Fragment before binding. **")
        _binding = FragmentCustomerDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Log.i(Constants.LOG_TAG, "Customer Details Fragment. Done onCreateView.")
        return root
    }


}