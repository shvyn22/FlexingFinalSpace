package shvyn22.finalspaceapplication.ui.character

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
import shvyn22.finalspaceapplication.data.local.model.CharacterModel
import shvyn22.finalspaceapplication.data.util.fromCharacterDTOToModel
import shvyn22.finalspaceapplication.repository.FakeCharacterRepository
import shvyn22.finalspaceapplication.util.MainCoroutineRule
import shvyn22.finalspaceapplication.util.Resource
import shvyn22.finalspaceapplication.util.character1
import shvyn22.finalspaceapplication.util.characters

@ExperimentalCoroutinesApi
class CharacterViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var api: FakeApiInterface
    private lateinit var viewModel: CharacterViewModel

    @Before
    fun setup() {
        api = FakeApiInterface()
        viewModel = CharacterViewModel(FakeCharacterRepository(api = api))
    }

    @Test
    fun populateWith2Items_Returns2Characters() = runBlocking {
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
    fun populateWith1Item_Returns1Character() = runBlocking {
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
    fun populateWithNoItems_ReturnsNoCharacter() = runBlocking {
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