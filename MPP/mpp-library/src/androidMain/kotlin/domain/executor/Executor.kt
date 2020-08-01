package domain.executor

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Executor {
    actual val main: CoroutineDispatcher = Dispatchers.Main
    actual val background: CoroutineDispatcher = Dispatchers.IO
}