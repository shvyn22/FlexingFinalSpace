package shvyn22.finalspaceapplication.ui.episode

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
import shvyn22.finalspaceapplication.api.FakeApiInterface
import shvyn22.finalspaceapplication.data.local.model.EpisodeModel
import shvyn22.finalspaceapplication.data.util.fromEpisodeDTOToModel
import shvyn22.finalspaceapplication.repository.FakeEpisodeRepository
import shvyn22.finalspaceapplication.util.MainCoroutineRule
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.episode1
import shvyn22.finalspaceapplication.util.episodes

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
        api = FakeApiInterface(false)
        viewModel = EpisodeViewModel(FakeEpisodeRepository(api = api))
    }

    @Test
    fun populateWith2Items_Returns2Episodes() = runBlocking {
        api.initEpisodes(episodes)

        val items = viewModel.items.drop(1).first()

        assertThat(items, `is`(instanceOf(Resource.Success::class.java)))

        assertThat(
            (items as Resource.Success<List<EpisodeModel>>).data,
            `is`(episodes.map { fromEpisodeDTOToModel(it) })
        )
    }

    @Test
    fun populateWith1Item_Returns1Episode() = runBlocking {
        api.initEpisodes(listOf(episode1))

        val items = viewModel.items.drop(1).first()

        assertThat(items, `is`(instanceOf(Resource.Success::class.java)))

        assertThat(
            (items as Resource.Success<List<EpisodeModel>>).data,
            `is`(listOf(fromEpisodeDTOToModel(episode1)))
        )
    }

    @Test
    fun populateWithNoItems_ReturnsNoEpisode() = runBlocking {
        val items = viewModel.items.drop(1).first()

        assertThat(items, `is`(instanceOf(Resource.Success::class.java)))

        assertThat(
            (items as Resource.Success<List<EpisodeModel>>).data,
            `is`(emptyList())
        )
    }
}