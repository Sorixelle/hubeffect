package net.thereturningvoid.hubeffect

object Predefs {

  implicit class Kestrel[A](val value: A) extends AnyVal {
    def tap[B](f: A => B) = { f(value); value }
  }

}
