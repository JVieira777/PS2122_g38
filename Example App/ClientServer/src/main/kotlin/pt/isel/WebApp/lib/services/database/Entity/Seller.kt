package pt.isel.WebApp.lib.services.database.Entity

import java.util.*
import javax.persistence.*
@Entity
@Table(name = "seller")
data class Seller (
    @Id
    val id : UUID = UUID.randomUUID(),
    val name : String?,
    var country : String?,
    var description : String?,
    var rate : Float?,
    val wallet : String,
    var terminated : Boolean = false,
    val uid : UUID
){
    constructor() : this(UUID(0L, 0L), "", "", "", 0.0f,"",false, UUID(0L, 0L))
}
