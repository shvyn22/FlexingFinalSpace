package shvyn22.flexingfinalspace.presentation.character.details

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import shvyn22.flexingfinalspace.R
import shvyn22.flexingfinalspace.data.local.model.CharacterModel

@Composable
fun CharacterDetailsScreen(
    character: CharacterModel?,
    modifier: Modifier = Modifier
) {
    if (character == null) {
        CharacterDetailsErrorContent(
            modifier = modifier
        )
    } else {
        CharacterDetailsSuccessContent(
            character = character,
            modifier = modifier
        )
    }
}

@Composable
fun CharacterDetailsSuccessContent(
    character: CharacterModel,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        val (
            img,
            name,
            status,
            alias,
            species,
            gender,
            hair,
            origin,
            abilities
        ) = createRefs()

        Image(
            painter = rememberImagePainter(
                data = character.imgURL,
                builder = {
                    crossfade(true)
                    error(R.drawable.ic_error)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
                .constrainAs(img) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
        )

        val characterStatus = when {
            listOf("Alive", "Operational").any { it in character.status } ->
                Status.ALIVE
            character.status.contains("Deceased") ->
                Status.DECEASED
            else -> Status.UNKNOWN
        }

        Icon(
            painter = when (characterStatus) {
                Status.ALIVE ->
                    painterResource(R.drawable.ic_alive)
                Status.DECEASED ->
                    painterResource(R.drawable.ic_dead)
                else ->
                    painterResource(R.drawable.ic_unknown)
            },
            contentDescription = stringResource(id = characterStatus.desc),
            tint = MaterialTheme.colors.onBackground,
            modifier = modifier
                .size(32.dp)
                .constrainAs(status) {
                    top.linkTo(img.top)
                    end.linkTo(parent.end)
                }
        )

        Text(
            text = character.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(img.top)
                    bottom.linkTo(img.bottom)
                    start.linkTo(img.end, 5.dp)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_alias,
                character.alias.joinToString(", ")
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(alias) {
                    top.linkTo(img.bottom, 5.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_species,
                character.species
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(species) {
                    top.linkTo(alias.bottom, 5.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_gender,
                character.gender
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(gender) {
                    top.linkTo(species.bottom, 5.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_hair,
                character.hair
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(hair) {
                    top.linkTo(gender.bottom, 5.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_origin,
                character.origin
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(origin) {
                    top.linkTo(hair.bottom, 5.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = stringResource(
                id = R.string.text_abilities,
                character.abilities.joinToString(", ")
            ),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .constrainAs(abilities) {
                    top.linkTo(origin.bottom, 5.dp)
                    start.linkTo(parent.start)
                }
        )
    }
}

@Composable
fun CharacterDetailsErrorContent(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.text_details_error),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h3,
        )
    }
}

enum class Status(@StringRes val desc: Int) {
    ALIVE(R.string.text_alive),
    DECEASED(R.string.text_deceased),
    UNKNOWN(R.string.text_unknown)
}