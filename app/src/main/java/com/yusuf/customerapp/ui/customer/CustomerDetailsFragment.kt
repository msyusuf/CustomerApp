package com.yusuf.customerapp.ui.customer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.yusuf.customerapp.Constants
import com.yusuf.customerapp.CustomerApplication
import com.yusuf.customerapp.databinding.FragmentCustomerDetailsBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CustomerDetailsFragment : Fragment() {


    val customerViewModel: CustomerViewModel by viewModels {
        CustomerViewModelFactory((activity?.application as CustomerApplication).repository)
    }

    private var _binding: FragmentCustomerDetailsBinding? = null
    private val binding get() = _binding!!

    /***
     * onCreateView */
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
     * onViewCreated */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val customer =
                customerViewModel.getCustomerById("1")
            val name = customer.first()?.firstName
            Log.e(Constants.LOG_TAG, "**** Customer details fragment. Customer name is ${name}")
        } // end LifecycleScope.launch

    }   // end fun onViewCreated

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.i(Constants.LOG_TAG, "On Destroy called for Customer DETAILS Fragment")
    } // end onDestroy

} // end class CustomerDetailsFragment