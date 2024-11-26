package co.zibi.mf.dto

import co.zibi.mf.constant.AccountRoleType
import co.zibi.mf.constant.MemberRoleType
import co.zibi.mf.constant.UserProtocol
import co.zibi.mf.domain.member.Member
import lombok.Getter


class AuthorizedMember (
    val accountId: Long,
    val memberId: Long,
    val familyId: Long,

    val accountRole: AccountRoleType,
    val memberRole: MemberRoleType,
    val isBlock: Boolean
) {

}