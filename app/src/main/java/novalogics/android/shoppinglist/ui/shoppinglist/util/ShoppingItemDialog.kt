package novalogics.android.shoppinglist.ui.shoppinglist.util

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.databinding.DialogAddShoppingItemsBinding

class ShoppingItemDialog(
    context: Context,
    private val addDialogListener: AddDialogListener
) : AppCompatDialog(context) {

    private lateinit var binding: DialogAddShoppingItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DialogAddShoppingItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val name: String = binding.etName.text.toString().trim()
            val amount: String = binding.etAmount.text.toString().trim()

            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(
                    context,
                    "Please enter all information",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name = name, amount = amount.toInt())
            addDialogListener.onAddButtonClicked(item = item)

            dismiss()
        }

        binding.btnCancel.setOnClickListener {
            cancel()
        }
    }
}
