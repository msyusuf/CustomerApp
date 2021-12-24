package com.yusuf.customerapp.ui.customer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yusuf.customerapp.R

class CustomerDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_customer_details, container, false)
        return inflater.inflate(R.layout.fragment_customer_details, container, false)
    }
}