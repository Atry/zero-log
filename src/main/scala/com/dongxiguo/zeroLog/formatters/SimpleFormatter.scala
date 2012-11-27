// vim: expandtab shiftwidth=2 softtabstop=2
/*
 * Copyright 2011 杨博 (Yang Bo)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dongxiguo.zeroLog
package formatters

import java.io.PrintWriter
import java.io.Writer
import java.util.Calendar
import scala.compat.Platform
import scala.language.implicitConversions
import scala.util.logging.Logged
import com.dongxiguo.zeroLog.context.CurrentClass
import com.dongxiguo.zeroLog.context.CurrentLine
import com.dongxiguo.zeroLog.context.CurrentMethodName
import com.dongxiguo.zeroLog.context.CurrentSource
import com.dongxiguo.fastring.Fastring
import com.dongxiguo.fastring.Fastring.Implicits._

/**
 * A simple [[com.dongxiguo.zeroLog.Formatter]] implementation.
 */
final object SimpleFormatter extends Formatter {

  private val SingletonPattern = """^(.*)\$$""".r

  private def createLazy(setter: Function0[String] => Unit,
    initial: => String) = { () =>
    val result = initial
    setter { () => result }
    result
  }

  private final class PrintThrowable(throwable: Throwable) extends Fastring {
    override final def foreach[U](visitor: String => U) {
      val writer = new PrintWriter(new Writer {
        override final def close() {}
        override final def flush() {}
        override final def write(buffer: Array[Char], offset: Int, length: Int) {
          visitor(new String(buffer, offset, length))
        }

        override final def write(char: Int) {
          visitor(char.asInstanceOf[Char].toString)
        }

        override final def write(string: String) {
          visitor(string)
        }

        override final def write(string: String, offset: Int, length: Int) {
          visitor(string.substring(offset, offset + length))
        }
      })
      throwable.printStackTrace(writer)
      writer.flush()
    }
  }

  private def now: Fastring = {
    val calendarNow = Calendar.getInstance
    import calendarNow.get
    import Calendar._
    fast"${
      get(YEAR).filled(4, '0')
    }-${
      (get(MONTH) + 1).filled(2, '0')
    }-${
      get(DATE).filled(2, '0')
    } ${
      get(HOUR_OF_DAY).filled(2, ':')
    }-${
      get(MINUTE).filled(2, ':')
    }-${
      get(SECOND).filled(2, '0')
    }"
  }

  override final def format(
    level: Level,
    message: Fastring,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]): Fastring = {
    @inline
    def methodName = currentMethodNameOption.getOrElse("<init>")
    @inline
    def sourceName = new java.io.File(currentSource.get).getName
    fast"$now $sourceName:${currentLine.get} $methodName ${Platform.EOL}${level.name}: $message"
  }

  override final def format[A](
    level: Level,
    message: Fastring,
    throwable: Throwable,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]): Fastring = {
    @inline
    def methodName = currentMethodNameOption.getOrElse("<init>")
    @inline
    def sourceName = new java.io.File(currentSource.get).getName
    fast"$now $sourceName:${currentLine.get} $methodName ${Platform.EOL}${level.name}: $message ${new PrintThrowable(throwable)}"
  }

  override final def format(
    level: Level,
    throwable: Throwable,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]): Fastring = {
    @inline
    def methodName = currentMethodNameOption.getOrElse("<init>")
    @inline
    def sourceName = new java.io.File(currentSource.get).getName
    fast"$now $sourceName:${currentLine.get} $methodName ${Platform.EOL}${level.name}: ${new PrintThrowable(throwable)}"
  }

}
