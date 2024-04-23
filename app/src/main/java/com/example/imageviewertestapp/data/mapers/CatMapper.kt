package com.example.imageviewertestapp.data.mapers

import com.example.imageviewertestapp.data.local.CatEntity
import com.example.imageviewertestapp.data.remote.CatDto
import com.example.imageviewertestapp.domain.Cat

fun CatDto.toCat(): Cat {
    return Cat(
        id = id,
        url = url,
        width = width,
        height = height
    )
}

fun Cat.toCatEntity() = CatEntity(
    id = id,
    url = url,
    width = width,
    height = height
)

fun CatEntity.toCat() = Cat(
    id = id,
    url = url,
    width = width,
    height = height
)