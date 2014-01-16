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

sealed abstract class Level(final val value: Int) {
  final val name = toString.toUpperCase
}

final object Level {
  final case object Finest extends Level(elidable.FINEST)
  final case object Finer extends Level(elidable.FINER)
  final case object Fine extends Level(elidable.FINE)
  final case object Config extends Level(elidable.CONFIG)
  final case object Info extends Level(elidable.INFO)
  final case object Warning extends Level(elidable.WARNING)
  final case object Severe extends Level(elidable.SEVERE)
}
