package shvyn22.flexingfinalspace.presentation.character

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.FragmentCharacterBinding
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.collectOnLifecycle
import shvyn22.flexingfinalspace.util.showError

@AndroidEntryPoint
class CharacterFragment : Fragment(R.layout.fragment_character) {

    private val viewModel: CharacterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterBinding.bind(view)

        val characterAdapter = CharacterAdapter {
            findNavController().navigate(
                CharacterFragmentDirections.actionNavigateToCharacterDetails(it)
            )
        }

        binding.apply {
            rvCharacters.adapter = characterAdapter

            viewModel.items.collectOnLifecycle(viewLifecycleOwner) { resource ->
                if (resource is Resource.Success)
                    characterAdapter.updateAndNotify(resource.data)
                else if (resource is Resource.Error) {
                    view.showError(resource.error)
                    characterAdapter.updateAndNotify(resource.data)
                }
                rvCharacters.scheduleLayoutAnimation()

                pbLoading.isVisible = resource is Resource.Loading
            }
        }
    }
}