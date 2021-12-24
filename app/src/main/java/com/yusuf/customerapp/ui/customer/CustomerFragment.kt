package com.yusuf.customerapp.ui.customer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuf.customerapp.Constants
import com.yusuf.customerapp.Constants.Companion.LOG_TAG
import com.yusuf.customerapp.Customer
import com.yusuf.customerapp.Customer.*
import com.yusuf.customerapp.CustomerAdapter
import com.yusuf.customerapp.CustomerApplication
import com.yusuf.customerapp.databinding.FragmentCustomerBinding
//import timber.log.Timber

class CustomerFragment : Fragment() {

    val customerViewModel: CustomerViewModel by viewModels {
        CustomerViewModelFactory((activity?.application as CustomerApplication).repository)
    }
    private var allCustomers: LiveData<List<Customer>>? = null

    private var _binding: FragmentCustomerBinding? = null
    private val binding get() = _binding!!

    /***
     * onCreateView
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
         _binding = FragmentCustomerBinding.inflate(inflater, container, false)
        val root: View = binding.root
        Log.i(LOG_TAG, "CustomerFragment. Done onCreateView.")
        return root
    }

    /***
     * onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customerViewModel.deleteAllCustomers()
        try {
            for (i in 1..10   ) {
                val customerFirstName = "First name" + (100 + i )
                val customerLastName = "Last name" + (100 + i )
                val customer = Customer(0, customerFirstName, customerLastName)
                customerViewModel.insert(customer)
            }
        }
        catch (e: Exception) {
            Log.e(
                Constants.LOG_TAG, String.format( "**** Exception In CustomerFragment, after createing customer object in Customer fragment%s", e.message)
            )
        }
       allCustomers = customerViewModel.allCustomers

        val adapter = CustomerAdapter(allCustomers!!)

        binding.recyclerview.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerview.adapter = adapter

        customerViewModel.allCustomers.observe(this.viewLifecycleOwner) { items ->
            items.let{
                adapter.setCustomerList(allCustomers!!)
            }
        }

        Log.i(LOG_TAG, "CustomerFragment. Done ** onViewCreated. **")
        // listenerSetup()
        //observerSetup(viewModel.getAllCustomer())
        // recyclerSetup()
    }


//    private fun listenerSetup() {
//        TODO("Not yet implemented")
//    }

    /***
     *  recyclerSetup()
     */
    private fun recyclerSetup() {
    } // end recyclerSetup()


    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)
        Log.i(LOG_TAG, "CustomerFragment,start of onActivityResult...")


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i(LOG_TAG, "On Destroy called for Customer Fragment")
    }
}