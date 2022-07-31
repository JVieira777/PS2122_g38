package pt.isel.WebApp.lib.services.database.Entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
@Entity
@Table(name = "refundform")
data class RefundForm (
    @Id
    val id : Long,
    val client_id : UUID,
    val exchange_id : Long,
    var description : String
){
    constructor() : this(
        -1,
        UUID(0L, 0L),
       -1,
        ""
    )
}