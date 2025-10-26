#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt.
    2. Run the tests by executing: `python async-ap/ap_quiz_package06.py`

This is a list of quiz questions for practice, including:
1.  gHappy: Check if all 'g' chars in the string are happy (surrounded by 'g's).
2.  canBalance: Determine if a list can be split into two parts with equal sums.
3.  no14: Check if a list contains no 1's or no 4's.
4.  isEverywhere: Verify if a value appears in every adjacent pair in a list. 
5.  repeatEnd: Create a new string by repeating the last n characters of a string n times.
6.  mixString: Merge two strings by alternating their characters.
7.  xyBalance: Check if all 'x' chars in a string are followed by a 'y' char later in the string.
8.  bobThere: Check if a string contains the pattern 'b?b' ('b', any char, 'b').
9.  roundSum: Round three integers to the nearest ten and return their sum.
10. countTriple: Count the number of triples (three identical chars in a row) in a string.
"""

# Import the unittest module, needed for creating test cases.
# We only need to import it once at the top of the file.
import unittest

# Import List from the typing module for type hinting (e.g., List[str])
# This is preferred for compatibility with Python versions < 3.10
from typing import List

































# -----------------------------------------------------------------
# Question 1: gHappy
# -----------------------------------------------------------------
def gHappy(s: str) -> bool:
    """
    Description:
        We'll say that a lowercase 'g' in a string is "happy" if there is another 'g'
        immediately to its left or right. Return True if all the 'g's in the given string
        are happy.

    Examples:
        gHappy("xxggxx") -> True
        gHappy("xxgxx") -> False
        gHappy("xxggyygxx") -> False

    Args:
        s (str): The input string.

    Returns:
        bool: True if every 'g' in the string is happy, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.



# -----------------------------------------------------------------
# Question 2: canBalance
# -----------------------------------------------------------------
def canBalance(nums: list[int]) -> bool:
    """
    Description:
        Given a non-empty list of integers, return True if there is a place to split the list
        so that the sum of the numbers on one side is equal to the sum of the numbers on the other side.

    Examples:
        canBalance([1, 1, 1, 2, 1]) -> True   # split after third 1: sum([1,1,1]) == sum([2,1])
        canBalance([2, 1, 1, 2, 1]) -> False
        canBalance([10, 10]) -> True
        canBalance([10, 0, 1, -1, 10]) -> True
        canBalance([1, 1, 1, 1, 4]) -> True
        canBalance([2, 1, 1, 1, 4]) -> False
        canBalance([2, 3, 4, 1, 2]) -> False
        canBalance([1, 2, 3, 1, 0, 2, 3]) -> True
        canBalance([1, 2, 3, 1, 0, 1, 3]) -> False
        canBalance([1]) -> False

    Args:
        nums (list[int]): A non-empty list of integers.

    Returns:
        bool: True if there exists an index where the sum of the elements to the left
              equals the sum of the elements to the right.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 3: no14
# -----------------------------------------------------------------
def no14(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if it contains no 1's or it contains no 4's (or both).
        In other words, it fails only if there is at least one 1 and at least one 4.

    Examples:
        no14([1, 2, 3]) -> True   # has 1's but no 4's
        no14([1, 2, 3, 4]) -> False  # has both 1 and 4
        no14([2, 3, 4]) -> True   # has 4's but no 1's

    Args:
        nums (list[int]): The list of integers to examine.

    Returns:
        bool: True if the list contains no 1's or no 4's, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.



# -----------------------------------------------------------------
# Question 4: isEverywhere
# -----------------------------------------------------------------
def isEverywhere(nums: list[int], val: int) -> bool:
    """
    Description:
        We'll say that a value is "everywhere" in a list if for every pair of adjacent elements
        in the list, at least one of the pair is that value. Return True if the given value is
        everywhere in the list. An empty list or a single-element list trivially satisfies this.

    Examples:
        isEverywhere([1, 2, 1, 3], 1) -> True
        isEverywhere([1, 2, 1, 3], 2) -> False
        isEverywhere([1, 2, 1, 3, 4], 1) -> False

    Args:
        nums (list[int]): The list of integers to check.
        val (int): The value to verify is "everywhere".

    Returns:
        bool: True if `val` is everywhere in `nums`, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 5: repeatEnd
