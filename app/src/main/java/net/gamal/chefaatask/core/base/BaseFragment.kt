package net.gamal.chefaatask.core.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import net.gamal.chefaatask.core.android.extension.castToActivity
import net.gamal.chefaatask.core.android.helpers.viewModel.CurrentAction
import net.gamal.chefea.android.extentions.debug
import net.gamal.chefea.core.common.data.model.exception.LeonException

abstract class BaseFragment<B : ViewBinding> : BaseViewBinding<B>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onFragmentReady()
        subscribeToObservables()
    }

    abstract fun onFragmentReady()

    abstract fun subscribeToObservables()

    abstract fun onRetryCurrentAction(currentAction: CurrentAction?, message: String)

    protected fun handleHttpsStatusCode(
        exception: LeonException, currentAction: CurrentAction? = null,
        onValidateForm: (Pair<String, String>) -> Unit = {}
    ) {
        if (requireActivity() is BaseActivity<*>) {
            castToActivity<BaseActivity<*>> {
                it?.handleHttpsStatusCode(exception, currentAction, onValidateForm)
            }
        } else debug("Cast activity to handle status code")
    }

    override fun onRefreshView() {}
}