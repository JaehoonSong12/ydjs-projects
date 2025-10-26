#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt.
    2. Run the tests by executing: `python async-ap/ap_quiz_package05.py`

This is a list of quiz questions for practice, including:
1.  more14: Check if the array contains more 1's than 4's.
2.  luckySum: Sum three integers, ignoring 13 and values to its right.
3.  equalIsNot: Compare counts of "is" and "not" in a string.
4.  fix45: Rearrange list so every 4 is followed by a 5.
5.  repeatSeparator: Create a string with repeated words separated by a given separator.
6.  repeatFront: Build a string from the front of another string.
7.  fizzArray: Generate a list of integers from 0 to n-1.
8.  only14: Check if the list contains only 1's and 4's.
9.  fizzArray2: Generate a list of string representations of integers from 0 to n-1.
10. noTeenSum: Sum three integers, treating 'teen' values as 0 (with exceptions).
"""

# Import the unittest module, needed for creating test cases.
# We only need to import it once at the top of the file.
import unittest

# Import List from the typing module for type hinting (e.g., List[str])
# This is preferred for compatibility with Python versions < 3.10
from typing import List

































# -----------------------------------------------------------------
# Question 1: countCode
# -----------------------------------------------------------------
def more14(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the number of 1's in the list is greater than
        the number of 4's.

    Examples:
        more14([1, 4, 1]) -> True
        more14([1, 4, 1, 4]) -> False
        more14([1, 1]) -> True
        more14([1, 6, 6]) -> True

    Args:
        nums (list[int]): The list of integers to examine.

    Returns:
        bool: True if count of 1's is greater than count of 4's, otherwise False.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 2: luckySum
# -----------------------------------------------------------------
def luckySum(a: int, b: int, c: int) -> int:
    """
    Description:
        Given 3 integer values, a, b, c, return their sum. However, if one of the values
        is 13 then it does not count toward the sum and values to its right do not count.
        So for example, if b is 13, then both b and c do not count.

    Examples:
        luckySum(1, 2, 3) -> 6
        luckySum(1, 2, 13) -> 3
        luckySum(1, 13, 3) -> 1

    Args:
        a (int): First integer.
        b (int): Second integer.
        c (int): Third integer.

    Returns:
        int: The sum as described, skipping 13 and any values to its right.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 3: equalIsNot
# -----------------------------------------------------------------
def equalIsNot(s: str) -> bool:
    """
    Description:
        Given a string, return True if the number of appearances 
        of "is" anywhere in the string
        is equal to the number of appearances of "not" anywhere in 
        the string. The match is case-sensitive.

    Examples:
        equalIsNot("This is not") -> False
        equalIsNot("This is notnot") -> True
        equalIsNot("noisxxnotyynotxisi") -> True

    Args:
        s (str): The input string to examine.

    Returns:
        bool: True if count of "is" equals count of "not", otherwise False.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 4: fix45
# -----------------------------------------------------------------
def fix45(nums: list[int]) -> list[int]:
    """
    Description:
        Return a list containing exactly the same numbers as the given list, but rearranged so that every 4
        is immediately followed by a 5. Do not move the 4's themselves, but every other number may move.
        You may assume:
          - The list contains the same number of 4's and 5's.
          - Every 4 has a non-4 immediately after it.
          - 5's may appear anywhere in the original list.

    Examples:
        fix45([5, 4, 9, 4, 9, 5]) -> [9, 4, 5, 4, 5, 9]
        fix45([1, 4, 1, 5]) -> [1, 4, 5, 1]
        fix45([1, 4, 1, 5, 5, 4, 1]) -> [1, 4, 5, 1, 1, 4, 5]
        fix45([4, 2, 2, 5]) -> [4, 5, 2, 2]

    Args:
        nums (list[int]): The input list of integers containing matching numbers of 4's and 5's.

    Returns:
        list[int]: A new list where each 4 is immediately followed by a 5.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 5: repeatSeparator
