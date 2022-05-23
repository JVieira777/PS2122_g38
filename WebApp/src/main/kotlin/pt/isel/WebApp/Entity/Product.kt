package pt.isel.WebApp.Entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table
data class Product (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : BigInteger,
    var product_name : String?,
    var product_description : String?,
    var price : Int,
    var Product_rate : Float?,
    val sid : BigInteger
        )
