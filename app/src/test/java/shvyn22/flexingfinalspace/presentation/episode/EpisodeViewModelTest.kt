package shvyn22.flexingfinalspace.presentation.episode

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.data.local.dao.FakeEpisodeDao
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel
import shvyn22.flexingfinalspace.data.remote.api.FakeApiService
import shvyn22.flexingfinalspace.data.util.fromEpisodeDTOToModel
import shvyn22.flexingfinalspace.repository.EpisodeRepository
import shvyn22.flexingfinalspace.util.MainCoroutineRule
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.episode1
import shvyn22.flexingfinalspace.util.episodes

@ExperimentalCoroutinesApi
class EpisodeViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var api: FakeApiService
    private lateinit var dao: FakeEpisodeDao
    private lateinit var viewModel: EpisodeViewModel

    @Before
    fun setup() {
        api = FakeApiService()
        dao = FakeEpisodeDao()
        viewModel = EpisodeViewModel(EpisodeRepository(api = api, dao = dao))
    }

    @Test
    fun populateWith2Items_Returns2Episodes() = runTest {
        api.initEpisodes(episodes)

        val items = viewModel.items.drop(1).first()
        advanceUntilIdle()

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
    fun populateWith1Item_Returns1Episode() = runTest {
        api.initEpisodes(listOf(episode1))

        val items = viewModel.items.drop(1).first()
        advanceUntilIdle()

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
    fun populateWithNoItems_ReturnsNoEpisode() = runTest {
        val items = viewModel.items.drop(1).first()
        advanceUntilIdle()

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