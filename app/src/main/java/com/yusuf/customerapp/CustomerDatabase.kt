package com.yusuf.customerapp

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.yusuf.customerapp.Constants.Companion.LOG_TAG
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
// import timber.log.Timber

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */

@Database (entities = [Customer::class], version = 2)
abstract class CustomerDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    companion object {
        @Volatile
        private var INSTANCE: CustomerDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CustomerDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            Log.i(LOG_TAG,"Inside create database...")
            return INSTANCE ?: synchronized(this) {
                Log.i(LOG_TAG,"Inside create database, Creating a new Instance?")
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerDatabase::class.java,
                    "scheduler_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .addCallback(CustomerDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


        private class CustomerDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.i(LOG_TAG,"Inside CustomerDatabaseCallback, onCreate")

                // If you want to keep the data through app restarts,
                // comment out the following lines and try catch. or
                // comment out customerDao.deleteAll() in populateDatabase
                try {
                    INSTANCE?.let { database ->
                        scope.launch(Dispatchers.IO) {
                            populateDatabase(database.customerDao())
                        }
                    }
                } catch (e: Exception) {
                    Log.e(LOG_TAG,"**** Exception populating DB. ${e.message}" )
                }
            }

            /**
             * Populate the database in a new coroutine.
             */

            suspend fun populateDatabase(customerDao: CustomerDao) {
                // Start the app with a clean database every time.
                // Not needed if you only populate on creation.
                Log.e(LOG_TAG,
                    "**** FIX ME - from callback, populateDatabase(Dao). Keeping track on how often this is called." )
                // customerDao.deleteAll()
            }
        }


    }
}
