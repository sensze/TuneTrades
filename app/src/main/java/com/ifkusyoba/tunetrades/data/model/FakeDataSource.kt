package com.ifkusyoba.tunetrades.data.model

import com.ifkusyoba.tunetrades.R

object FakeDataSource {
    val dummyMusic = listOf(
        Music(1, R.drawable.music_violin, "String Section Violin", 2000000, R.string.description_violin),
        Music(2, R.drawable.music_viola, "String Section Viola", 1000000, R.string.description_viola),
        Music(3, R.drawable.music_cello, "String Section Cello", 1500000, R.string.description_cello),
        Music(4, R.drawable.music_bass, "String Section Double Bass", 1500000, R.string.description_bass),
        Music(5, R.drawable.music_harp, "Key Section Harp", 2000000, R.string.description_harp),
        Music(6, R.drawable.music_flute, "Woodwind Section Flute", 1000000, R.string.description_flute),
        Music(7, R.drawable.music_oboe, "Woodwind Section Oboe", 1000000, R.string.description_oboe),
        Music(8, R.drawable.music_clarinet, "Woodwind Section Clarinet", 1000000, R.string.description_clarinet),
        Music(9, R.drawable.music_bassoons, "Woodwind Section Bassoon", 1000000, R.string.description_bassoon),
        Music(10, R.drawable.music_trumpet, "Brass Section Trumpet", 1000000, R.string.description_trumpet),
        Music(11, R.drawable.music_french, "Brass Section French Horn", 1000000, R.string.description_french),
        Music(12, R.drawable.music_trombone, "Brass Section Trombone", 1000000, R.string.description_trombone),
        Music(13, R.drawable.music_tuba, "Brass Section Tuba", 1000000, R.string.description_tuba),
    )
}