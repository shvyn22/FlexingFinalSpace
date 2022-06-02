package shvyn22.flexingfinalspace.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.QuoteModel

@Composable
fun QuoteItem(
    quote: QuoteModel,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Image(
                painter = rememberImagePainter(
                    data = quote.imgURL,
                    builder = {
                        error(R.drawable.ic_error)
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
            )

            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = quote.author,
                    style = MaterialTheme.typography.subtitle2
                )

                Text(
                    text = quote.quote,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        }
    }
}