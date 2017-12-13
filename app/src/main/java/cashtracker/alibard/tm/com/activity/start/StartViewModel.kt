package cashtracker.alibard.tm.com.activity.start

import android.arch.lifecycle.ViewModel
import android.util.Log
import cashtracker.alibard.tm.com.pojo.Setting
import cashtracker.alibard.tm.com.pojo.User
import cashtracker.alibard.tm.com.repository.firebase.FirebaseRootChild
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class StartViewModel : ViewModel() {

    val root = FirebaseDatabase.getInstance().reference
    fun saveUser() {
        val currentUser = FirebaseRootChild("users").child(FirebaseAuth.getInstance().currentUser!!.uid)
        currentUser.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (!p0.exists()) {
                    with(FirebaseAuth.getInstance().currentUser!!) {
                        Log.d("tag", "registred")
                        currentUser.setValue(User(displayName ?: "", email ?: "", phoneNumber ?: "", photoUrl.toString(),true))
                    }
                }
            }

        })
    }

}