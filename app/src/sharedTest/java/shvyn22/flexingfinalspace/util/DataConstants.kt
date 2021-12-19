package shvyn22.flexingfinalspace.util

import shvyn22.flexingfinalspace.data.remote.CharacterDTO
import shvyn22.flexingfinalspace.data.remote.EpisodeDTO
import shvyn22.flexingfinalspace.data.remote.QuoteDTO

val character1 = CharacterDTO(
    id = 1,
    name = "Gary Goodspeed",
    status = "Alive",
    species = "Human",
    gender = "Male",
    hair = "Blonde",
    origin = "Earth",
    abilities = listOf("Piloting", "Marksmanship"),
    alias = listOf("The Gary", "Thunder Bandit"),
    imgURL = "https://finalspaceapi.com/api/character/avatar/gary_goodspeed.png"
)

val character2 = CharacterDTO(
    id = 2,
    name = "Mooncake",
    status = "Unknown",
    species = "Mooncake's Species",
    gender = "None (referred to as male)",
    hair = "None",
    origin = "Outer space",
    abilities = listOf("Hovering", "Firing Laser Beams"),
    alias = listOf("Specimen E - 351", "Little Buddy"),
    imgURL =  "https://finalspaceapi.com/api/character/avatar/mooncake.jpg"
)

val characters = listOf(character1, character2)

val episode1 = EpisodeDTO(
    id = 1,
    name = "Chapter 1",
    airDate = "02/15/2018",
    director = "Mike Roberts",
    writer = "Olan Rogers",
    imgURL = "http://finalspaceapi.com/api/episode/image/chapter1.jpg"
)

val episode2 = EpisodeDTO(
    id = 2,
    name = "Chapter 2",
    airDate = "02/15/2018",
    director = "Mike Roberts",
    writer = "Olan Rogers",
    imgURL = "http://finalspaceapi.com/api/episode/image/chapter2.jpg"
)

val episodes = listOf(episode1, episode2)

val quote1 = QuoteDTO(
    id = 1,
    quote = "How about fricken' no?!",
    author = "Gary Goodspeed",
    imgURL = "https://finalspaceapi.com/api/character/avatar/gary_goodspeed.jpg"
)

val quote2 = QuoteDTO(
    id = 2,
    quote = "You see, I like that. I like a girl with a lot of phones.",
    author = "Gary Goodspeed",
    imgURL = "https://finalspaceapi.com/api/character/avatar/gary_goodspeed.jpg"
)

val quotes = listOf(quote1, quote2)

const val TEST_PREFERENCES_FILENAME = "test-preferences"