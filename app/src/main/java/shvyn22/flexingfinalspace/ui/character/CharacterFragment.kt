package shvyn22.flexingfinalspace.ui.character

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.FragmentCharacterBinding
import shvyn22.flexingfinalspace.util.*
import javax.inject.Inject

class CharacterFragment: Fragment(R.layout.fragment_character) {

    @Inject
    lateinit var viewModelFactory: MultiViewModelFactory

    private val viewModel: CharacterViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        context.singletonComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterBinding.bind(view)

        val characterAdapter = CharacterAdapter {
            findNavController()
                .navigate(
                    CharacterFragmentDirections.actionNavigateToCharacterDetails(it)
                )
        }

        binding.apply {
            rvCharacters.adapter = characterAdapter

            viewModel.items.observe(viewLifecycleOwner) { resource ->
                if (resource is Resource.Success)
                    characterAdapter.updateAndNotify(resource.data)
                else if (resource is Resource.Error) {
                    view.showError(resource.error)
                    characterAdapter.updateAndNotify(resource.data)
                }
                pbLoading.isVisible = resource is Resource.Loading
            }
        }
    }
}