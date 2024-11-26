package co.zibi.mf.vo

import co.zibi.mf.constant.AccountRoleType
import co.zibi.mf.constant.MemberRoleType


class AuthorizedMember (
    val accountId: Long,
    val memberId: Long,
    val familyId: Long,

    val accountRole: AccountRoleType,
    val memberRole: MemberRoleType,
    val isBlock: Boolean
) {

}