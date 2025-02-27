package novalogics.android.shoppinglist.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * [AppModule] is a Dagger Hilt module that provides application-wide dependencies.
 * This module is installed in the [SingletonComponent], meaning the provided dependencies
 * will have a singleton scope and live as long as the application.
 *
 * This module currently provides the [Context] of the application, which can be injected
 * into other parts of the app where needed.
 *
 * @Provides: Indicates that the function provides a dependency.
 * @Singleton: Ensures that the provided dependency is a singleton.
 * @ApplicationContext: Injects the application context provided by Hil
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ): Context = context
}
