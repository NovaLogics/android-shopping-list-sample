package novalogics.android.shoppinglist.data.repository

import novalogics.android.shoppinglist.data.database.ShoppingDao
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao
) {
    suspend fun insertOrUpdate(item: ShoppingItem) = shoppingDao.insertOrUpdate(item)

    suspend fun delete(item: ShoppingItem) = shoppingDao.delete(item)

    fun getAllItems() = shoppingDao.getAllItems()
}
