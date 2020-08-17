import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.architecture.mpp.extension.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.subKodein
import org.kodein.di.android.x.kodein
import View as PresenterView

abstract class BaseFragment <out V : PresenterView> : Fragment(), KodeinAware, PresenterView {
    abstract val presenter: BasePresenter<V>

    abstract val layoutResourceId: Int

    abstract val fragmentModule: Kodein.Module

    override val kodein by subKodein(kodein()) {
        import(fragmentModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeUI()
        registerListeners()

        presenter.attach()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(layoutResourceId, container, false)


    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    abstract fun initializeUI()

    abstract fun registerListeners()

    override fun showError(error: String) {
        toast(error)
    }

    override fun showMessage(message: String) {
        toast(message, Toast.LENGTH_SHORT)
    }
}