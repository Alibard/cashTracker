package cashtracker.alibard.tm.com.repository.firebase

import com.google.firebase.database.*
import io.reactivex.Single


fun FirebaseRootChild(name: String) = FirebaseDatabase.getInstance().reference.child(name)

fun DatabaseReference.setUnicValue(value: Any) {
    push().setValue(value)
}

fun DatabaseReference.toRxCheckExist() : Single<Boolean> {
    return Single.create<Boolean>{emiter ->
        addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {
                emiter.onError(p0?.toException())
            }

            override fun onDataChange(p0: DataSnapshot?) {
                if(p0?.value==null){
                    emiter.onSuccess(false)
                }else{
                    emiter.onSuccess(true)
                }
            }

        })
    }
}
