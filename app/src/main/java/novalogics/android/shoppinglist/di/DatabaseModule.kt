package novalogics.android.shoppinglist.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import novalogics.android.shoppinglist.data.database.ShoppingDao
import novalogics.android.shoppinglist.data.database.ShoppingDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ShoppingDatabase {
        return ShoppingDatabase.getDatabase(context = context)
    }

    @Provides
    fun provideShoppingDao(database: ShoppingDatabase): ShoppingDao {
        return database.getShoppingDao()
    }
}
