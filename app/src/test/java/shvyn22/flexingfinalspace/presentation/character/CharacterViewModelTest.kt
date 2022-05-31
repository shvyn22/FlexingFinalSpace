package shvyn22.flexingfinalspace.presentation.character

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.data.local.dao.FakeCharacterDao
import shvyn22.flexingfinalspace.data.local.model.CharacterModel
import shvyn22.flexingfinalspace.data.remote.api.FakeApiService
import shvyn22.flexingfinalspace.data.util.fromCharacterDTOToModel
import shvyn22.flexingfinalspace.repository.CharacterRepository
import shvyn22.flexingfinalspace.util.MainCoroutineRule
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.character1
import shvyn22.flexingfinalspace.util.characters

@ExperimentalCoroutinesApi
class CharacterViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var api: FakeApiService
    private lateinit var dao: FakeCharacterDao
    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setup() {
        api = FakeApiService()
        dao = FakeCharacterDao()
        viewModel = CharacterViewModel(CharacterRepository(api = api, dao = dao))
    }

    @Test
    fun populateWith2Items_Returns2Characters() = runTest {
        api.initCharacters(characters)

        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<CharacterModel>>).data,
            `is`(characters.map { fromCharacterDTOToModel(it) })
        )
    }

    @Test
    fun populateWith1Item_Returns1Character() = runTest {
        api.initCharacters(listOf(character1))

        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<CharacterModel>>).data,
            `is`(listOf(fromCharacterDTOToModel(character1)))
        )
    }

    @Test
    fun populateWithNoItems_ReturnsNoCharacter() = runTest {
        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<CharacterModel>>).data,
            `is`(emptyList())
        )
    }
}