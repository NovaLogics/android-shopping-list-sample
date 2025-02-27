package novalogics.android.shoppinglist.app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import novalogics.android.shoppinglist.databinding.ActivityMainBinding
import novalogics.android.shoppinglist.ui.shoppinglist.ShoppingListFragment

/**
 * [MainActivity] is the entry point of the application and serves as the main container
 * for the app's UI. It sets up the activity's layout, enables edge-to-edge display,
 * and manages the navigation between fragments.
 *
 * ## Key Responsibilities:
 * - Sets up the activity's layout using view binding.
 * - Enables edge-to-edge display to allow content to extend behind system bars.
 * - Manages fragment transactions, replacing the fragment container with the [ShoppingListFragment]
 *   when the activity is first created.
 *
 * ## Flow of Interaction:
 * 1. The activity is launched, and the layout is inflated using view binding.
 * 2. Edge-to-edge display is enabled, allowing the app's content to extend behind the system bars.
 * 3. If the activity is being created for the first time (i.e., `savedInstanceState` is `null`),
 *    the [ShoppingListFragment] is added to the fragment container.
 * 4. The [ShoppingListFragment] becomes the main UI of the activity, handling the display
 *    and interaction of the shopping list.
 *
 * ## Usage:
 * - The activity is annotated with [@AndroidEntryPoint] to enable Hilt dependency injection.
 * - View binding is used to safely access UI elements.
 * - Edge-to-edge display is enabled to provide a modern, immersive user experience.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // View binding for the activity's layout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display
        enableEdgeToEdge()

        // Inflate the activity's layout using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Replace the fragment container with the ShoppingListFragment if this is the first creation
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, ShoppingListFragment())
                .commit()
        }
    }
}
