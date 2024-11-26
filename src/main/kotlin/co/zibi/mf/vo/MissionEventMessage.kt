package co.zibi.mf.vo

import co.zibi.mf.event.MissionEvent

interface MissionEventMessage<T> where T: MissionEvent {

    val authorizedMember: AuthorizedMember
    var event: T
    var timestamp: Long
}