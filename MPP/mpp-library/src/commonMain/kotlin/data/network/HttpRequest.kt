package data.network


import domain.error.Either
import domain.error.Result

suspend fun <R> execute(f: suspend () -> R): Either<Result.Error, R> =
    try {
        Either.Right(f())
    } catch (requestError: Throwable) {
        val error: Result.Error = Result.Error.Default(requestError.message ?: "")
        Either.Left(error)
    }