package cashtracker.alibard.tm.com.activity

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import android.content.Intent
import android.widget.Toast
import cashtracker.alibard.tm.com.cashtracker.R


class StartActivity : AppCompatActivity() {
    val RC_SIGN_IN = 123
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            startActivity(Intent(this,MainActivity::class.java))
        } else {
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setTheme(R.style.GreenTheme)
                            .setAvailableProviders(
                                    arrayListOf( AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                             AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
                                             AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                            .setIsSmartLockEnabled(false)
                            .build(),
                    RC_SIGN_IN)
//            startActivityForResult(
//                    // Get an instance of AuthUI based on the default app
//                    AuthUI.getInstance().createSignInIntentBuilder().build(),
//                    RC_SIGN_IN)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            // Successfully signed in
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this,"Loginetd",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,MainActivity::class.java))
                finish()
                return
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Toast.makeText(this,"sign_in_cancelled",Toast.LENGTH_SHORT).show()
//                    showSnackbar(R.string.sign_in_cancelled)
                    return
                }

                if (response.errorCode == ErrorCodes.NO_NETWORK) {
                    Toast.makeText(this,"no_internet_connection",Toast.LENGTH_SHORT).show()
//                    showSnackbar(R.string.no_internet_connection)
                    return
                }

                if (response.errorCode == ErrorCodes.UNKNOWN_ERROR) {
                    Toast.makeText(this,"unknown_error",Toast.LENGTH_SHORT).show()
//                    showSnackbar(R.string.unknown_error)
                    return
                }
            }
            Toast.makeText(this,"unknown_sign_in_response",Toast.LENGTH_SHORT).show()
//            showSnackbar(R.string.unknown_sign_in_response)
        }
    }
}
