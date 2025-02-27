package novalogics.android.shoppinglist.ui.shoppinglist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.databinding.LayoutShoppingItemBinding

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val deleteItem: (ShoppingItem) -> Unit,
    private val updateItem: (ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val binding = LayoutShoppingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoppingViewHolder(binding)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currentShoppingItem: ShoppingItem = items[position]

        with(holder) {
            binding.tvName.text = currentShoppingItem.name
            binding.tvAmount.text = "${currentShoppingItem.amount}"

            binding.ivDelete.setOnClickListener {
                deleteItem(currentShoppingItem)
            }

            binding.ivPlus.setOnClickListener {
                currentShoppingItem.amount++
                updateItem(currentShoppingItem)
            }

            binding.ivMinus.setOnClickListener {
                if (currentShoppingItem.amount > 0) {
                    currentShoppingItem.amount--
                    updateItem(currentShoppingItem)
                }
            }
        }
    }

    inner class ShoppingViewHolder(val binding: LayoutShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
