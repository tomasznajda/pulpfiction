package com.tomasznajda.pulpfiction.util

import android.net.Uri
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.util.Util

fun String.toMediaSource(mediaDataSourceFactory: DataSource.Factory,
                         manifestDataSourceFactory: DataSource.Factory) =
        Uri.parse(this).toMediaSource(mediaDataSourceFactory, manifestDataSourceFactory)

fun Uri.toMediaSource(mediaDataSourceFactory: DataSource.Factory,
                      manifestDataSourceFactory: DataSource.Factory) =
        Util.inferContentType(this).let {
            when (it) {
                C.TYPE_DASH -> toDashMediaSource(mediaDataSourceFactory, manifestDataSourceFactory)
                C.TYPE_HLS -> toHlsMediaSource(mediaDataSourceFactory)
                C.TYPE_OTHER -> toOtherMediaSource(mediaDataSourceFactory)
                else -> throw IllegalStateException("Unsupported type: $it")
            }
        }!!


private fun Uri.toDashMediaSource(mediaDataSourceFactory: DataSource.Factory,
                                  manifestDataSourceFactory: DataSource.Factory) =
        DashMediaSource
                .Factory(DefaultDashChunkSource.Factory(mediaDataSourceFactory), manifestDataSourceFactory)
                .createMediaSource(this)

private fun Uri.toHlsMediaSource(mediaDataSourceFactory: DataSource.Factory) =
        HlsMediaSource
                .Factory(mediaDataSourceFactory)
                .createMediaSource(this)

private fun Uri.toOtherMediaSource(mediaDataSourceFactory: DataSource.Factory) =
        ExtractorMediaSource
                .Factory(mediaDataSourceFactory)
                .createMediaSource(this)