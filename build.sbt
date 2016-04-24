// vim: expandtab shiftwidth=2 softtabstop=2 syntax=scala

lazy val context =
  Project(id = "context", base = file("context"))

lazy val root =
  Project(id = "root", base = file(".")).dependsOn(context).aggregate(context)


name := "zero-log"

organization := "com.dongxiguo"

organizationHomepage := None

libraryDependencies <+= scalaVersion { sv =>
  "org.scala-lang" % "scala-reflect" % sv
}

libraryDependencies += "com.dongxiguo" %% "fastring" % "0.2.4"

libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default"

incOptions := incOptions.value.withNameHashing(true)

releasePublishArtifactsAction := PgpKeys.publishSigned.value

releaseCrossBuild := true

crossScalaVersions := Seq("2.10.4", "2.11.2")

scalacOptions <++= (scalaVersion) map { sv =>
  if (sv.startsWith("2.10.")) {
    Seq("-deprecation") // Fully compatible with 2.10.x 
  } else {
    Seq() // May use deprecated API in 2.11.x
  }
}

startYear := Some(2011)

scalacOptions += "-unchecked"

scalacOptions += "-feature"

scalacOptions ++= Seq("-Xelide-below", "FINEST")

publishTo <<= (isSnapshot) { isSnapshot: Boolean =>
  if (isSnapshot)
    Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots") 
  else
    Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
}

licenses := Seq(
  "Apache License, Version 2.0" ->
  url("http://www.apache.org/licenses/LICENSE-2.0.html"))

scmInfo := Some(ScmInfo(
  url(raw"""https://github.com/ThoughtWorksInc/${name.value}"""),
  raw"""scm:git:https://github.com/ThoughtWorksInc/${name.value}.git""",
  Some(raw"""scm:git:git@github.com:ThoughtWorksInc/${name.value}.git""")))

homepage := Some(url("https://code.google.com/p/zero-log/"))

developers in ThisBuild := List(
  Developer(
    "Atry",
    "杨博 (Yang Bo)",
    "pop.atry@gmail.com",
    url("https://github.com/Atry")
  )
)

import ReleaseTransformations._

releaseProcess := {
  releaseProcess.value.patch(releaseProcess.value.indexOf(pushChanges), Seq[ReleaseStep](releaseStepCommand("sonatypeRelease")), 0)
}

releaseProcess -= runClean

releaseProcess -= runTest
