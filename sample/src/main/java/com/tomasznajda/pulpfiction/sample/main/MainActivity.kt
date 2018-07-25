package com.tomasznajda.pulpfiction.sample.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.jaredrummler.materialspinner.MaterialSpinner
import com.tomasznajda.pulpfiction.PulpFiction
import com.tomasznajda.pulpfiction.entity.PlayerConfig
import com.tomasznajda.pulpfiction.sample.*
import com.tomasznajda.pulpfiction.util.gone
import com.tomasznajda.pulpfiction.util.load
import com.tomasznajda.pulpfiction.util.visible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val config = PlayerConfig(userAgentHeader = "PulpFiction-UserAgent")
    private var player: PulpFiction? = null

    val streams = mapOf(
            "NO STREAM" to null,
            "DASH" to DASH_STREAM,
            "HLS" to HLS_STREAM,
            "MP4 (1)" to MP4_STREAM_1,
            "MP4 (2)" to MP4_STREAM_2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = PulpFiction(this, config)

        initStreamField()
        initVideoField()
        initPlaceholderField()
        initTransparentBackgroundField()

        switchVideo.isChecked = true
        switchPlaceholder.isChecked = true
    }

    override fun onDestroy() {
        player?.release()
        player = null
        super.onDestroy()
    }

    private fun initStreamField() {
        fldStream.setItems(streams.keys.toList())
        fldStream.setOnItemSelectedListener { view, position, id, item ->
            view.getStream(position)?.let { player?.load(it) } ?: player?.stop()
        }
    }

    private fun initVideoField() {
        switchVideo.setOnCheckedChangeListener { _, isChecked ->
            player?.videoView = if (isChecked) playerView else null
            if (isChecked) playerView.visible() else playerView.gone()
        }
    }

    private fun initPlaceholderField() {
        switchPlaceholder.setOnCheckedChangeListener { _, isChecked ->
            playerView.setPlaceholderImage(if (isChecked) R.drawable.logo else null)
        }
    }

    private fun initTransparentBackgroundField() {
        switchTransparentBackground.setOnCheckedChangeListener { _, isChecked ->
            (if (isChecked) android.R.color.transparent else android.R.color.black)
                    .let { ContextCompat.getColor(this, it) }
                    .let { playerView.setShutterBackgroundColor(it) }
        }
    }

    private fun MaterialSpinner.getStream(position: Int) =
            getItems<String>()[position].let { streams[it] }
}