package com.dongxiguo.zeroLog.context

import language.experimental.macros
import scala.reflect.runtime.universe.TypeTag

final class CurrentClass (val get: Class[_]) extends AnyVal

object CurrentClass {

  final def currentClass_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    val classExpr = c.Expr(c.parse("this.getClass"))
    reify(new CurrentClass(classExpr.splice))
  }

  implicit final def currentClass: CurrentClass = macro currentClass_impl
}
