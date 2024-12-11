package co.zibi.mf.vo

import co.zibi.mf.event.MissionEvent

interface EventMessage<T> where T: MessageEvent {

    val authorizedMember: AuthorizedMember
    var event: T
    var timestamp: Long

    fun validate(): EventMessage<T> {
        event.validate()
        return this
    }

    operator fun component1() = authorizedMember
    operator fun component2() = event
    operator fun component3() = timestamp
}