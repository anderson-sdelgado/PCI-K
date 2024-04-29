package br.com.usinasantafe.pcik.presenter.initial.config

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.usinasantafe.pcik.domain.usecases.initial.InitialConfig
import br.com.usinasantafe.pcik.domain.usecases.initial.RecoverConfig
import br.com.usinasantafe.pcik.utils.ResultUpdateDatabase
import br.com.usinasantafe.pcik.utils.StatusRecover
import br.com.usinasantafe.pcik.utils.StatusUpdate
import br.com.usinasantafe.pcik.utils.WEB_RETURN_CLEAR_EQUIP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ConfigViewModel @Inject constructor(
    private val recoverConfig: RecoverConfig,
//    private val updateAllDataBase: UpdateAllDataBase,
    private val initialConfig: InitialConfig,
) : ViewModel() {

    private val _uiLiveData = MutableLiveData<ConfigFragmentState>()
    val uiLiveData: LiveData<ConfigFragmentState> = _uiLiveData

    private fun setLoadingDataBase(statusUpdate: StatusUpdate) {
        _uiLiveData.value = ConfigFragmentState.FeedbackLoadingDataBase(statusUpdate)
    }

    private fun setLoadingToken(statusRecover: StatusRecover) {
        _uiLiveData.value = ConfigFragmentState.FeedbackLoadingToken(statusRecover)
    }

    private fun setConfig(config: ConfigModel) {
        _uiLiveData.value = ConfigFragmentState.RecoverConfigModel(config)
    }

    private fun setResultUpdate(resultUpdateDatabase: ResultUpdateDatabase){
        _uiLiveData.value = ConfigFragmentState.SetResultUpdate(resultUpdateDatabase)
    }

    fun recoverDataConfig() = viewModelScope.launch {
        recoverConfig()?.let { setConfig(it) }
    }

    fun saveDataInitialConfig(nroAparelho: String, senha: String, version: String) =
        viewModelScope.launch {
            initialConfig(nroAparelho, senha, version)
                .catch { catch ->
                    setResultUpdate(ResultUpdateDatabase(100, "Erro: $catch", 100))
                    setLoadingToken(StatusRecover.FAILURE)
                }
                .collect { result ->
                    result.fold(
                        onSuccess = { resultUpdateDatabase ->
                            setResultUpdate(resultUpdateDatabase)
                            if (resultUpdateDatabase.percentage == 100) {
                                if (resultUpdateDatabase.describe == WEB_RETURN_CLEAR_EQUIP) {
                                    setLoadingToken(StatusRecover.EMPTY)
                                } else {
                                    setLoadingToken(StatusRecover.SUCCESS)
                                }
                            }
                        },
                        onFailure = { catch ->
                            setResultUpdate(ResultUpdateDatabase(100, "Erro: $catch", 100))
                            setLoadingToken(StatusRecover.FAILURE)
                        })
                }
        }

//    fun updateDataBaseInitial() =
//        viewModelScope.launch {
//            updateAllDataBase()
//                .catch { catch ->
//                    setResultUpdate(ResultUpdateDatabase(100, "Erro: $catch", 100))
//                    setLoadingDataBase(StatusUpdate.FAILURE)
//                }
//                .collect { result ->
//                    result.fold(
//                        onSuccess = { resultUpdateDatabase ->
//                            setResultUpdate(resultUpdateDatabase)
//                            if (resultUpdateDatabase.percentage == 100) {
//                                setLoadingDataBase(StatusUpdate.UPDATED)
//                            }
//                        },
//                        onFailure = { catch ->
//                            setResultUpdate(ResultUpdateDatabase(100, "Erro: $catch", 100))
//                            setLoadingDataBase(StatusUpdate.FAILURE)
//                            return@collect cancel()
//                        }
//                    )
//                }
//        }
}

sealed class ConfigFragmentState {
    data class RecoverConfigModel(val config: ConfigModel) : ConfigFragmentState()
    data class FeedbackLoadingDataBase(val statusUpdateDataBase: StatusUpdate) :
        ConfigFragmentState()
    data class FeedbackLoadingToken(val statusToken: StatusRecover) : ConfigFragmentState()
//    data class IsCheckUpdate(val isCheckUpdate: Boolean) : ConfigFragmentState()
    data class SetResultUpdate(val resultUpdateDatabase: ResultUpdateDatabase) : ConfigFragmentState()
}