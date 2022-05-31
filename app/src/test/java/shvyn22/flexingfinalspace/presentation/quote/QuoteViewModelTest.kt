package shvyn22.flexingfinalspace.presentation.quote

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
import shvyn22.flexingfinalspace.data.local.dao.FakeQuoteDao
import shvyn22.flexingfinalspace.data.local.model.QuoteModel
import shvyn22.flexingfinalspace.data.remote.api.FakeApiService
import shvyn22.flexingfinalspace.data.util.fromQuoteDTOToModel
import shvyn22.flexingfinalspace.repository.QuoteRepository
import shvyn22.flexingfinalspace.util.MainCoroutineRule
import shvyn22.flexingfinalspace.util.Resource
import shvyn22.flexingfinalspace.util.quote1
import shvyn22.flexingfinalspace.util.quotes

@ExperimentalCoroutinesApi
class QuoteViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var api: FakeApiService
    private lateinit var dao: FakeQuoteDao
    private lateinit var viewModel: QuoteViewModel

    @Before
    fun setup() {
        api = FakeApiService()
        dao = FakeQuoteDao()
        viewModel = QuoteViewModel(QuoteRepository(api = api, dao = dao))
    }

    @Test
    fun populateWith2Items_Returns2Quotes() = runTest {
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
    fun populateWith1Item_Returns1Quote() = runTest {
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
    fun populateWithNoItems_ReturnsNoQuote() = runTest {
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