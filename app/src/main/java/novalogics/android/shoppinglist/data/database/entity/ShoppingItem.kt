package novalogics.android.shoppinglist.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @ColumnInfo(name = "item_name")
    var name: String,

    @ColumnInfo(name = "item_amount")
    var amount: Int,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    var id: Int = 0
}
