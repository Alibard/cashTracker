package cashtracker.alibard.tm.com.ui.settings

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import cashtracker.alibard.tm.com.pojo.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SettingViewModel : ViewModel() {
    val root = FirebaseDatabase.getInstance().reference
    var user : MutableLiveData<User> = MutableLiveData()
    var notif = ObservableBoolean()
    fun fillSettings() {
        root.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).addListenerForSingleValueEvent(object  : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                user.value = (p0.getValue(User::class.java))
                notif.set( user.value!!.notification)
            }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })

    }

}