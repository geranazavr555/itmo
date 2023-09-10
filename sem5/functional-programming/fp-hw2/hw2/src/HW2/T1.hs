module HW2.T1 (
  Option(..), mapOption,
  Pair(..), mapPair,
  Quad(..), mapQuad,
  Annotated(..), mapAnnotated,
  Except(..), mapExcept,
  Prioritised(..), mapPrioritised,
  Stream(..), mapStream,
  List(..), mapList,
  Fun(..), mapFun,
  Tree(..), mapTree
) where

infixr 5 :>
infixr 5 :.
infix 0 :#

data Option a = None | Some a
data Pair a = P a a
data Quad a = Q a a a a
data Annotated e a = a :# e
data Except e a = Error e | Success a
data Prioritised a = Low a | Medium a | High a
data Stream a = a :> Stream a
data List a = Nil | a :. List a
newtype Fun i a = F (i -> a)
data Tree a = Leaf | Branch (Tree a) a (Tree a)


mapOption :: (a -> b) -> (Option a -> Option b)
mapOption f (Some x) = Some (f x)
mapOption _ None     = None

mapPair :: (a -> b) -> (Pair a -> Pair b)
mapPair f (P x y) = P (f x) (f y)

mapQuad :: (a -> b) -> (Quad a -> Quad b)
mapQuad f (Q w x y z) = Q (f w) (f x) (f y) (f z)

mapAnnotated :: (a -> b) -> (Annotated e a -> Annotated e b)
mapAnnotated f (a :# e) = f a :# e

mapExcept :: (a -> b) -> (Except e a -> Except e b)
mapExcept f (Success a) = Success (f a)
mapExcept _ (Error e)   = Error e

mapPrioritised :: (a -> b) -> (Prioritised a -> Prioritised b)
mapPrioritised f wrapped = case wrapped of
  Low x    -> Low (f x)
  Medium x -> Medium (f x)
  High x   -> High (f x)

mapStream :: (a -> b) -> (Stream a -> Stream b)
mapStream f (a :> rest) = f a :> mapStream f rest

mapList :: (a -> b) -> (List a -> List b)
mapList f (a :. rest) = f a :. mapList f rest
mapList _ _           = Nil

mapFun :: (a -> b) -> (Fun i a -> Fun i b)
mapFun f (F g) = F (f . g)

mapTree :: (a -> b) -> (Tree a -> Tree b)
mapTree f (Branch l x r) = Branch (mapTree f l) (f x) (mapTree f r)
mapTree _ _              = Leaf
