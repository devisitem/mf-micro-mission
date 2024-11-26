package co.zibi.mf.domain.member

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class AccountRole (

    @Comment("역할 [0: 게스트, 1: 일반회원, 2: 관리자]")
    @Column(name = "role", nullable = false, length = 1)
    val role: Int

) {
}