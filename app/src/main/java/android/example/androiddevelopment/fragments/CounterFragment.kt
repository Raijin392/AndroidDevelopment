package android.example.androiddevelopment.fragments

import android.example.androiddevelopment.R
import android.example.androiddevelopment.databinding.FragmentCounterBinding
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

class CounterFragment : Fragment(R.layout.fragment_counter) {

    private var binding : FragmentCounterBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCounterBinding.bind(view)

        val counter = arguments?.getInt(COUNTER_VALUE)

        binding?.tvCounter2?.text = "Counter value: $counter"

        when(counter) {
            in 0..50 -> view.setBackgroundResource(R.color.color1)
            in 51..100 -> view.setBackgroundResource(R.color.color2)
            else -> view.setBackgroundResource(R.color.color3)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object {

        private const val COUNTER_VALUE = "COUNTER_VALUE"

        fun newInstance(counterValue : Int) = CounterFragment().apply {
            arguments = Bundle().apply {
                putInt(COUNTER_VALUE, counterValue)
            }
        }

    }

}