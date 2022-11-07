package android.example.androiddevelopment.fragments

import android.example.androiddevelopment.R
import android.example.androiddevelopment.databinding.FragmentListBinding
import android.example.androiddevelopment.model.Product
import android.example.androiddevelopment.recyclerview.MarginItemDecoration
import android.example.androiddevelopment.recyclerview.ProductAdapter
import android.example.androiddevelopment.repository.ClothesRepository
import android.example.androiddevelopment.repository.ShoesRepository
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class ListFragment : Fragment(R.layout.fragment_list) {

    private var binding : FragmentListBinding? = null
    private var list : List<Product>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListBinding.bind(view)

        val choice = arguments?.getString(ARG_CHOICE)

        binding?.run {

            toolbarList.inflateMenu(R.menu.list_actionbar)

            toolbarList.setNavigationIcon(R.drawable.ic_back_arrow)
            toolbarList.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }

            toolbarList.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.search -> {
                        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.search_by_photo -> {
                        Toast.makeText(context, "Search by photo", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

            choice?.apply {
                list = choiceList(this)
                rvProducts.adapter = ProductAdapter(choiceList(this), Glide.with(view)) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, ItemFragment.newInstance(it.id, this))
                        .addToBackStack("ListFragment")
                        .commit()
                }
                toolbarList.title = choice
                toolbarList.subtitle = "${list?.size} products"
            }

            rvProducts.addItemDecoration(
                MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.rv_product))
            )

        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        private const val ARG_CHOICE = "ARG_CHOICE"

        fun newInstance(choice : String) = ListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_CHOICE, choice)
            }
        }
    }

    private fun choiceList(choice: String) : List<Product> {
        return if (choice == "Shoes") {
            ShoesRepository.shoes
        } else {
            ClothesRepository.clothes
        }
    }

}