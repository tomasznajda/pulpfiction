package com.tomasznajda.pulpfiction.__util

import org.junit.Assert

fun <T : Any> assertEquals(expected: T?, actual: T?) = Assert.assertEquals(expected, actual)
fun <T : Any> assertNotEquals(unexpected: T?, actual: T?) = Assert.assertNotEquals(unexpected, actual)