{-# LANGUAGE InstanceSigs #-}

module HW2.T5 (
  ExceptState(..), mapExceptState, wrapExceptState, joinExceptState, modifyExceptState, throwExceptState,
  EvaluationError(..),
  eval
) where

import qualified Control.Monad
import           HW2.T1
import           HW2.T4        (Expr (..), Prim (..))



newtype ExceptState e s a = ES {runES :: s -> Except e (Annotated s a)}

wrapExceptState :: a -> ExceptState e s a
wrapExceptState f = ES $ \s -> Success (f :# s)

throwExceptState :: e -> ExceptState e s a
throwExceptState e = ES $ \_ -> Error e

mapExceptState :: (a -> b) -> ExceptState e s a -> ExceptState e s b
mapExceptState mapF exceptState = ES $ mapExcept (mapAnnotated mapF) . runES exceptState

modifyExceptState :: (s -> s) -> ExceptState e s ()
modifyExceptState mapF = ES $ \s -> Success (() :# mapF s)

joinExceptState :: ExceptState e s (ExceptState e s a) -> ExceptState e s a
joinExceptState state = ES $ \s -> case runES state s of
                                     Success (a :# newState) -> runES a newState
                                     Error e                 -> Error e

instance Functor (ExceptState e s) where
      fmap :: (a -> b) -> ExceptState e s a -> ExceptState e s b
      fmap = mapExceptState

instance Applicative (ExceptState e s) where
      pure :: a -> ExceptState e s a
      pure = wrapExceptState

      (<*>) :: ExceptState e s (a -> b) -> ExceptState e s a -> ExceptState e s b
      (<*>) = Control.Monad.ap

instance Monad (ExceptState e s) where
      (>>=) :: ExceptState e s a -> (a -> ExceptState e s b) -> ExceptState e s b
      exceptState >>= f = joinExceptState (fmap f exceptState)


data EvaluationError = DivideByZero

binaryOp :: (Double -> Double -> Prim Double) ->
            (Double -> Double -> Double) ->
            Expr ->
            Expr ->
            ExceptState EvaluationError [Prim Double] Double
binaryOp opConstructor op x y = do
  resx <- eval x
  resy <- eval y
  modifyExceptState (opConstructor resx resy :)
  return $ resx `op` resy

unaryOp :: (Double -> Prim Double) ->
           (Double -> Double) ->
           Expr ->
           ExceptState EvaluationError [Prim Double] Double
unaryOp opConstructor op x = do
  resx <- eval x
  modifyExceptState (opConstructor resx :)
  return $ op resx

eval :: Expr -> ExceptState EvaluationError [Prim Double] Double
eval (Val x) = pure x
eval (Op (Add x y)) = binaryOp Add (+) x y
eval (Op (Sub x y)) = binaryOp Sub (-) x y
eval (Op (Mul x y)) = binaryOp Mul (*) x y
eval (Op (Abs x)) = unaryOp Abs abs x
eval (Op (Sgn x)) = unaryOp Sgn signum x
eval (Op (Div x y)) = do
  resx <- eval x
  resy <- eval y
  if resy == 0 then
    throwExceptState DivideByZero
  else do
    modifyExceptState (\s -> Div resx resy : s)
    return $ resx / resy

