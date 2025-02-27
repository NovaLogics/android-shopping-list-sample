package novalogics.android.shoppinglist.ui.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.databinding.LayoutShoppingItemBinding

/**
 * [ShoppingItemAdapter] is a RecyclerView adapter that manages the display and interaction
 * of a list of [ShoppingItem] objects. It binds each item to a view holder and handles
 * user interactions such as deleting an item or updating its quantity.
 *
 * ## Key Responsibilities:
 * - Binds the list of [ShoppingItem] objects to the RecyclerView.
 * - Handles user interactions for deleting an item or updating its quantity (increment/decrement).
 * - Notifies the parent component of changes via the [deleteItem] and [updateItem] callbacks.
 *
 * ## Flow of Interaction:
 * 1. The adapter receives a list of [ShoppingItem] objects and binds them to the RecyclerView.
 * 2. Each item is displayed with its name, quantity, and buttons for deletion, increment, and decrement.
 * 3. When the user interacts with an item (e.g., clicks the delete or increment button):
 *    - The corresponding callback ([deleteItem] or [updateItem]) is invoked.
 *    - The parent component handles the action (e.g., updating the data source).
 * 4. The adapter updates the UI to reflect any changes in the data.
 *
 * ## Usage:
 * - The adapter is initialized with a list of [ShoppingItem] objects and two callbacks:
 *   - [deleteItem]: Called when the user deletes an item.
 *   - [updateItem]: Called when the user updates an item's quantity.
 * - The parent component (e.g., a fragment or activity) provides the callbacks to handle the actions.
 */
class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val deleteItem: (ShoppingItem) -> Unit,
    private val updateItem: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    /**
     * Creates a new [ShoppingViewHolder] by inflating the item layout.
     *
     * @param parent The parent view group.
     * @param viewType The view type of the new view.
     * @return A new [ShoppingViewHolder] instance.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = LayoutShoppingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoppingViewHolder(binding)
    }

    /**
     * Returns the number of items in the list.
     *
     * @return The number of items.
     */
    override fun getItemCount(): Int = items.count()

    /**
     * Binds the data for a specific [ShoppingItem] to the view holder.
     *
     * @param holder The view holder to bind the data to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem: ShoppingItem = items[position]

        with(holder) {
            // Bind the item's name and amount to the view
            binding.tvName.text = currentShoppingItem.name
            binding.tvAmount.text = "${currentShoppingItem.amount}"

            // Set up the delete button click listener
            binding.ivDelete.setOnClickListener {
                deleteItem(currentShoppingItem)
            }

            // Set up the increment button click listener
            binding.ivPlus.setOnClickListener {
                currentShoppingItem.amount++
                updateItem(currentShoppingItem)
            }

            // Set up the decrement button click listener
            binding.ivMinus.setOnClickListener {
                if (currentShoppingItem.amount > 0) {
                    currentShoppingItem.amount--
                    updateItem(currentShoppingItem)
                }
            }
        }
    }

    /**
     * [ShoppingViewHolder] is a ViewHolder that holds the views for a single [ShoppingItem].
     *
     * @property binding The view binding for the item layout.
     */
    inner class ShoppingViewHolder(val binding: LayoutShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
