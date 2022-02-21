package shvyn22.flexingfinalspace.ui.quote

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.FragmentQuoteBinding
import shvyn22.flexingfinalspace.util.*
import javax.inject.Inject

class QuoteFragment: Fragment(R.layout.fragment_quote) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: QuoteViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.singletonComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentQuoteBinding.bind(view)

        val quoteAdapter = QuoteAdapter()

        binding.apply {
            rvQuotes.adapter = quoteAdapter

            viewModel.items.observe(viewLifecycleOwner) { resource ->
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