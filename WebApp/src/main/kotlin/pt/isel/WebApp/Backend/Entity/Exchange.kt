package pt.isel.WebApp.Backend.Entity

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
    val Value : Int,
    val Quantity : Int,
    var rate : Float,
    var Terminated : Boolean = false,
    val End_Date : Date
){
    constructor() : this(UUID(0L, 0L), UUID(0L, 0L), UUID(0L, 0L),UUID(0L, 0L), 0, 0,0.0f,false, Date())
}

