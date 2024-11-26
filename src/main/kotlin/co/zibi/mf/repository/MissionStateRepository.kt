package co.zibi.mf.repository

import co.zibi.mf.domain.mission.MissionState
import org.springframework.data.jpa.repository.JpaRepository

interface MissionStateRepository : JpaRepository<MissionState, Long> {
}