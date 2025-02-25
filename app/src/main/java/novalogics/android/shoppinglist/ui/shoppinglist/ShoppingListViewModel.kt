package novalogics.android.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.data.repository.ShoppingRepository
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    fun insertOrUpdate(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        shoppingRepository.insertOrUpdate(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        shoppingRepository.delete(item)
    }

    fun getAllItems() = shoppingRepository.getAllItems()
}
