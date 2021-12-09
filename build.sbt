name := "AdventOfCode"

version := "0.1"

scalaVersion := "3.0.0"

idePackagePrefix := Some("lt.markvl.adventofcode")

// Runtime dependencies
libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.4",
  "org.scalactic" %% "scalactic" % "3.2.10",
  "org.typelevel" %% "cats-core" % "2.7.0",
  "org.typelevel" %% "cats-effect" % "3.3.0",
  "dev.optics" %% "monocle-core" % "3.0.0",
  "dev.optics" %% "monocle-macro" % "3.0.0"
)

// Test dependencies
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.9",
  "dev.optics" %% "monocle-macro" % "3.0.0"
).map(_ % Test)

fork := true
javaOptions ++= Seq(
  "-XX:+UseG1GC",
  "-Xmx8192M"
)
