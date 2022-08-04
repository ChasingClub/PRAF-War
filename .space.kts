/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

@file:DependsOn("pinont.server.minigame")

import pinont.server.minigame.*

job("Get example.com") {
    container(image = "openjdk:11") {
        kotlinScript {
            val client = OkHttpClient()
            val request = Request.Builder().url("http://example.com").build()
            val response = client.newCall(request).execute()
            println(response)
        }
    }
}
