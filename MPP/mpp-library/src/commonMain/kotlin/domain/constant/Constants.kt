package domain.constant

class Constants {
    companion object {
        val EMPTY_STRING: String = ""

        val DEFAULT_LONG: Long = 0

        val DEFAULT_INT: Int = 0

        const val DEFAULT_TIMEOUT_SECONDS: Long = 1250

        fun preferencesName(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> "herokuapp_debug"
            BuildType.RELEASE -> "herokuapp"
        }

        fun notificationChannelId(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> "herokuapp_debug"
            BuildType.RELEASE -> "herokuapp"
        }

        fun endpointUrl(buildType: BuildType): String = when (buildType) {
            BuildType.DEBUG -> "https://t21services.herokuapp.com"
            BuildType.RELEASE -> "https://t21services.herokuapp.com"
        }
    }
}

fun buildType(type: String): BuildType = when (type) {
    "debug" -> BuildType.DEBUG
    else -> BuildType.RELEASE
}

enum class BuildType {
    DEBUG, RELEASE
}