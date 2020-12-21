package com.example.championsapp

import com.example.championsapp.model.Champion

object ChampionMock {

    val championListMock = listOf<Champion>(
        Champion("https://images.tacter.app/set4/champions/portraits/wukong.png"),
        Champion("https://images.tacter.app/set4/champions/portraits/ashe.png"),
        Champion("https://images.tacter.app/set4/champions/portraits/veigar.png")
    )

    val emptyChampionListMock = listOf<Champion>()
}