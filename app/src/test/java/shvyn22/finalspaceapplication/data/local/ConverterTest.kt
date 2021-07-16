package shvyn22.finalspaceapplication.data.local

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import shvyn22.finalspaceapplication.data.util.fromCharacterDTOToModel
import shvyn22.finalspaceapplication.util.character1

class ConverterTest {

    @Test
    fun convertFromAliasListToString_ReturnsValidString() {
        val result = Converter()
            .fromListToString(fromCharacterDTOToModel(character1).alias)

        assertThat(
            result,
            `is`("The Gary, Thunder Bandit")
        )
    }

    @Test
    fun convertFromAliasStringToList_ReturnsValidList() {
        val result = Converter()
            .fromStringToList("The Gary, Thunder Bandit")

        assertThat(
            result,
            `is`(fromCharacterDTOToModel(character1).alias)
        )
    }
}