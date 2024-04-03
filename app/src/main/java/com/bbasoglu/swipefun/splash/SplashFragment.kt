package com.bbasoglu.swipefun.splash

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bbasoglu.swipefun.databinding.FragmentSplashBinding
import com.bbasoglu.swipefun.main.MainActivity
import com.bbasoglu.swipefun.uimodule.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    override val viewModel by activityViewModels<SplashActivityViewModel>()

    private var countDownTimerContinue: CountDownTimer? = null

    private var splashCompleted = false

    override fun getViewBinding(container: ViewGroup?): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startCountDownTimer()
    }

    private fun startCountDownTimer() {
        countDownTimerContinue?.cancel()
        countDownTimerContinue = null
        countDownTimerContinue = object : CountDownTimer(
            TOTAL_TIME,
            COUNT_DOWN_INTERVAL
        ) {
            override fun onFinish() {
                splashCompleted()
            }

            override fun onTick(millisUntilFinished: Long) {
                val resultantFloat: Float =
                    (TOTAL_TIME - millisUntilFinished).toFloat() / TOTAL_TIME
                val resultantInt: Int = (resultantFloat * TOTAL_SECONDS).toInt()
                if (resultantInt > 5) {
                    splashCompleted()
                }
            }
        }.start()
    }

    override fun onDestroy() {
        countDownTimerContinue?.cancel()
        countDownTimerContinue = null
        super.onDestroy()
    }

    fun splashCompleted() {
        countDownTimerContinue?.cancel()
        countDownTimerContinue = null
        if (splashCompleted) {
            return
        }
        splashCompleted = true
        activity?.runOnUiThread {
            navigateToNextScreen()
        }
    }

    private fun navigateToNextScreen() {
        startActivity(Intent(requireActivity(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        })
        requireActivity().finish()
    }

    companion object {
        private const val TOTAL_SECONDS = 1 // seconds for count down
        private const val COUNT_DOWN_INTERVAL = 1000L // interval for count down DO NOT CHANGE
        private const val TOTAL_TIME =
            TOTAL_SECONDS * COUNT_DOWN_INTERVAL // total time that counted down
    }
}
