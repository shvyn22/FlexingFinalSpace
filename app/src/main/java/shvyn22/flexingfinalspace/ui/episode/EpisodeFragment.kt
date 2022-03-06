package shvyn22.flexingfinalspace.ui.episode

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.FragmentEpisodeBinding
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.collectOnLifecycle
import shvyn22.flexingfinalspace.util.showError

@AndroidEntryPoint
class EpisodeFragment: Fragment(R.layout.fragment_episode) {

    private val viewModel: EpisodeViewModel by viewModels()

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

            viewModel.items.collectOnLifecycle(viewLifecycleOwner) { resource ->
                if (resource is Resource.Success)
                    episodeAdapter.updateAndNotify(resource.data)
                else if (resource is Resource.Error) {
                    view.showError(resource.error)
                    episodeAdapter.updateAndNotify(resource.data)
                }
                rvEpisodes.scheduleLayoutAnimation()
                pbLoading.isVisible = resource is Resource.Loading
            }
        }
    }
}