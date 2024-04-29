package br.com.usinasantafe.pcik.infra.datasource.sharedpreferences

import br.com.usinasantafe.pcik.domain.entities.Config

interface ConfigSharedPreferencesDatasource {

    suspend fun hasConfig(): Boolean

    suspend fun getConfig(): Config

    suspend fun saveConfig(config: Config)

}