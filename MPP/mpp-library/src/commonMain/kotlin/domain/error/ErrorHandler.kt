package domain.error

expect class ErrorHandler {
    fun convert(error: Result.Error): String
}