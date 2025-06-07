plugins{
    java
    application
}

application {
    mainClass.set("Main")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.24.2")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.codehaus.groovy:groovy-json:3.0.20")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")


}

tasks.test{
    useJUnitPlatform()
}