# -----------------------------------------------------------------
def repeatEnd(s: str, n: int) -> str:
    """
    Description:
        Given a string `s` and an integer `n`, return a new string made of `n` repetitions
        of the last `n` characters of `s`. You may assume that 0 ≤ n ≤ len(s).

    Examples:
        repeatEnd("Hello", 3) -> "llollollo"
        repeatEnd("Hello", 2) -> "lolo"
        repeatEnd("Hello", 1) -> "o"

    Args:
        s (str): The input string.
        n (int): Number of characters from the end to repeat (0 ≤ n ≤ len(s)).

    Returns:
        str: A string consisting of `n` copies of the last `n` characters of `s`.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 6: mixString
# -----------------------------------------------------------------
def mixString(a: str, b: str) -> str:
    """
    Description:
        Given two strings, `a` and `b`, create a new string by alternating characters:
        take the first char of `a`, then the first char of `b`, then the second char of `a`,
        then the second char of `b`, and so on. Any leftover characters from the longer string
        go at the end of the result.

    Examples:
        mixString("abc", "xyz") -> "axbycz"
        mixString("Hi", "There") -> "HTihere"
        mixString("xxxx", "There") -> "xTxhxexre"

    Args:
        a (str): First input string.
        b (str): Second input string.

    Returns:
        str: The merged string with alternating characters.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.



# -----------------------------------------------------------------
# Question 7: xyBalance
# -----------------------------------------------------------------
def xyBalance(s: str) -> bool:
    """
    Description:
        We'll say that a string is xy-balanced if for all the 'x' characters in the string,
        there exists a 'y' character somewhere later in the string. One 'y' can balance multiple 'x's.
        Return True if the given string is xy-balanced.

    Examples:
        xyBalance("aaxbby") -> True    # both x's have a y after them
        xyBalance("aaxbb") -> False    # x at pos 2 has no y after
        xyBalance("yaaxbb") -> False   # same, no y after the x's

    Args:
        s (str): The input string to check for xy-balance.

    Returns:
        bool: True if the string is xy-balanced, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 8: bobThere
# -----------------------------------------------------------------
def bobThere(s: str) -> bool:
    """
    Description:
        Return True if the given string contains a "bob" pattern—i.e. a 'b', then any character, then another 'b'.
        The middle character can be any char. Overlapping patterns count.

    Examples:
        bobThere("abcbob") -> True    # "bob" at positions 3-5
        bobThere("b9b") -> True       # "b9b"
        bobThere("bbb") -> True       # first and third 'b'

    Args:
        s (str): The input string to search.

    Returns:
        bool: True if any substring of the form 'b?b' appears, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 9: roundSum
# -----------------------------------------------------------------
def roundSum(a: int, b: int, c: int) -> int:
    """
    Description:
        Given three integers a, b, c, round each to the nearest multiple of 10
        (rounding up if the rightmost digit is 5 or more, otherwise down),
        then return the sum of the rounded values.
        Use a helper function round10(num) to perform the rounding for each value.

    Examples:
        roundSum(16, 17, 18) -> 60   # 20 + 20 + 20
        roundSum(12, 13, 14) -> 30   # 10 + 10 + 10
        roundSum(6, 4, 4)   -> 10    # 10 + 0 + 0

    Args:
        a (int): First integer.
        b (int): Second integer.
        c (int): Third integer.

    Returns:
        int: Sum of the three values after rounding each to nearest ten.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 10: countTriple
# -----------------------------------------------------------------
def countTriple(s: str) -> int:
    """
    Description:
        We'll say that a "triple" in a string is a char appearing three times in a row.
        Return the number of triples in the given string. Triples may overlap.

    Examples:
        countTriple("abcXXXabc") -> 1
        countTriple("xxxabyyyycd") -> 3
        countTriple("a") -> 0

    Args:
        s (str): The input string to examine for triples.

    Returns:
        int: The count of positions where a character appears three times in a row.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.
















# -----------------------------------------------------------------
# Unit Tests
# -----------------------------------------------------------------
class TestGHappy(unittest.TestCase):
    def test_gHappy(self):
        self.assertTrue(gHappy("xxggxx"))
        self.assertFalse(gHappy("xxgxx"))
        self.assertFalse(gHappy("xxggyygxx"))
        self.assertFalse(gHappy("g"))
        self.assertTrue(gHappy("gg"))
        self.assertTrue(gHappy(""))
        self.assertTrue(gHappy("xxgggxyz"))
        self.assertFalse(gHappy("xxgggxyg"))
        self.assertTrue(gHappy("xxgggxygg"))
        self.assertFalse(gHappy("mgm"))
        self.assertTrue(gHappy("mggm"))
        self.assertTrue(gHappy("yyygggxyy"))

