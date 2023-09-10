{-# LANGUAGE BlockArguments             #-}
{-# LANGUAGE DerivingStrategies         #-}
{-# LANGUAGE GeneralizedNewtypeDeriving #-}

module HW2.T6 (
  ParseError(..),
  Parser(..),
  runP,
  pChar,
  parseError,
  pEof,
  pAbbr,
  parseExpr
) where

import           Control.Applicative (Alternative (..))
import           Control.Monad       (MonadPlus, mfilter)
import qualified Data.Char
import qualified Data.Scientific     as Scientific
import           GHC.Natural         (Natural)
import           HW2.T1              (Annotated (..), Except (..))
import           HW2.T4              (Expr (..), Prim (..))
import           HW2.T5              (ExceptState (..), runES)

newtype ParseError = ErrorAtPos Natural

newtype Parser a = P (ExceptState ParseError (Natural, String) a)
  deriving newtype (Functor, Applicative, Monad)

runP :: Parser a -> String -> Except ParseError a
runP (P exceptState) str = case runES exceptState (0, str) of
  Success (result :# _) -> Success result
  Error e               -> Error e

-- Tries to consume a first character from the string.
-- Is the string is empty returns an ErrorAtPos 'alreadyConsumedChars' error.
-- Otherwise modifies 'alreadyConsumedChars' in the state, drops the first
-- character in the string and returns it as a result.
pChar :: Parser Char
pChar = P $ ES \(pos, s) ->
  case s of
    []     -> Error (ErrorAtPos pos)
    (c:cs) -> Success (c :# (pos + 1, cs))

pExactChar :: Char -> Parser Char
pExactChar c = P $ ES \(pos, s) ->
  case s of
    []     -> Error (ErrorAtPos pos)
    (x:xs) -> if c == x then Success (x :# (pos + 1, xs)) else Error (ErrorAtPos pos)

parseError :: Parser a
parseError = P $ ES $ \(pos, _) -> Error $ ErrorAtPos pos

instance Alternative Parser where
  empty = parseError
  (P exceptState1) <|> (P exceptState2) = P $ ES $ \state -> case runES exceptState1 state of
    Success result -> Success result
    Error _        -> runES exceptState2 state

instance MonadPlus Parser   -- No methods.

pEof :: Parser ()
pEof = P $ ES $ \(pos, s) ->
  case s of
    [] -> Success (() :# (pos, s))
    _  -> Error $ ErrorAtPos pos

pAbbr :: Parser String
pAbbr = do
  abbr <- some (mfilter Data.Char.isUpper pChar)
  pEof
  return abbr

pDigitChar :: Parser String
pDigitChar = do some (mfilter Data.Char.isDigit pChar)

pSpaces :: Parser ()
pSpaces = do
  _ <- many (mfilter Data.Char.isSpace pChar)
  return ()

digitCharVal :: Char -> Integer
digitCharVal x = fromIntegral (Data.Char.ord x - Data.Char.ord '0')

parseInteger :: String -> Integer
parseInteger s = foldl f 0 (map digitCharVal s)
  where
    f :: Integer -> Integer -> Integer
    f x y = x * 10 + y

pFloatNumber :: Parser Double
pFloatNumber = do
  intPart <- pDigitChar
  _ <- pExactChar '.'
  floatPart <- pDigitChar
  let int = parseInteger (intPart ++ floatPart)
  return $ Scientific.toRealFloat (Scientific.scientific int (- (length floatPart)))

pIntNumber :: Parser Double
pIntNumber = do
  intPart <- pDigitChar
  let int = parseInteger intPart
  return $ Scientific.toRealFloat (Scientific.scientific int 0)

pNumber :: Parser Double
pNumber = pFloatNumber <|> pIntNumber

pOp :: (Expr -> Expr -> Prim Expr) -> Char -> Parser (Expr -> Expr -> Prim Expr)
pOp opConstructor char = do
  _ <- pExactChar char
  return opConstructor

pAdditiveOp :: Parser (Expr -> Expr -> Prim Expr)
pAdditiveOp = pOp Add '+' <|> pOp Sub '-'

pMultiplicativeOp :: Parser (Expr -> Expr -> Prim Expr)
pMultiplicativeOp = pOp Mul '*' <|> pOp Div '/'

pVal :: Parser Expr
pVal = do Val <$> pNumber

pExpr :: Parser Expr
pExpr = (pExactChar '(' *> pAdditive <* pExactChar ')') <|> pVal

pMultiplicativeRest :: Parser (Expr -> Expr)
pMultiplicativeRest = (
    do
      _ <- pSpaces
      opConstructor <- pMultiplicativeOp
      _ <- pSpaces
      expr <- pExpr
      _ <- pSpaces
      rest <- pMultiplicativeRest
      _ <- pSpaces
      return $ \left -> rest (Op (opConstructor left expr))
  ) <|> (
    do
      _ <- pSpaces
      return id
  )

pAdditiveRest :: Parser (Expr -> Expr)
pAdditiveRest = (
    do
      _ <- pSpaces
      opConstructor <- pAdditiveOp
      _ <- pSpaces
      expr <- pMultiplicative
      _ <- pSpaces
      rest <- pAdditiveRest
      _ <- pSpaces
      return $ \left -> rest (Op (opConstructor left expr))
  ) <|> (
    do
      _ <- pSpaces
      return id
  )


pMultiplicative :: Parser Expr
pMultiplicative = do
  _ <- pSpaces
  left <- pExpr
  _ <- pSpaces
  right <- pMultiplicativeRest
  _ <- pSpaces
  return $ right left

pAdditive :: Parser Expr
pAdditive = do
  _ <- pSpaces
  left <- pMultiplicative
  _ <- pSpaces
  right <- pAdditiveRest
  _ <- pSpaces
  return $ right left

pFullExpr :: Parser Expr
pFullExpr = pAdditive <* pEof

parseExpr :: String -> Except ParseError Expr
parseExpr = runP pFullExpr
