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


abstract class Logger private[zeroLog]() extends Logger.NonElidableLogger {

  @elidable(elidable.FINEST)
  override def finest(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    super.finest(appendee)
  }

  @elidable(elidable.FINER)
  override def finer(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    super.finer(appendee)
  }

  @elidable(elidable.FINE)
  override def fine(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    super.fine(appendee)
  }

  @elidable(elidable.CONFIG)
  override def config(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    super.config(appendee)
  }

  @elidable(elidable.INFO)
  override def info(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    super.info(appendee)
  }

  @elidable(elidable.WARNING)
  override def warning(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    super.warning(appendee)
  }

  @elidable(elidable.SEVERE)
  override def severe(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    super.severe(appendee)
  }
}

object Logger {
  sealed abstract class NonElidableLogger {

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Finest]] level.
   */
  def finest(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
      formatter(appendee, Level.Finest)
    }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Finer]] level.
   */
  def finer(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
      formatter(appendee, Level.Finer)
    }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Fine]] level.
   */
  def fine(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
      formatter(appendee, Level.Fine)
    }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Config]] level.
   */
  def config(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
      formatter(appendee, Level.Config)
    }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Info]] level.
   */
  def info(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
      formatter(appendee, Level.Info)
    }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Warning]] level.
   */
  def warning(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
      formatter(appendee, Level.Warning)
    }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Severe]] level.
   */
  def severe(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
      formatter(appendee, Level.Severe)
    }
  }
}
