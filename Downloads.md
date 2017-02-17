Binary release and scaladocs can be found on [Maven central repository](http://search.maven.org/#search|ga|1|zero-log). You may download these files directly, or add dependency to `zero-log` in your building management tool's configuration:

# Sbt #
If you use [sbt](http://www.scala-sbt.org/) 0.12.x or 0.13.x,
add following configuration to your `build.sbt`:
```
libraryDependencies += "com.dongxiguo" %% "zero-log" % "0.3.6"
```

# Maven #
Or add following configuration if you use maven:
```
<properties>
	<scala.version>2.11.0</scala.version>
</properties>
<dependency>
	<groupId>com.dongxiguo</groupId>
	<artifactId>zero-log_2.10</artifactId>
	<version>0.3.6</version>
	<scope>compile</scope>
</dependency>
```