package com.dongxiguo.zeroLog

import com.dongxiguo.zeroLog.context.CurrentSource
import com.dongxiguo.zeroLog.context.CurrentLine
import com.dongxiguo.zeroLog.context.CurrentClass
import com.dongxiguo.zeroLog.context.CurrentMethodName
import com.dongxiguo.fastring.Fastring

trait Formatter {

  def format(
    level: Level,
    message: Fastring,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]): Fastring

  def format[A](
    level: Level,
    message: Fastring,
    throwable: Throwable,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]): Fastring

  def format(
    level: Level,
    thrown: Throwable,
    currentSource: CurrentSource,
    currentLine: CurrentLine,
    currentClass: CurrentClass,
    currentMethodNameOption: Option[CurrentMethodName]): Fastring

}
