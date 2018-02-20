package cashtracker.alibard.tm.com.app

import android.support.v4.util.Preconditions.checkState
import android.util.Log
import cashtracker.alibard.tm.com.db.entities.SpendingType
import cashtracker.alibard.tm.com.db.entities.StructureSettings
import cashtracker.alibard.tm.com.init_settings.BaseType
import cashtracker.alibard.tm.com.repository.local.LocalRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class Initializer(val repository: LocalRepository) {
    init {
        checkStatusState()
    }

    private fun checkStatusState() {
        repository.getInitialSettings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isEmpty()) {
                        initialSetup()
                    }
                }, {
                    Log.d("tag", "" + it.message)
                })
    }

    companion object {
        fun initialize(repository: LocalRepository): Initializer {
            return Initializer(repository)
        }

    }

    private fun initialSetup() {

        repository.insertSettings(StructureSettings(1, true))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Settings","done")
                }, {
                    Log.d("Settings",""+it.message)
                })


        repository.insertBaseType(fillType())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("Type","done")
                }, {
                    Log.d("Type",""+it.message)
                })

    }

    private fun fillType(): List<SpendingType> {
        val listOfType: MutableList<SpendingType> = mutableListOf()
        BaseType.values().forEach {
            listOfType.add(SpendingType(it.ordinal.toLong(), it.value, 1, 1))
        }
        return listOfType
    }
}
