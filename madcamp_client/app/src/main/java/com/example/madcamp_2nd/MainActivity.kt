package com.example.madcamp_2nd

import android.content.Intent

import android.content.Context
import com.facebook.login.widget.LoginButton
import com.facebook.CallbackManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlinx.android.synthetic.main.activity_main_two.*
import java.util.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.madcamp_2nd.fb_app.MainActivityTwo


class MainActivity : AppCompatActivity() {
    private var mContext: Context? = null
    private var btn_facebook_login: LoginButton? = null
    private var mLoginCallback: LoginCallback? = null
    private var mCallbackManager: CallbackManager? = null

    val REQUEST_CODE_RELOGIN = 224

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = getApplicationContext()
        mCallbackManager = CallbackManager.Factory.create()
        mLoginCallback = LoginCallback()

        btn_facebook_login = findViewById(R.id.btn_facebook_login) as LoginButton
        btn_facebook_login!!.setPermissions(Arrays.asList("public_profile", "email"))
        btn_facebook_login!!.registerCallback(mCallbackManager, mLoginCallback)

        // 첫 화면에서 로그인 여부 확인
        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired
        if(isLoggedIn){
            val intent = Intent(this, MainActivityTwo::class.java)
            startActivity(intent)
        }

        //페이스북 로그인 없이 사용
        default_login_button.setOnClickListener {
            var startintent = Intent(this@MainActivity, com.example.madcamp_2nd.local_app.MainActivityTwo::class.java)
            Toast.makeText(this,"로그인 되지 않았습니다."+ "\n"+"앞으로의 행동은 저장되지 않습니다.", Toast.LENGTH_SHORT).show()
            //startintent.putExtra("CHECK", 1234)
            startActivity(startintent)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        mCallbackManager!!.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE_RELOGIN){
            Toast.makeText(this,"로그아웃되셨습니다", Toast.LENGTH_SHORT).show()
        } else {
            var startintent = Intent(this@MainActivity, com.example.madcamp_2nd.fb_app.MainActivityTwo::class.java)
            startActivityForResult(startintent, REQUEST_CODE_RELOGIN)

        }
    }
}