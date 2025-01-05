package com.example.mycinema

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        // Given
        val val1 = 2
        val val2 = 2
        // When
        val res = val1 + val2
        // Then
        val result = 4
        assertEquals(result, res)
    }
}