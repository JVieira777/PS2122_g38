package pt.isel.WebApp.Entity

import java.math.BigInteger
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "product")
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : UUID,
    var product_name : String?,
    var product_description : String?,
    var price : Int,
    var Product_rate : Float?,
    val sid : UUID
        )
