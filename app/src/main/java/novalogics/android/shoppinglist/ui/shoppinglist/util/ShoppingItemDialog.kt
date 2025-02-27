package novalogics.android.shoppinglist.ui.shoppinglist.util

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.databinding.DialogAddShoppingItemsBinding

/**
 * [ShoppingItemDialog] is a custom dialog that allows users to add a new shopping item.
 * It provides input fields for the item's name and amount, and buttons to confirm or cancel the action.
 *
 * ## Key Responsibilities:
 * - Displays input fields for the shopping item's name and amount.
 * - Validates user input to ensure all required fields are filled.
 * - Communicates the new shopping item back to the parent component via the [AddDialogListener].
 * - Provides a "Cancel" button to dismiss the dialog without adding an item.
 *
 * ## Flow of Interaction:
 * 1. The dialog is displayed to the user with empty input fields.
 * 2. The user enters the item's name and amount.
 * 3. When the "Add" button is clicked:
 *    - The input is validated to ensure no fields are empty.
 *    - If validation passes, a new [ShoppingItem] is created and passed to the [AddDialogListener].
 *    - The dialog is dismissed.
 * 4. If the "Cancel" button is clicked, the dialog is dismissed without any action.
 *
 * ## Usage:
 * - The dialog is initialized with a context and an instance of [AddDialogListener].
 * - The parent component (e.g., a fragment or activity) implements [AddDialogListener] to handle the new item.
 */
class ShoppingItemDialog(
    context: Context,
    private val addDialogListener: AddDialogListener
) : AppCompatDialog(context) {

    // View binding for the dialog layout
    private lateinit var binding: DialogAddShoppingItemsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the dialog layout using view binding
        binding = DialogAddShoppingItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the "Add" button click listener
        binding.btnAdd.setOnClickListener {
            // Get the input values
            val name: String = binding.etName.text.toString().trim()
            val amount: String = binding.etAmount.text.toString().trim()

            // Validate the input
            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(
                    context,
                    "Please enter all information",
                    Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }

            // Create a new ShoppingItem and pass it to the listener
            val item = ShoppingItem(name = name, amount = amount.toInt())
            addDialogListener.onAddButtonClicked(item = item)

            // Dismiss the dialog
            dismiss()
        }

        // Set up the "Cancel" button click listener
        binding.btnCancel.setOnClickListener {
            cancel()
        }
    }
}
