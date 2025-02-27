package novalogics.android.shoppinglist.ui.shoppinglist.util

import novalogics.android.shoppinglist.data.database.entity.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}