class TestCanBalance(unittest.TestCase):
    def test_canBalance(self):
        self.assertTrue(canBalance([1, 1, 1, 2, 1]))
        self.assertFalse(canBalance([2, 1, 1, 2, 1]))
        self.assertTrue(canBalance([10, 10]))
        self.assertTrue(canBalance([10, 0, 1, -1, 10]))
        self.assertTrue(canBalance([1, 1, 1, 1, 4]))
        self.assertFalse(canBalance([2, 1, 1, 1, 4]))
        self.assertFalse(canBalance([2, 3, 4, 1, 2]))
        self.assertTrue(canBalance([1, 2, 3, 1, 0, 2, 3]))
        self.assertFalse(canBalance([1, 2, 3, 1, 0, 1, 3]))
        self.assertFalse(canBalance([1]))

class TestNo14(unittest.TestCase):
    def test_no14(self):
        self.assertTrue(no14([1, 2, 3]))
        self.assertFalse(no14([1, 2, 3, 4]))
        self.assertTrue(no14([2, 3, 4]))
        self.assertFalse(no14([1, 1, 4, 4]))
        self.assertTrue(no14([2, 2, 4, 4]))
        self.assertFalse(no14([2, 3, 4, 1]))
        self.assertTrue(no14([2,1,1]))
        self.assertFalse(no14([1, 4]))
        self.assertTrue(no14([2]))
        self.assertTrue(no14([2, 1]))
        self.assertTrue(no14([1]))
        self.assertTrue(no14([4]))
        self.assertTrue(no14([]))
        self.assertTrue(no14([1, 1, 1, 1]))
        self.assertFalse(no14([9, 4, 4, 1]))
        self.assertFalse(no14([4, 2, 3, 1]))
        self.assertTrue(no14([4, 2, 3, 5]))
        self.assertTrue(no14([4, 4, 2]))
        self.assertFalse(no14([1, 4, 4]))

class TestIsEverywhere(unittest.TestCase):
    def test_isEverywhere(self):
        self.assertTrue(isEverywhere([1, 2, 1, 3], 1))
        self.assertFalse(isEverywhere([1, 2, 1, 3], 2))
        self.assertFalse(isEverywhere([1, 2, 1, 3, 4], 1))
        self.assertTrue(isEverywhere([2, 1, 2, 1], 1))
        self.assertTrue(isEverywhere([2, 1, 2, 1], 2))
        self.assertFalse(isEverywhere([2, 1, 2, 3, 1], 2))
        self.assertTrue(isEverywhere([3, 1], 3))
        self.assertFalse(isEverywhere([3, 1], 2))
        self.assertTrue(isEverywhere([3], 1))
        self.assertTrue(isEverywhere([], 1))
        self.assertTrue(isEverywhere([1, 2, 1, 2, 3, 2, 5], 2))
        self.assertFalse(isEverywhere([1, 2, 1, 1, 1, 2], 2))
        self.assertFalse(isEverywhere([2, 1, 2, 1, 1, 2], 2))
        self.assertFalse(isEverywhere([2, 1, 2, 2, 2, 1, 1, 2], 2))
        self.assertTrue(isEverywhere([2, 1, 2, 2, 2, 1, 2, 1], 2))
        self.assertTrue(isEverywhere([2, 1, 2, 1, 2], 2))


class TestRepeatEnd(unittest.TestCase):
    def test_repeatEnd(self):
        self.assertEqual(repeatEnd("Hello", 3), "llollollo")
        self.assertEqual(repeatEnd("Hello", 2), "lolo")
        self.assertEqual(repeatEnd("Hello", 1), "o")
        self.assertEqual(repeatEnd("Hello", 0), "")
        self.assertEqual(repeatEnd("abc", 3), "abcabcabc")
        self.assertEqual(repeatEnd("1234", 2), "3434")
        self.assertEqual(repeatEnd("1234", 3), "234234234")
        self.assertEqual(repeatEnd("", 0), "")


class TestMixString(unittest.TestCase):
    def test_mixString(self):
        self.assertEqual(mixString("abc", "xyz"), "axbycz")
        self.assertEqual(mixString("Hi", "There"), "HTihere")
        self.assertEqual(mixString("xxxx", "There"), "xTxhxexre")
        self.assertEqual(mixString("xxx", "X"), "xXxx")
        self.assertEqual(mixString("2/", "27 around"), "22/7 around")
        self.assertEqual(mixString("", "Hello"), "Hello")
        self.assertEqual(mixString("Abc", ""), "Abc")
        self.assertEqual(mixString("", ""), "")
        self.assertEqual(mixString("a", "b"), "ab")
        self.assertEqual(mixString("ax", "b"), "abx")
        self.assertEqual(mixString("a", "bx"), "abx")
        self.assertEqual(mixString("So", "Long"), "SLoong")
        self.assertEqual(mixString("Long", "So"), "LSoong")

