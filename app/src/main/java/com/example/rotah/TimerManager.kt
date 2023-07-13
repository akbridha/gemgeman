import android.os.CountDownTimer

object TimerManager {
    private var countdownTimer: CountDownTimer? = null
    private var timerCallback: TimerCallback? = null

    fun startTimer(totalTimeInMillis: Long) {
        countdownTimer?.cancel()

        countdownTimer = object : CountDownTimer(totalTimeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
//                val seconds = millisUntilFinished / 1000
//                val timeString = formatTime(seconds)
                timerCallback?.onTimerTick(millisUntilFinished)
            }

            override fun onFinish() {
                timerCallback?.onTimerFinish()
            }
        }

        countdownTimer?.start()
    }

    fun stopTimer() {
        countdownTimer?.cancel()
    }

    fun registerCallback(callback: TimerCallback) {
        timerCallback = callback
    }

    private fun formatTime(seconds: Long): String {
        val minutes = seconds / 60
        val secondsRemaining = seconds % 60
        return String.format("%02d:%02d", minutes, secondsRemaining)
    }

    interface TimerCallback {
        fun onTimerTick(millisUntilFinished : Long)
        fun onTimerFinish()
    }
}