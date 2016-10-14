import sbt._
import Keys._
import sbtassembly.{AssemblyKeys, MergeStrategy, PathList}
import AssemblyKeys._

object Build extends Build {

  val Org = "com.cloudwick.kafka"
  val ProjectName = "sequence-producer"

  val ScalaVersion = "2.11.8"
  val Kafka = "0.9.0.1"

  lazy val commonSettings = Seq(
    version := "0.1",
    scalaVersion := ScalaVersion,
    organization := Org,
    scalacOptions ++= Seq("-unchecked", "-deprecation")
  )

  lazy val assemblySettings = Seq(
    test in assembly := {},
    assemblyMergeStrategy in assembly := {
      case PathList(ps @ _*) if ps.last endsWith ".html"  => MergeStrategy.first
      case PathList(ps @ _*) if ps.last endsWith ".md"    => MergeStrategy.discard
      case ".gitkeep"                                     => MergeStrategy.first
      case "pom.xml"                                      => MergeStrategy.discard
      case "pom.properties"                               => MergeStrategy.discard
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )

  lazy val root = Project(
    ProjectName,
    file("."),
    settings = commonSettings ++ assemblySettings) settings(
    libraryDependencies ++= Dependencies.compile,
    libraryDependencies ++= Dependencies.test
    )

  object Dependencies {
    val compile = Seq(
      "org.apache.kafka"      %% "kafka"                      % Kafka
        exclude("log4j", "log4j")
        exclude("io.netty", "netty")
        exclude("net.sf.jopt-simple", "jopt-simple")
        excludeAll ExclusionRule(organization = "org.slf4j")
    )

    val test = Seq(
      "org.scalactic" %% "scalactic" % "3.0.0" % "test",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test"
    )
  }
}
