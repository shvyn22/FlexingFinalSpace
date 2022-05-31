package shvyn22.flexingfinalspace.presentation.episode.details

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
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.util.fromEpisodeDTOToModel
import shvyn22.flexingfinalspace.util.episode1
import shvyn22.flexingfinalspace.util.launchFragmentInHiltContainer

@HiltAndroidTest
class EpisodeDetailsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun passEpisode_DataIsDisplayed() {

        val res = ApplicationProvider.getApplicationContext<Context>().resources
        val director = res.getString(
            R.string.text_director,
            episode1.director
        )
        val writer = res.getString(
            R.string.text_writer,
            episode1.writer
        )

        launchFragmentInHiltContainer<EpisodeDetailsFragment>(
            fragmentArgs = Bundle().apply {
                putParcelable(
                    "episode",
                    fromEpisodeDTOToModel(episode1)
                )
            }
        )

        onView(withId(R.id.tv_name))
            .check(matches(withText(episode1.name)))
        onView(withId(R.id.tv_air_date))
            .check(matches(withText(episode1.airDate)))
        onView(withId(R.id.tv_director))
            .check(matches(withText(director)))
        onView(withId(R.id.tv_writer))
            .check(matches(withText(writer)))
    }
}