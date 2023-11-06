package com.example.hexagonalchess.domain_layer.player

import kotlin.math.absoluteValue

fun generatePlayerIdFromName(input: String): Int {
    val hashCode = input.hashCode().absoluteValue
    return String.format("%06d", hashCode % 1000000).toInt()
}