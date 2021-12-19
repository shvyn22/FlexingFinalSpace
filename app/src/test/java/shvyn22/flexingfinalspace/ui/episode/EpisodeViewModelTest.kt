package shvyn22.flexingfinalspace.ui.episode

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.api.FakeApiInterface
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.util.fromEpisodeDTOToModel
import shvyn22.flexingfinalspace.repository.FakeEpisodeRepository
import shvyn22.flexingfinalspace.util.MainCoroutineRule
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.episode1
import shvyn22.flexingfinalspace.util.episodes

@ExperimentalCoroutinesApi
class EpisodeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var api: FakeApiInterface
    private lateinit var viewModel: EpisodeViewModel

    @Before
    fun setup() {
        api = FakeApiInterface()
        viewModel = EpisodeViewModel(FakeEpisodeRepository(api = api))
    }

    @Test
    fun populateWith2Items_Returns2Episodes() = runBlocking {
        api.initEpisodes(episodes)

        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<EpisodeModel>>).data,
            `is`(episodes.map { fromEpisodeDTOToModel(it) })
        )
    }

    @Test
    fun populateWith1Item_Returns1Episode() = runBlocking {
        api.initEpisodes(listOf(episode1))

        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<EpisodeModel>>).data,
            `is`(listOf(fromEpisodeDTOToModel(episode1)))
        )
    }

    @Test
    fun populateWithNoItems_ReturnsNoEpisode() = runBlocking {
        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<EpisodeModel>>).data,
            `is`(emptyList())
        )
    }
}