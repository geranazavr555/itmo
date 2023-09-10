module HW2.T2 (
  distOption, wrapOption,
  distPair, wrapPair,
  distQuad, wrapQuad,
  distAnnotated, wrapAnnotated,
  distExcept, wrapExcept,
  distPrioritised, wrapPrioritised,
  distStream, wrapStream,
  distList, wrapList,
  distFun, wrapFun
) where

import HW2.T1

distOption :: (Option a, Option b) -> Option (a, b)
distOption (Some x, Some y) = Some (x, y)
distOption _                = None

wrapOption :: a -> Option a
wrapOption = Some

distPair :: (Pair a, Pair b) -> Pair (a, b)
distPair (P w x, P y z) = P (w, y) (x, z)

wrapPair :: a -> Pair a
wrapPair x = P x x

distQuad :: (Quad a, Quad b) -> Quad (a, b)
distQuad (Q a1 a2 a3 a4, Q b1 b2 b3 b4) = Q (a1, b1) (a2, b2) (a3, b3) (a4, b4)

wrapQuad :: a -> Quad a
wrapQuad x = Q x x x x

distAnnotated :: Semigroup e => (Annotated e a, Annotated e b) -> Annotated e (a, b)
distAnnotated (x :# e1, y :# e2) = (x, y) :# (e1 <> e2)

wrapAnnotated :: Monoid e => a -> Annotated e a
wrapAnnotated x = x :# mempty

distExcept :: (Except e a, Except e b) -> Except e (a, b)
distExcept (Success x, Success y) = Success (x, y)
distExcept (Error e, _)           = Error e
distExcept (_, Error e)           = Error e

wrapExcept :: a -> Except e a
wrapExcept = Success

distPrioritised :: (Prioritised a, Prioritised b) -> Prioritised (a, b)
distPrioritised (High x, High y)     =   High (x, y)
distPrioritised (High x, Medium y)   =   High (x, y)
distPrioritised (High x, Low y)      =   High (x, y)
distPrioritised (Medium x, High y)   =   High (x, y)
distPrioritised (Medium x, Medium y) = Medium (x, y)
distPrioritised (Medium x, Low y)    = Medium (x, y)
distPrioritised (Low x, High y)      =   High (x, y)
distPrioritised (Low x, Medium y)    = Medium (x, y)
distPrioritised (Low x, Low y)       =    Low (x, y)

wrapPrioritised :: a -> Prioritised a
wrapPrioritised = Low

distStream :: (Stream a, Stream b) -> Stream (a, b)
distStream (a :> restA, b :> restB) = (a, b) :> distStream (restA, restB)

wrapStream :: a -> Stream a
wrapStream x = x :> wrapStream x

distListHelper :: a -> List b -> List (a, b)
distListHelper x (y :. rest) = (x, y) :. distListHelper x rest
distListHelper _ Nil         = Nil

listConcat :: List a -> List a -> List a
listConcat Nil list         = list
listConcat (x :. rest) list = x :. (rest `listConcat` list)

distList :: (List a, List b) -> List (a, b)
distList (Nil, _) = Nil
distList (x :. rest, list) = distListHelper x list `listConcat` distList (rest, list)

wrapList :: a -> List a
wrapList x = x :. Nil

distFun :: (Fun i a, Fun i b) -> Fun i (a, b)
distFun (F f, F g) = F (\i -> (f i, g i))

wrapFun :: a -> Fun i a
wrapFun res = F (const res)
