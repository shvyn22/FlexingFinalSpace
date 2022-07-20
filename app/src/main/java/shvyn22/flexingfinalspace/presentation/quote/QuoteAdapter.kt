package shvyn22.flexingfinalspace.presentation.quote

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.databinding.ItemQuoteBinding
import shvyn22.flexingfinalspace.util.defaultRequests

class QuoteAdapter: RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    private val items = mutableListOf<QuoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        return QuoteViewHolder(
            ItemQuoteBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateAndNotify(newItems: List<QuoteModel>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class QuoteViewHolder(
        private val binding: ItemQuoteBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QuoteModel) {
            binding.apply {
                ivImage.load(item.imgURL) {
                    defaultRequests()
                }

                tvAuthor.text = item.author
                tvQuote.text = item.quote
            }
        }
    }
}