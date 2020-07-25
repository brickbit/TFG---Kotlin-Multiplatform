package domain.error

sealed class Result {
    data class Success<T>(val value: T) : Result()
    sealed class CustomError : Result() {
        data class Default(val messageError: String = ""): Error()
        object NoInternet : Error()
    }
}