// vim: expandtab shiftwidth=2 softtabstop=2 syntax=scala

name := "zero-log"

organization := "com.dongxiguo"

libraryDependencies += "com.novocode" % "junit-interface" % "0.7" % "test->default"

crossScalaVersions := Seq("2.8.0", "2.8.1", "2.9.1", "2.10.0-M2")

version := "0.1-SNAPSHOT"

pomExtra <<= scalaVersion { sv =>
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
