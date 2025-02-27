package novalogics.android.shoppinglist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem

/**
 * Represents the Room database for the ShoppingList application
 *
 * This class provides access to the underlying SQLite database using Room's abstraction layer.
 * It follows the singleton pattern to ensure only one instance of the database is created
 * throughout the application's lifecycle
 *
 * Annotations:
 * - `@Database`: Marks this class as a Room database. It specifies the entities (tables)
 *                contained in the database and the database version. Optionally, it can
 *                export schema versions for version control.
 * - `@Volatile`: Ensures visibility of changes to the `instance` variable across threads.
 * - `@JvmStatic`: (Implicitly used in the companion object) Ensures that the methods in the
 *                 companion object are treated as static methods in Java.
 *
 * Database Properties:
 * - `entities`: Lists the entity classes (tables) included in the database.
 * - `version`: Specifies the version of the database. Increment this value when the schema changes.
 * - `exportSchema`: Controls whether Room exports the database schema to a folder. Set to `false`
 *                   to disable schema export.
 */
@Database(
    entities = [ShoppingItem::class],
    version = 1,
    exportSchema = false
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingDatabase? = null
        /**
         * Returns the singleton instance of the database
         * If the instance doesn't exist, it creates one using the provided context
         */
        fun getDatabase(context: Context): ShoppingDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create a new instance of the database if it doesn't exist
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingDatabase::class.java,
                    "shopping_database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
