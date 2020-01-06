package com.example.madcamp_2nd.fb_app.tab3_fb

import android.app.AppOpsManager
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp_2nd.R
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.android.synthetic.main.custom_flower.*

class CustomFlowerActivity : AppCompatActivity() {

    lateinit var tvUsageStats: TextView
    var time : Int ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_flower)
//        tvUsageStats = findViewById(R.id.UsageState)

//        flower1 = findViewById(R.id.flower1) as ImageView

        //권한 없을시 세팅 들어가서 설정하게 해주기
        if (!checkForPermission()) {
//            Log.i(TAG, "The user may not allow the access to apps usage. ")
            Toast.makeText(this, "앱을 쓰시려면 권한 설정에 동의해주십시오." + "\n" + "Settings > Security > Apps 에서 설정할 수 있습니다.", Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        } else {
            // 권한 생겼으니 실행
            //showUsageStats()
            val disappear = AnimationUtils.loadAnimation(this, R.anim.anim_disappear)
            time = getYouTubeTime()

            when (time){
                0 ->  Toast.makeText(this, "Congratuation!!!", Toast.LENGTH_LONG).show()
                1 ->  padle1.startAnimation(disappear)
                2 -> {
                    padle1.startAnimation(disappear)
                    padle2.startAnimation(disappear)
                }
                3 -> {
                    padle1.startAnimation(disappear)
                    padle2.startAnimation(disappear)
                    padle3.startAnimation(disappear)
                }
                4 -> {
                    padle1.startAnimation(disappear)
                    padle2.startAnimation(disappear)
                    padle3.startAnimation(disappear)
                    padle4.startAnimation(disappear)
                }
                5 -> {
                    padle1.startAnimation(disappear)
                    padle2.startAnimation(disappear)
                    padle3.startAnimation(disappear)
                    padle4.startAnimation(disappear)
                    padle5.startAnimation(disappear)
                }
                6 -> {
                    padle1.startAnimation(disappear)
                    padle2.startAnimation(disappear)
                    padle3.startAnimation(disappear)
                    padle4.startAnimation(disappear)
                    padle5.startAnimation(disappear)
                    padle6.startAnimation(disappear)
                }
                7 -> {
                    padle1.startAnimation(disappear)
                    padle2.startAnimation(disappear)
                    padle3.startAnimation(disappear)
                    padle4.startAnimation(disappear)
                    padle5.startAnimation(disappear)
                    padle6.startAnimation(disappear)
                    padle7.startAnimation(disappear)
                }
                else -> {
                    padle1.startAnimation(disappear)
                    padle2.startAnimation(disappear)
                    padle3.startAnimation(disappear)
                    padle4.startAnimation(disappear)
                    padle5.startAnimation(disappear)
                    padle6.startAnimation(disappear)
                    padle7.startAnimation(disappear)
                    padle8.startAnimation(disappear)
                    Toast.makeText(this, "심각하군요!!!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun getYouTubeTime(): Int? {
        var usageStatsManager: UsageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        var cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        var queryUsageStats: List<UsageStats> = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, cal.timeInMillis, System.currentTimeMillis())

        var longspendtime: Long ? = null
        var spendminute: Int ? =null

        for(i in 0..queryUsageStats.size -1){

            if (queryUsageStats.get(i).packageName == "com.google.android.youtube") {
                longspendtime = queryUsageStats.get(i).totalTimeInForeground
                break
            }
        }
        spendminute = longspendtime!!.toInt() / 6000
        return spendminute
    }

    /***************
    private fun showUsageStats() {
        var usageStatsManager: UsageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        var cal: Calendar = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_MONTH, -1)
        var queryUsageStats: List<UsageStats> = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, cal.timeInMillis, System.currentTimeMillis())
        var stats_data: String = ""
        for (i in 0..queryUsageStats.size - 1) {
            stats_data =
                stats_data + "Package Name : " + queryUsageStats.get(i).packageName + "\n\n" +
                        "Last Time Used : " + convertTime1(queryUsageStats.get(i).lastTimeUsed) + "\n" +
                        "Describe Contents : " + queryUsageStats.get(i).describeContents() + "\n" +
                        "First Time Stamp : " + convertTime1(queryUsageStats.get(i).firstTimeStamp) + "\n" +
                        "Last Time Stamp : " + convertTime1(queryUsageStats.get(i).lastTimeStamp) + "\n" +
                        "Total Time in Foreground : " + convertTime2(queryUsageStats.get(i).totalTimeInForeground) + "\n\n"
        }
        tvUsageStats.setText(stats_data)
    }
    *******************/

//    //시간 바꿔주기
//    private fun convertTime1(lastTimeUsed: Long): String {
//        var date: Date = Date(lastTimeUsed)
//        var format: SimpleDateFormat = SimpleDateFormat("dd/ MM/ yyyy hh:mm a", Locale.ENGLISH)
//        return format.format(date)
//    }

//    //시간 바꿔주기2
//    private fun convertTime2(lastTimeUsed: Long): String {
//        var date: Date = Date(lastTimeUsed)
//        var format: SimpleDateFormat = SimpleDateFormat("hh:mm", Locale.ENGLISH)
//        return format.format(date)
//    }

    //어플 시간 보기 권한 체크
    fun checkForPermission(): Boolean {
        val appOps = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = appOps.unsafeCheckOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, android.os.Process.myUid(), packageName)
        return mode == AppOpsManager.MODE_ALLOWED
    }
}