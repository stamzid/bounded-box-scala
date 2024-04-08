name := "bounded-box"

organization := "stamzid"

version := "1.0.0"

scalaVersion := "3.3.1"

lazy val scalatestVersion = "3.2.18"

Test / parallelExecution := false

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) if xs.contains("MANIFEST.MF") => MergeStrategy.discard
  case PathList("META-INF", "services", _) => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) => MergeStrategy.filterDistinctLines
  case "reference.conf" => MergeStrategy.concat
  case "application.conf" => MergeStrategy.concat
  case "logback.xml" => MergeStrategy.first
  case _ => MergeStrategy.first
}

libraryDependencies ++= Seq(
  "org.slf4j" % "slf4j-api" % "2.0.12",
  "ch.qos.logback" % "logback-classic" % "1.5.3" % Test,
  "org.scalatest" %% "scalatest" % scalatestVersion % Test
)

