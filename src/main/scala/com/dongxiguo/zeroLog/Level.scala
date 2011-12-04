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

sealed abstract class Level(value: Int) {
  val name = toString.toUpperCase
}

object Level {
  case object Finest extends Level(elidable.FINEST)
  case object Finer extends Level(elidable.FINER)
  case object Fine extends Level(elidable.FINE)
  case object Config extends Level(elidable.CONFIG)
  case object Info extends Level(elidable.INFO)
  case object Warning extends Level(elidable.WARNING)
  case object Severe extends Level(elidable.SEVERE)
}
