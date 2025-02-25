package novalogics.android.shoppinglist.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem

/**
 * Data Access Object (DAO) for performing database operations on the `shopping_item` table.
 * This interface provides methods to insert, delete, and query shopping items in the database.
 * Room automatically generates the implementation of this interface at compile time.
 *
 * Annotations:
 * - `@Dao`: Marks this interface as a DAO, enabling Room to generate the necessary code for database operations.
 * - `@Insert`: Marks a method for inserting an item into the database. The `onConflict` strategy specifies
 *              how to handle conflicts (e.g., replacing the existing item if the primary key matches).
 * - `@Delete`: Marks a method for deleting an item from the database.
 * - `@Query`: Allows writing custom SQL queries to interact with the database. The query is validated at compile time.
 * - `OnConflictStrategy.REPLACE`: Specifies that if a conflict occurs (e.g., duplicate primary key),
 *                                 the existing item will be replaced.
 * - `suspend`: Marks a function as a coroutine, allowing it to be called from a coroutine or another suspend function.
 * - `LiveData`: Wraps the query result in an observable data holder, enabling real-time updates to the UI.
 */

@Dao
interface ShoppingDao {
    /**
     * Inserts or replaces a shopping item in the database
     * If an item with the same primary key already exists, it will be replaced.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(item: ShoppingItem)

    /**
     * Deletes a shopping item from the database.
     */
    @Delete
    suspend fun delete(item: ShoppingItem)

    /**
     * Retrieves all shopping items from the database as LiveData.
     * LiveData allows observing changes to the data in real-time.
     */
    @Query("SELECT * FROM shopping_items")
    fun getAllItems(): LiveData<List<ShoppingItem>>
}
