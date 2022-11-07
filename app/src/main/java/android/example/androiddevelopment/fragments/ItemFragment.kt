package android.example.androiddevelopment.fragments

import android.example.androiddevelopment.R
import android.example.androiddevelopment.databinding.FragmentItemBinding
import android.example.androiddevelopment.databinding.FragmentListBinding
import android.example.androiddevelopment.model.Product
import android.example.androiddevelopment.repository.ClothesRepository
import android.example.androiddevelopment.repository.ShoesRepository
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ItemFragment : Fragment(R.layout.fragment_item) {

    private var binding : FragmentItemBinding? = null
    private var product : Product? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentItemBinding.bind(view)

        val id = arguments?.getInt(ARG_ID)
        val choice = arguments?.getString(ARG_CHOICE)

        choice?.apply {
            id?.apply {
                product = choiceList(choice)[this]
            }
        }

        binding?.run {

            toolbarItem.inflateMenu(R.menu.item_actionbar)

            toolbarItem.setNavigationIcon(R.drawable.ic_back_arrow)
            toolbarItem.setNavigationOnClickListener {
                parentFragmentManager.popBackStack()
            }

            toolbarItem.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.search -> {
                        Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }

            Glide.with(root)
                .load(product?.imageUrl)
                .into(imgExpanded)

            product?.apply {
                tvBrand.text = this.brand
                tvModel.text = this.model
                tvPrice.text = this.price
                tvSizes.text = this.sizes
                tvDescription.text = this.description
            }

        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        private const val ARG_ID ="ARG_ID"
        private const val ARG_CHOICE = "ARG_CHOICE"

        fun newInstance(
            id: Int,
            choice : String
        ) = ItemFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
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