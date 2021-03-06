package com.example.madcamp_2nd.local_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_two.*
import com.facebook.login.LoginManager
import com.facebook.appevents.codeless.internal.ViewHierarchy.setOnClickListener
import android.R
import android.app.Activity
import android.app.AppOpsManager
import android.app.AppOpsManager.MODE_ALLOWED
import android.app.AppOpsManager.OPSTR_GET_USAGE_STATS
import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.provider.Settings
import android.util.Log
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*


class MainActivityTwo : AppCompatActivity() {

    private val REQUEST_CODE_PERMISSIONS = 101
    private val REQUIRED_PERMISSIONS = arrayOf("android.permission.CAMERA")
    //lateinit var UsageState: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.madcamp_2nd.R.layout.activity_main_two)

//        setSupportActionBar(toolbar)
//        Fritz.configure(this, "417e5b76c3d64043ae4ed82820985f08");
//        set adapter for view pager
        val adapter = MainAdapter(supportFragmentManager)
        view_pager.adapter = adapter

        // sync view pager with tabs
        tab.setupWithViewPager(view_pager)
        setPermissions()

        // 로그아웃 구현
        btn_facebook_logout.setOnClickListener {
            LoginManager.getInstance().logOut()
            //var startintent = Intent(this@MainActivityTwo, MainActivity::class.java)
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    //뒤로가기 한 번 눌렸을 때 이상한 짓 못하게 하려고
    private var time: Long = 0
    override fun onBackPressed() {
        if (System.currentTimeMillis() - time >= 2000) {
            time = System.currentTimeMillis()
            Toast.makeText(applicationContext, "뒤로 버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show()
        } else if (System.currentTimeMillis() - time < 2000) {
            finish()
        }
    }





    /********
    //default 유저 왔을시
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //val checktool = data!!.getExtras()!!.getInt("CHECK")
    }
*********/
/************************************************음 하다가 실패
    private fun getAppUsageStats(): MutableList<UsageStats> {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)      // 현재로부터 하루 전의 시간을 가져온다 (쿼리에 쓰려고)
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager      // UsageStatsManager 객체를 가져옴

        val queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, cal.timeInMillis, System.currentTimeMillis())     // 인자와 함께 쿼리한다.

        return queryUsageStats
    }
*************************************************/

    /**
     * setPermissions()
     *      setting permissions on contacts
     */
    private fun setPermissions() {
        if (ContextCompat.checkSelfPermission(
                this@MainActivityTwo,
                Manifest.permission.READ_CONTACTS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // manually defined but don't know
            val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@MainActivityTwo,
                    Manifest.permission.READ_CONTACTS
                )
            ) {
                // description here if needed.
            } else {
                // No explanation needed, we can request the permission.
                // TODO
                ActivityCompat.requestPermissions(
                    this@MainActivityTwo,
                    arrayOf(Manifest.permission.READ_CONTACTS),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS
                )
            }
        } else {
            // Permission has already been granted
        }

        if (allPermissionsGranted()) {

        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {

        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
//                startCamera()
            } else {
                Toast.makeText(this, "Permissions not granted by the user.", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted(): Boolean {

        for (permission in REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

}

/*
class MainActivity : AppCompatActivity() {

    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, ContactFragment()).commit()
        // set bottom navigation bar
        setBottomNavBar()
        // 기능
        setPermissions()
    }

    /**
     *  setBottomNavBar()
     *      configure views for navigation bar
     */
    private fun setBottomNavBar() {
        toolbar = supportActionBar!!
        toolbar.title = "Contacts"
        // listen selection
        bottom_navigation.setOnNavigationItemSelectedListener {
                item ->
            when (item.itemId) {
                R.id.navigation_contacts -> {
                    val fragment = ContactFragment()
                    toolbar.title = "Contacts"
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
                }
                R.id.navigation_gallery -> {
                    val fragment = GalleryFragment()
                    toolbar.title = "Gallery"
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
                }
                R.id.navigation_custom -> {
                    val fragment = CustomFragment()
                    toolbar.title = "Custom"
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
                }
            }
            false
        }
    }
}
*/