package co.zibi.mf.vo

import co.zibi.mf.domain.mission.MissionState

class MissionStateVo(
    val id: Long,
    val missionId: Long,
    val status: Int,
    val startAt: Long,
    val endAt: Long,
    val concreteStartAt: Long,
    val concreteCompleteAt: Long
) {
    companion object {
        fun of(state: MissionState): MissionStateVo {
            return MissionStateVo(
                state.id,
                state.missionId,
                state.missionStatus.status,
                state.startStamp,
                state.endStamp,
                state.startTime,
                state.completeTime
            )
        }
    }
}