package android.example.androiddevelopment.fragments

import android.example.androiddevelopment.R
import android.example.androiddevelopment.databinding.FragmentChoiceBinding
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class ChoiceFragment : Fragment(R.layout.fragment_choice) {

    private var binding : FragmentChoiceBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChoiceBinding.bind(view)

        binding?.run {

            toolbarChoice.setNavigationIcon(R.drawable.ic_back_arrow)
            toolbarChoice.setNavigationOnClickListener {
                Toast.makeText(context, "back", Toast.LENGTH_SHORT).show()
            }

            btnClothes.setOnClickListener {
                val choice = "Clothes"
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ListFragment.newInstance(choice))
                    .addToBackStack("ChoiceFragment")
                    .commit()
            }

            btnShoes.setOnClickListener {
                val choice = "Shoes"
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ListFragment.newInstance(choice))
                    .addToBackStack("ChoiceFragment")
                    .commit()
            }
        }

    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    companion object {
        const val ARG_CHOICE = "ARG_CHOICE"
    }

}