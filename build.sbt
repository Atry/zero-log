// vim: expandtab shiftwidth=2 softtabstop=2 syntax=scala

name := "zero-log"

organization := "com.dongxiguo"

organizationHomepage := None

libraryDependencies <+= scalaVersion { sv =>
  "org.scala-lang" % "scala-reflect" % sv
}

libraryDependencies += "com.dongxiguo" %% "fastring" % "0.2.2"

libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default"

crossScalaVersions := Seq("2.10.3")

version := "0.3.4"

scalacOptions += "-deprecation"

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

homepage := Some(url("https://code.google.com/p/zero-log/"))

pomExtra <<= scalaVersion { sv =>
  <scm>
    <url>https://code.google.com/p/zero-log/source/browse</url>
    <connection>scm:hg:https://code.google.com/p/zero-log/</connection>
  </scm>
  <developers>
    <developer>
      <id>Atry</id>
      <name>杨博</name>
    </developer>
  </developers>
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <scala.version>{sv}</scala.version>
  </properties>
  <build>
    <plugins>
      <plugin>
        <groupId>org.scala-tools</groupId>
        <artifactId>maven-scala-plugin</artifactId>
        <version>2.15.2</version>
        <executions>
          <execution>
            <goals>
              <goal>compile</goal>
              <goal>testCompile</goal>
            </goals>
          </execution>
        </executions>
        <configuration>
          <recompileMode>modified-only</recompileMode>
          <args>
            <arg>-Xelide-below</arg>
            <arg>FINEST</arg>
            <arg>-deprecation</arg>
          </args>
        </configuration>
      </plugin>
    </plugins>
  </build>
}
