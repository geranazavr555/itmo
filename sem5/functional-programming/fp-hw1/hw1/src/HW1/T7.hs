{-# LANGUAGE InstanceSigs #-}

module HW1.T7 (
  ListPlus (..),
  Inclusive (..),
  DotString (..),
  Fun (..)
) where

import Data.Semigroup ((<>))

infixr 5 :+
data ListPlus a = a :+ ListPlus a | Last a
  deriving (Show)

instance Semigroup (ListPlus a) where
  (<>) :: ListPlus a -> ListPlus a -> ListPlus a
  (<>) (Last x) rest  = x :+ rest
  (<>) (x :+ xs) rest = x :+ (xs <> rest)


data Inclusive a b = This a | That b | Both a b
  deriving (Show)

instance (Semigroup a, Semigroup b) => Semigroup (Inclusive a b) where
  (<>) :: Inclusive a b -> Inclusive a b -> Inclusive a b
  (<>) (This x) (This y)         = This (x <> y)
  (<>) (This x) (That y)         = Both x y
  (<>) (This x) (Both y1 y2)     = Both (x <> y1) y2
  (<>) (That x) (This y)         = Both y x
  (<>) (That x) (That y)         = That (x <> y)
  (<>) (That x) (Both y1 y2)     = Both y1 (x <> y2)
  (<>) (Both x1 x2) (This y)     = Both (x1 <> y) x2
  (<>) (Both x1 x2) (That y)     = Both x1 (x2 <> y)
  (<>) (Both x1 x2) (Both y1 y2) = Both (x1 <> y1) (x2 <> y2)


newtype DotString = DS String
  deriving (Show)

instance Semigroup DotString where
  (<>) :: DotString -> DotString -> DotString
  (<>) x (DS "")     = x
  (<>) (DS "") y     = y
  (<>) (DS x) (DS y) = DS (x ++ ('.' : y))

instance Monoid DotString where
  mempty :: DotString
  mempty = DS ""

  mappend :: DotString -> DotString -> DotString
  mappend = (Data.Semigroup.<>)


newtype Fun a = F (a -> a)

instance Semigroup (Fun a) where
  (<>) :: Fun a -> Fun a -> Fun a
  (<>) (F x) (F y) = F (x . y)

instance Monoid (Fun a) where
  mempty :: Fun a
  mempty = F id

  mappend :: Fun a -> Fun a -> Fun a
  mappend = (Data.Semigroup.<>)
