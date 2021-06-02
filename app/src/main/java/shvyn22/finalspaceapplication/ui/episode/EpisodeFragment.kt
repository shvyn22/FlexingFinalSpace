package shvyn22.finalspaceapplication.ui.episode

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.finalspaceapplication.R
import shvyn22.finalspaceapplication.databinding.FragmentEpisodeBinding
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.collectOnLifecycle
import shvyn22.finalspaceapplication.util.showError

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

            viewModel.items.collectOnLifecycle(viewLifecycleOwner) {
                if (it is Resource.Success) episodeAdapter.updateAndNotify(it.data)
                else if (it is Resource.Error) {
                    view.showError(it.error)
                    episodeAdapter.updateAndNotify(it.data)
                }
                pbLoading.isVisible = it is Resource.Loading
            }
        }
    }
}