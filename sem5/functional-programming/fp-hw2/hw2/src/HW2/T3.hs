module HW2.T3 (
  joinOption,
  joinExcept,
  joinAnnotated,
  joinList,
  joinFun,
) where

import HW2.T1

joinOption :: Option (Option a) -> Option a
joinOption (Some (Some x)) = Some x
joinOption _               = None

joinExcept :: Except e (Except e a) -> Except e a
joinExcept (Success (Success x)) = Success x
joinExcept (Success (Error e))   = Error e
joinExcept (Error e)             = Error e

joinAnnotated :: Semigroup e => Annotated e (Annotated e a) -> Annotated e a
joinAnnotated ((ai :# ei) :# eo) = ai :# (eo <> ei)

joinList :: List (List a) -> List a
joinList Nil                     = Nil
joinList (Nil :. restO)          = joinList restO
joinList ((x :. restI) :. restO) = x :. joinList (restI :. restO)

unwrapFun :: Fun i a -> (i -> a)
unwrapFun (F f) = f

joinFun :: Fun i (Fun i a) -> Fun i a
joinFun (F iToFunIA) = F (\i -> unwrapFun (iToFunIA i) i)

