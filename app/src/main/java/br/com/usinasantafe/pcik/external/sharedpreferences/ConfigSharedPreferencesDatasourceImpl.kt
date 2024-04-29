package br.com.usinasantafe.pcik.external.sharedpreferences

import android.content.SharedPreferences
import br.com.usinasantafe.pcik.domain.entities.Config
import br.com.usinasantafe.pcik.infra.datasource.sharedpreferences.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.pcik.utils.BASE_SHARE_PREFERENCES_TABLE_CONFIG
import com.google.gson.Gson
import javax.inject.Inject

class ConfigSharedPreferencesDatasourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ConfigSharedPreferencesDatasource {
    override suspend fun hasConfig(): Boolean {
        val result = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, null)
        return result != null
    }

    override suspend fun getConfig(): Config {
        val config = sharedPreferences.getString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, null)
        if(config.isNullOrEmpty())
            return Config()
        return Gson().fromJson(config, Config::class.java)
    }

    override suspend fun saveConfig(config: Config) {
        val editor = sharedPreferences.edit()
        editor.putString(BASE_SHARE_PREFERENCES_TABLE_CONFIG, Gson().toJson(config))
        editor.commit()
    }
}