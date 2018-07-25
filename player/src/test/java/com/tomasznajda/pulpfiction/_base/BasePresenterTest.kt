package com.tomasznajda.pulpfiction._base

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.tomasznajda.pulpfiction.__util.assertEquals
import com.tomasznajda.pulpfiction.__util.spy
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import org.junit.Test

class BasePresenterTest {

    interface TestView
    class TestPresenter : BasePresenter<TestView>() {
        fun getView() = view
        public override fun attached() {}
        public override fun detached() {}
        fun saveDisposable(disposable: Disposable) = disposable.save()
    }

    @Test
    fun `attach attaches view to presenter`() {
        val view = mock<TestView>()
        val presenter = TestPresenter()
        presenter.attach(view)
        assertEquals(
                expected = view,
                actual = presenter.getView())
    }

    @Test
    fun `detach detaches view from presenter`() {
        val view = mock<TestView>()
        val presenter = TestPresenter()
        presenter.attach(view)
        presenter.detach()
        assertEquals(
                expected = null,
                actual = presenter.getView())
    }

    @Test
    fun `attach invokes attached`() {
        val presenter = TestPresenter().spy()
        presenter.attach(mock())
        verify(presenter).attached()
    }

    @Test
    fun `detach invokes detached after clearing view`() {
        val presenter = TestPresenter().spy()
        presenter.attach(mock())
        presenter.detach()
        verify(presenter).detached()
    }

    @Test
    fun `detach clears saved disposables`() {
        val presenter = TestPresenter()
        presenter.attach(mock())
        val disposable = PublishSubject.create<Unit>().subscribe()
        presenter.saveDisposable(disposable)
        assertEquals(
                expected = false,
                actual = disposable.isDisposed)
        presenter.detach()
        assertEquals(
                expected = true,
                actual = disposable.isDisposed)
    }
}