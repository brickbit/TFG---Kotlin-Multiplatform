import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<out V : BasePresenter.View> : AppCompatActivity(), BasePresenter.View  {
    
}