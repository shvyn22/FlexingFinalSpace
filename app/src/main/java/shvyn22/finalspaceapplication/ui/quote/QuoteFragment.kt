package shvyn22.finalspaceapplication.ui.quote

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.finalspaceapplication.R
import shvyn22.finalspaceapplication.databinding.FragmentQuoteBinding
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.collectOnLifecycle
import shvyn22.finalspaceapplication.util.showError

@AndroidEntryPoint
class QuoteFragment: Fragment(R.layout.fragment_quote) {

    private val viewModel: QuoteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentQuoteBinding.bind(view)

        val quoteAdapter = QuoteAdapter()

        binding.apply {
            rvQuotes.adapter = quoteAdapter

            viewModel.items.collectOnLifecycle(viewLifecycleOwner) { resource ->
                if (resource is Resource.Success)
                    quoteAdapter.updateAndNotify(resource.data)
                else if (resource is Resource.Error) {
                    view.showError(resource.error)
                    quoteAdapter.updateAndNotify(resource.data)
                }
                pbLoading.isVisible = resource is Resource.Loading
            }
        }
    }
}