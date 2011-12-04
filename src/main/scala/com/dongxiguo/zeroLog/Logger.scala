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

  @elidable(elidable.FINEST) override def finest(appendee: => StringBuilder => Unit)(
    implicit formatter: (StringBuilder => Unit, Level) => Unit) {
    super.finest(appendee)
  }

  @elidable(elidable.FINER) override def finer(appendee: => StringBuilder => Unit)(
    implicit formatter: (StringBuilder => Unit, Level) => Unit) {
    super.finer(appendee)
  }

  @elidable(elidable.FINE) override def fine(appendee: => StringBuilder => Unit)(
    implicit formatter: (StringBuilder => Unit, Level) => Unit) {
    super.fine(appendee)
  }

  @elidable(elidable.CONFIG) override def config(appendee: => StringBuilder => Unit)(
    implicit formatter: (StringBuilder => Unit, Level) => Unit) {
    super.config(appendee)
  }

  @elidable(elidable.INFO) override def info(appendee: => StringBuilder => Unit)(
    implicit formatter: (StringBuilder => Unit, Level) => Unit) {
    super.info(appendee)
  }

  @elidable(elidable.WARNING) override def warning(appendee: => StringBuilder => Unit)(
    implicit formatter: (StringBuilder => Unit, Level) => Unit) {
    super.warning(appendee)
  }

  @elidable(elidable.SEVERE) override def severe(appendee: => StringBuilder => Unit)(
    implicit formatter: (StringBuilder => Unit, Level) => Unit) {
    super.severe(appendee)
  }
}

object Logger {
  sealed abstract class NonElidableLogger {

    def finest(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {
      formatter(appendee, Level.Finest)
    }

    def finer(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {
      formatter(appendee, Level.Finer)
    }

    def fine(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {
      formatter(appendee, Level.Fine)
    }

    def config(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {
      formatter(appendee, Level.Config)
    }

    def info(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {
      formatter(appendee, Level.Info)
    }

    def warning(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {
      formatter(appendee, Level.Warning)
    }

    def severe(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {
      formatter(appendee, Level.Severe)
    }
  }
}
