package com.dongxiguo.zeroLog.context

import language.experimental.macros

final class CurrentSource (val get: String) extends AnyVal

object CurrentSource {

  final def currentSource_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    c.Expr(New(
      typeOf[CurrentSource],
      Literal(Constant(c.enclosingPosition.source.path))))
  }
  implicit final def currentSource: CurrentSource = macro currentSource_impl

}
