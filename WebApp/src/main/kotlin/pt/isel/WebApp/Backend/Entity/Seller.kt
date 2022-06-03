package pt.isel.WebApp.Backend.Entity

import java.math.BigInteger
import java.util.*
import javax.persistence.*
@Entity
@Table(name = "seller")
data class Seller (
    @Id
    val id : UUID = UUID.randomUUID(),
    val Name : String?,
    var contry : String?,
    var description : String?,
    var rate : Float?,
    var Terminated : Boolean = false,
    val uid : UUID
){
    constructor() : this(UUID(0L, 0L), "", "", "", 0.0f,false, UUID(0L, 0L))
}
