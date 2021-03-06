package shvyn22.flexingfinalspace.util

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.data.local.dao.FakeCharacterDao
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.remote.api.FakeApiService
import shvyn22.flexingfinalspace.data.util.fromCharacterDTOToModel

@ExperimentalCoroutinesApi
class NetworkBoundResourceTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun successfulQueryAndSuccessfulFetch_ReturnsLoadingAndSuccess() = runBlocking {
        val api = FakeApiService(false)
        api.initCharacters(characters)
        val dao = FakeCharacterDao()

        val result = networkBoundResource(
            query = { dao.getAll() },
            fetch = { api.getCharacters() },
            saveFetchResult = {
                dao.deleteAll()
                dao.insertAll(it.map { dto ->
                    fromCharacterDTOToModel(dto)
                })
            }
        ).take(2).toList()

        assertThat(
            result[0],
            `is`(instanceOf(Resource.Loading::class.java))
        )

        assertThat(
            result[1],
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (result[1] as Resource.Success<List<CharacterModel>>).data,
            `is`(characters.map { fromCharacterDTOToModel(it) })
        )
    }

    @Test
    fun successfulQueryAndUnsuccessfulFetch_ReturnsLoadingAndError() = runBlocking {
        val api = FakeApiService(true)
        val dao = FakeCharacterDao()

        val result = networkBoundResource(
            query = { dao.getAll() },
            fetch = { api.getCharacters() },
            saveFetchResult = {
                dao.deleteAll()
                dao.insertAll(it.map { dto ->
                    fromCharacterDTOToModel(dto)
                })
            }
        ).take(2).toList()

        assertThat(
            result[0],
            `is`(instanceOf(Resource.Loading::class.java))
        )

        assertThat(
            result[1],
            `is`(instanceOf(Resource.Error::class.java))
        )

        assertThat(
            (result[1] as Resource.Error<List<CharacterModel>>).data,
            `is`(emptyList())
        )
    }
}