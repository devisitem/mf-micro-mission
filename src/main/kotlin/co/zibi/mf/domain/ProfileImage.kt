package co.zibi.mf.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.annotations.Comment

@Embeddable
class ProfileImage (

    @Comment("프로필 이미지 URL")
    @Column(name = "profile_image_url")
    val url: String

) {
}