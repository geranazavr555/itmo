module HW1.T5 (
  splitOn, joinWith
) where

import Data.List.NonEmpty (NonEmpty (..), (<|))

splitHelper :: Eq a => a -> a -> NonEmpty [a] -> NonEmpty [a]
splitHelper sep x accum@(cur :| rest) = if sep == x
                                        then [] <| accum
                                        else (x : cur) :| rest

splitOn :: Eq a => a -> [a] -> NonEmpty [a]
splitOn sep = foldr (splitHelper sep) ([] :| [])

joinHelper :: a -> [[a]] -> [[a]]
joinHelper sep = map (sep :)

joinWith :: a -> NonEmpty [a] -> [a]
joinWith _ (el :| [])  = el
joinWith sep (el :| l) = concat (el : joinHelper sep l)
