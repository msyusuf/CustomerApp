package com.yusuf.customerapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
// Fimport timber.log.Timber

class CustomerApplication : Application() {

    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

/*    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())        // Using Timber for logging.
    }*/

    val database by lazy { CustomerDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { CustomerRepository(database.customerDao()) }

}