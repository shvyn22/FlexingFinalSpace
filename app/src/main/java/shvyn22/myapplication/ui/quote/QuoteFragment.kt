package shvyn22.myapplication.ui.quote

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.myapplication.R
import shvyn22.myapplication.databinding.FragmentQuoteBinding
import shvyn22.myapplication.util.Resource
import shvyn22.myapplication.util.collectOnLifecycle
import shvyn22.myapplication.util.showError

@AndroidEntryPoint
class QuoteFragment: Fragment(R.layout.fragment_quote) {

    private val viewModel: QuoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentQuoteBinding.bind(view)

        val quoteAdapter = QuoteAdapter()

        binding.apply {
            rvQuotes.adapter = quoteAdapter

            viewModel.items.collectOnLifecycle(viewLifecycleOwner) {
                if (it is Resource.Success) quoteAdapter.updateAndNotify(it.data)
                else if (it is Resource.Error) {
                    view.showError()
                    quoteAdapter.updateAndNotify(it.data)
                }
                pbLoading.isVisible = it is Resource.Loading
            }
        }
    }
}