package cashtracker.alibard.tm.com.model

import android.util.Log
import android.view.View


data class User(val name: String, val email: String,val photo:String){
    fun locClick(view: View){
        Log.d("User","User")
    }
}