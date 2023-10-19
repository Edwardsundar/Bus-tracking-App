package com.demo.bustracking

object Constant {
    val locations = listOf(
        Location(
            latitude = "11.043487458971315",
            longitude = "76.86402232415257",
            name = "70 (Gandhipuram to marudhamali)"
        ),
        Location(
            latitude = "11.01647393963337",
            longitude = "76.96898864796009",
            name = "70 (marudhamali to Gandhipuram)"
        ),
        Location(
            latitude = "11.043487458971315",
            longitude = "76.86402232415257",
            name = "S26 (Gandhi puram to marudha mali)"
        ),
        Location(
            latitude = "11.01647393963337",
            longitude = "76.96898864796009",
            name = "S26 (marudha mali to Gandhi puram)"
        ),
        Location(
            latitude = "10.994633376049718",
            longitude = "76.95979399133644",
            name = "46 (marudha mali to Town Hall)",
        ),
        Location(
            latitude = "11.043487458971315",
            longitude = "76.86402232415257",
            name = "46 (Town Hall to marudha mali)"
        ),
        Location(
            latitude = "11.003278534308238",
            longitude = "77.04994615799416",
            name = "1C (Vadavalli to Ondipudur)"
        ),
        Location(
            latitude = "11.027824488196298",
            longitude = "76.9059129007831",
            name = "1C (Ondipudur to Vadavali)"
        ),
    )
}
data class Location(
    val latitude : String ,
    val longitude : String ,
    val name : String = ""
)

