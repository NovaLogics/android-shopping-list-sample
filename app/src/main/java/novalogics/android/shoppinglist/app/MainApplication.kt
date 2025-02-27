package novalogics.android.shoppinglist.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * [MainApplication] is the application class for the app and serves as the entry point
 * for Hilt's dependency injection. It initializes Hilt and provides application-wide
 * dependencies.
 *
 * ## Key Responsibilities:
 * - Acts as the application's entry point and initializes Hilt for dependency injection.
 * - Provides a central place for application-level configurations and dependencies.
 *
 * ## Usage:
 * - The class is annotated with [@HiltAndroidApp] to enable Hilt's code generation
 *   and dependency injection.
 * - Hilt automatically generates necessary components and modules for dependency injection
 *   based on this class.
 *
 * ## Example:
 * - Any application-wide dependencies (e.g., database, network, or shared preferences)
 *   can be provided using Hilt modules and accessed throughout the app.
 */
@HiltAndroidApp
class MainApplication : Application()
