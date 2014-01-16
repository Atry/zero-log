package com.dongxiguo.zeroLog
package appenders

import com.dongxiguo.fastring.Fastring

final object ConsoleAppender extends Appender {
  override final def append(message: Fastring) {
    // 为了保证原子性，不使用 message.content.appendTo(Console.out)
    print(message.toString)
  }
}