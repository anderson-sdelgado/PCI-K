package br.com.usinasantafe.pcik.presenter.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcik.domain.usecases.common.StartApp
import br.com.usinasantafe.pcik.utils.PointerStart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val startAPP: StartApp
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<SplashState>()
    val uiLiveData: LiveData<SplashState> = _uiLiveData

    private fun checkData(pointerStart: PointerStart) {
        _uiLiveData.value = SplashState.CheckStartAPP(pointerStart)
    }

    fun startSent() = viewModelScope.launch {
        checkData(startAPP())
    }

}

sealed class SplashState {
    data class CheckStartAPP(val pointerStart: PointerStart) : SplashState()
}