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
        name = item.name,
        status = item.status,
        species = item.species,
        gender = item.gender,
        hair = item.hair,
        origin = item.origin,
        abilities = item.abilities,
        alias = item.alias,
        imgURL = item.imgURL
    )

fun fromEpisodeDTOToModel(item: EpisodeDTO) =
    EpisodeModel(
        id = item.id,
        name = item.name,
        airDate = item.airDate,
        director = item.director,
        writer = item.writer,
        imgURL = item.imgURL
    )

fun fromQuoteDTOToModel(item: QuoteDTO) =
    QuoteModel(
        id = item.id,
        quote = item.quote,
        author = item.author,
        imgURL = item.imgURL
    )