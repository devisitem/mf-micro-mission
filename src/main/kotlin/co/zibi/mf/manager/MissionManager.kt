package co.zibi.mf.manager

import co.zibi.mf.repository.MissionRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class MissionManager (
    repository: MissionRepository
) {

}