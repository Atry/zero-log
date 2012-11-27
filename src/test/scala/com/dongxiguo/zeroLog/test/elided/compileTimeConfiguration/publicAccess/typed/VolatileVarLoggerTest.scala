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

package com.dongxiguo.zeroLog.test.elided
package compileTimeConfiguration.publicAccess.typed

import com.dongxiguo.zeroLog.Logger

import org.junit._; import com.dongxiguo.fastring.Fastring;import Fastring.Implicits._; import language.postfixOps
import Assert._
private object VolatileVarLoggerTest {
  
  @volatile var logger: Logger = ZeroLoggerFactory.newLogger(this)._1
  implicit val (_, formatter, appender) = ZeroLoggerFactory.newLogger(this)
  import formatter._

  private def doTest(p1: Double, p2: Int) = {
    val e = new Exception("For test only.") with scala.util.control.NoStackTrace
    var i = 0
    val start = System.nanoTime()
    while(i < 1000) {
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      logger.finest(
        fast"Hello, World!${start}blahblah$i${p1}\n${p2}blahblah" ->
        e)
      i += 1
    }
    val time = (System.nanoTime() - start)
    time.toDouble / 10000000.0

  }
}
import VolatileVarLoggerTest._

class VolatileVarLoggerTest {
  import formatter._

  @Before
  def setUp {
  }

  @After
  def tearDown {
  }

  @Test
  def test = {
    print("zero-log spends "); print(0 until 5 map { VolatileVarLoggerTest.doTest(math.random, _) } min); println(" nanoseconds per log.")
  }

}
