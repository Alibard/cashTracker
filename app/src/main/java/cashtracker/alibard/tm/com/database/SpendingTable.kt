package cashtracker.alibard.tm.com.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


@Entity(tableName = "tasks")
class SpendingTable @JvmOverloads constructor(
        @ColumnInfo(name = "price") var title: String = "",
        @ColumnInfo(name = "currency") var currency: String = "",
        @ColumnInfo(name = "type") var type: String = "",
        @ColumnInfo(name = "description") var description: String = "",
        @PrimaryKey @ColumnInfo(name = "entryid") var id: String = UUID.randomUUID().toString()
) {

}