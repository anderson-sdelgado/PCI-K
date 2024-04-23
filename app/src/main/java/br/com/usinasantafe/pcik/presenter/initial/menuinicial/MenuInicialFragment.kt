package br.com.usinasantafe.pcik.presenter.initial.menuinicial

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.usinasantafe.pcpk.R

class MenuInicialFragment : Fragment() {

    companion object {
        fun newInstance() = MenuInicialFragment()
    }

    private lateinit var viewModel: MenuInicialViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_inicial, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuInicialViewModel::class.java)
        // TODO: Use the ViewModel
    }

}