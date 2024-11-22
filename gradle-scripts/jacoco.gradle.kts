
apply(plugin = "jacoco")
val jacocoVersion = "0.8.10"

val coverageExcludes = listOf(
    // Data binding
    "android/databinding/**/*.class",
    "**/databinding/*Binding.class",
    "**/android/databinding/*Binding.class",
    "**/android/databinding/*",
    "**/androidx/databinding/*",
    "**/BR.*",
    // Android
    "**/R.class",
    "**/R2*.*",
    "**/R2.class",
    "**/R2$*.class",
    "**/R$*.class",
    "**/BuildConfig.*",
    "**/Manifest*.*",
    "**/*Test*.*",
    "android/**/*.*",
    "androidx/**/*.*",
    "com/android/**/*.*",
    "com/google/**/*.*",
    // Kotlin
    "**/*MapperImpl*.*",
    "**/*Component*.*",
    "**/*BR*.*",
    "**/*Companion*.*",
    "**/*Module*.*",
    "**/*Dagger*.*",
    "**/*Hilt*.*",
    "**/*Koin*.*",
    "**/*MembersInjector*.*",
    "**/*_MembersInjector.class",
    "**/*_Factory*.*",
    "**/*_Provide*Factory*.*",
    "**/*_ViewBinding*.*",
    // Sealed and data classes
    "**/*$Result.*",
    "**/*$Result$*.*",
    // Moshi adapters
    "**/*JsonAdapter.*",
    // Others
    "**/AutoValue_*.*",
    "**/*Directions$*",
    "**/*Directions.*",
    "**/*JavascriptBridge.class",
    "**/*Mock*.*"
)

tasks.register<JacocoReport>("allDebugCoverage") {
    group = "Reporting"
    description = "Generate overall Jacoco coverage report for the debug build."

    reports {
        html.required.set(true)
        xml.required.set(true)
    }

    val jClasses = subprojects.map { "${it.buildDir}/intermediates/javac/debug/classes" }
    val kClasses = subprojects.map { "${it.buildDir}/tmp/kotlin-classes/debug" }

    val javaClasses = jClasses.map { fileTree(it) { exclude(coverageExcludes) } }
    val kotlinClasses = kClasses.map { fileTree(it) { exclude(coverageExcludes) } }

    classDirectories.setFrom(files(javaClasses + kotlinClasses))

    val sources = subprojects.flatMap {
        listOf(
            "${it.projectDir}/src/main/java",
            "${it.projectDir}/src/main/kotlin",
            "${it.projectDir}/src/debug/java",
            "${it.projectDir}/src/debug/kotlin"
        )
    }
    sourceDirectories.setFrom(files(sources))

    val allReportFiles = fileTree(projectDir) {
        include(
            "**/build/jacoco/*UnitTest.exec",
            "**/build/outputs/code_coverage/**/*.ec",
            "**/build/outputs/unit_test_code_coverage/**/*.exec"
        )
    }
    executionData.setFrom(files(allReportFiles))
}

afterEvaluate {
    subprojects.forEach { p ->
        tasks.named("allDebugCoverage") {
            dependsOn("${p.name}:testDebugUnitTest")
            dependsOn("${p.name}:connectedDebugAndroidTest")
        }
    }
}

subprojects {
    apply(plugin = "jacoco")

    afterEvaluate {
        tasks.register<JacocoReport>("debugCoverage") {
            group = "Reporting"
            description = "This task is not creating the report. It is only creating the exec and ec files."
            dependsOn("testDebugUnitTest")
        }
    }

    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jacoco") {
                useVersion(jacocoVersion)
            }
        }
    }
}