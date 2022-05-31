package shvyn22.flexingfinalspace.presentation.character

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.databinding.ItemGridBinding
import shvyn22.flexingfinalspace.util.defaultRequests

class CharacterAdapter(
    private val onClick: (CharacterModel) -> Unit
) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val items = mutableListOf<CharacterModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemGridBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateAndNotify(newItems: List<CharacterModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(
        private val binding: ItemGridBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CharacterModel) {
            binding.apply {
                root.setOnClickListener { onClick(item) }

                Glide.with(itemView)
                    .load(item.imgURL)
                    .defaultRequests()
                    .into(ivImage)

                tvName.text = item.name
            }
        }
    }
}