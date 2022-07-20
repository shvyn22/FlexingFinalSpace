package shvyn22.flexingfinalspace.presentation.episode.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.FragmentEpisodeDetailsBinding
import shvyn22.flexingfinalspace.util.defaultRequests

class EpisodeDetailsFragment : BottomSheetDialogFragment() {

    private val args: EpisodeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentEpisodeDetailsBinding.bind(view)

        val episode = args.episode

        binding.apply {
            ivImage.load(episode.imgURL) {
                defaultRequests()
            }

            tvName.text = episode.name
            tvAirDate.text = episode.airDate
            tvDirector.text = getString(R.string.text_director, episode.director)
            tvWriter.text = getString(R.string.text_writer, episode.writer)
        }
    }
}