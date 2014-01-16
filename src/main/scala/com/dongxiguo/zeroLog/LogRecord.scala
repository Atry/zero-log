package com.dongxiguo.zeroLog

import com.dongxiguo.fastring.Fastring
import com.dongxiguo.fastring.Fastring.Implicits._
import language.implicitConversions
import com.dongxiguo.zeroLog.context.CurrentSource
import com.dongxiguo.zeroLog.context.CurrentLine
import com.dongxiguo.zeroLog.context.CurrentClass
import com.dongxiguo.zeroLog.context.CurrentMethodName

sealed abstract class LogRecord {
  def log(level: Level)
}

object LogRecord {

  implicit final class StringLogRecord(message: String)(
    implicit formatter: Formatter,
    appender: Appender,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]) extends LogRecord {
    override final def log(level: Level) {
      appender.append(formatter.format(level, Fastring(message), currentSource, currentLine, currentClass, currentMethodNameOption))
    }
  }

  implicit final class FastringLogRecord(message: Fastring)(
    implicit formatter: Formatter,
    appender: Appender,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]) extends LogRecord {
    override final def log(level: Level) {
      appender.append(formatter.format(level, message, currentSource, currentLine, currentClass, currentMethodNameOption))
    }
  }

  implicit final class StringThrowableLogRecord(pair: (String, Throwable))(
    implicit formatter: Formatter,
    appender: Appender,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]) extends LogRecord {
    override final def log(level: Level) {
      appender.append(formatter.format(level, Fastring(pair._1), pair._2, currentSource, currentLine, currentClass, currentMethodNameOption))
    }
  }

  implicit final class FastringThrowableLogRecord(pair: (Fastring, Throwable))(
    implicit formatter: Formatter,
    appender: Appender,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]) extends LogRecord {
    override final def log(level: Level) {
      appender.append(formatter.format(level, pair._1, pair._2, currentSource, currentLine, currentClass, currentMethodNameOption))
    }
  }

  implicit final class ThrowableLogRecord(throwable: Throwable)(
    implicit formatter: Formatter,
    appender: Appender,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]) extends LogRecord {
    override final def log(level: Level) {
      appender.append(formatter.format(level, throwable, currentSource, currentLine, currentClass, currentMethodNameOption))
    }
  }

}
