package co.zibi.mf.manager

import co.zibi.mf.domain.mission.MissionState
import co.zibi.mf.repository.MissionStateRepository
import jakarta.transaction.Transactional
import jakarta.transaction.Transactional.TxType
import org.springframework.stereotype.Service

@Service
class MissionStateManager(
    val repository: MissionStateRepository
) {

    @Transactional(TxType.MANDATORY)
    fun createStates(states: List<MissionState>): List<MissionState> {
        return repository.saveAll(states)
    }
}