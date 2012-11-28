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
        c.Expr(
          New(typeOf[CurrentMethodName],
            Literal(Constant(currentDefTree.name.decoded))))
    }
  }

  implicit final def currentMethodName: CurrentMethodName = macro currentMethodName_impl

  final def currentMethodNameOption_impl(c: scala.reflect.macros.Context) = {
    import c.universe._
    c.enclosingMethod match {
      case null =>
        reify(None)
      case currentDefTree: DefTree =>
        c.Expr(
          New(typeOf[Some[CurrentMethodName]],
            New(typeOf[CurrentMethodName],
              Literal(Constant(currentDefTree.name.decoded)))))
    }
  }

  implicit final def currentMethodNameOption: Option[CurrentMethodName] = macro currentMethodNameOption_impl

}
