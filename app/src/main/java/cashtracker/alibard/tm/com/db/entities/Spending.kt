package cashtracker.alibard.tm.com.db.entities

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "spending")
data class Spending(@PrimaryKey(autoGenerate = true) val id: Long = 0,
                    val type: String,
                    val price: Int,
                    val currency: String,
                    val description: String)