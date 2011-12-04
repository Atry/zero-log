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

object Filter {

  object All extends Logger

  object Finest extends Logger

  sealed abstract class NonElidableFiner extends Logger {
    override def finest(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  sealed abstract class Finer extends NonElidableFiner {
    @elidable(elidable.MINIMUM)
    override final def finest(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  object Finer extends Finer

  sealed abstract class NonElidableFine extends Finer {
    override def finer(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  sealed abstract class Fine extends NonElidableFine {
    @elidable(elidable.MINIMUM)
    override final def finer(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  object Fine extends Fine

  sealed abstract class NonElidableConfig extends Fine {
    override def fine(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  sealed abstract class Config extends NonElidableConfig {
    @elidable(elidable.MINIMUM)
    override final def fine(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  object Config extends Config

  sealed abstract class NonElidableInfo extends Config {
    override def config(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  sealed abstract class Info extends NonElidableInfo {
    @elidable(elidable.MINIMUM)
    override final def config(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  object Info extends Info

  sealed abstract class NonElidableWarning extends Info {
    override def info(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  sealed abstract class Warning extends NonElidableWarning {
    @elidable(elidable.MINIMUM)
    override final def info(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  object Warning extends Warning

  sealed abstract class NonElidableSevere extends Warning {
    override def warning(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  sealed abstract class Severe extends NonElidableSevere {
    @elidable(elidable.MINIMUM)
    override final def warning(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  object Severe extends Severe

  sealed abstract class NonElidableOff extends Severe {
    override def severe(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  sealed abstract class Off extends NonElidableOff {
    @elidable(elidable.MINIMUM)
    override final def severe(appendee: => StringBuilder => Unit)(
      implicit formatter: (StringBuilder => Unit, Level) => Unit) {}
  }

  object Off extends Off

  final def elideBlow(value: Int): Logger = {
    // 人肉实现二分查找
    if (value <= elidable.CONFIG) {
      if (value <= elidable.FINER) {
        if (value <= elidable.FINEST) {
          Finest
        } else {
          Finer
        }
      } else {
        if (value <= elidable.FINE) {
          Fine
        } else {
          Config
        }
      }
    } else {
      if (value <= elidable.WARNING) {
        if (value <= elidable.INFO) {
          Info
        } else {
          Warning
        }
      } else {
        if (value <= elidable.SEVERE) {
          Severe
        } else {
          Off
        }
      }
    }
  }
}