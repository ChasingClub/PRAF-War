/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Test-CommandExcuting") {
    container(displayName = "Test-CommandExcuting", image = "gradlew") { 
		shellScript { 
        	
            mountDir = "/mnt/mySpace"
        	workDir = "/mnt/mySpace/work"
        	user = "root"
            entrypoint("/bin/sh")
            args("-c", "echo \"Project content\" && ls ./ ")
    }
            
        }
    }
}
