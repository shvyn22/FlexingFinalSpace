package shvyn22.flexingfinalspace.ui.character.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.databinding.FragmentCharacterDetailsBinding
import shvyn22.flexingfinalspace.util.defaultRequests

class CharacterDetailsFragment: Fragment(R.layout.fragment_character_details) {

    private val args: CharacterDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentCharacterDetailsBinding.bind(view)

        val character = args.character

        binding.apply {
            Glide.with(view)
                .load(character.imgURL)
                .defaultRequests()
                .into(ivImage)

            ivStatus.setImageResource(
                when {
                    listOf("Alive", "Operational").any { it in character.status } ->
                        R.drawable.ic_alive
                    character.status.contains("Deceased") -> R.drawable.ic_dead
                    else -> R.drawable.ic_unknown
                }
            )

            tvName.text = character.name
            tvAlias.text = getString(
                    R.string.text_alias,
                    character.alias.joinToString(", ")
            )
            tvAbilities.text = getString(
                    R.string.text_abilities,
                    character.abilities.joinToString(", ")
            )
            tvSpecies.text = getString(R.string.text_species, character.species)
            tvGender.text = getString(R.string.text_gender, character.gender)
            tvHair.text = getString(R.string.text_hair, character.hair)
            tvOrigin.text = getString(R.string.text_origin, character.origin)
        }
    }
}