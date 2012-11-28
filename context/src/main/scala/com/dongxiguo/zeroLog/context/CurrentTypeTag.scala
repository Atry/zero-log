package com.dongxiguo.zeroLog.context

import language.experimental.macros
import scala.reflect.runtime.universe.TypeTag

final class CurrentTypeTag (val get: TypeTag[_]) extends AnyVal

object CurrentTypeTag {

  final def currentTypeTag_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    val typeTagExpr =
      c.Expr(c.parse(
        "_root_.scala.reflect.runtime.universe.typeTag[this.type]"))
    reify(new CurrentTypeTag(typeTagExpr.splice))
  }

  implicit final def currentTypeTag: CurrentTypeTag = macro currentTypeTag_impl
}
