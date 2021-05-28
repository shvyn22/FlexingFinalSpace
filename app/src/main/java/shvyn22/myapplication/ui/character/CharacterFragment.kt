package shvyn22.myapplication.ui.character

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import shvyn22.myapplication.R
import shvyn22.myapplication.databinding.FragmentCharacterBinding

@AndroidEntryPoint
class CharacterFragment: Fragment(R.layout.fragment_character) {

    private val viewModel: CharacterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterBinding.bind(view)

        binding.apply {
            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                    viewModel.items.collect {

                    }
                }
            }
        }
    }
}