class TestXyBalance(unittest.TestCase):
    def test_xyBalance(self):
        self.assertTrue(xyBalance("aaxbby"))
        self.assertFalse(xyBalance("aaxbb"))
        self.assertFalse(xyBalance("yaaxbb"))
        self.assertTrue(xyBalance("yaaxbby"))
        self.assertTrue(xyBalance("xaxxbby"))
        self.assertFalse(xyBalance("xaxxbbyx"))
        self.assertTrue(xyBalance("xxbxy"))
        self.assertFalse(xyBalance("xxbx"))
        self.assertTrue(xyBalance("bbb"))
        self.assertFalse(xyBalance("bxbb"))
        self.assertTrue(xyBalance("bxyb"))
        self.assertTrue(xyBalance("xy"))
        self.assertTrue(xyBalance("y"))
        self.assertFalse(xyBalance("x"))
        self.assertTrue(xyBalance(""))
        self.assertFalse(xyBalance("yxyxyxyx"))
        self.assertTrue(xyBalance("yxyxyxyxy"))
        self.assertTrue(xyBalance("12xabxxydxyxyzz"))

class TestBobThere(unittest.TestCase):
    def test_bobThere(self):
        self.assertTrue(bobThere("abcbob"))
        self.assertTrue(bobThere("b9b"))
        self.assertFalse(bobThere("bac"))
        self.assertTrue(bobThere("bbb"))
        self.assertFalse(bobThere("abcdefb"))
        self.assertTrue(bobThere("123abcbcdbabxyz"))
        self.assertFalse(bobThere("b12"))
        self.assertTrue(bobThere("b1b"))
        self.assertTrue(bobThere("b12b1b"))
        self.assertFalse(bobThere("bbc"))
        self.assertFalse(bobThere("bb"))
        self.assertFalse(bobThere("b"))

class TestRoundSum(unittest.TestCase):
    def test_roundSum(self):
        self.assertEqual(roundSum(16, 17, 18), 60)
        self.assertEqual(roundSum(12, 13, 14), 30)
        self.assertEqual(roundSum(6, 4, 4), 10)
        self.assertEqual(roundSum(4, 6, 5), 20)
        self.assertEqual(roundSum(4, 4, 6), 10)
        self.assertEqual(roundSum(9, 4, 4), 10)
        self.assertEqual(roundSum(0, 0, 1), 0)
        self.assertEqual(roundSum(0, 9, 0), 10)
        self.assertEqual(roundSum(10, 10, 19), 40)
        self.assertEqual(roundSum(20, 30, 40), 90)
        self.assertEqual(roundSum(45, 21, 30), 100)
        self.assertEqual(roundSum(23, 11, 26), 60)
        self.assertEqual(roundSum(23, 24, 25), 70)
        self.assertEqual(roundSum(25, 24, 25), 80)
        self.assertEqual(roundSum(23, 24, 29), 70)
        self.assertEqual(roundSum(11, 24, 36), 70)
        self.assertEqual(roundSum(24, 36, 32), 90)
        self.assertEqual(roundSum(14, 12, 26), 50)
        self.assertEqual(roundSum(12, 10, 24), 40)

class TestCountTriple(unittest.TestCase):
    def test_countTriple(self):
        self.assertEqual(countTriple("abcXXXabc"), 1)
        self.assertEqual(countTriple("xxxabyyyycd"), 3)
        self.assertEqual(countTriple("a"), 0)
        self.assertEqual(countTriple(""), 0)
        self.assertEqual(countTriple("XXXabc"), 1)
        self.assertEqual(countTriple("XXXXabc"), 2)
        self.assertEqual(countTriple("XXXXXabc"), 3)
        self.assertEqual(countTriple("222abyyycdXXX"), 3)
        self.assertEqual(countTriple("abYYYabXXXXXab"), 4)
        self.assertEqual(countTriple("abYYXabXXYXXab"), 0)
        self.assertEqual(countTriple("122abhhh2"), 1)

# -----------------------------------------------------------------
# Main execution block
# -----------------------------------------------------------------

if __name__ == "__main__":
    """
    This conditional block checks if the script is being run directly.
    If it is, it calls `unittest.main()`.
    
    `unittest.main()` discovers all test cases (TestWordsFront
    and TestWordsWithoutList) and runs them.
    
    `verbosity=2` is added to provide a more detailed output,
    showing the result of each test method individually, which
    satisfies the requirement for clear results for each function.
    """
    unittest.main(verbosity=2)