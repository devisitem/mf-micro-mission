package co.zibi.mf.manager

import co.zibi.mf.domain.mission.Mission
import co.zibi.mf.domain.mission.MissionDetail
import co.zibi.mf.repository.MissionDetailRepository
import co.zibi.mf.repository.MissionRepository
import jakarta.transaction.Transactional
import jakarta.transaction.Transactional.TxType
import org.springframework.stereotype.Service

@Service
class MissionManager(

    private val missionRepository: MissionRepository,
    private val missionDetailRepository: MissionDetailRepository

) {
    @Transactional(TxType.MANDATORY)
    fun createDetailMissions(details: List<MissionDetail>): List<MissionDetail> {
        return missionDetailRepository.saveAll(details)
    }

    @Transactional(TxType.MANDATORY)
    fun crateMissions(missions: List<Mission>): List<Mission> {
        return missionRepository.saveAll(missions)
    }

}