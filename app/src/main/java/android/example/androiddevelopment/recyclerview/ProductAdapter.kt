package android.example.androiddevelopment.recyclerview

import android.example.androiddevelopment.R
import android.example.androiddevelopment.databinding.ItemProductBinding
import android.example.androiddevelopment.model.Product
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager

class ProductAdapter(
    private val list : List<Product>,
    private val glide: RequestManager,
    private val action: (Product) -> Unit,
) : RecyclerView.Adapter<ProductItem>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItem = ProductItem(
        binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        action = action
    )

    override fun onBindViewHolder(
        holder: ProductItem,
        position: Int
    ) {
        holder.onBind(list[position])
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.anim_adapter)
    }

    override fun getItemCount(): Int = list.size
}