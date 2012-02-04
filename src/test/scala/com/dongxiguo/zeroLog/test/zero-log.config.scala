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

import com.dongxiguo.zeroLog.Filter
import com.dongxiguo.zeroLog.formatters.SimpleFormatter
import scala.util.logging.ConsoleLogger
import scala.util.logging.Logged

package com.dongxiguo.zeroLog.test {
  package elided {
    private[elided] object ZeroLoggerFactory {
      final def newLogger(singleton: Singleton) =
        (Filter.Info, new SimpleFormatter(singleton) with ConsoleLogger)
    }
  }
  package formatting {
    private[formatting] object ZeroLoggerFactory {
      final def newLogger(singleton: Singleton) =
        (Filter.All, new SimpleFormatter(singleton) {
          override final def log(s: String) {}
        })
    }
  }
}
