package com.dongxiguo.zeroLog.context

import language.experimental.macros

final class CurrentLine (val get: Int) extends AnyVal

object CurrentLine {
  final def currentLine_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    c.Expr(New(
      typeOf[CurrentLine],
      Literal(Constant(c.enclosingPosition.line))))
  }

  implicit final def currentLine: CurrentLine = macro currentLine_impl
}

