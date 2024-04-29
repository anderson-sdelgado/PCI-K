package br.com.usinasantafe.pcik.module.datasource.sharedpreferences

import br.com.usinasantafe.pcik.external.sharedpreferences.ConfigSharedPreferencesDatasourceImpl
import br.com.usinasantafe.pcik.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface SharedPreferencesDatasourceModule {

    @Binds
    @Singleton
    fun bindConfigDatasourceSharedPreferences(dataSource: ConfigSharedPreferencesDatasourceImpl): ConfigSharedPreferencesDatasource

}