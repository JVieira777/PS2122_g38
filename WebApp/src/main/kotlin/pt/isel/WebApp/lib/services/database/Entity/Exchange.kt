package pt.isel.WebApp.lib.services.database.Entity

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "exchange")
data class Exchange (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long ,
    val client_id : UUID,
    val seller_id : UUID,
    val pid : UUID,
    val value : Int,
    val quantity : Int,
    var completed : Boolean = false,
    val end_Date : Date?
){
    constructor() : this(
        0,
        UUID(0L, 0L),
        UUID(0L, 0L),
        UUID(0L, 0L),
        0,
        0,
        false,
        Date())
    constructor(client_id: UUID, seller_id : UUID, productID: UUID , value: Int, quantity: Int ,end_date: Date):
            this(
               0,
                client_id,
                seller_id,
                productID,
                value,
                quantity,
                false,
                end_date
            )
}

