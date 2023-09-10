module HW1.T4 (
  tfoldr, treeToList
) where

import HW1.T3

tfoldr :: (a -> b -> b) -> b -> Tree a -> b
tfoldr _ accum Leaf                      = accum
tfoldr f accum (Branch _ left val right) = tfoldr f (f val (tfoldr f accum right)) left

treeToList :: Tree a -> [a]    -- output list is sorted
treeToList = tfoldr (:) []
