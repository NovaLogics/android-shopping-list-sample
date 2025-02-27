package novalogics.android.shoppinglist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import novalogics.android.shoppinglist.data.database.ShoppingDao
import novalogics.android.shoppinglist.data.repository.ShoppingRepository

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideShoppingRepository(
        shoppingDao: ShoppingDao
    ): ShoppingRepository =
        ShoppingRepository(
            shoppingDao = shoppingDao
        )
}
