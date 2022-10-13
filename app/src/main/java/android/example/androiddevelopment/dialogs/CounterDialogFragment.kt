package android.example.androiddevelopment.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.example.androiddevelopment.R
import android.example.androiddevelopment.databinding.CustomDialogFragmentBinding
import android.example.androiddevelopment.databinding.FragmentCounterBinding
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.google.android.material.button.MaterialButton

class CounterDialogFragment() : DialogFragment() {

    private lateinit var onSubmitClickListenerAdd: (Int) -> Unit
    private lateinit var onSubmitClickListenerSubtract: (Int) -> Unit

    private lateinit var binding : CustomDialogFragmentBinding

    fun setOnSubmitClickListenerAdd(onSubmitClickListenerAdd: (Int) -> Unit) {
        this.onSubmitClickListenerAdd = onSubmitClickListenerAdd
    }

    fun setOnSubmitClickListenerSubtract(onSubmitClickListenerSubtract: (Int) -> Unit) {
        this.onSubmitClickListenerSubtract = onSubmitClickListenerSubtract
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = CustomDialogFragmentBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.btnAdd.setOnClickListener {
            var strET = binding.etValue.text.toString()
            if (strET.contains(".") || strET.isEmpty() || strET.toInt() > 100) {
                binding.etValue.error = "Не верный формат данных"
            } else {
                onSubmitClickListenerAdd.invoke(binding.etValue.text.toString().toInt())
                dismiss()
            }
        }
        binding.btnSubtract.setOnClickListener {
            var strET = binding.etValue.text.toString()
            if (strET.contains(".") || strET.isEmpty() || strET.toInt() > 100) {
                binding.etValue.error = "Не верный формат данных"
            } else {
                onSubmitClickListenerSubtract.invoke(binding.etValue.text.toString().toInt())
                dismiss()
            }
        }
        binding.btnBack.setOnClickListener {
            dismiss()
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}