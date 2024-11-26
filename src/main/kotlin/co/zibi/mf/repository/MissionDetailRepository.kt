package co.zibi.mf.repository

import co.zibi.mf.domain.mission.MissionDetail
import org.springframework.data.jpa.repository.JpaRepository

interface MissionDetailRepository : JpaRepository<MissionDetail, Long> {
}