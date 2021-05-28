package shvyn22.myapplication.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shvyn22.myapplication.data.local.model.CharacterModel
import shvyn22.myapplication.databinding.ItemGridBinding
import shvyn22.myapplication.util.defaultRequests

class CharacterAdapter(
    private val onClick: (CharacterModel) -> Unit
): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

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

    fun updateAndNotify(newItems: List<CharacterModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(
        private val binding: ItemGridBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                onClick(items[position])
            }
        }

        fun bind(item: CharacterModel) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.imgURL)
                    .defaultRequests()
                    .into(ivImage)
                tvName.text = item.name
            }
        }
    }
}