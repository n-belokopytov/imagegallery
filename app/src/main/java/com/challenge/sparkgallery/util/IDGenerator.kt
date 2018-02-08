package com.challenge.sparkgallery.util

import java.util.*

/**
 * Created by 805640 on 06.02.2018.
 */
class IDGenerator {
    companion object {
        private val SYMBOLS: CharSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        private val VISITOR_ID_LENGTH = 32

        private val randomGen = Random()

        fun generateVisitorId(): String {
            var result = ""
            for (i in 0 until VISITOR_ID_LENGTH) {
                result += SYMBOLS[randomGen.nextInt(SYMBOLS.length - 1)]
            }
            return result
        }
    }
}