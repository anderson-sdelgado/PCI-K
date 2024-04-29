package br.com.usinasantafe.pcik.presenter.initial.senha

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import br.com.usinasantafe.pcik.presenter.initial.FragmentAttachListenerInitial
import br.com.usinasantafe.pcik.utils.BaseFragment
import br.com.usinasantafe.pcik.utils.onBackPressed
import br.com.usinasantafe.pcik.utils.showGenericAlertDialog
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.databinding.FragmentSenhaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SenhaFragment : BaseFragment<FragmentSenhaBinding>(
    R.layout.fragment_senha,
    FragmentSenhaBinding::bind
) {

    private val viewModel: SenhaViewModel by viewModels()
    private var fragmentAttachListenerInitial: FragmentAttachListenerInitial? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()
        setListener()

    }

    private fun observeState() {
        viewModel.uiLiveData.observe(viewLifecycleOwner) { state ->
            handleStateChange(state)
        }
    }

    private fun setListener() {
        binding.buttonOkSenha.setOnClickListener {
            viewModel.verificarSenha(binding.editTextSenha.text.toString())
        }
        binding.buttonCancSenha.setOnClickListener {
            fragmentAttachListenerInitial?.goMenuInicial()
        }
    }

    private fun handleStateChange(state: SenhaFragmentState) {
        when (state) {
            is SenhaFragmentState.Error -> handleErrorLogin()
            is SenhaFragmentState.Success -> handleSuccessLogin()
        }
    }

    private fun handleErrorLogin() {
        showGenericAlertDialog(getString(R.string.texto_senha_invalida), requireContext())
    }

    private fun handleSuccessLogin() {
        fragmentAttachListenerInitial?.goConfig()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAttachListenerInitial) {
            fragmentAttachListenerInitial = context
        }
        onBackPressed {}
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentAttachListenerInitial = null
    }

}