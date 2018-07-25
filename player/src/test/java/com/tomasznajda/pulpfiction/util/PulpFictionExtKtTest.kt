package com.tomasznajda.pulpfiction.util

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tomasznajda.pulpfiction.PulpFiction
import com.tomasznajda.pulpfiction.entity.Clip
import org.junit.Test

class PulpFictionExtKtTest {

    @Test
    fun `load invokes load with Clip`() {
        val pulpFiction = mock<PulpFiction>()
        pulpFiction.load("example.com", true)
        verify(pulpFiction).load(Clip("example.com"), true)
        pulpFiction.load("example.com", false)
        verify(pulpFiction).load(Clip("example.com"), false)
    }

    @Test
    fun `load sets autoplay to true as default`() {
        val pulpFiction = mock<PulpFiction>()
        pulpFiction.load("example.com")
        verify(pulpFiction).load(Clip("example.com"), true)
    }

}