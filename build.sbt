// vim: expandtab shiftwidth=2 softtabstop=2 syntax=scala

lazy val context = project

dependsOn(context)

name := "zero-log"

organization := "com.dongxiguo"

organizationHomepage := None

libraryDependencies <+= scalaVersion { sv =>
  "org.scala-lang" % "scala-reflect" % sv
}

libraryDependencies += "com.dongxiguo" %% "fastring" % "0.3.1"

libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default"

incOptions := incOptions.value.withNameHashing(true)

releasePublishArtifactsAction := PgpKeys.publishSigned.value

releaseCrossBuild := true

crossScalaVersions := Seq("2.10.6", "2.11.8", "2.12.1")

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
