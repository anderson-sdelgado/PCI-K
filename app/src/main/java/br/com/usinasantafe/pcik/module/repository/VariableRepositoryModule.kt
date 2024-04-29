package br.com.usinasantafe.pcik.module.repository

import br.com.usinasantafe.pcik.domain.repostiories.variable.ConfigRepository
import br.com.usinasantafe.pcik.infra.repositories.variable.ConfigRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableRepositoryModule {

    @Binds
    @Singleton
    fun bindConfigRepository(repository: ConfigRepositoryImpl): ConfigRepository

}