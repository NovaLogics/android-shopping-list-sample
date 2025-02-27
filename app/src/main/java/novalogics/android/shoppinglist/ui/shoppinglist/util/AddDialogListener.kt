package novalogics.android.shoppinglist.ui.shoppinglist.util

import novalogics.android.shoppinglist.data.database.entity.ShoppingItem

/**
 * [AddDialogListener] is an interface used to communicate user actions from the [ShoppingItemDialog]
 * back to the parent component (e.g., a fragment or activity). It provides a callback method for
 * handling the addition of a new shopping item.
 *
 * ## Key Responsibilities:
 * - Defines a contract for handling the "Add" button click event in the [ShoppingItemDialog].
 * - Allows the parent component to receive the new [ShoppingItem] and process it (e.g., save it to a database).
 *
 * ## Usage:
 * - The parent component (e.g., a fragment or activity) implements this interface.
 * - The [ShoppingItemDialog] is initialized with an instance of the parent component as the listener.
 * - When the user clicks the "Add" button in the dialog, the [onAddButtonClicked] method is called,
 *   passing the new [ShoppingItem] to the parent component.
 *
 * ## Example:
 * ```kotlin
 * class ShoppingListFragment : Fragment(), AddDialogListener {
 *     override fun onAddButtonClicked(item: ShoppingItem) {
 *         // Handle the new shopping item (e.g., save it to a database)
 *         viewModel.insertOrUpdate(item)
 *     }
 * }
 * ```
 */
interface AddDialogListener {
    /**
     * Called when the user clicks the "Add" button in the [ShoppingItemDialog]
     *
     * @param item The new [ShoppingItem] created from the user's input
     */
    fun onAddButtonClicked(item: ShoppingItem)
}
