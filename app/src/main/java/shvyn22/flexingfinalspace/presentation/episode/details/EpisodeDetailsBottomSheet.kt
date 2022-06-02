package shvyn22.flexingfinalspace.presentation.episode.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.EpisodeModel

@Composable
fun EpisodeDetailsBottomSheet(
    episode: EpisodeModel,
    modifier: Modifier = Modifier
) {
    EpisodeDetailsContent(
        episode = episode,
        modifier = modifier
    )
}

@Composable
fun EpisodeDetailsContent(
    episode: EpisodeModel,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .padding(6.dp)
    ) {
        val (
            img,
            date,
            name,
            director,
            writer
        ) = createRefs()

        Image(
            painter = rememberImagePainter(
                data = episode.imgURL,
                builder = {
                    crossfade(true)
                    error(R.drawable.ic_error)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .size(110.dp, 90.dp)
                .padding(10.dp, 0.dp)
        )

        Text(
            text = episode.name,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(img.top)
                    start.linkTo(img.end)
                }
        )

        Text(
            text = episode.airDate,
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(date) {
                    top.linkTo(name.bottom)
                    start.linkTo(name.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_director,
                episode.director
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(director) {
                    top.linkTo(date.bottom)
                    start.linkTo(name.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_writer,
                episode.writer
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(writer) {
                    top.linkTo(director.bottom)
                    start.linkTo(name.start)
                }
        )
    }
}