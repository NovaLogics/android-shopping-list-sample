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

@AndroidEntryPoint
class ShoppingListFragment : Fragment() {

    private val viewModel: ShoppingListViewModel by viewModels()

    private var _binding: FragmentShoppingListBinding? = null
    private val binding get() = _binding!!

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

        adapter = ShoppingItemAdapter(
            items = mutableListOf(),
            deleteItem = { item -> viewModel.delete(item) },
            updateItem = { item -> viewModel.insertOrUpdate(item) }
        )

        binding.rvShoppingItems.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ShoppingListFragment.adapter
        }

        viewModel.getAllItems().observe(viewLifecycleOwner) { items ->
            adapter.items = items
            adapter.notifyDataSetChanged()
        }

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
        _binding = null
    }
}
