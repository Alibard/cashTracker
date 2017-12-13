package cashtracker.alibard.tm.com.ui.settings

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import cashtracker.alibard.tm.com.pojo.Setting
import cashtracker.alibard.tm.com.pojo.User
import cashtracker.alibard.tm.com.repository.firebase.FirebaseRootChild
import cashtracker.alibard.tm.com.repository.firebase.setUnicValue
import cashtracker.alibard.tm.com.repository.firebase.toRxCheckExist
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SettingViewModel : ViewModel() {
    val root = FirebaseDatabase.getInstance().reference
    var user : MutableLiveData<User> = MutableLiveData()
    var notif : Boolean = false
    fun fillSettings() {
        root.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).addListenerForSingleValueEvent(object  : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                user.value = (p0.getValue(User::class.java))
                notif = user.value!!.notification
            }

            override fun onCancelled(p0: DatabaseError?) {

            }

        })

    }

}