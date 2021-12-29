package com.yusuf.customerapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class CustomerTest {

    private lateinit var customerDao: CustomerDao
    private lateinit var db: CustomerDatabase

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, CustomerDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        customerDao = db.customerDao()
    } // end createDb

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    } // end closeDb

    /*
        Insert Customer
     */
    @Test
    @Throws(Exception::class)
    fun insertCustomer() = runBlocking {
        // This is a new database that is just for testing. Add a new customer and validate
        val customer = Customer(0, "Mohammad", "Yusuf")
        customerDao.insert(customer)
        val allCustomers = customerDao.getAllCustomers().first()
        assertEquals(allCustomers[0].firstName, customer.firstName)
    }

    /*
        Get Customer Count
         1) DeleteAll customers,
         2) get customer count. it should be 0.
    */
    @Test
    @Throws(Exception::class)
    fun getCustomerCount() = runBlocking {
        customerDao.deleteAll()
        var count: Int = customerDao.getCustomerCount()
        assertEquals(0, count)
    }

    /*
     Get Customer Count
      1) DeleteAll customers,
      2) Insert 2 customers,
      3) get customer count. it should be 2.
 */
    @Test
    @Throws(Exception::class)
    fun getCustomerCount2() = runBlocking {
        customerDao.deleteAll()

        val customer1 = Customer(0, "Mohammad", "Yusuf")
        customerDao.insert(customer1)
        val customer2 = Customer(0, "Mohammad Saleem", "Yusuf")
        customerDao.insert(customer2)

        val count = customerDao.getCustomerCount()
        assertEquals(2, count)
    }

    @Test
    @Throws(Exception::class)
    fun getCustomerById() = runBlocking {
        val customer1 = Customer(0, "Mohammad", "Yusuf")
        customerDao.insert(customer1)
        val customer = customerDao.getCustomerById("1")
        val name = customer.first()?.firstName
        assertEquals("Mohammad", customer.first()?.firstName)
    }


} // end CustomerTest