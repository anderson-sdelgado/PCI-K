package br.com.usinasantafe.pcik.presenter.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val startAPP: StartApp
) : ViewModel() {
    // TODO: Implement the ViewModel
}