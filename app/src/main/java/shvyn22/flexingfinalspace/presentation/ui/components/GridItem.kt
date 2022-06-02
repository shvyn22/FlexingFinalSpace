package shvyn22.flexingfinalspace.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import shvyn22.flexingfinalspace.R

@Composable
fun GridItem(
    name: String,
    imgUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(120.dp)
            .padding(vertical = 5.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = imgUrl,
                builder = {
                    error(R.drawable.ic_error)
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = modifier
                .height(120.dp)
                .width(120.dp)
        )

        Text(
            text = name,
            textAlign = TextAlign.Center,
            maxLines = 2,
            style = MaterialTheme.typography.subtitle1,
            modifier = modifier.width(120.dp)
        )
    }
}