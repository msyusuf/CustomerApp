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
import com.yusuf.customerapp.databinding.FragmentCustomerBinding
import com.yusuf.customerapp.databinding.FragmentCustomerDetailsBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CustomerDetailsFragment : Fragment() {


    val customerViewModel: CustomerViewModel by viewModels {
        CustomerViewModelFactory((activity?.application as CustomerApplication).repository)
    }

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
    } // end onCreateView

    /***
     * onViewCreated
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customer = customerViewModel.geCustomerById("11")           // customerViewModel.getCustById("11")
        //Log.i(Constants.LOG_TAG, "Customer details fragment. ${customer.first().firstName}.  Done ** onViewCreated. **")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i(Constants.LOG_TAG, "On Destroy called for Customer DETAILS Fragment")
    }
}