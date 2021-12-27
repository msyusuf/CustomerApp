package com.yusuf.customerapp.ui.customer

//import androidx.lifecycle.*
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yusuf.customerapp.Constants.Companion.LOG_TAG
import com.yusuf.customerapp.Customer
import com.yusuf.customerapp.CustomerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
// import timber.log.Timber

/**
 * View Model to keep a reference to the word repository and
 * an up-to-date list of all words.
 */

open class CustomerViewModel (private val repository: CustomerRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.

    val allCustomers: LiveData<List<Customer>> = repository.allCustomers.asLiveData()

    fun insert(customer: Customer) = viewModelScope.launch {
        repository.insert(customer)
    }

    fun deleteAllCustomers() = viewModelScope.launch {
        repository.deleteAllCustomers()
    }
}
class CustomerViewModelFactory(private val repository: CustomerRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.i(LOG_TAG, "******** In CustomerViewModelFactory ******** ")
        if (modelClass.isAssignableFrom(CustomerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CustomerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

