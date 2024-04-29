package br.com.usinasantafe.pcik.presenter.initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcik.presenter.initial.menuinicial.MenuInicialFragment
import br.com.usinasantafe.pcik.presenter.initial.senha.SenhaFragment
import br.com.usinasantafe.pcik.presenter.splash.SplashActivity
import br.com.usinasantafe.pcik.utils.replaceFragment
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.databinding.ActivityInitialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialActivity : AppCompatActivity(), FragmentAttachListenerInitial {

    private lateinit var binding: ActivityInitialBinding

    companion object {
        const val KEY_FLOW_INITIAL = "key_flow_initial";
        enum class FlowInitial { START, RETURN }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val flowInitial = FlowInitial.values()[bundle?.getInt(KEY_FLOW_INITIAL)!!]

        when(flowInitial){
            FlowInitial.START -> goMenuInicial()
            FlowInitial.RETURN -> {}
        }
    }

    override fun popBackStack() {
    }

    override fun goConfig() {
    }

    override fun goSplash() {
        val intent = Intent(this, SplashActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun goMenuInicial() {
        replaceFragment(MenuInicialFragment())
    }

    override fun goSenha() {
        replaceFragment(SenhaFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        replaceFragment(R.id.initial_manager_fragment, fragment)
    }
}