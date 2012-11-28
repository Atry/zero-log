package com.dongxiguo.zeroLog.context

import language.experimental.macros

sealed trait NoCurrentMethod

object NoCurrentMethod {
  final case object NoCurrentMethod

  final def noCurrentMethod_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    c.enclosingMethod match {
      case null =>
        reify(null)
      case currentDefTree: DefTree =>
        c.Expr(c.parse("implicitly[_root_.com.dongxiguo.zeroLog.context.NoCurrentMethod]"))
    }
  }

  implicit final def noCurrentMethod: NoCurrentMethod = macro noCurrentMethod_impl
}