# -----------------------------------------------------------------
def repeatSeparator(word: str, sep: str, count: int) -> str:
    """
    Description:
        Return a big string made of `count` occurrences of the given `word`, 
        separated by the given separator string `sep`.

    Examples:
        repeatSeparator("Word", "X", 3) -> "WordXWordXWord"
        repeatSeparator("This", "And", 2) -> "ThisAndThis"
        repeatSeparator("This", "And", 1) -> "This"

    Args:
        word (str): The word to repeat.
        sep   (str): The separator to place between words.
        count (int): The number of times to repeat `word`.

    Returns:
        str: The resulting string of repeated words separated by `sep`.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 6: repeatFront
# -----------------------------------------------------------------
def repeatFront(s: str, n: int) -> str:
    """
    Description:
        Given a string `s` and an integer `n`, return a string made of the first `n` characters
        of `s`, followed by the first `n-1` characters, and so on down to 1. You may assume
        0 ≤ n ≤ len(s).

    Examples:
        repeatFront("Chocolate", 4) -> "ChocChoChC"
        repeatFront("Chocolate", 3) -> "ChoChC"
        repeatFront("Ice Cream", 2) -> "IcI"

    Args:
        s (str): The input string.
        n (int): Number of characters to start with (0 ≤ n ≤ len(s)).

    Returns:
        str: The concatenated prefix sequence.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 7: fizzArray
