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

/**
 * Facade of zero-log.
 */
abstract class Logger private[zeroLog]() {
  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Finest]] level.
   */
  @elidable(elidable.FINEST)
  def finest(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
    formatter(appendee, Level.Finest)
  }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Finer]] level.
   */
  @elidable(elidable.FINER)
  def finer(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
    formatter(appendee, Level.Finer)
  }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Fine]] level.
   */
  @elidable(elidable.FINE)
  def fine(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
    formatter(appendee, Level.Fine)
  }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Config]] level.
   */
  @elidable(elidable.CONFIG)
  def config(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
    formatter(appendee, Level.Config)
  }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Info]] level.
   */
  @elidable(elidable.INFO)
  def info(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
    formatter(appendee, Level.Info)
  }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Warning]] level.
   */
  @elidable(elidable.WARNING)
  def warning(appendee: => Appendee)(
      implicit formatter: (Appendee, Level) => Unit) {
    formatter(appendee, Level.Warning)
  }

  /**
   * Print log at [[com.dongxiguo.zeroLog.Level.Severe]] level.
   */
  @elidable(elidable.SEVERE)
  def severe(appendee: => Appendee)(
    implicit formatter: (Appendee, Level) => Unit) {
    formatter(appendee, Level.Severe)
  }

}
