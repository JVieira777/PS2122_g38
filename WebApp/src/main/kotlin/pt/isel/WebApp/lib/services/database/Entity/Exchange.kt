package pt.isel.WebApp.lib.services.database.Entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "exchange")
data class Exchange (
    @Id
    val id : UUID = UUID.randomUUID(),
    val client_id : UUID,
    val seller_id : UUID,
    val pid : UUID,
    val value : Int,
    val quantity : Int,
    var completed : Boolean = false,
    val end_Date : Date
){
    constructor() : this(
        UUID(0L, 0L),
        UUID(0L, 0L),
        UUID(0L, 0L),
        UUID(0L, 0L),
        0,
        0,
        false,
        Date())
    constructor(client_id: UUID, seller_id : UUID, productID: UUID , value: Int, quantity: Int ,end_date: Date):
            this(
                UUID(0L, 0L),
                client_id,
                seller_id,
                productID,
                value,
                quantity,
                false,
                end_date
            )
}

