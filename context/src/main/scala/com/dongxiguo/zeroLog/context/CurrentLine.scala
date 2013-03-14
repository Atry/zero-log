package com.dongxiguo.zeroLog.context

import language.experimental.macros

final class CurrentLine (val get: Int) extends AnyVal

object CurrentLine {
  final def currentLine_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    val lineExpr = c.Expr(Literal(Constant(c.enclosingPosition.line)))
    reify(new _root_.com.dongxiguo.zeroLog.context.CurrentLine(lineExpr.splice))
  }

  implicit final def currentLine: CurrentLine = macro currentLine_impl
}

