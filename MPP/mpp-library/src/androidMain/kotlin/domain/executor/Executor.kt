package domain.executor

import kotlinx.coroutines.CoroutineDispatcher

actual class Executor {
    actual val main: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    actual val background: CoroutineDispatcher
        get() = TODO("Not yet implemented")
}