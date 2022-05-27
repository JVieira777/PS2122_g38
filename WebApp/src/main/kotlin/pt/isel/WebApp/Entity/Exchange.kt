package pt.isel.WebApp.Entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "exchange")
data class Exchange (
    @Id
    val id : UUID = UUID.randomUUID(),
    val uidA : UUID,
    val uidB : UUID,
    val pid : UUID,
    val Exchange_Value : Int,
    val Exchange_Quantity : Int,
    var Exchange_rate : Float,
    val End_Date : Date
){
    constructor() : this(UUID(0L, 0L), UUID(0L, 0L), UUID(0L, 0L),UUID(0L, 0L), 0, 0,0.0f, Date())
}

