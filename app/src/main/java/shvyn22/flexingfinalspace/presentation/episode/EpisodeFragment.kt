package shvyn22.flexingfinalspace.presentation.episode

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.FragmentEpisodeBinding
import shvyn22.flexingfinalspace.util.*
import javax.inject.Inject

class EpisodeFragment: Fragment(R.layout.fragment_episode) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: EpisodeViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.singletonComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEpisodeBinding.bind(view)

        val episodeAdapter = EpisodeAdapter {
            findNavController()
                .navigate(
                    EpisodeFragmentDirections.actionNavigateToEpisodeDetails(it)
                )
        }

        binding.apply {
            rvEpisodes.adapter = episodeAdapter

            viewModel.items.observe(viewLifecycleOwner) { resource ->
                if (resource is Resource.Success)
                    episodeAdapter.updateAndNotify(resource.data)
                else if (resource is Resource.Error) {
                    view.showError(resource.error)
                    episodeAdapter.updateAndNotify(resource.data)
                }

                pbLoading.isVisible = resource is Resource.Loading
            }
        }
    }
}