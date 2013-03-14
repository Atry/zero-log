package com.dongxiguo.zeroLog.context

import language.experimental.macros

final class CurrentMethodName (val get: String) extends AnyVal

object CurrentMethodName {

  final case object NoMethodName
  final def noMethodName_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    c.enclosingMethod match {
      case null =>
        reify(NoMethodName)
      case currentDefTree: DefTree =>
        c.Expr(c.parse("implicitly[_root_.com.dongxiguo.zeroLog.context.CurrentMethodName.NoMethodName]"))
    }
  }

  implicit final def noMethodName: NoMethodName.type = macro noMethodName_impl

  final def currentMethodName_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    c.enclosingMethod match {
      case null =>
        c.Expr(c.parse("implicitly[_root_.com.dongxiguo.zeroLog.context.CurrentMethodName]"))
      case currentDefTree: DefTree =>
        val nameExpr = c.Expr(Literal(Constant(currentDefTree.name.decoded)))
        reify(new _root_.com.dongxiguo.zeroLog.context.CurrentMethodName(nameExpr.splice))
    }
  }

  implicit final def currentMethodName: CurrentMethodName = macro currentMethodName_impl

  final def currentMethodNameOption_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    c.enclosingMethod match {
      case null =>
        reify(_root_.scala.None)
      case currentDefTree: DefTree =>
        val nameExpr = c.Expr(Literal(Constant(currentDefTree.name.decoded)))
        reify(_root_.scala.Some(new _root_.com.dongxiguo.zeroLog.context.CurrentMethodName(nameExpr.splice)))
    }
  }

  implicit final def currentMethodNameOption: Option[CurrentMethodName] = macro currentMethodNameOption_impl

}
