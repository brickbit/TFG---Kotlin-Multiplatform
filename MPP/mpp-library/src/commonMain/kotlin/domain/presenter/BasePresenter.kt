import domain.error.Either
import domain.error.ErrorHandler
import domain.error.Result
import domain.executor.Executor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BasePresenter<out V : View>(
    protected val errorHandler: ErrorHandler,
    protected val executor: Executor,
    val view: V
) {

    private val job = SupervisorJob()

    protected val scope = CoroutineScope(job + executor.main)


    abstract fun attach()

    fun detach() = job.cancel()

    fun CoroutineScope.ui(f: () -> Unit) {
        this.launch(executor.main) { f() }
    }

    protected suspend fun <T> execute(f: suspend () -> Either<Result.Error, T>): Either<Result.Error, T> = withContext(executor.background) { f() }

    protected fun onError(callback: (String) -> Unit): (Result.Error) -> Unit = {
        view.hideProgress()

        val message = errorHandler.convert(it)

        callback(message)
    }
}

interface View {
    fun showProgress()
    fun hideProgress()
    fun showMessage(message: String)
    fun showError(error: String)
}
