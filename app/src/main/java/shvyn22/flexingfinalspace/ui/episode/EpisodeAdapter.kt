package shvyn22.flexingfinalspace.ui.episode

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.databinding.ItemGridBinding
import shvyn22.flexingfinalspace.util.defaultRequests

class EpisodeAdapter(
    private val onClick: (EpisodeModel) -> Unit
): RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>() {

    private val items = mutableListOf<EpisodeModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemGridBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateAndNotify(newItems: List<EpisodeModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class EpisodeViewHolder(
        private val binding: ItemGridBinding
    ): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                onClick(items[position])
            }
        }

        fun bind(item: EpisodeModel) {
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