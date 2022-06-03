package pt.isel.WebApp.lib.database.Entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product (
    @Id
    val id : UUID = UUID.randomUUID(),
    var product_name : String?,
    var product_description : String?,
    var price : Int,
    var Product_rate : Float?,
    val sid : UUID
    ){
    constructor() : this(UUID(0L, 0L), "", "", 0, 0.0f, UUID(0L, 0L))
}
