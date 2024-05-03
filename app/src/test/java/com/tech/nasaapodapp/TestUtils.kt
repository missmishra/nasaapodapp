package com.tech.nasaapodapp

import com.tech.nasaapodapp.nasa_feature.data.remote.model.NasaApodDataItem

object TestUtils {
     val fakeApiKey = "valid_api_key"
    val fakeDate = "2023-04-01"
    private val fakeApodItemOne = NasaApodDataItem(
        copyright = "DJQWrr",
        date="3-05-24",
        explanation = "What created this unusual celestial firework? The nebula, dubbed Pa 30, appears in the same sky direction now as a bright \\\"guest star\\\" did in the year 1181. Although Pa 30's filaments look similar to that created by a nova (for example GK Per), and a planetary nebula (for example NGC 6751), some astronomers now propose that it was created by a rare type of supernova: a thermonuclear Type Iax, and so is (also) named SN 1181.  In this model, the supernova was not the result of the detonation of a single star, but rather a blast that occurred when two white dwarf stars spiraled together and merged.",
        hdurl = "https://apod.nasa.gov/apod/image/2404/Pa30_NASA_4350.jpg",
        media_type = "image",
        service_version = "1",
        title = "Unusual Nebula Pa 30",
        url = "https://apod.nasa.gov/apod/image/2404/Pa30V_NASA_960.jpg"
    )
    private val fakeApodItemTwo = NasaApodDataItem(
        copyright = "DJQWrr",
        date="23-09-22",
        explanation = "What created this unusual celestial firework? The nebula, dubbed Pa 30, appears in the same sky direction now as a bright \\\"guest star\\\" did in the year 1181. Although Pa 30's filaments look similar to that created by a nova (for example GK Per), and a planetary nebula (for example NGC 6751), some astronomers now propose that it was created by a rare type of supernova: a thermonuclear Type Iax, and so is (also) named SN 1181.  In this model, the supernova was not the result of the detonation of a single star, but rather a blast that occurred when two white dwarf stars spiraled together and merged.",
        hdurl = "https://apod.nasa.gov/apod/image/2404/Pa30_NASA_4350.jpg",
        media_type = "image",
        service_version = "1",
        title = "Unusual Nebula Pa 30",
        url = "https://apod.nasa.gov/apod/image/2404/Pa30V_NASA_960.jpg"
    )
    private val fakeApodItemThree = NasaApodDataItem(
        copyright = "DJQWrr",
        date="23-09-22",
        explanation = "What created this unusual celestial firework? The nebula, dubbed Pa 30, appears in the same sky direction now as a bright \\\"guest star\\\" did in the year 1181. Although Pa 30's filaments look similar to that created by a nova (for example GK Per), and a planetary nebula (for example NGC 6751), some astronomers now propose that it was created by a rare type of supernova: a thermonuclear Type Iax, and so is (also) named SN 1181.  In this model, the supernova was not the result of the detonation of a single star, but rather a blast that occurred when two white dwarf stars spiraled together and merged.",
        hdurl = "https://apod.nasa.gov/apod/image/2404/Pa30_NASA_4350.jpg",
        media_type = "image",
        service_version = "1",
        title = "Unusual Nebula Pa 30",
        url = "https://apod.nasa.gov/apod/image/2404/Pa30V_NASA_960.jpg"
    )
    val fakeApodList = listOf(
        fakeApodItemOne,
        fakeApodItemTwo,
        fakeApodItemThree
    )
}