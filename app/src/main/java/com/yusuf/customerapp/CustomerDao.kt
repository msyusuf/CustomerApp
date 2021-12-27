package com.yusuf.customerapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {


    // Simple query that does not take parameters and returns all customers.
    @Query("SELECT * FROM customer_table")
    fun getAllCustomers(): Flow<List<Customer>>

    @Query("SELECT * FROM customer_table ORDER BY last_name ASC")
    fun getAlphabetizedCustomers(): Flow<List<Customer>>


    @Query("SELECT COUNT(*) FROM customer_table")
    suspend fun getCustomerCount(): Int

    // Simple query that does not take parameters and returns nothing.
    @Query("DELETE FROM customer_table")
    suspend fun deleteAll() :Void

    @Query("SELECT first_name, last_name FROM customer_table")
    fun loadFullName(): List<NameTuple>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    // @Insert(onConflict = OnConflictStrategy.REPLACE)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(customer: Customer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomers(vararg customers: Customer)

    @Update
    suspend fun update(customer: Customer)

    @Update
    suspend fun updateCustomers(vararg customers: Customer)


    @Delete
    suspend fun delete(customer: Customer) :Void

    @Delete
    suspend fun deleteCustomers(vararg customers: Customer) :Void




}