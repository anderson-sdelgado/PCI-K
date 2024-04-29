package br.com.usinasantafe.pcik.presenter.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import br.com.usinasantafe.pcik.presenter.initial.InitialActivity
import br.com.usinasantafe.pcik.presenter.initial.InitialActivity.Companion.FlowInitial
import br.com.usinasantafe.pcik.presenter.initial.InitialActivity.Companion.KEY_FLOW_INITIAL
import br.com.usinasantafe.pcik.utils.PointerStart
import br.com.usinasantafe.pcpk.R
import br.com.usinasantafe.pcpk.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler.post(runnable)
        observeState()

    }

    override fun onResume() {
        startEvents()
        super.onResume()
    }

    private fun observeState() {
        viewModel.uiLiveData.observe(this) {
                state -> handleStateChange(state)
        }
    }

    private fun startEvents() {
        viewModel.startSent()
    }

    private val runnable: Runnable
        get() = Runnable {
            binding.splashImg.animate()
                .alpha(0f)
                .setDuration(2000)
                .withEndAction{
                    binding.splashImg.animate()
                        .alpha(1f).duration = 2000
                }
            handler.postDelayed(runnable, 4000)
        }

    private fun handleStateChange(state: SplashState){
        when(state){
            is SplashState.CheckStartAPP -> handleCheckStartAPP(state.pointerStart)
        }
    }

    private fun handleCheckStartAPP(checkStartAPP: PointerStart){
        when(checkStartAPP){
            PointerStart.MENUINICIAL -> goMenuInicial(FlowInitial.START)
            PointerStart.MENUAPONT -> {}
        }
    }

    private fun goMenuInicial(flowInitial: FlowInitial){
        val intent = Intent(this, InitialActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        intent.apply {
            putExtra(KEY_FLOW_INITIAL, flowInitial.ordinal)
        }
        startActivity(intent)
    }


}