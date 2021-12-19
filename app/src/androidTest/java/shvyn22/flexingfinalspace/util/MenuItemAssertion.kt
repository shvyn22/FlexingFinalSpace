package shvyn22.flexingfinalspace.util

import androidx.annotation.StringRes
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.test.espresso.ViewAssertion
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

fun withItemTitle(
    @StringRes titleRes: Int
) = ViewAssertion { view, noViewFoundException ->
    if (noViewFoundException != null) throw noViewFoundException

    val menuItem = view as ActionMenuItemView
    val title = view.context.getString(titleRes)

    assertThat(menuItem.itemData.title, `is`(title))
}