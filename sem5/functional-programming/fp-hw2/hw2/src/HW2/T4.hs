{-# LANGUAGE InstanceSigs #-}

module HW2.T4 (
  State(..), mapState, wrapState, joinState, modifyState,
  Prim(..),
  Expr(..),
  eval
) where

import qualified Control.Monad
import           HW2.T1

newtype State s a = S { runS :: s -> Annotated s a }

wrapState :: a -> State s a
wrapState f = S (f :#)

mapState :: (a -> b) -> State s a -> State s b
mapState mapF state = S $ mapAnnotated mapF . runS state

joinState :: State s (State s a) -> State s a
joinState state = S $ \s -> let (a :# newState) = runS state s
                            in runS a newState

modifyState :: (s -> s) -> State s ()
modifyState mapF = S $ \s -> () :# mapF s

instance Functor (State s) where
  fmap = mapState

instance Applicative (State s) where
  pure = wrapState
  p <*> q = Control.Monad.ap p q

instance Monad (State s) where
  m >>= f = joinState (fmap f m)


data Prim a =
    Add a a
  | Sub a a
  | Mul a a
  | Div a a
  | Abs a
  | Sgn a 

data Expr = Val Double | Op (Prim Expr) 

instance Num Expr where
  abs :: Expr -> Expr
  abs expr = Op $ Abs expr

  signum :: Expr -> Expr
  signum expr = Op $ Sgn expr

  (+) :: Expr -> Expr -> Expr
  x + y = Op $ Add x y

  (-) :: Expr -> Expr -> Expr
  x - y = Op $ Sub x y

  (*) :: Expr -> Expr -> Expr
  x * y = Op $ Mul x y

  fromInteger :: Integer -> Expr
  fromInteger x = Val $ fromInteger x

instance Fractional Expr where
  (/) :: Expr -> Expr -> Expr
  x / y = Op $ Div x y

  fromRational :: Rational -> Expr
  fromRational x = Val $ fromRational x

binaryOp :: (Double -> Double -> Prim Double) ->
            (Double -> Double -> Double) ->
            Expr ->
            Expr ->
            State [Prim Double] Double
binaryOp opConstructor op x y = do
  resx <- eval x
  resy <- eval y
  modifyState (opConstructor resx resy :)
  return $ resx `op` resy
  
  
unaryOp :: (Double -> Prim Double) ->
           (Double -> Double) ->
           Expr ->
           State [Prim Double] Double  
unaryOp opConstructor op x = do
  resx <- eval x
  modifyState (opConstructor resx :)
  return $ op resx        

eval :: Expr -> State [Prim Double] Double
eval (Val x) = pure x
eval (Op (Add x y)) = binaryOp Add (+) x y
eval (Op (Sub x y)) = binaryOp Sub (-) x y
eval (Op (Mul x y)) = binaryOp Mul (*) x y
eval (Op (Div x y)) = binaryOp Div (/) x y
eval (Op (Abs x)) = unaryOp Abs abs x
eval (Op (Sgn x)) = unaryOp Sgn signum x
