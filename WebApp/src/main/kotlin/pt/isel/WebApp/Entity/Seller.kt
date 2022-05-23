package pt.isel.WebApp.Entity

import java.math.BigInteger
import javax.persistence.*

@Entity
@Table
data class Seller (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : BigInteger,
    val seller_Name : String?,
    var contry : String?,
    var seller_description : String?,
    var seller_rate : Float?,
    val uid : BigInteger,
)