# -----------------------------------------------------------------
def fizzArray(n: int) -> list[int]:
    """
    Description:
        Given a number n, create and return a new list of length n containing the numbers
        0, 1, 2, ... n-1. If n is 0, return an empty list.

    Examples:
        fizzArray(4) -> [0, 1, 2, 3]
        fizzArray(1) -> [0]
        fizzArray(10) -> [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]

    Args:
        n (int): Non-negative length of the array to generate.

    Returns:
        list[int]: A list of integers from 0 up to n-1.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 8: only14
# -----------------------------------------------------------------
def only14(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if every element is either a 1 or a 4.
        An empty list counts as True since there are no disallowed elements.

    Examples:
        only14([1, 4, 1, 4]) -> True
        only14([1, 4, 2, 4]) -> False
        only14([1, 1]) -> True

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if every element is 1 or 4, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 9: fizzArray2
# -----------------------------------------------------------------
def fizzArray2(n: int) -> list[str]:
    """
    Description:
        Given a non-negative integer n, create and return a new list of length n
        containing the string representations of the numbers 0, 1, 2, ... n-1.
        If n is 0, return an empty list.

    Examples:
        fizzArray2(4)  -> ["0", "1", "2", "3"]
        fizzArray2(10) -> ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]
        fizzArray2(2)  -> ["0", "1"]

    Args:
        n (int): The length of the array to generate (n ≥ 0).

    Returns:
        list[str]: A list of strings "0" through str(n-1).
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 10: noTeenSum
# -----------------------------------------------------------------
def noTeenSum(a: int, b: int, c: int) -> int:
    """
    Description:
        Given three integer values a, b, and c, return their sum.
        However, if any of the values is a 'teen' (in the range 13..19 inclusive),
        that value counts as 0, except 15 and 16 are not considered teens.
        Use a helper function fixTeen(n) to handle teen adjustment,
        avoiding repeating code.

    Examples:
        noTeenSum(1, 2, 3)   -> 6
        noTeenSum(2, 13, 1)  -> 3
        noTeenSum(2, 1, 14)  -> 3

    Args:
        a (int): First integer value.
        b (int): Second integer value.
        c (int): Third integer value.

    Returns:
        int: The sum after applying the teen rules.
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
class TestMore14(unittest.TestCase):
    def test_more14(self):
        self.assertTrue(more14([1, 4, 1]))
        self.assertFalse(more14([1, 4, 1, 4]))
        self.assertTrue(more14([1, 1]))
        self.assertTrue(more14([1, 6, 6]))
        self.assertTrue(more14([1]))
        self.assertFalse(more14([1, 4]))
        self.assertTrue(more14([6, 1, 1]))
        self.assertFalse(more14([1, 6, 4]))
        self.assertTrue(more14([1, 1, 4, 4, 1]))
        self.assertTrue(more14([1, 1, 6, 4, 4, 1]))
        self.assertFalse(more14([]))
        self.assertFalse(more14([4, 1, 4, 6]))
        self.assertFalse(more14([4, 1, 4, 6, 1]))
        self.assertTrue(more14([1, 4, 1, 4, 1, 6]))

class TestLuckySum(unittest.TestCase):
    def test_luckySum(self):
        self.assertEqual(luckySum(1, 2, 3), 6)
        self.assertEqual(luckySum(1, 2, 13), 3)
        self.assertEqual(luckySum(1, 13, 3), 1)
        self.assertEqual(luckySum(1, 13, 13), 1)
        self.assertEqual(luckySum(6, 5, 2), 13)
        self.assertEqual(luckySum(13, 2, 3), 0)
        self.assertEqual(luckySum(13, 2, 13), 0)
        self.assertEqual(luckySum(13, 13, 2), 0)
        self.assertEqual(luckySum(9, 4, 13), 13)
        self.assertEqual(luckySum(8, 13, 2), 8)
        self.assertEqual(luckySum(7, 2, 1), 10)
        self.assertEqual(luckySum(3, 3, 13), 6)

class TestEqualIsNot(unittest.TestCase):
    def test_equalIsNot(self):
        self.assertFalse(equalIsNot("This is not"))
        self.assertTrue(equalIsNot("This is notnot"))
        self.assertTrue(equalIsNot("noisxxnotyynotxisi"))
        self.assertFalse(equalIsNot("noisxxnotyynotxsi"))
        self.assertTrue(equalIsNot("xxxyyyzzzintint"))
        self.assertTrue(equalIsNot(""))
        self.assertTrue(equalIsNot("isisnotnot"))
        self.assertFalse(equalIsNot("isisnotno7Not"))
        self.assertFalse(equalIsNot("isnotis"))
        self.assertFalse(equalIsNot("mis3notpotbotis"))

class TestFix45(unittest.TestCase):
    def test_fix45(self):
        self.assertEqual(fix45([5, 4, 9, 4, 9, 5]), [9, 4, 5, 4, 5, 9])
        self.assertEqual(fix45([1, 4, 1, 5]), [1, 4, 5, 1])
        self.assertEqual(fix45([1, 4, 1, 5, 5, 4, 1]), [1, 4, 5, 1, 1, 4, 5])
        self.assertEqual(fix45([4, 9, 4, 9, 5, 5, 4, 9, 5]), [4, 5, 4, 5, 9, 9, 4, 5, 9])
        self.assertEqual(fix45([5, 5, 4, 1, 4, 1]), [1, 1, 4, 5, 4, 5])
        self.assertEqual(fix45([4, 2, 2, 5]), [4, 5, 2, 2])
        self.assertEqual(fix45([4, 2, 4, 2, 5, 5]), [4, 5, 4, 5, 2, 2])
        self.assertEqual(fix45([4, 2, 4, 5, 5]), [4, 5, 4, 5, 2])
        self.assertEqual(fix45([1, 1, 1]), [1, 1, 1])
        self.assertEqual(fix45([4, 5]), [4, 5])
        self.assertEqual(fix45([5, 4, 1]), [1, 4, 5])
        self.assertEqual(fix45([]), [])
        self.assertEqual(fix45([5, 4, 5, 4, 1]), [1, 4, 5, 4, 5])
        self.assertEqual(fix45([4, 5, 4, 1, 5]), [4, 5, 4, 5, 1])
        self.assertEqual(fix45([3, 4, 5]), [3, 4, 5])
        self.assertEqual(fix45([4, 1, 5]), [4, 5, 1])
        self.assertEqual(fix45([5, 4, 1]), [1, 4, 5])
        self.assertEqual(fix45([2, 4, 2, 5]), [2, 4, 5, 2])

class TestRepeatSeparator(unittest.TestCase):
    def test_repeatSeparator(self):
        self.assertEqual(repeatSeparator("Word", "X", 3), "WordXWordXWord")
        self.assertEqual(repeatSeparator("This", "And", 2), "ThisAndThis")
        self.assertEqual(repeatSeparator("This", "And", 1), "This")
        self.assertEqual(repeatSeparator("Hi", "-n-", 2), "Hi-n-Hi")
        self.assertEqual(repeatSeparator("AAA", "", 1), "AAA")
        self.assertEqual(repeatSeparator("AAA", "", 0), "")
        self.assertEqual(repeatSeparator("A", "B", 5), "ABABABABA")
        self.assertEqual(repeatSeparator("abc", "XX", 3), "abcXXabcXXabc")
        self.assertEqual(repeatSeparator("abc", "XX", 2), "abcXXabc")
        self.assertEqual(repeatSeparator("abc", "XX", 1), "abc")
        self.assertEqual(repeatSeparator("XYZ", "a", 2), "XYZaXYZ")

class TestRepeatFront(unittest.TestCase):
    def test_repeatFront(self):
        self.assertEqual(repeatFront("Chocolate", 4), "ChocChoChC")
        self.assertEqual(repeatFront("Chocolate", 3), "ChoChC")
        self.assertEqual(repeatFront("Ice Cream", 2), "IcI")
        self.assertEqual(repeatFront("Ice Cream", 1), "I")
        self.assertEqual(repeatFront("Ice Cream", 0), "")
        self.assertEqual(repeatFront("xyz", 3), "xyzxyx")
        self.assertEqual(repeatFront("", 0), "")
        self.assertEqual(repeatFront("Java", 4), "JavaJavJaJ")
        self.assertEqual(repeatFront("Java", 1), "J")


class TestFizzArray(unittest.TestCase):
    def test_fizzArray(self):
        self.assertEqual(fizzArray(4), [0, 1, 2, 3])
        self.assertEqual(fizzArray(1), [0])
        self.assertEqual(fizzArray(10), [0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
        self.assertEqual(fizzArray(0), [])
        self.assertEqual(fizzArray(2), [0, 1])
        self.assertEqual(fizzArray(7), [0, 1, 2, 3, 4, 5, 6])


class TestOnly14(unittest.TestCase):
    def test_only14(self):
        self.assertTrue(only14([1, 4, 1, 4]))
        self.assertFalse(only14([1, 4, 2, 4]))
        self.assertTrue(only14([1, 1]))
        self.assertTrue(only14([4, 1]))
        self.assertFalse(only14([2]))
        self.assertTrue(only14([]))
        self.assertFalse(only14([1, 4, 1, 3]))
        self.assertFalse(only14([3, 1, 3]))
        self.assertTrue(only14([1]))
        self.assertTrue(only14([4]))
        self.assertFalse(only14([3, 4]))
        self.assertFalse(only14([1, 3, 4]))
        self.assertTrue(only14([1, 1, 1]))
        self.assertFalse(only14([1, 1, 1, 5]))
        self.assertTrue(only14([4, 1, 4, 1]))


class TestFizzArray2(unittest.TestCase):
    def test_fizzArray2(self):
        self.assertEqual(fizzArray2(4),  ["0", "1", "2", "3"])
        self.assertEqual(fizzArray2(10), ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"])
        self.assertEqual(fizzArray2(2),  ["0", "1"])
        self.assertEqual(fizzArray2(1),  ["0"])
        self.assertEqual(fizzArray2(0),  [])
        self.assertEqual(fizzArray2(7),  ["0","1","2","3","4","5","6"])
        self.assertEqual(fizzArray2(9),  ["0","1","2","3","4","5","6","7","8"])
        self.assertEqual(fizzArray2(11), ["0","1","2","3","4","5","6","7","8","9","10"])


class TestNoTeenSum(unittest.TestCase):
    def test_noTeenSum(self):
        self.assertEqual(noTeenSum(1, 2, 3), 6)
        self.assertEqual(noTeenSum(2, 13, 1), 3)
        self.assertEqual(noTeenSum(2, 1, 14), 3)
        self.assertEqual(noTeenSum(2, 1, 15), 18)
        self.assertEqual(noTeenSum(2, 1, 16), 19)
        self.assertEqual(noTeenSum(2, 1, 17), 3)
        self.assertEqual(noTeenSum(17, 1, 2), 3)
        self.assertEqual(noTeenSum(2, 15, 2), 19)
        self.assertEqual(noTeenSum(16, 17, 18), 16)
        self.assertEqual(noTeenSum(17, 18, 19), 0)
        self.assertEqual(noTeenSum(15, 16, 1), 32)
        self.assertEqual(noTeenSum(15, 15, 19), 30)
        self.assertEqual(noTeenSum(15, 19, 16), 31)
        self.assertEqual(noTeenSum(5, 17, 18), 5)
        self.assertEqual(noTeenSum(17, 18, 16), 16)
        self.assertEqual(noTeenSum(17, 19, 18), 0)


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