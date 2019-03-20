import laplacian.gradle.task.LaplacianGenerateExtension
group = "laplacian"
version = "1.0.0"
plugins {
    `maven-publish`
    `java-gradle-plugin`
    kotlin("jvm") version "1.3.10"
    id("laplacian.model.metamodel") version "1.0.0"
}
repositories {
    maven(url = "../mvn-repo/")
    maven(url = "https://raw.github.com/nabla-squared/mvn-repo/maven/")
    jcenter()
}
dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}
configure<LaplacianGenerateExtension> {
    model {
        files("src/main/resources")
    }
}
gradlePlugin {
    plugins {
        create("MetamodelPlugin") {
            id = "laplacian.model.metamodel"
            implementationClass = "laplacian.model.metamodel.gradle.MetamodelPlugin"
        }
    }
}
publishing {
    repositories {
        maven(url = "../mvn-repo/")
    }
    publications.create("mavenJava", MavenPublication::class.java) {
        from(components["java"])
    }
}