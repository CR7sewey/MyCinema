package com.example.mycinema

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

//DTO
//@kotlinx.serialization.InternalSerializationApi
data class MovieDTO(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("poster_path")
    val postPath: String,
) {
    val posterFullPath: String
        get () {
            val currentPost = this.postPath
            val path = "https://image.tmdb.org/t/p/original"
            return "$path$currentPost"
        }
}


/*

{
  "dates": {
    "maximum": "2024-12-18",
    "minimum": "2024-11-06"
  },
  "page": 1,
  "results": [
    {
      "adult": false,
      "backdrop_path": "/3V4kLQg0kSqPLctI5ziYWabAZYF.jpg",
      "genre_ids": [
        28,
        878,
        12,
        53
      ],
      "id": 912649,
      "original_language": "en",
      "original_title": "Venom: The Last Dance",
      "overview": "Eddie and Venom are on the run. Hunted by both of their worlds and with the net closing in, the duo are forced into a devastating decision that will bring the curtains down on Venom and Eddie's last dance.",
      "popularity": 9486.301,
      "poster_path": "/aosm8NMQ3UyoBVpSxyimorCQykC.jpg",
      "release_date": "2024-10-22",
      "title": "Venom: The Last Dance",
      "video": false,
      "vote_average": 6.771,
      "vote_count": 1560
    },
    {
      "adult": false,
      "backdrop_path": "/tElnmtQ6yz1PjN1kePNl8yMSb59.jpg",
      "genre_ids": [
        16,
        12,
        10751,
        35
      ],
      "id": 1241982,
      "original_language": "en",
      "original_title": "Moana 2",
      "overview": "After receiving an unexpected call from her wayfinding ancestors, Moana journeys alongside Maui and a new crew to the far seas of Oceania and into dangerous, long-lost waters for an adventure unlike anything she's ever faced.",
      "popularity": 3980.558,
      "poster_path": "/4YZpsylmjHbqeWzjKpUEF8gcLNW.jpg",
      "release_date": "2024-11-21",
      "title": "Moana 2",
      "video": false,
      "vote_average": 6.922,
      "vote_count": 499
    },
    {
      "adult": false,
      "backdrop_path": "/bHkn3yuOFdu5LJcq67Odofhx6cb.jpg",
      "genre_ids": [
        28,
        14,
        35
      ],
      "id": 845781,
      "original_language": "en",
      "original_title": "Red One",
      "overview": "After Santa Claus (codename: Red One) is kidnapped, the North Pole's Head of Security must team up with the world's most infamous tracker in a globe-trotting, action-packed mission to save Christmas.",
      "popularity": 3379.818,
      "poster_path": "/cdqLnri3NEGcmfnqwk2TSIYtddg.jpg",
      "release_date": "2024-10-31",
      "title": "Red One",
      "video": false,
      "vote_average": 7,
      "vote_count": 717
    },
    {
      "adult": false,
      "backdrop_path": "/au3o84ub27qTZiMiEc9UYzN74V3.jpg",
      "genre_ids": [
        28,
        878,
        53
      ],
      "id": 1035048,
      "original_language": "en",
      "original_title": "Elevation",
      "overview": "A single father and two women venture from the safety of their homes to face monstrous creatures to save the life of a young boy.",
      "popularity": 2167.591,
      "poster_path": "/uQhYBxOVFU6s9agD49FnGHwJqG5.jpg",
      "release_date": "2024-11-07",
      "title": "Elevation",
      "video": false,
      "vote_average": 6.5,
      "vote_count": 163
    },
    {
      "adult": false,
      "backdrop_path": "/nOoGIymGBNtA7AEN0B6nshSEQ1p.jpg",
      "genre_ids": [
        28,
        80,
        53
      ],
      "id": 974453,
      "original_language": "en",
      "original_title": "Absolution",
      "overview": "An aging ex-boxer gangster working as muscle for a Boston crime boss receives an upsetting diagnosis.  Despite a faltering memory, he attempts to rectify the sins of his past and reconnect with his estranged children. He is determined to leave a positive legacy for his grandson, but the criminal underworld isn’t done with him and won’t loosen their grip willingly.",
      "popularity": 1732.435,
      "poster_path": "/cNtAslrDhk1i3IOZ16vF7df6lMy.jpg",
      "release_date": "2024-10-31",
      "title": "Absolution",
      "video": false,
      "vote_average": 6.1,
      "vote_count": 133
    },
    {
      "adult": false,
      "backdrop_path": "/ag66gJCiZ06q1GSJuQlhGLi3Udx.jpg",
      "genre_ids": [
        27,
        53
      ],
      "id": 1138194,
      "original_language": "en",
      "original_title": "Heretic",
      "overview": "Two young missionaries are forced to prove their faith when they knock on the wrong door and are greeted by a diabolical Mr. Reed, becoming ensnared in his deadly game of cat-and-mouse.",
      "popularity": 1511.671,
      "poster_path": "/5HJqjCTcaE1TFwnNh3Dn21be2es.jpg",
      "release_date": "2024-10-31",
      "title": "Heretic",
      "video": false,
      "vote_average": 7.2,
      "vote_count": 332
    },
    {
      "adult": false,
      "backdrop_path": "/rWs3Q9bT7zCH6t004z67UGuWUwu.jpg",
      "genre_ids": [
        16,
        35,
        10751,
        14,
        12
      ],
      "id": 645757,
      "original_language": "en",
      "original_title": "That Christmas",
      "overview": "It's an unforgettable Christmas for the townsfolk of Wellington-on-Sea when the worst snowstorm in history alters everyone's plans — including Santa's.",
      "popularity": 1480.413,
      "poster_path": "/bX6dx2U4hOk1esI7mYwtD3cEKdC.jpg",
      "release_date": "2024-11-27",
      "title": "That Christmas",
      "video": false,
      "vote_average": 7.496,
      "vote_count": 132
    },
    {
      "adult": false,
      "backdrop_path": "/evFChfYeD2LqobEJf8iQsrYcGTw.jpg",
      "genre_ids": [
        28,
        80,
        53
      ],
      "id": 1182387,
      "original_language": "en",
      "original_title": "Armor",
      "overview": "Armored truck security guard James Brody is working with his son Casey transporting millions of dollars between banks when a team of thieves led by Rook orchestrate a takeover of their truck to seize the riches. Following a violent car chase, Rook soon has the armored truck surrounded and James and Casey find themselves cornered onto a decrepit bridge.",
      "popularity": 1356.595,
      "poster_path": "/pnXLFioDeftqjlCVlRmXvIdMsdP.jpg",
      "release_date": "2024-10-30",
      "title": "Armor",
      "video": false,
      "vote_average": 5.445,
      "vote_count": 73
    },
    {
      "adult": false,
      "backdrop_path": "/v9Du2HC3hlknAvGlWhquRbeifwW.jpg",
      "genre_ids": [
        28,
        12,
        53
      ],
      "id": 539972,
      "original_language": "en",
      "original_title": "Kraven the Hunter",
      "overview": "Kraven Kravinoff's complex relationship with his ruthless gangster father, Nikolai, starts him down a path of vengeance with brutal consequences, motivating him to become not only the greatest hunter in the world, but also one of its most feared.",
      "popularity": 1267.538,
      "poster_path": "/i47IUSsN126K11JUzqQIOi1Mg1M.jpg",
      "release_date": "2024-12-11",
      "title": "Kraven the Hunter",
      "video": false,
      "vote_average": 5.7,
      "vote_count": 83
    },
    {
      "adult": false,
      "backdrop_path": "/m2hs6YPVrzjvRC21SE9BeXtgqFW.jpg",
      "genre_ids": [
        16,
        9648,
        878,
        28
      ],
      "id": 1299652,
      "original_language": "en",
      "original_title": "Watchmen: Chapter II",
      "overview": "Suspicious of the events ensnaring their former colleagues, Nite Owl and Silk Spectre are spurred out of retirement to investigate. As they grapple with personal ethics, inner demons and a society turned against them, they race the clock to uncover a deepening plot that might trigger global nuclear war.",
      "popularity": 1226.19,
      "poster_path": "/4rBObJFpiWJOG7aIlRrOUniAkBs.jpg",
      "release_date": "2024-11-25",
      "title": "Watchmen: Chapter II",
      "video": false,
      "vote_average": 7.6,
      "vote_count": 49
    },
    {
      "adult": false,
      "backdrop_path": "/cBTwQKMd7Vn6rGTGBtoFLIfj7uM.jpg",
      "genre_ids": [
        16,
        12,
        28,
        14
      ],
      "id": 1147416,
      "original_language": "fr",
      "original_title": "Miraculous World : Londres, la course contre le temps",
      "overview": "To save the future from a terrible fate, Marinette becomes Chronobug and teams up with Bunnyx to defeat a mysterious opponent who travels through time. Who is this new supervillain, and why are they obsessed with exposing Marinette's secret superhero identity? Marinette's only hope is to defeat her new opponent to prevent the end of Ladybug and time itself!",
      "popularity": 1219.89,
      "poster_path": "/6AtoMpHvs9pxd30KsyK8QmJ9W9M.jpg",
      "release_date": "2024-11-14",
      "title": "Miraculous World, London: At the Edge of Time",
      "video": false,
      "vote_average": 8.5,
      "vote_count": 47
    },
    {
      "adult": false,
      "backdrop_path": "/fyeyxFzZ3qYqDsaMVYF6sq3pp2n.jpg",
      "genre_ids": [
        28,
        12,
        53
      ],
      "id": 1167271,
      "original_language": "en",
      "original_title": "Weekend in Taipei",
      "overview": "A former DEA agent and a former undercover operative revisit their romance during a fateful weekend in Taipei, unaware of the dangerous consequences of their past.",
      "popularity": 1206.516,
      "poster_path": "/qSc0AUvs8mRy00R9y8QYEHWIAQ9.jpg",
      "release_date": "2024-09-19",
      "title": "Weekend in Taipei",
      "video": false,
      "vote_average": 6.2,
      "vote_count": 71
    },
    {
      "adult": false,
      "backdrop_path": "/eK8HiCLv4zo6iZ0NbjZnmD9LZkd.jpg",
      "genre_ids": [
        16,
        28,
        878,
        10751
      ],
      "id": 1184918,
      "original_language": "en",
      "original_title": "The Wild Robot",
      "overview": "After a shipwreck, an intelligent robot called Roz is stranded on an uninhabited island. To survive the harsh environment, Roz bonds with the island's animals and cares for an orphaned baby goose.",
      "popularity": 1177.7,
      "poster_path": "/wTnV3PCVW5O92JMrFvvrRcV39RU.jpg",
      "release_date": "2024-09-08",
      "title": "The Wild Robot",
      "video": false,
      "vote_average": 8.4,
      "vote_count": 3447
    },
    {
      "adult": false,
      "backdrop_path": "/euYIwmwkmz95mnXvufEmbL6ovhZ.jpg",
      "genre_ids": [
        28,
        12,
        36
      ],
      "id": 558449,
      "original_language": "en",
      "original_title": "Gladiator II",
      "overview": "Years after witnessing the death of the revered hero Maximus at the hands of his uncle, Lucius is forced to enter the Colosseum after his home is conquered by the tyrannical Emperors who now lead Rome with an iron fist. With rage in his heart and the future of the Empire at stake, Lucius must look to his past to find strength and honor to return the glory of Rome to its people.",
      "popularity": 1105.753,
      "poster_path": "/2cxhvwyEwRlysAmRH4iodkvo0z5.jpg",
      "release_date": "2024-11-05",
      "title": "Gladiator II",
      "video": false,
      "vote_average": 6.692,
      "vote_count": 1095
    },
    {
      "adult": false,
      "backdrop_path": "/t98L9uphqBSNn2Mkvdm3xSFCQyi.jpg",
      "genre_ids": [
        27,
        878
      ],
      "id": 933260,
      "original_language": "en",
      "original_title": "The Substance",
      "overview": "A fading celebrity decides to use a black market drug, a cell-replicating substance that temporarily creates a younger, better version of herself.",
      "popularity": 927.484,
      "poster_path": "/lqoMzCcZYEFK729d6qzt349fB4o.jpg",
      "release_date": "2024-09-07",
      "title": "The Substance",
      "video": false,
      "vote_average": 7.2,
      "vote_count": 2596
    },
    {
      "adult": false,
      "backdrop_path": "/c6nouvFYnmNO50WQDLcKMI3p0jA.jpg",
      "genre_ids": [
        12,
        10751,
        18,
        16
      ],
      "id": 762509,
      "original_language": "en",
      "original_title": "Mufasa: The Lion King",
      "overview": "Rafiki relays the legend of Mufasa to lion cub Kiara, daughter of Simba and Nala, with Timon and Pumbaa lending their signature schtick. Told in flashbacks, the story introduces Mufasa as an orphaned cub, lost and alone until he meets a sympathetic lion named Taka—the heir to a royal bloodline. The chance meeting sets in motion a journey of misfits searching for their destiny and working together to evade a threatening and deadly foe.",
      "popularity": 867.518,
      "poster_path": "/lurEK87kukWNaHd0zYnsi3yzJrs.jpg",
      "release_date": "2024-12-18",
      "title": "Mufasa: The Lion King",
      "video": false,
      "vote_average": 7.4,
      "vote_count": 5
    },
    {
      "adult": false,
      "backdrop_path": "/18TSJF1WLA4CkymvVUcKDBwUJ9F.jpg",
      "genre_ids": [
        27,
        53
      ],
      "id": 1034541,
      "original_language": "en",
      "original_title": "Terrifier 3",
      "overview": "Five years after surviving Art the Clown's Halloween massacre, Sienna and Jonathan are still struggling to rebuild their shattered lives. As the holiday season approaches, they try to embrace the Christmas spirit and leave the horrors of the past behind. But just when they think they're safe, Art returns, determined to turn their holiday cheer into a new nightmare. The festive season quickly unravels as Art unleashes his twisted brand of terror, proving that no holiday is safe.",
      "popularity": 829.481,
      "poster_path": "/ju10W5gl3PPK3b7TjEmVOZap51I.jpg",
      "release_date": "2024-10-09",
      "title": "Terrifier 3",
      "video": false,
      "vote_average": 6.9,
      "vote_count": 1228
    },
    {
      "adult": false,
      "backdrop_path": "/uVlUu174iiKhsUGqnOSy46eIIMU.jpg",
      "genre_ids": [
        18,
        10749,
        14
      ],
      "id": 402431,
      "original_language": "en",
      "original_title": "Wicked",
      "overview": "In the land of Oz, ostracized and misunderstood green-skinned Elphaba is forced to share a room with the popular aristocrat Glinda at Shiz University, and the two's unlikely friendship is tested as they begin to fulfill their respective destinies as Glinda the Good and the Wicked Witch of the West.",
      "popularity": 772.138,
      "poster_path": "/xDGbZ0JJ3mYaGKy4Nzd9Kph6M9L.jpg",
      "release_date": "2024-11-20",
      "title": "Wicked",
      "video": false,
      "vote_average": 7.6,
      "vote_count": 546
    },
    {
      "adult": false,
      "backdrop_path": "/liuBLPXvisMRo5w2JEKHXceWq5u.jpg",
      "genre_ids": [
        28,
        80,
        18
      ],
      "id": 1171640,
      "original_language": "fr",
      "original_title": "GTMAX",
      "overview": "When a notorious gang of bikers recruits her brother for a heist, a former motocross champion must face her deepest fears to keep her family safe.",
      "popularity": 767.827,
      "poster_path": "/bx92hl70NUhojjO3eV6LqKllj4L.jpg",
      "release_date": "2024-11-19",
      "title": "GTMAX",
      "video": false,
      "vote_average": 6.1,
      "vote_count": 74
    },
    {
      "adult": false,
      "backdrop_path": "/h3fwlwHotd3JfV13HdW0mxDcxPD.jpg",
      "genre_ids": [
        35,
        10749,
        10770
      ],
      "id": 957119,
      "original_language": "en",
      "original_title": "Sidelined: The QB and Me",
      "overview": "Dallas, a burdened but headstrong dancer, is determined to get into the best dance school in the country—her late mother’s alma mater. However, that dream is suddenly derailed when the cheeky yet secretly grieving football star, Drayton, crashes into her life with a unique story of his own. Will the two of them be able to grow into their dreams together, or will their dreams be sidelined?",
      "popularity": 754.205,
      "poster_path": "/hklQwv6QVoOp5bWyh1bjuF2ydyG.jpg",
      "release_date": "2024-11-29",
      "title": "Sidelined: The QB and Me",
      "video": false,
      "vote_average": 6.4,
      "vote_count": 67
    }
  ],
  "total_pages": 268,
  "total_results": 5357
}
 */