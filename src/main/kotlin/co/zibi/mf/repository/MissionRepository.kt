package co.zibi.mf.repository

import co.zibi.mf.domain.mission.Mission
import org.springframework.data.jpa.repository.JpaRepository

interface MissionRepository: JpaRepository<Mission, Long> {
}