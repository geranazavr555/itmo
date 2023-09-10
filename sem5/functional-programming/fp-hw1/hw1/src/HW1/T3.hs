module HW1.T3 (
  Tree (..),
  tsize, tdepth, tmember, tinsert, tFromList
) where

type SizeAndDepth = (Int, Int)

data Tree a = Leaf | Branch SizeAndDepth (Tree a) a (Tree a)
  deriving(Show)

-- | Size of the tree, O(1).
tsize :: Tree a -> Int
tsize Leaf                     = 0
tsize (Branch (size, _) _ _ _) = size

-- | Depth of the tree.
tdepth :: Tree a -> Int
tdepth Leaf                      = 0
tdepth (Branch (_, depth) _ _ _) = depth

-- | Check if the element is in the tree, O(log n)
tmember :: Ord a => a -> Tree a -> Bool
tmember _ Leaf = False
tmember x (Branch _ left val right)
  | x < val   = tmember x left
  | x > val   = tmember x right
  | otherwise = True

balance :: Tree a -> Tree a -> Int
balance left right = rd - ld where
  ld = tdepth left
  rd = tdepth right

balanceTree :: Tree a -> Int
balanceTree Leaf             = 0
balanceTree (Branch _ l _ r) = balance l r

cntSizeAndDepth :: Tree a -> Tree a -> SizeAndDepth
cntSizeAndDepth left right = (tsize left + tsize right + 1, max (tdepth left) (tdepth right) + 1)

mkBranchNaive :: Tree a -> a -> Tree a -> Tree a
mkBranchNaive left val right = Branch (cntSizeAndDepth left right) left val right

rotateLeft :: Tree a -> Tree a
rotateLeft (Branch _ l val (Branch _ rl rval rr)) =
  Branch (cntSizeAndDepth lnew rr) lnew rval rr
    where
      lnew = Branch (cntSizeAndDepth l rl) l val rl
rotateLeft tree = tree

rotateRight :: Tree a -> Tree a
rotateRight (Branch _ (Branch _ ll lval lr) val r) =
  Branch (cntSizeAndDepth ll rnew) ll lval rnew
    where
      rnew = Branch (cntSizeAndDepth lr r) lr val r
rotateRight tree = tree

mkBranch :: Tree a -> a -> Tree a -> Tree a
mkBranch l val r = case balance l r of
  (-2) -> if balanceTree l > 0
          then rotateRight (mkBranchNaive (rotateLeft l) val r)
          else rotateRight (mkBranchNaive l val r)
  2    -> if balanceTree r < 0
          then rotateLeft (mkBranchNaive l val (rotateRight r))
          else rotateLeft (mkBranchNaive l val r)
  _    -> mkBranchNaive l val r

-- | Insert an element into the tree, O(log n)
tinsert :: Ord a => a -> Tree a -> Tree a
tinsert x Leaf = mkBranch Leaf x Leaf
tinsert x tree@(Branch _ left val right)
  | x < val   = mkBranch (tinsert x left) val right
  | x > val   = mkBranch left val (tinsert x right)
  | otherwise = tree

-- | Build a tree from a list, O(n log n)
tFromList :: Ord a => [a] -> Tree a
tFromList = foldr tinsert Leaf
