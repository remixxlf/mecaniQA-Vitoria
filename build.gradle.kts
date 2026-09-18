plugins {
    id("java")
    id("application")
}

group = "br.com.mecaniqa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets {
    named("main") {
        java.setSrcDirs(listOf("src"))
    }
}

application {
    mainClass.set("main.Main")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}
