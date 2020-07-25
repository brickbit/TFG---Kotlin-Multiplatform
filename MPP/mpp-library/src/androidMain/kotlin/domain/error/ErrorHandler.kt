package domain.error

actual class ErrorHandler {
    actual fun convert(error: Result.CustomError): String  =
        when (error) {
            else -> "There was an application error"
        }
}