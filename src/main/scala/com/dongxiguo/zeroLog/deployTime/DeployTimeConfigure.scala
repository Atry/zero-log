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
package deployTime

import com.dongxiguo.zeroLog.formatters.Formatter
import com.dongxiguo.zeroLog.formatters.SimpleFormatter
import scala.util.logging.ConsoleLogger

private object DeployTimeConfigure {
  // Eat my own dog food.
  private val (logger, formatter) = ZeroLoggerFactory.newLogger(this)

  private def defaultLogger[Scope <: AnyRef](
    scope: Scope): (Logger, Formatter) =
      (Filter.Info, new SimpleFormatter(scope) with ConsoleLogger)
}

trait DeployTimeConfigure {
  import DeployTimeConfigure._
  import DeployTimeConfigure.formatter._

  final def ZeroLoggerFactory = new {
    final def newLogger[Scope <: AnyRef : Manifest](scope: Scope) = {
      try {
        val result = ReflectUtils.invokeStatic(
          DeployTimeConfigure.this.getClass.getPackage.getName,
          "ZeroLoggerFactory",
          "newLogger", scope).asInstanceOf[(Logger, Formatter)]
        logger.config { _ append "Best matched configure is " append
                       "{filter=" append result._1 append
                       ",formatter=" append result._2 append ')' }
        result
      } catch {
        case _: ClassNotFoundException =>
          logger.config("No ZeroLoggerFactory is found, use defaultLogger.")
          defaultLogger(scope)
        case e: NoSuchMethodException =>
          throw new IllegalStateException("Bad configure!", e)
      }
    }
  }

}
