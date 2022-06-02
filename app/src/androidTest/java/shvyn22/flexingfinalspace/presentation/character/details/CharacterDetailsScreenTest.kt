package shvyn22.flexingfinalspace.presentation.character.details

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.util.character1Model
import shvyn22.flexingfinalspace.util.character2Model

class CharacterDetailsScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun passAliveCharacter_ValidInformationIsDisplayed() {
        val res = ApplicationProvider.getApplicationContext<Context>().resources
        val status = res.getString(R.string.text_alive)
        val alias = res.getString(
            R.string.text_alias,
            character1Model.alias.joinToString(", ")
        )
        val species = res.getString(
            R.string.text_species,
            character1Model.species
        )
        val gender = res.getString(
            R.string.text_gender,
            character1Model.gender
        )
        val hair = res.getString(
            R.string.text_hair,
            character1Model.hair
        )
        val origin = res.getString(
            R.string.text_origin,
            character1Model.origin
        )
        val abilities = res.getString(
            R.string.text_abilities,
            character1Model.abilities.joinToString(", ")
        )

        composeRule.apply {

            setContent {
                CharacterDetailsScreen(character = character1Model)
            }

            onNodeWithContentDescription(status)
                .assertIsDisplayed()

            onNodeWithText(character1Model.name)
                .assertIsDisplayed()

            onNodeWithText(alias)
                .assertIsDisplayed()

            onNodeWithText(species)
                .assertIsDisplayed()

            onNodeWithText(gender)
                .assertIsDisplayed()

            onNodeWithText(hair)
                .assertIsDisplayed()

            onNodeWithText(origin)
                .assertIsDisplayed()

            onNodeWithText(abilities)
                .assertIsDisplayed()
        }
    }

    @Test
    fun passDeceasedCharacter_ValidInformationIsDisplayed() {
        val res = ApplicationProvider.getApplicationContext<Context>().resources
        val status = res.getString(R.string.text_deceased)
        val alias = res.getString(
            R.string.text_alias,
            character1Model.alias.joinToString(", ")
        )
        val species = res.getString(
            R.string.text_species,
            character1Model.species
        )
        val gender = res.getString(
            R.string.text_gender,
            character1Model.gender
        )
        val hair = res.getString(
            R.string.text_hair,
            character1Model.hair
        )
        val origin = res.getString(
            R.string.text_origin,
            character1Model.origin
        )
        val abilities = res.getString(
            R.string.text_abilities,
            character1Model.abilities.joinToString(", ")
        )

        composeRule.apply {

            setContent {
                CharacterDetailsScreen(character = character1Model.copy(status = "Deceased"))
            }

            onNodeWithContentDescription(status)
                .assertIsDisplayed()

            onNodeWithText(character1Model.name)
                .assertIsDisplayed()

            onNodeWithText(alias)
                .assertIsDisplayed()

            onNodeWithText(species)
                .assertIsDisplayed()

            onNodeWithText(gender)
                .assertIsDisplayed()

            onNodeWithText(hair)
                .assertIsDisplayed()

            onNodeWithText(origin)
                .assertIsDisplayed()

            onNodeWithText(abilities)
                .assertIsDisplayed()
        }
    }

    @Test
    fun passUnknownStatusCharacter_ValidInformationIsDisplayed() {
        val res = ApplicationProvider.getApplicationContext<Context>().resources
        val status = res.getString(R.string.text_unknown)
        val alias = res.getString(
            R.string.text_alias,
            character2Model.alias.joinToString(", ")
        )
        val species = res.getString(
            R.string.text_species,
            character2Model.species
        )
        val gender = res.getString(
            R.string.text_gender,
            character2Model.gender
        )
        val hair = res.getString(
            R.string.text_hair,
            character2Model.hair
        )
        val origin = res.getString(
            R.string.text_origin,
            character2Model.origin
        )
        val abilities = res.getString(
            R.string.text_abilities,
            character2Model.abilities.joinToString(", ")
        )

        composeRule.apply {

            setContent {
                CharacterDetailsScreen(character = character2Model)
            }

            onNodeWithContentDescription(status)
                .assertIsDisplayed()

            onNodeWithText(character2Model.name)
                .assertIsDisplayed()

            onNodeWithText(alias)
                .assertIsDisplayed()

            onNodeWithText(species)
                .assertIsDisplayed()

            onNodeWithText(gender)
                .assertIsDisplayed()

            onNodeWithText(hair)
                .assertIsDisplayed()

            onNodeWithText(origin)
                .assertIsDisplayed()

            onNodeWithText(abilities)
                .assertIsDisplayed()
        }
    }
}