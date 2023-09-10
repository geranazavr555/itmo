module HW1.T6 (
  mcat, epart
) where

mcatHelper :: Monoid a => Maybe a -> a -> a
mcatHelper Nothing accum  = accum
mcatHelper (Just x) accum = x <> accum

mcat :: Monoid a => [Maybe a] -> a
mcat = foldr mcatHelper mempty


epartHelper :: (Monoid a, Monoid b) => Either a b -> (a, b) -> (a, b)
epartHelper (Left x) (a, b)  = (x <> a, b)
epartHelper (Right x) (a, b) = (a, x <> b)

epart :: (Monoid a, Monoid b) => [Either a b] -> (a, b)
epart = foldr epartHelper (mempty, mempty)
