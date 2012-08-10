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
import scala.util.logging.Logged
import scala.compat.Platform

private object SimpleFormatter {

  private val SingletonPattern = """^(.*)\$$"""r
  
  private final class ZeroFilledInt(n: Int, minSize: Int)
  extends Traversable[Char] {
    override final def foreach[U](f: Char => U) {
      assert(minSize >= 0)
      assert(n >= 0)
      var zeros = minSize
      var i = 1
      var t = n
      while(i <= t) {
        if (i != 100000000) {
          i *= 10
          zeros -= 1
        } else {
          while (i != 1) {
            f(java.lang.Character.forDigit(t / i, 10))
            t %= i
            i /= 10
          }
          f(java.lang.Character.forDigit(t, 10))
          return
        }
      }
      while (zeros > 0) {
        f('0')
        zeros -= 1
      }
      while (i > 1) {
        i /= 10
        f(java.lang.Character.forDigit(t / i, 10))
        t %= i
      }
    }
  }

  private final def toWriter(sb: StringBuilder) = new Writer {
    override final def close() {}
    override final def flush() {}
    override final def write(cbuf: Array[Char], off: Int, len: Int) {
      sb.appendAll(cbuf, off, len)
    }
    override final def write(c: Int) {
      sb += c.asInstanceOf[Char]
    }
  }

  private final def createLazy(setter: Function0[String] => Unit,
                               initial: => String) = { () =>
    val result = initial
    setter { () => result }
    result
  }

}

/**
 * A simple [[com.dongxiguo.zeroLog.formatters.Formatter]] implementation.
 */
abstract class SimpleFormatter(loggerNameInitial: => String)
extends Formatter with Logged {
  import SimpleFormatter._

  override def log(s: String)

  private var loggerName: Function0[String] =
    createLazy(loggerName_=, loggerNameInitial)

  final def this(singleton: Singleton) = this {
    singleton.asInstanceOf[AnyRef].getClass.getCanonicalName match {
      case SimpleFormatter.SingletonPattern(className) => className
      case _ =>
        throw new IllegalArgumentException(
          singleton + " should be a singleton object.")
    }
  }

  private def writeTime(buffer: StringBuilder) {
    val now = Calendar.getInstance
    import now.get
    import Calendar._
    buffer ++=
    new ZeroFilledInt(get(YEAR), 4) += '-' ++=
    new ZeroFilledInt(get(MONTH) + 1, 2) += '-' ++=
    new ZeroFilledInt(get(DATE), 2) += ' ' ++=
    new ZeroFilledInt(get(HOUR_OF_DAY), 2) += ':' ++=
    new ZeroFilledInt(get(MINUTE), 2) += ':' ++=
    new ZeroFilledInt(get(SECOND), 2) += ' '
  }

  private def writeHead(buffer: StringBuilder, level: Level) {
    writeTime(buffer)
    buffer ++=
    loggerName() ++= Platform.EOL ++= level.name ++= ": "
  }

  implicit override final def pairToAppendee[A](
    pair: (A, Throwable))(implicit converter: A => Appendee) = {
    buffer: StringBuilder =>
    val (message, thrown) = pair
    converter(message)(buffer)
    buffer += ' '
    thrown.printStackTrace(new PrintWriter(toWriter(buffer)))
  }

  implicit override final def thrownToAppendee(thrown: Throwable) = {
    buffer: StringBuilder =>
    thrown.printStackTrace(new PrintWriter(toWriter(buffer)))
  }

  implicit override final def log(content: Appendee, level: Level) {
    val buffer = new StringBuilder
    writeHead(buffer, level)
    content(buffer)
    log(buffer.toString)
  }

}
