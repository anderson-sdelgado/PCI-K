package br.com.usinasantafe.pcik.domain.usecases.common

import br.com.usinasantafe.pcik.utils.PointerStart
import javax.inject.Inject

interface StartApp {
    suspend operator fun invoke(): PointerStart
}

class StartAppImpl @Inject constructor() : StartApp {
    override suspend fun invoke(): PointerStart {
        return PointerStart.MENUINICIAL
    }

}