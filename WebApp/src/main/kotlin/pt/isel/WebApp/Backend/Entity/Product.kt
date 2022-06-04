package pt.isel.WebApp.Backend.Entity

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product (
    @Id
    val id : UUID = UUID.randomUUID(),
    var name : String?,
    var description : String?,
    var price : Int,
    var rate : Float?,
    val sid : UUID
    ){
    constructor() : this(UUID(0L, 0L), "", "", 0, 0.0f, UUID(0L, 0L))
}
