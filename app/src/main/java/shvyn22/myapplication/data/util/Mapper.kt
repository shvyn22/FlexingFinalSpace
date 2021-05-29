package shvyn22.myapplication.data.util

import shvyn22.myapplication.data.local.model.CharacterModel
import shvyn22.myapplication.data.local.model.EpisodeModel
import shvyn22.myapplication.data.local.model.QuoteModel
import shvyn22.myapplication.data.remote.CharacterDTO
import shvyn22.myapplication.data.remote.EpisodeDTO
import shvyn22.myapplication.data.remote.QuoteDTO

fun fromCharacterDTOToModel(item: CharacterDTO) =
    CharacterModel(
        id = item.id,
        name = item.name ?: "",
        status = item.status ?: "Unknown",
        species = item.species ?: "Unknown",
        gender = item.gender ?: "Unknown",
        hair = item.hair ?: "Unknown",
        origin = item.origin ?: "Unknown",
        abilities = item.abilities ?: emptyList(),
        alias = item.alias ?: emptyList(),
        imgURL = item.imgURL ?: ""
    )

fun fromEpisodeDTOToModel(item: EpisodeDTO) =
    EpisodeModel(
        id = item.id,
        name = item.name ?: "",
        airDate = item.airDate ?: "Unknown",
        director = item.director ?: "Unknown",
        writer = item.writer ?: "Unknown",
        imgURL = item.imgURL ?: ""
    )

fun fromQuoteDTOToModel(item: QuoteDTO) =
    QuoteModel(
        id = item.id,
        quote = item.quote ?: "",
        author = item.author ?: "Unknown",
        imgURL = item.imgURL ?: ""
    )