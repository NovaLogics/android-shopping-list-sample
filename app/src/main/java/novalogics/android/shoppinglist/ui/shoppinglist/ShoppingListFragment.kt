package novalogics.android.shoppinglist.ui.shoppinglist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import novalogics.android.shoppinglist.data.database.entity.ShoppingItem
import novalogics.android.shoppinglist.databinding.FragmentShoppingListBinding
import novalogics.android.shoppinglist.ui.shoppinglist.adapter.ShoppingItemAdapter
import novalogics.android.shoppinglist.ui.shoppinglist.util.AddDialogListener
import novalogics.android.shoppinglist.ui.shoppinglist.util.ShoppingItemDialog

/**
 * [ShoppingListFragment] is a fragment that displays a list of shopping items and allows users to
 * add, update, or delete items. It serves as the UI layer for the shopping list feature
 *
 * ## Key Responsibilities:
 * - Displays a list of shopping items using a RecyclerView and [ShoppingItemAdapter]
 * - Observes changes in the shopping list data from the [ShoppingListViewModel] and updates the UI
 * - Handles user interactions, such as adding, updating, or deleting items, by delegating actions
 *   to the [ShoppingListViewModel]
 * - Uses a FloatingActionButton (FAB) to open a dialog for adding new shopping items
 *
 * ## Flow of Data:
 * 1. The fragment observes the list of shopping items from the [ShoppingListViewModel]
 * 2. When the data changes, the RecyclerView adapter is updated, and the UI is refreshed
 * 3. User interactions (e.g., adding, updating, or deleting items) are forwarded to the ViewModel
 * 4. The ViewModel processes these actions and updates the repository, which in turn updates the data source
 * 5. The fragment observes the updated data and reflects the changes in the UI
 *
 * ## Usage:
 * - The fragment is annotated with [@AndroidEntryPoint] to enable Hilt dependency injection.
 * - The ViewModel is accessed using the `by viewModels()` delegate, ensuring it is scoped to the fragment's lifecycle
 * - View binding is used to safely access UI elements, and the binding is cleared in [onDestroyView] to avoid memory leaks
 */
@AndroidEntryPoint
class ShoppingListFragment : Fragment() {

    // ViewModel instance provided by Hilt
    private val viewModel: ShoppingListViewModel by viewModels()

    // View binding for the fragment
    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!

    // Adapter for the RecyclerView
    private lateinit var adapter: ShoppingItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentShoppingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the adapter with empty data and click listeners
        adapter = ShoppingItemAdapter(
            items = mutableListOf(),
            deleteItem = { item -> viewModel.delete(item) },
            updateItem = { item -> viewModel.insertOrUpdate(item) }
        )

        // Set up the RecyclerView with a LinearLayoutManager and the adapter
        binding.rvShoppingItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ShoppingListFragment.adapter
        }

        // Observe the list of shopping items from the ViewModel
        viewModel.getAllItems().observe(viewLifecycleOwner) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged()
        }

        // Set up the FloatingActionButton to open a dialog for adding new items
        binding.fabAdd.setOnClickListener {
            ShoppingItemDialog(requireContext(), object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.insertOrUpdate(item)
                }
            }).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Clear the binding to avoid memory leaks
        _binding = null
    }
}
