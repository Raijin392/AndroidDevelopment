package android.example.androiddevelopment.recyclerview

import android.example.androiddevelopment.databinding.ItemProductBinding
import android.example.androiddevelopment.model.Product
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class ProductItem(
    private val binding : ItemProductBinding,
    private val glide: RequestManager,
    private val action : (Product) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val option = RequestOptions
        .diskCacheStrategyOf(DiskCacheStrategy.ALL)

    fun onBind(product : Product) {
        with(binding) {
            tvPrice.text = product.price
            tvBrand.text = product.brand
            tvModel.text = product.model
            tvSizes.text = product.sizes

            glide.load(product.imageUrl)
                .apply(option)
                .into(imgItem)

            root.setOnClickListener {
                action(product)
            }
        }
    }

}