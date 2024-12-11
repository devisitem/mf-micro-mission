package co.zibi.mf.service

import co.zibi.mf.constant.ScheduleModeType
import co.zibi.mf.domain.mission.Mission
import co.zibi.mf.domain.mission.MissionState
import co.zibi.mf.domain.schedule.Schedule
import co.zibi.mf.manager.MissionStateManager
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MissionStateService (

    private val stateManager: MissionStateManager
) {

    @Transactional
    fun createRepeatState(
        missions: List<Mission>,
        schedulesById: Map<Long, Schedule>,
        mode: ScheduleModeType
    ): List<MissionState> {
        if (mode.isNotRepeat()) {
            return emptyList()
        }

        val states = missions.mapNotNull { mission ->
            schedulesById[mission.scheduleId]
                ?.let {
                    schedule -> MissionState.forCreateMission(mission, schedule)
                }
        }
        return stateManager.createStates(states)
    }
}