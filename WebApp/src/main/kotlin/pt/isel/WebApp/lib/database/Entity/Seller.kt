package pt.isel.WebApp.lib.database.Entity

import java.util.*
import javax.persistence.*
@Entity
@Table(name = "seller")
data class Seller (
    @Id
    val id : UUID = UUID.randomUUID(),
    val seller_Name : String?,
    var contry : String?,
    var seller_description : String?,
    var seller_rate : Float?,
    val uid : UUID
){
    constructor() : this(UUID(0L, 0L), "", "", "", 0.0f, UUID(0L, 0L))
}
