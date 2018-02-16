package cashtracker.alibard.tm.com.pojo

import cashtracker.alibard.tm.com.db.entities.Spending


class Spending(var price: String = "", var currency: String = "", var type: String = "", var description: String = "",var id:Int = 0){
    companion object {
        fun fromDao(daoModel : Spending):cashtracker.alibard.tm.com.pojo.Spending{
            return Spending(daoModel.price.toString(),
                    daoModel.currency,
                    daoModel.type,
                    daoModel.description,
                    daoModel.id.toInt())
        }
    }
}