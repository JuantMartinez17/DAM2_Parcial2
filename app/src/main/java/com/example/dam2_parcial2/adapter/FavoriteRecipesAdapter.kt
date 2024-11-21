import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dam2_parcial2.adapter.FavoriteRecipeViewHolder
import com.example.dam2_parcial2.databinding.ItemFavoriteRecipeBinding
import com.example.dam2_parcial2.model.FavoriteRecipe

class FavoriteRecipesAdapter(
    private val onItemClick: (FavoriteRecipe) -> Unit
) : RecyclerView.Adapter<FavoriteRecipeViewHolder>() {

    private val favoriteRecipes = mutableListOf<FavoriteRecipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteRecipeViewHolder {
        val binding = ItemFavoriteRecipeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FavoriteRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteRecipeViewHolder, position: Int) {
        holder.bind(favoriteRecipes[position], onItemClick)
    }

    override fun getItemCount(): Int = favoriteRecipes.size

    fun updateData(newRecipes: List<FavoriteRecipe>) {
        favoriteRecipes.clear()
        favoriteRecipes.addAll(newRecipes)
        notifyDataSetChanged()
    }
}