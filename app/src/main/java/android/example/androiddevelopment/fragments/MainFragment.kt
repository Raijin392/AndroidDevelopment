package android.example.androiddevelopment.fragments

import android.content.res.Configuration
import android.example.androiddevelopment.R
import android.example.androiddevelopment.databinding.FragmentMainBinding
import android.example.androiddevelopment.dialogs.CounterDialogFragment
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding : FragmentMainBinding? = null
    private var counter = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(ARG_COUNT)
        }

        binding?.run {

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {

                tvCounter.text = "Counter value: $counter"

                btn1.setOnClickListener {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, CounterFragment.newInstance(counter))
                        .setCustomAnimations(
                            android.R.anim.slide_out_right,
                            android.R.anim.slide_in_left,
                            android.R.anim.slide_in_left,
                            android.R.anim.slide_out_right
                        )
                        .addToBackStack("MainFragment")
                        .commit()
                }

                btn2.setOnClickListener {
                    counter++
                    tvCounter.text = "Counter value: $counter"
                }

                btn3.setOnClickListener {
                    val dialog = CounterDialogFragment()
                    dialog.setOnSubmitClickListenerAdd {
                            counterDialog ->
                        Toast.makeText(requireContext(), "Add: $counterDialog", Toast.LENGTH_SHORT).show()
                        counter += counterDialog
                        tvCounter.text = "Counter value: $counter"
                    }
                    dialog.setOnSubmitClickListenerSubtract {
                            counterDialog ->
                        Toast.makeText(requireContext(), "Subtract: $counterDialog", Toast.LENGTH_SHORT).show()
                        if ((counter - counterDialog) < 0) {
                            counter = 0
                        } else {
                            counter -= counterDialog
                        }
                        tvCounter.text = "Counter value: $counter"
                    }
                    dialog.show(parentFragmentManager, "dialog")
                }

            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

                tvCounter.text = "Counter value: $counter"

                btn1.setOnClickListener {
                    refreshCounterFragment()
                }

                btn2.setOnClickListener {
                    counter++
                    tvCounter.text = "Counter value: $counter"
                    refreshCounterFragment()
                }

                btn3.setOnClickListener {

                    val dialog = CounterDialogFragment()
                    dialog.setOnSubmitClickListenerAdd {
                            counterDialog ->
                        Toast.makeText(requireContext(), "Add: $counterDialog", Toast.LENGTH_SHORT).show()
                        counter += counterDialog
                        tvCounter.text = "Counter value: $counter"

                        refreshCounterFragment()
                    }
                    dialog.setOnSubmitClickListenerSubtract {
                            counterDialog ->
                        Toast.makeText(requireContext(), "Subtract: $counterDialog", Toast.LENGTH_SHORT).show()
                        if ((counter - counterDialog) < 0) {
                            counter = 0
                        } else {
                            counter -= counterDialog
                        }
                        tvCounter.text = "Counter value: $counter"

                        refreshCounterFragment()
                    }
                    dialog.show(parentFragmentManager, "dialog")
                }

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun refreshCounterFragment() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container2, CounterFragment.newInstance(counter))
            .commit()
    }

    companion object {
        const val ARG_COUNT = "ARG_COUNT"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ARG_COUNT, counter)
    }

}