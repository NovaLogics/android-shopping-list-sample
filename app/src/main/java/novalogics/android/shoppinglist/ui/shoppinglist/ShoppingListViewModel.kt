package novalogics.android.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.data.repository.ShoppingRepository
import javax.inject.Inject

/**
 * [ShoppingListViewModel] is a ViewModel that manages the UI-related data for the shopping list feature.
 * It acts as a communication layer between the UI (e.g., fragments or activities) and the data layer
 * (e.g., [ShoppingRepository])
 *
 * ## Key Responsibilities:
 * - Fetches all shopping items from the repository using [getAllItems]
 * - Inserts or updates a shopping item in the repository using [insertOrUpdate]
 * - Deletes a shopping item from the repository using [delete]
 *
 * ## Flow of Data:
 * 1. The UI (e.g., a fragment) triggers actions like fetching, inserting, updating, or deleting items.
 * 2. The ViewModel forwards these actions to the [ShoppingRepository].
 * 3. The repository interacts with the data source (e.g., database or network) to perform the operation.
 * 4. The ViewModel exposes the results (e.g., LiveData or StateFlow) back to the UI for display.
 *
 * ## Usage:
 * - The ViewModel is scoped to the lifecycle of the UI component (e.g., fragment or activity) that uses it.
 * - Dependencies like [ShoppingRepository] are injected via Hilt's dependency injection.
 *
 * @property shoppingRepository The repository responsible for handling data operations related to shopping items.
 */

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
