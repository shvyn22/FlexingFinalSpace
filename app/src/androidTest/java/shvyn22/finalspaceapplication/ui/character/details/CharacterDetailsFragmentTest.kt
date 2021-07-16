package shvyn22.finalspaceapplication.ui.character.details

import android.content.Context
import android.os.Bundle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.finalspaceapplication.R
import shvyn22.finalspaceapplication.data.util.fromCharacterDTOToModel
import shvyn22.finalspaceapplication.util.character1
import shvyn22.finalspaceapplication.util.character2
import shvyn22.finalspaceapplication.util.launchFragmentInHiltContainer
import shvyn22.finalspaceapplication.util.withDrawable

@HiltAndroidTest
class CharacterDetailsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun passAliveCharacter_ValidInformationIsDisplayed() {
        val res = ApplicationProvider.getApplicationContext<Context>().resources
        val alias = res.getString(
            R.string.text_alias,
            character1.alias?.joinToString(", ")
        )
        val species = res.getString(
            R.string.text_species,
            character1.species
        )
        val gender = res.getString(
            R.string.text_gender,
            character1.gender
        )
        val hair = res.getString(
            R.string.text_hair,
            character1.hair
        )
        val origin = res.getString(
            R.string.text_origin,
            character1.origin
        )
        val abilities = res.getString(
            R.string.text_abilities,
            character1.abilities?.joinToString(", ")
        )

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable(
                    "character",
                    fromCharacterDTOToModel(character1)
                )
            }
        )

        onView(withId(R.id.iv_status))
            .check(
                matches(
                    withDrawable(
                        R.drawable.ic_alive,
                        R.color.black900
                    )
                )
            )

        onView(withId(R.id.tv_name))
            .check(matches(withText(character1.name)))

        onView(withId(R.id.tv_alias))
            .check(matches(withText(alias)))

        onView(withId(R.id.tv_species))
            .check(matches(withText(species)))

        onView(withId(R.id.tv_gender))
            .check(matches(withText(gender)))

        onView(withId(R.id.tv_hair))
            .check(matches(withText(hair)))

        onView(withId(R.id.tv_origin))
            .check(matches(withText(origin)))

        onView(withId(R.id.tv_abilities))
            .check(matches(withText(abilities)))
    }

    @Test
    fun passDeceasedCharacter_ValidInformationIsDisplayed() {
        val res = ApplicationProvider.getApplicationContext<Context>().resources
        val alias = res.getString(
            R.string.text_alias,
            character1.alias?.joinToString(", ")
        )
        val species = res.getString(
            R.string.text_species,
            character1.species
        )
        val gender = res.getString(
            R.string.text_gender,
            character1.gender
        )
        val hair = res.getString(
            R.string.text_hair,
            character1.hair
        )
        val origin = res.getString(
            R.string.text_origin,
            character1.origin
        )
        val abilities = res.getString(
            R.string.text_abilities,
            character1.abilities?.joinToString(", ")
        )

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable(
                    "character",
                    fromCharacterDTOToModel(character1).copy(status = "Deceased")
                )
            }
        )

        onView(withId(R.id.iv_status))
            .check(
                matches(
                    withDrawable(
                        R.drawable.ic_dead,
                        R.color.black900
                    )
                )
            )

        onView(withId(R.id.tv_name))
            .check(matches(withText(character1.name)))

        onView(withId(R.id.tv_alias))
            .check(matches(withText(alias)))

        onView(withId(R.id.tv_species))
            .check(matches(withText(species)))

        onView(withId(R.id.tv_gender))
            .check(matches(withText(gender)))

        onView(withId(R.id.tv_hair))
            .check(matches(withText(hair)))

        onView(withId(R.id.tv_origin))
            .check(matches(withText(origin)))

        onView(withId(R.id.tv_abilities))
            .check(matches(withText(abilities)))
    }

    @Test
    fun passUnknownStatusCharacter_ValidInformationIsDisplayed() {
        val res = ApplicationProvider.getApplicationContext<Context>().resources
        val alias = res.getString(
            R.string.text_alias,
            character2.alias?.joinToString(", ")
        )
        val species = res.getString(
            R.string.text_species,
            character2.species
        )
        val gender = res.getString(
            R.string.text_gender,
            character2.gender
        )
        val hair = res.getString(
            R.string.text_hair,
            character2.hair
        )
        val origin = res.getString(
            R.string.text_origin,
            character2.origin
        )
        val abilities = res.getString(
            R.string.text_abilities,
            character2.abilities?.joinToString(", ")
        )

        launchFragmentInHiltContainer<CharacterDetailsFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable(
                    "character",
                    fromCharacterDTOToModel(character2)
                )
            }
        )

        onView(withId(R.id.iv_status))
            .check(
                matches(
                    withDrawable(
                        R.drawable.ic_unknown,
                        R.color.black900
                    )
                )
            )

        onView(withId(R.id.tv_name))
            .check(matches(withText(character2.name)))

        onView(withId(R.id.tv_alias))
            .check(matches(withText(alias)))

        onView(withId(R.id.tv_species))
            .check(matches(withText(species)))

        onView(withId(R.id.tv_gender))
            .check(matches(withText(gender)))

        onView(withId(R.id.tv_hair))
            .check(matches(withText(hair)))

        onView(withId(R.id.tv_origin))
            .check(matches(withText(origin)))

        onView(withId(R.id.tv_abilities))
            .check(matches(withText(abilities)))
    }
}