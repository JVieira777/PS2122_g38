package pt.isel.WebApp.lib.midlewares.enums

enum class ValidationType(input: String) {

    ANNONYMOUS ("Online"),
    VERIFIED_TOKEN ( "Verified");

    val value: String = input

}