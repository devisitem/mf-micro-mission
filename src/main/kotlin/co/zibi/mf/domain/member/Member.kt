package co.zibi.mf.domain.member

import co.zibi.mf.domain.ProfileImage
import jakarta.persistence.*
import org.hibernate.annotations.Comment
import java.io.Serializable

@Entity
@Comment("일반 멤버 정보")
@Table(name = "mf_member",
    indexes = [
        Index(name = "idx_aid", columnList = "aid", unique = true),
    ],
    uniqueConstraints = [
        UniqueConstraint(name = "uk_aud", columnNames = ["aid"]),
        UniqueConstraint(name = "uk_nickname", columnNames = ["nickname"])
    ]
)
class Member (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long,

    @Comment("계정 ID")
    @Column(name = "aid")
    val accountId: Long,

    @Embedded
    val accountRole: AccountRole,

    @Embedded
    val profileImage: ProfileImage,

    @Comment("닉네임")
    @Column(name = "nickname")
    val nickname: String,

    @Comment("이용 정지여부")
    @Column(name = "is_blocked")
    val isBlocked: Boolean,

    @Comment("가입 일시")
    @Column(name = "registered_at")
    val registeredAt: Long

): Serializable {
}