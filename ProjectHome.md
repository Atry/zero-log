`zero-log` is a logging framework for Scala, which is designed to take advantage of Scala feature to let logging faster and simpler.

# Why use `zero-log` instead of `log4j`, `logback`, etc... #
  * Zero cost if the log is disabled by compile-time configuration.
  * Zero XML or Propertis files you need to write.
  * 10+ times faster for string formatting than any other Java logging libraries (log4j, logback, ...).
  * Support `@scala.annotation.elidable` and `scala.util.logging.Logged`.

For more comparation, see [log4j VS zero-log](Log4jVsZeroLog.md).

# Performance #
`zero-log` is extremely fast.

Thanks to Scala's `@elidable`, when using compile-time configuration, the cost for disabled logs can be exactly zero.

`zero-log`'s string formatting can be 10+ times faster than any other Java logging libraries. See [Fastring](https://github.com/Atry/fastring) for more information.

# Usage #

You only need call `ZeroLoggerFactory.newLogger(this)` to get the logger, and call `logger.info()` to log. No more configuation for simple use.

```
package com.yourDomain.yourProject
object Sample {
  implicit val (logger, formatter, appender) = ZeroLoggerFactory.newLogger(this)

  def main(args: Array[String]) {
    logger.info("Logging in a Singleton.")
    logger.fine("Hello,")
    logger.warning("World!")
    logger.finest(fast"Faster string formatting: args.length is ${args.length}")
    new Sample
  }
}

class Sample {
  import Sample._

  logger.info("Logging in a class instance.")
  logger.finer("Hello,")
  logger.severe("World!", new Exception("With some Exception"))
}
```

Note: By default, only logging level `info`, `warning` and `severe` are enabled, and the other levels are disabled.

## Configure logging level, formatting, or target ##

Unlike `log4j`, `logback`, `java.util.logging`, the `zero-log` does not load any XML or Properties file as configuration. Instead, `zero-log` use `ZeroLoggerFactory` to configure logging level, logging formatting, or logging target. If you need custom configuration, just put your own `ZeroLoggerFactory` on scalac's source path.

For example, you may create a file at `src/main/scala/zero-log.config.scala` to change logging level for `com.yourDomain.yourProject.Sample`:

```
import com.dongxiguo.zeroLog.Filter
import com.dongxiguo.zeroLog.formatters.SimpleFormatter
import com.dongxiguo.zeroLog.appenders.ConsoleAppender

// Set global default logging level to Warning, and send logs to ConsoleAppender
object ZeroLoggerFactory {
  final def newLogger(singleton: Singleton) =
    (Filter.Warning, SimpleFormatter, ConsoleAppender)
}

package com.yourDomain.yourProject {
  object ZeroLoggerFactory {
    // Set package com.yourDomain.yourProject's default logging level to Info
    final def newLogger(singleton: Singleton) =
      (Filter.Info, SimpleFormatter, ConsoleAppender)

    // Set Sample's logging level to Finest
    final def newLogger(singleton: Sample.type) =
      (Filter.Finest, SimpleFormatter, ConsoleAppender)
  }
}
```

Look at logger's initializing code in `Sample.scala`:

```
  implicit val (logger, formatter, appender) = ZeroLoggerFactory.newLogger(this)
```

When `Sample.scala` is compiled with `zero-log.config.scala`, `ZeroLoggerFactory.newLogger` will be resolved as `com.yourDomain.yourProject.ZeroLoggerFactory.newLogger`. So `logger` and `formatter` will be the result of `newLogger` you defined at `zero-log.config.scala`, which are `Filter.Finest` , `SimpleFormatter`, and `ConsoleAppender` for `Sample`.

### Run-time configuration ###

When you create a library, you may want the user of your library to be able to change logging settings without change your library's source code.

Don't worry. `zero-log` can resolve `ZeroLoggerFactory` by reflection. Just call `ZeroLoggerFactory.newLogger` without `com.yourDomain.yourProject.ZeroLoggerFactory` in source path, and ship your library without `com.yourDomain.yourProject.ZeroLoggerFactory` in your release. Your users can define their own `com.yourDomain.yourProject.ZeroLoggerFactory` or `com.yourDomain.ZeroLoggerFactory`, which are resoved by `zero-log` at run-time.