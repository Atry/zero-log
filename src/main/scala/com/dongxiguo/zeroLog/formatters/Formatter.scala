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

package com.dongxiguo.zeroLog.formatters

import com.dongxiguo.zeroLog.Level
import com.dongxiguo.zeroLog.Appendee

/**
 * A <code>Formatter</code> provides support for formatting logs.
 * Formatting a log consists of three steps:
 *   1. Convert various parameters to [[com.dongxiguo.zeroLog.Appendee]].
 *   1. If the [[com.dongxiguo.zeroLog.Logger]] decide to print log,
 *   <code>Formatter.log(Appendee,Level)</code> is called to make final log
 *   text.
 *   1. <code>Formatter</code> pass final log text to the
 *   [[scala.util.logging.Logged]] implemetation which <code>Formatter</code>
 *   mixed in.
 */
trait Formatter {

  implicit def toAppendee(message: String): Appendee =
  { _.append(message) }

  implicit def toAppendeeWithThrown(
    pair: (String, Throwable)): Appendee = {
    val (message, thrown) = pair
    formatMessageWithThrown[Unit](message, thrown)
  }

  implicit def formatMessageWithThrown[U](
    pair: (StringBuilder => U, Throwable)): Appendee

  implicit def formatThrown(thrown: Throwable): Appendee

  implicit def log(content: Appendee, level: Level)
}
