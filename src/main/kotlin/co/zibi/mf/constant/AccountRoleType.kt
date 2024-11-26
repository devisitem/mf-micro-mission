package co.zibi.mf.constant

import co.zibi.mf.domain.member.AccountRole
import lombok.Getter


enum class AccountRoleType (
    val level: Int
) {
    GUEST(0),
    MEMBER(1),
    ADMIN(2),
    ;

    fun isNotAllowed(other: AccountRoleType): Boolean {
        return this.level < other.level
    }

    companion object {
        private val CACHED: Map<Int, AccountRoleType> = entries.associateBy { it.level }

        fun exists(level: Int): Boolean {
            return CACHED.containsKey(level)
        }

        fun of(level: Int): AccountRoleType? {
            return CACHED[level]
        }

        fun from(accountRole: AccountRole): AccountRoleType {
            return exists(accountRole.role).let {
                of(accountRole.role) ?: MEMBER
            }
        }

    }
}