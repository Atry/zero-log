import sbt._
import Keys._

object ZeroLogBuild extends Build {
  lazy val context =
    Project(id = "context", base = file("context"))

  lazy val root =
    Project(id = "root", base = file(".")).dependsOn(context)
}
// vim: set softtabstop=2 shiftwidth=2 expandtab:
