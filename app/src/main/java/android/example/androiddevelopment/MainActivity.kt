package android.example.androiddevelopment

import android.annotation.SuppressLint
import android.content.Intent
import android.example.androiddevelopment.databinding.ActivityMainBinding
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    @SuppressLint("PrivateResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {

            buttonPanel1.setOnClickListener {
                openWebPage("https://www.toneden.io/248mgmt/post/spaceman")
            }

            buttonPanel2.setOnClickListener {
                openChooser()
            }

            buttonPanel3.setOnClickListener {
                showMap("geo:47.6,-122.3")
            }

        }
    }

    private fun openWebPage(url : String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun openChooser() {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT, "My message from Intent!\n" +
                "Yes, yes, yes, I burned my mail =D")
        intent.type = "text/plan"
        val chooserIntent = Intent.createChooser(
            intent,
            getString(R.string.chooser_title)
        )

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(chooserIntent)
        }
    }

    private fun showMap(geoLocation: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(geoLocation)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}