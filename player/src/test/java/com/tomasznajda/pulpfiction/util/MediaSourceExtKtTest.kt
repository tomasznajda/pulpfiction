package com.tomasznajda.pulpfiction.util

import android.net.Uri
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.nhaarman.mockitokotlin2.mock
import com.tomasznajda.pulpfiction.BuildConfig
import com.tomasznajda.pulpfiction.__util.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MediaSourceExtKtTest {

    val DASH_STREAM = "http://rdmedia.bbc.co.uk/dash/ondemand/elephants_dream/1/client_manifest-all.mpd"
    val HLS_STREAM = "https://mnmedias.api.telequebec.tv/m3u8/29880.m3u8"
    val MP4_STREAM = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"

    @Test
    fun `Uri# toMediaSource creates DashMediaSource when method is invoked on DASH manifest url`() {
        val mediaSource = Uri.parse(DASH_STREAM).toMediaSource(mock(), mock())
        assertEquals(
                expected = true,
                actual = mediaSource is DashMediaSource)
    }

    @Test
    fun `Uri# toMediaSource creates HlsMediaSource when method is invoked on HLS playlist url`() {
        val mediaSource = Uri.parse(HLS_STREAM).toMediaSource(mock(), mock())
        assertEquals(
                expected = true,
                actual = mediaSource is HlsMediaSource)
    }

    @Test
    fun `Uri# toMediaSource creates ExtractorMediaSource when method is invoked on MP4 url`() {
        val mediaSource = Uri.parse(MP4_STREAM).toMediaSource(mock(), mock())
        assertEquals(
                expected = true,
                actual = mediaSource is ExtractorMediaSource)
    }

    @Test
    fun `String# toMediaSource creates DashMediaSource when method is invoked on DASH manifest url`() {
        val mediaSource = DASH_STREAM.toMediaSource(mock(), mock())
        assertEquals(
                expected = true,
                actual = mediaSource is DashMediaSource)
    }

    @Test
    fun `String# toMediaSource creates HlsMediaSource when method is invoked on HLS playlist url`() {
        val mediaSource = HLS_STREAM.toMediaSource(mock(), mock())
        assertEquals(
                expected = true,
                actual = mediaSource is HlsMediaSource)
    }

    @Test
    fun `String# toMediaSource creates ExtractorMediaSource when method is invoked on MP4 url`() {
        val mediaSource = MP4_STREAM.toMediaSource(mock(), mock())
        assertEquals(
                expected = true,
                actual = mediaSource is ExtractorMediaSource)
    }
}