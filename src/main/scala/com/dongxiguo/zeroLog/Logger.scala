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

import scala.annotation.elidable
import com.dongxiguo.fastring.Fastring

/**
 * Facade of zero-withLevel.
 */
abstract class Logger private[zeroLog] () {
  /**
   * Print withLevel at [[com.dongxiguo.zeroLog.Level.Finest]] level.
   */
  @elidable(elidable.FINEST)
  def finest(logRecord: => LogRecord) {
    logRecord.log(Level.Finest)
  }

  /**
   * Print withLevel at [[com.dongxiguo.zeroLog.Level.Finer]] level.
   */
  @elidable(elidable.FINER)
  def finer(logRecord: => LogRecord) {
    logRecord.log(Level.Finer)
  }

  /**
   * Print withLevel at [[com.dongxiguo.zeroLog.Level.Fine]] level.
   */
  @elidable(elidable.FINE)
  def fine(logRecord: => LogRecord) {
    logRecord.log(Level.Fine)
  }

  /**
   * Print withLevel at [[com.dongxiguo.zeroLog.Level.Config]] level.
   */
  @elidable(elidable.CONFIG)
  def config(logRecord: => LogRecord) {
    logRecord.log(Level.Config)
  }

  /**
   * Print withLevel at [[com.dongxiguo.zeroLog.Level.Info]] level.
   */
  @elidable(elidable.INFO)
  def info(logRecord: => LogRecord) {
    logRecord.log(Level.Info)
  }

  /**
   * Print withLevel at [[com.dongxiguo.zeroLog.Level.Warning]] level.
   */
  @elidable(elidable.WARNING)
  def warning(logRecord: => LogRecord) {
    logRecord.log(Level.Warning)
  }

  /**
   * Print withLevel at [[com.dongxiguo.zeroLog.Level.Severe]] level.
   */
  @elidable(elidable.SEVERE)
  def severe(logRecord: => LogRecord) {
    logRecord.log(Level.Severe)
  }

}
