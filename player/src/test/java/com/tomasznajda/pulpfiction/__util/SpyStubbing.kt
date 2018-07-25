package com.tomasznajda.pulpfiction.__util

import org.mockito.Mockito

//usage: User().spy { on { id }.doReturn("testId") }
fun <ObjectT> ObjectT.spy(stubbing: KSpyStubbing<ObjectT>.(ObjectT) -> Unit = { }) =
        Mockito.spy(this).apply { KSpyStubbing(this).stubbing(this) }

class KSpyStubbing<ObjectT>(private val spy: ObjectT) {

    fun <MethodValueT> on(methodCall: ObjectT.() -> MethodValueT?) = OngoingSpyStubbing(spy, methodCall)
}

class OngoingSpyStubbing<ObjectT, MethodValueT>(private val spy: ObjectT,
                                                private val methodCall: ObjectT.() -> MethodValueT?) {

    fun doReturn(value: MethodValueT?) = Mockito.doReturn(value).`when`(spy).methodCall()

    fun <T : Throwable> doThrow(error: T) = Mockito.doThrow(error).`when`(spy).methodCall()
}