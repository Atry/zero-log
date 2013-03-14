package com.dongxiguo.zeroLog.context

import language.experimental.macros

final class CurrentSource (val get: String) extends AnyVal

object CurrentSource {

  final def currentSource_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    val sourceExpr = c.Expr(Literal(Constant(c.enclosingPosition.source.path)))
    reify(new _root_.com.dongxiguo.zeroLog.context.CurrentSource(sourceExpr.splice))
  }
  implicit final def currentSource: CurrentSource = macro currentSource_impl

}
