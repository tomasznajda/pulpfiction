package com.tomasznajda.pulpfiction

import android.content.Context
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.tomasznajda.pulpfiction.entity.Clip
import com.tomasznajda.pulpfiction.entity.PlayerConfig
import com.tomasznajda.pulpfiction.entity.PlayerState
import com.tomasznajda.pulpfiction.event.analitics.AnalyticsEvent
import com.tomasznajda.pulpfiction.event.analitics.AnalyticsObservable
import com.tomasznajda.pulpfiction.event.exoplayer.ExoPlayerObservable
import com.tomasznajda.pulpfiction.event.player.PlayerEvent
import com.tomasznajda.pulpfiction.event.player.PlayerObservable
import com.tomasznajda.pulpfiction.event.video.VideoEvent
import com.tomasznajda.pulpfiction.event.video.VideoObservable
import com.tomasznajda.pulpfiction.fullscreen.FullscreenDelegate
import com.tomasznajda.pulpfiction.util.pulpFictionState
import com.tomasznajda.pulpfiction.util.toMediaSource
import io.reactivex.Observable
import com.tomasznajda.pulpfiction.event.exoplayer.PlayerEvent as ExoPlayerEvent

class PulpFiction(context: Context, config: PlayerConfig) : Player {

    val exoPlayerEvents: Observable<ExoPlayerEvent> by lazy { ExoPlayerObservable(exoPlayer) }
    val videoEvents: Observable<VideoEvent> by lazy { VideoObservable(exoPlayer) }
    val analyticsEvents: Observable<AnalyticsEvent> by lazy { AnalyticsObservable(exoPlayer) }
    override val events: Observable<PlayerEvent> by lazy { PlayerObservable(exoPlayer) }
    val state: PlayerState
        get() = exoPlayer.pulpFictionState

    var fullscreen = FullscreenDelegate()
        private set

    override var videoView: PfVideoView? = null
        set(value) {
            when {
                value == null -> clearVideoView(field)
                field == null -> initVideoView(value)
            }
            field = value
        }

    private val bandwidthMeter = DefaultBandwidthMeter()
    private val mediaDataSourceFactory =
            DefaultDataSourceFactory(context, bandwidthMeter, DefaultHttpDataSourceFactory(config.userAgentHeader, bandwidthMeter))
    private val manifestDataSourceFactory =
            DefaultDataSourceFactory(context, null, DefaultHttpDataSourceFactory(config.userAgentHeader, null))
    internal var exoPlayer = ExoPlayerFactory.newSimpleInstance(context, DefaultTrackSelector(bandwidthMeter))
        private set

    override fun load(clip: Clip, autoplay: Boolean) {
        exoPlayer.playWhenReady = autoplay
        exoPlayer.prepare(clip.url.toMediaSource(mediaDataSourceFactory, manifestDataSourceFactory))

    }

    override fun play() {
        exoPlayer.playWhenReady = true
    }

    override fun pause() {
        exoPlayer.playWhenReady = false
    }

    override fun stop() {
        exoPlayer.stop(true)
    }

    override fun release() {
        exoPlayer.release()
    }

    fun enableFullscreen(enterFullscreenCallback: () -> Boolean, exitFullscreenCallback: () -> Boolean) =
            fullscreen.enable(enterFullscreenCallback, exitFullscreenCallback)

    fun disableFullscreen() = fullscreen.disable()

    fun enterFullscreen() = fullscreen.enter()

    fun exitFullscreen() = fullscreen.exit()

    private fun initVideoView(view: PfVideoView?) {
        view?.pulpFiction = this
    }

    private fun clearVideoView(view: PfVideoView?) {
        exoPlayer.clearVideoSurface()
        view?.pulpFiction = null
    }
}