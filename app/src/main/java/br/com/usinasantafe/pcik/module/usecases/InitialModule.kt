package br.com.usinasantafe.pcik.module.usecases

import br.com.usinasantafe.pcik.domain.usecases.initial.CheckPasswordConfig
import br.com.usinasantafe.pcik.domain.usecases.initial.CheckPasswordConfigImpl
import br.com.usinasantafe.pcik.domain.usecases.initial.InitialConfig
import br.com.usinasantafe.pcik.domain.usecases.initial.InitialConfigImpl
import br.com.usinasantafe.pcik.domain.usecases.initial.RecoverConfig
import br.com.usinasantafe.pcik.domain.usecases.initial.RecoverConfigImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface InitialModule {

    @Singleton
    @Binds
    fun bindCheckPasswordConfig(usecase: CheckPasswordConfigImpl): CheckPasswordConfig

    @Singleton
    @Binds
    fun bindInitialConfig(usecase: InitialConfigImpl): InitialConfig

    @Singleton
    @Binds
    fun bindRecoverConfig(usecase: RecoverConfigImpl): RecoverConfig

}