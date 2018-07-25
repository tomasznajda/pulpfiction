package com.tomasznajda.pulpfiction.controls

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.subjects.PublishSubject
import org.junit.Before
import org.junit.Test

class ControlsPresenterTest {

    val view = mock<ControlsContract.View>()
    val presenter = ControlsPresenter()

    val playClicksSubject = PublishSubject.create<Unit>()
    val pauseClicksSubject = PublishSubject.create<Unit>()

    @Before
    fun setUp() {
        whenever(view.playClicks).thenReturn(playClicksSubject)
        whenever(view.pauseClicks).thenReturn(pauseClicksSubject)
    }

    @Test
    fun `playClick invokes play`() {
        presenter.attach(view)
        playClicksSubject.onNext(Unit)
        verify(view).play()
    }

    @Test
    fun `pauseClick invokes pause`() {
        presenter.attach(view)
        pauseClicksSubject.onNext(Unit)
        verify(view).pause()
    }
}