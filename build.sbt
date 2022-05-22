import sbt.Keys._
import play.sbt.PlaySettings

name := "crawler"
version := "0.1"
scalaVersion := "2.13.8"

lazy val root = (project in file(".")).enablePlugins(PlayScala)
libraryDependencies += guice

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.41"
)
// https://mvnrepository.com/artifact/org.scalatestplus.play/scalatestplus-play
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
// https://mvnrepository.com/artifact/net.liftweb/lift-json
libraryDependencies += "net.liftweb" %% "lift-json" % "3.5.0"



