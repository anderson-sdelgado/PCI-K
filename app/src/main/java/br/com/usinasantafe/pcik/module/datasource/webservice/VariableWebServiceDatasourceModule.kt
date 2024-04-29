package br.com.usinasantafe.pcik.module.datasource.webservice

import br.com.usinasantafe.pcik.external.webservices.datasource.variable.ConfigWebServiceDatasourceImpl
import br.com.usinasantafe.pcik.infra.datasource.webservice.ConfigWebServiceDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableWebServiceDatasourceModule {

    @Binds
    @Singleton
    fun bindConfigWebServiceDatasource(dataSource: ConfigWebServiceDatasourceImpl): ConfigWebServiceDatasource

}