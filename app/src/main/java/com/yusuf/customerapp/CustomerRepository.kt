package com.yusuf.customerapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.WorkerThread
import com.yusuf.customerapp.Constants.Companion.LOG_TAG
import kotlinx.coroutines.flow.Flow
import java.lang.Exception

// import timber.log.Timber


/**
 * Abstracted Repository as promoted by the Architecture Guide.
 * https://developer.android.com/topic/libraries/architecture/guide.html
 */
class CustomerRepository (private val customerDao: CustomerDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allCustomers: Flow<List<Customer>> = customerDao.getAlphabetizedCustomers()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun getCustomerById(cust_id: String) : Flow<Customer> {
        return customerDao.getCustomerById(cust_id)
    }
/*
    fun getCustomerById(cust_id: String) : Flow<Customer> = customerDao.getCustomerById(cust_id)
*/


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(customer: Customer) {
        try {
            customerDao.insert(customer)
            Log.i(LOG_TAG, "******** Inserted a customer ${customer} using Dao.")
        }
        catch (e: Exception ) {
            Log.e(LOG_TAG, String.format( "**** Exception ******** Inserted a customer ${customer} using Dao.", e.message))
        }
    }
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    @SuppressLint("LogNotTimber")
    suspend fun deleteAllCustomers() {
        try {
            customerDao.deleteAll()
            Log.i(LOG_TAG, "CustomerRepository. Deleting all customers using CustomerDao.")
        }
        catch (e: Exception ) {
            Log.e(LOG_TAG, String.format( "**** Exception in CustomerRepository. Deleting all customers using CustomerDao.", e.message))
        }
    }
 }