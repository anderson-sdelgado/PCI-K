package br.com.usinasantafe.pcik.module.usecases

import br.com.usinasantafe.pcik.domain.usecases.common.StartApp
import br.com.usinasantafe.pcik.domain.usecases.common.StartAppImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {

    @Binds
    @Singleton
    fun bindStartAPP(usecase: StartAppImpl): StartApp

}