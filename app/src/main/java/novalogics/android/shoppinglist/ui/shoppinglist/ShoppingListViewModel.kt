package novalogics.android.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.data.repository.ShoppingRepository
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    fun getAllItems() = shoppingRepository.getAllItems()

    fun insertOrUpdate(item: ShoppingItem) {
        viewModelScope.launch {
            shoppingRepository.insertOrUpdate(item)
        }
    }

    fun delete(item: ShoppingItem) {
        viewModelScope.launch {
            shoppingRepository.delete(item)
        }
    }
}
