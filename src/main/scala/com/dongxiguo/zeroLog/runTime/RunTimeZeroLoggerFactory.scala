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
package runTime

import com.dongxiguo.zeroLog.formatters.Formatter
import com.dongxiguo.zeroLog.formatters.SimpleFormatter
import scala.util.logging.ConsoleLogger

object RunTimeZeroLoggerFactory {
  // Eat my own dog food.
  private val (logger, formatter) = ZeroLoggerFactory.newLogger(this)
  import formatter._

  private def defaultLogger(singleton: Singleton): (Logger, Formatter) = {
      (Filter.Info, new SimpleFormatter(singleton) with ConsoleLogger)
  }

  val isRunning = new ThreadLocal[java.lang.Boolean] {
    override def initialValue = java.lang.Boolean.FALSE
  }

  /**
   * @return A logger which is located by reflection.
   */
  final def newLogger(singleton: Singleton) = {
    if (isRunning.get.booleanValue) {
      logger.config("No ZeroLoggerFactory is found, use defaultLogger.")
      defaultLogger(singleton)
    } else {
      isRunning.set(java.lang.Boolean.TRUE)
      try {
        val packageName =
          singleton.asInstanceOf[AnyRef].getClass.getPackage match {
            case null => null
            case nonDefaultPackage => nonDefaultPackage.getName
          }
        val result =
          ReflectUtils.invokeStatic(
            packageName,
            "ZeroLoggerFactory",
            "newLogger",
            singleton.asInstanceOf[AnyRef]).asInstanceOf[(Logger, Formatter)]
        logger.config { _ ++= "Best matched configuration is " ++=
                       "{logger=" ++= result._1.toString ++=
                       ",formatter=" ++= result._2.toString += ')' }
        result
      } catch {
        case _: ClassNotFoundException =>
          logger.config("No ZeroLoggerFactory is found, use defaultLogger.")
          defaultLogger(singleton)
        case e: NoSuchMethodException =>
          throw new IllegalStateException("Bad configuration!", e)
      } finally {
        isRunning.set(java.lang.Boolean.FALSE)
      }
    }
  }

}
