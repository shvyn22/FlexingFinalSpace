package shvyn22.flexingfinalspace.ui.quote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shvyn22.flexingfinalspace.api.FakeApiInterface
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.data.util.fromQuoteDTOToModel
import shvyn22.flexingfinalspace.repository.FakeQuoteRepository
import shvyn22.flexingfinalspace.util.MainCoroutineRule
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.quote1
import shvyn22.flexingfinalspace.util.quotes

@ExperimentalCoroutinesApi
class QuoteViewModelTest {
    
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var api: FakeApiInterface
    private lateinit var viewModel: QuoteViewModel

    @Before
    fun setup() {
        api = FakeApiInterface()
        viewModel = QuoteViewModel(FakeQuoteRepository(api = api))
    }

    @Test
    fun populateWith2Items_Returns2Quotes() = runBlocking {
        api.initQuotes(quotes)

        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<QuoteModel>>).data,
            `is`(quotes.map { fromQuoteDTOToModel(it) })
        )
    }

    @Test
    fun populateWith1Item_Returns1Quote() = runBlocking {
        api.initQuotes(listOf(quote1))

        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<QuoteModel>>).data,
            `is`(listOf(fromQuoteDTOToModel(quote1)))
        )
    }

    @Test
    fun populateWithNoItems_ReturnsNoQuote() = runBlocking {
        val items = viewModel.items.drop(1).first()

        assertThat(
            items,
            `is`(instanceOf(Resource.Success::class.java))
        )

        assertThat(
            (items as Resource.Success<List<QuoteModel>>).data,
            `is`(emptyList())
        )
    }
}