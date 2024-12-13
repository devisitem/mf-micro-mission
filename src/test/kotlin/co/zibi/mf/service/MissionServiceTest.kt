package co.zibi.mf.service

import co.zibi.mf.manager.MissionManager
import co.zibi.mf.manager.MissionStateManager
import co.zibi.mf.manager.ScheduleManager
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK

internal class MissionServiceTest {


    @MockK
    private lateinit var missionManager: MissionManager

    @MockK
    private lateinit var scheduleManager: ScheduleManager

    @MockK
    private lateinit var missionStateManager: MissionStateManager

    @InjectMockKs
    private lateinit var missionService: MissionService

}