module HW1.T2 (
  N (..),
  nplus, nmult, nsub, ncmp, nToNum, nFromNatural, nEven, nOdd, ndiv, nmod
) where

import Numeric.Natural

data N = Z | S N
  deriving(Show)

nplus :: N -> N -> N        -- addition
nplus Z x     = x
nplus x Z     = x
nplus x (S y) = nplus (S x) y

nmult :: N -> N -> N        -- multiplication
nmult Z _     = Z
nmult _ Z     = Z
nmult x (S y) = nplus (nmult x y) x

nsub :: N -> N -> Maybe N   -- subtraction     (Nothing if result is negative)
nsub x Z         = Just x
nsub Z (S _)     = Nothing
nsub (S x) (S y) = nsub x y

ncmp :: N -> N -> Ordering  -- comparison      (Do not derive Ord)
ncmp Z Z         = EQ
ncmp (S _) Z     = GT
ncmp Z (S _)     = LT
ncmp (S x) (S y) = ncmp x y

nFromNatural :: Natural -> N
nFromNatural 0 = Z
nFromNatural n = S $ nFromNatural (n - 1)

nToNum :: Num a => N -> a
nToNum Z     = 0
nToNum (S n) = nToNum n + 1

-- Advanced

nEven :: N -> Bool    -- parity checking
nEven Z         = True
nEven (S Z)     = False
nEven (S (S n)) = nEven n

nOdd :: N -> Bool    -- parity checking
nOdd = not . nEven

ndiv :: N -> N -> N         -- integer division
ndiv Z _ = Z
ndiv x y = case nsub x y of
  Just xmy -> nplus (S Z) (ndiv xmy y)
  Nothing  -> Z

nmod :: N -> N -> N         -- modulo operation
nmod x y = case nsub x ((x `ndiv` y) `nmult` y) of
  Just result -> result
  Nothing     -> error "Unreachable case"
