// vim: expandtab shiftwidth=2 softtabstop=2 syntax=scala

name := "context"

organization := "com.dongxiguo.zero-log"

libraryDependencies <+= scalaVersion { sv =>
  "org.scala-lang" % "scala-reflect" % sv
} 

libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default"

scalacOptions += "-deprecation"

scalacOptions += "-feature"

scalacOptions += "-unchecked"

scalacOptions ++= Seq("-Xelide-below", "FINEST")
