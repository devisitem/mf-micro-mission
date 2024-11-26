package co.zibi.mf.constant

enum class MemberRoleType (
    val level: Int
) {

    NONE(-1),
    REGULAR(0),
    SUB_MASTER(1),
    MASTER(2)
    ;

    fun isNotAllowed(other: MemberRoleType): Boolean {
        return this.level < other.level
    }

    companion object {
        private val CACHED: Map<Int, MemberRoleType> = entries.associateBy { it.level }

        fun exists(level: Int): Boolean {
            return CACHED.containsKey(level)
        }

        fun of(level: Int): MemberRoleType? {
            return CACHED[level]
        }
    }
}