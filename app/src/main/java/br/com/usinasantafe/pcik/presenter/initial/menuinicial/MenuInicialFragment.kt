package br.com.usinasantafe.pcik.presenter.initial.menuinicial

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.media3.test.utils.BuildConfig
import br.com.usinasantafe.pcik.presenter.initial.FragmentAttachListenerInitial
import br.com.usinasantafe.pcik.utils.BaseFragment
import br.com.usinasantafe.pcik.utils.CustomAdapter
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.databinding.FragmentMenuInicialBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MenuInicialFragment : BaseFragment<FragmentMenuInicialBinding>(
    R.layout.fragment_menu_inicial,
    FragmentMenuInicialBinding::bind,
) {

    private val viewModel: MenuInicialViewModel by viewModels()
    private var fragmentAttachListenerInitial: FragmentAttachListenerInitial? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        viewList()
        version()

    }

    private fun observeState() {
    }

    private fun viewList() {

        val opcaoMenu = listOf(
            "APONTAMENTO",
            "CONFIGURAÇÃO",
            "SAIR",
        )

        val listAdapter = CustomAdapter(opcaoMenu).apply {
            onItemClick = { text, _ ->
                when(text){
                    "APONTAMENTO" -> eventApont()
                    "CONFIGURAÇÃO" -> eventConfig()
                    "SAIR" -> eventSair()
                }
            }
        }
        binding.listMenuInicial.run {
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }

    private fun eventApont() {
    }

    private fun eventConfig() {
        Log.i("PCI", "CHEGOU AKI")
        fragmentAttachListenerInitial?.goSenha()
    }

    private fun eventSair() {
    }

    private fun version() {
        val pInfo = context?.packageName?.let {
            this.context?.packageManager?.getPackageInfo(
                it, 0
            )
        }
        binding.textViewPrincipal.text = "PRINCIPAL - V " + pInfo?.versionName
    }

}