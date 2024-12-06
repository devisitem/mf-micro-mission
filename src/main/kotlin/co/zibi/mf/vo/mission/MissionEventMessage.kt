package co.zibi.mf.vo.mission

import co.zibi.mf.event.MissionEvent
import co.zibi.mf.vo.AuthorizedMember
import co.zibi.mf.vo.EventMessage

data class MissionEventMessage<T> (

    override val authorizedMember: AuthorizedMember,
    override var event: T,
    override var timestamp: Long

): EventMessage<T> where T: MissionEvent {
}
