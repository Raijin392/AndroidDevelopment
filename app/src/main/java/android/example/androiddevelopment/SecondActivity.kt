package android.example.androiddevelopment

import android.content.Intent.*
import android.example.androiddevelopment.databinding.ActivitySecondBinding
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View.GONE

class SecondActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val str = intent.getStringExtra(EXTRA_TEXT)
        val uri = Uri.parse(intent.getParcelableExtra<Parcelable>(EXTRA_STREAM).toString())

        with(binding) {

            if (str == null) {
                tvText.visibility = GONE
            } else {
                image.visibility = GONE
                tvText.text = str
            }

            if (uri == null) {
                tvText.visibility = GONE
            } else {
                image.setImageURI(uri)
            }

        }

    }

}