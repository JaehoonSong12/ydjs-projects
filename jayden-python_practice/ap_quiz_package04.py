#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt.
    2. Run the tests by executing: `python async-ap/ap_quiz_package04.py`

This is a list of quiz questions for practice, including:
1.  countCode: Count occurrences of "co_e" patterns in a string.
2.  sum13: Sum numbers in a list, ignoring 13 and the number after it.
3.  has22: Check if a list contains two consecutive 2's.
4.  loneSum: Sum three integers, ignoring duplicates.
5.  withoutString: Remove all instances of a substring from a base string, case-insensitively.
6.  fix34: Rearrange a list so every 3 is followed by a 4.
7.  endOther: Check if one string ends with another, case-insensitively.
8.  xyzThere: Check if "xyz" appears in a string not preceded by a period.
9.  lucky13: Check if a list contains no 1's and no 3's.
10. sum28: Check if the sum of all 2's in a list is exactly 8.
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
def countCode(s: str) -> int:
    """
    Description:
        Return the number of times that the string "code" appears anywhere in the given string,
        except we'll accept any letter for the 'd' position—so "cope" and "cooe" count as well.

    Examples:
        countCode("aaacodebbb") -> 1
        countCode("codexxcode") -> 2
        countCode("cozexxcope") -> 2

    Args:
        s (str): The input string to search within.

    Returns:
        int: The count of substrings matching the pattern "co_e".
    """
    ### [Your Implementation Here]
    i = 0
    count = 0
    while i < len(s) - 3:
        if s[i] == "c":
            if s[i + 1] == "o":
                if s[i + 3] == "e":
                    count += 1
        i += 1
    return count
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 2: sum13
# -----------------------------------------------------------------
def sum13(nums: list[int]) -> int:
    """
    Description:
        Return the sum of the numbers in the list, returning 0 for an empty list.
        The number 13 is unlucky—it does not count toward the sum, and any number
        immediately following a 13 also does not count.

    Examples:
        sum13([1, 2, 2, 1]) -> 6
        sum13([1, 1]) -> 2
        sum13([1, 2, 2, 1, 13]) -> 6

    Args:
        nums (list[int]): A list of integers.

    Returns:
        int: The sum according to the "13" rules described above.
    """
    ### [Your Implementation Here]
    sum = 0
    i = 0
    while i < len(nums):
        if nums[i] != 13:
            sum += nums[i]
            i += 1
        elif nums[i] == 13:
            i += 2
    return sum
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 3: has22
# -----------------------------------------------------------------
def has22(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the list contains a 2 next to a 2 somewhere.

    Examples:
        has22([1, 2, 2]) -> True
        has22([1, 2, 1, 2]) -> False
        has22([2, 1, 2]) -> False

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if there is at least one occurrence of two consecutive 2's, False otherwise.
    """
    ### [Your Implementation Here]
    i = 0
    while i < len(nums) - 1:
        if nums[i] == 2:
            if nums[i + 1] == 2:
                return True
        i += 1
    return False
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 4: loneSum
# -----------------------------------------------------------------
def loneSum(a: int, b: int, c: int) -> int:
    """
    Description:
        Given 3 integer values, a, b, and c, return their sum. However, if one of the values
        is the same as another value, that value does not count toward the sum.

    Examples:
        loneSum(1, 2, 3) -> 6
        loneSum(3, 2, 3) -> 2
        loneSum(3, 3, 3) -> 0

    Args:
        a (int): First integer.
        b (int): Second integer.
        c (int): Third integer.

    Returns:
        int: The sum of the values that are not duplicated.
    """
    ### [Your Implementation Here]
    total = 0
    if a != b and a != c:
        total += a
    if b != a and b != c:
        total += b
    if c != a and c != b:
        total += c
    return total
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 5: withoutString
# -----------------------------------------------------------------
def withoutString(base: str, remove: str) -> str:
    """
    Description:
        Given two strings, `base` and `remove`, return a version of the `base` string where all
        non-overlapping instances of the `remove` string have been removed. Removal is not case
        sensitive (e.g. removing "is" will remove "IS", "Is", etc.), but the remaining characters
        keep their original case. You may assume `remove` has length 1 or more.

    Examples:
        withoutString("Hello there", "llo") -> "He there"
        withoutString("Hello there", "e")   -> "Hllo thr"
        withoutString("Hello there", "x")   -> "Hello there"

    Args:
        base (str):    The original string.
        remove (str):  The substring to remove (case-insensitive).

    Returns:
        str: The resulting string after all instances of `remove` have been removed.
    """
    ### [Your Implementation Here]
    new_str = ""

    i = 0
    while i <= len(base):
        if (i > len(base) - len(remove)): 
            new_str += base[i:]
            break
        if (base[i:i+len(remove)].lower() == remove.lower()): 
            i = i + len(remove)
            continue
        else: 
            new_str += base[i]
        i += 1
    return new_str
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 6: fix34
# -----------------------------------------------------------------
def fix34(nums: list[int]) -> list[int]:
    """
    Description:
        Return a list containing exactly the same numbers as the given list, 
        but rearranged so that every 3 
        is immediately followed by a 4. Do not move the 3's themselves, but 
        every other number may move (specifically, may be swaped).
        You may assume:
          - The list contains the same number of 3's and 4's.
          - Every 3 has a non-3 immediately after it.
          - No 4 appears before the first 3.

    Examples:
        fix34([1, 3, 1, 4]) -> [1, 3, 4, 1]
        fix34([1, 3, 1, 4, 4, 3, 1]) -> [1, 3, 4, 1, 1, 3, 4]
        fix34([3, 2, 2, 4]) -> [3, 4, 2, 2]

    Args:
        nums (list[int]): The input list of integers containing matching numbers of 3's and 4's.

    Returns:
        list[int]: A new list where each 3 is immediately followed by a 4.
    """
    ### [Your Implementation Here]

    


    
    return None
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 7: endOther
# -----------------------------------------------------------------

def endOther(a: str, b: str) -> bool:
    """
    Description:
        Given two strings, return True if either of the strings appears at the very end
        of the other string, ignoring upper/lower case differences.

    Examples:
        endOther("Hiabc", "abc") -> True
        endOther("AbC", "HiaBc") -> True
        endOther("abc", "abXabc") -> True

    Args:
        a (str): First input string.
        b (str): Second input string.

    Returns:
    
        bool: True if one string appears at the end of the other (case-insensitive), else False.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 8: xyzThere
# -----------------------------------------------------------------
def xyzThere(s: str) -> bool:
    """
    Description:
        Return True if the given string contains an appearance of 
        "xyz" where the "xyz"
        is not directly preceded by a period ('.'). For example, 
        "xxyz" counts but "x.xyz" does not.

    Examples:
        xyzThere("abcxyz") -> True
        xyzThere("abc.xyz") -> False
        xyzThere("xyz.abc") -> True

    Args:
        s (str): The input string to check.

    Returns:
        bool: True if "xyz" appears not preceded by '.', otherwise False.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




# -----------------------------------------------------------------
# Question 9: lucky13
# -----------------------------------------------------------------
def lucky13(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the list contains no 1's and no 3's.

    Examples:
        lucky13([0, 2, 4]) -> True
        lucky13([1, 2, 3]) -> False
        lucky13([1, 2, 4]) -> False
        lucky13([2, 7, 2, 8]) -> True

    Args:
        nums (list[int]): The list of integers to check.

    Returns:
        bool: True if there are no 1's and no 3's in the list, otherwise False.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 10: sum28
# -----------------------------------------------------------------
def sum28(nums: list[int]) -> bool:
    """
    Description:
        Given a list of integers, return True if the sum of all the 2's in the list is exactly 8.

    Examples:
        sum28([2, 3, 2, 2, 4, 2]) -> True
        sum28([2, 3, 2, 2, 4, 2, 2]) -> False
        sum28([1, 2, 3, 4]) -> False
        sum28([2, 2, 2, 2]) -> True
        sum28([1, 2, 2, 2, 2, 4]) -> True

    Args:
        nums (list[int]): The list of integers to examine.

    Returns:
        bool: True if the sum of all 2's is exactly 8, otherwise False.
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
class TestCountCode(unittest.TestCase):
    def test_countCode(self):
        self.assertEqual(countCode("aaacodebbb"), 1)
        self.assertEqual(countCode("codexxcode"), 2)
        self.assertEqual(countCode("cozexxcope"), 2)
        self.assertEqual(countCode("cozfxxcope"), 1)
        self.assertEqual(countCode("xxcozeyycop"), 1)
        self.assertEqual(countCode("cozcop"), 0)
        self.assertEqual(countCode("abcxyz"), 0)
        self.assertEqual(countCode("code"), 1)
        self.assertEqual(countCode("ode"), 0)
        self.assertEqual(countCode("c"), 0)
        self.assertEqual(countCode(""), 0)
        self.assertEqual(countCode("AAcodeBBcoleCCccoreDD"), 3)
        self.assertEqual(countCode("AAcodeBBcoleCCccorfDD"), 2)
        self.assertEqual(countCode("coAcodeBcoleccoreDD"), 3)



class TestSum13(unittest.TestCase):
    def test_sum13(self):
        self.assertEqual(sum13([1, 2, 2, 1]), 6)
        self.assertEqual(sum13([1, 1]), 2)
        self.assertEqual(sum13([1, 2, 2, 1, 13]), 6)
        self.assertEqual(sum13([1, 2, 13, 2, 1, 13]), 4)
        self.assertEqual(sum13([13, 1, 2, 13, 2, 1, 13]), 3)
        self.assertEqual(sum13([]), 0)
        self.assertEqual(sum13([13]), 0)
        self.assertEqual(sum13([13, 13]), 0)
        self.assertEqual(sum13([13, 0, 13]), 0)
        self.assertEqual(sum13([13, 1, 13]), 0)
        self.assertEqual(sum13([5, 7, 2]), 14)
        self.assertEqual(sum13([5, 13, 2]), 5)
        self.assertEqual(sum13([0]), 0)
        self.assertEqual(sum13([13, 0]), 0)

class TestHas22(unittest.TestCase):
    def test_has22(self):
        self.assertTrue(has22([1, 2, 2]))
        self.assertFalse(has22([1, 2, 1, 2]))
        self.assertFalse(has22([2, 1, 2]))
        self.assertTrue(has22([2, 2, 1, 2]))
        self.assertFalse(has22([1, 3, 2]))
        self.assertTrue(has22([1, 3, 2, 2]))
        self.assertTrue(has22([2, 3, 2, 2]))
        self.assertTrue(has22([4, 2, 4, 2, 2, 5]))
        self.assertFalse(has22([1, 2]))
        self.assertTrue(has22([2, 2]))
        self.assertFalse(has22([2]))
        self.assertFalse(has22([]))
        self.assertTrue(has22([3, 3, 2, 2]))
        self.assertFalse(has22([5, 2, 5, 2]))


class TestLoneSum(unittest.TestCase):
    def test_loneSum(self):
        self.assertEqual(loneSum(1, 2, 3), 6)
        self.assertEqual(loneSum(3, 2, 3), 2)
        self.assertEqual(loneSum(3, 3, 3), 0)
        self.assertEqual(loneSum(9, 2, 2), 9)
        self.assertEqual(loneSum(2, 2, 9), 9)
        self.assertEqual(loneSum(2, 9, 2), 9)
        self.assertEqual(loneSum(2, 9, 3), 14)
        self.assertEqual(loneSum(4, 2, 3), 9)
        self.assertEqual(loneSum(1, 3, 1), 3)

class TestWithoutString(unittest.TestCase):
    def test_withoutString(self):
        self.assertEqual(withoutString("Hello there", "llo"), "He there")
        self.assertEqual(withoutString("Hello there", "e"), "Hllo thr")
        self.assertEqual(withoutString("Hello there", "x"), "Hello there")
        self.assertEqual(withoutString("This is a FISH", "IS"), "Th  a FH")
        self.assertEqual(withoutString("THIS is a FISH", "is"), "TH  a FH")
        self.assertEqual(withoutString("abxxxxab", "xx"), "abab")
        self.assertEqual(withoutString("abxxxab", "xx"), "abxab")
        self.assertEqual(withoutString("abxxxab", "x"), "abab")
        self.assertEqual(withoutString("xxx", "x"), "")
        self.assertEqual(withoutString("xxx", "xx"), "x")
        self.assertEqual(withoutString("xyzzy", "Y"), "xzz")
        self.assertEqual(withoutString("", "x"), "")
        self.assertEqual(withoutString("abcabc", "b"), "acac")
        self.assertEqual(withoutString("AA22bb", "2"), "AAbb")
        self.assertEqual(withoutString("1111", "1"), "")
        self.assertEqual(withoutString("1111", "11"), "")
        self.assertEqual(withoutString("MkjtMkx", "Mk"), "jtx")
        self.assertEqual(withoutString("Hi HoHo", "Ho"), "Hi ")

class TestFix34(unittest.TestCase):
    def test_fix34(self):
        self.assertEqual(fix34([1, 3, 1, 4]), [1, 3, 4, 1])
        self.assertEqual(fix34([1, 3, 1, 4, 4, 3, 1]), [1, 3, 4, 1, 1, 3, 4])
        self.assertEqual(fix34([3, 2, 2, 4]), [3, 4, 2, 2])
        self.assertEqual(fix34([3, 2, 3, 2, 4, 4]), [3, 4, 3, 4, 2, 2])
        self.assertEqual(fix34([2, 3, 2, 3, 2, 4, 4]), [2, 3, 4, 3, 4, 2, 2])
        self.assertEqual(fix34([5, 3, 5, 4, 5, 4, 5, 4, 3, 5, 3, 5]), 
                         [5, 3, 4, 5, 5, 5, 5, 5, 3, 4, 3, 4])
        self.assertEqual(fix34([3, 1, 4]), [3, 4, 1])
        self.assertEqual(fix34([3, 4, 1]), [3, 4, 1])
        self.assertEqual(fix34([1, 1, 1]), [1, 1, 1])
        self.assertEqual(fix34([1]), [1])
        self.assertEqual(fix34([]), [])
        self.assertEqual(fix34([7, 3, 7, 7, 4]), [7, 3, 4, 7, 7])
        self.assertEqual(fix34([3, 1, 4, 3, 1, 4]), [3, 4, 1, 3, 4, 1])
        self.assertEqual(fix34([3, 1, 1, 3, 4, 4]), [3, 4, 1, 3, 4, 1])

class TestEndOther(unittest.TestCase):
    def test_endOther(self):
        self.assertTrue(endOther("Hiabc", "abc"))
        self.assertTrue(endOther("AbC", "HiaBc"))
        self.assertTrue(endOther("abc", "abXabc"))
        self.assertFalse(endOther("Hiabc", "abcd"))
        self.assertTrue(endOther("Hiabc", "bc"))
        self.assertFalse(endOther("Hiabcx", "bc"))
        self.assertTrue(endOther("abc", "abc"))
        self.assertTrue(endOther("xyz", "12xyz"))
        self.assertFalse(endOther("yz", "12xz"))
        self.assertTrue(endOther("Z", "12xz"))
        self.assertTrue(endOther("12", "12"))
        self.assertFalse(endOther("abcXYZ", "abcDEF"))
        self.assertFalse(endOther("ab", "ab12"))
        self.assertTrue(endOther("ab", "12ab"))

class TestXyzThere(unittest.TestCase):
    def test_xyzThere(self):
        self.assertTrue(xyzThere("abcxyz"))
        self.assertFalse(xyzThere("abc.xyz"))
        self.assertTrue(xyzThere("xyz.abc"))
        self.assertFalse(xyzThere("abcxy"))
        self.assertTrue(xyzThere("xyz"))
        self.assertFalse(xyzThere("xy"))
        self.assertFalse(xyzThere("x"))
        self.assertFalse(xyzThere(""))
        self.assertTrue(xyzThere("abc.xyzxyz"))
        self.assertTrue(xyzThere("abc.xxyz"))
        self.assertFalse(xyzThere(".xyz"))
        self.assertFalse(xyzThere("12.xyz"))
        self.assertTrue(xyzThere("12xyz"))
        self.assertFalse(xyzThere("1.xyz.xyz2.xyz"))


class TestLucky13(unittest.TestCase):
    def test_lucky13(self):
        self.assertTrue(lucky13([0, 2, 4]))
        self.assertFalse(lucky13([1, 2, 3]))
        self.assertFalse(lucky13([1, 2, 4]))
        self.assertTrue(lucky13([2, 7, 2, 8]))
        self.assertFalse(lucky13([2, 7, 1, 8]))
        self.assertFalse(lucky13([3, 7, 2, 8]))
        self.assertFalse(lucky13([2, 7, 2, 1]))
        self.assertFalse(lucky13([1, 2]))
        self.assertTrue(lucky13([2, 2]))
        self.assertTrue(lucky13([2]))
        self.assertFalse(lucky13([3]))
        self.assertTrue(lucky13([]))


class TestSum28(unittest.TestCase):
    def test_sum28(self):
        self.assertTrue(sum28([2, 3, 2, 2, 4, 2]))
        self.assertFalse(sum28([2, 3, 2, 2, 4, 2, 2]))
        self.assertFalse(sum28([1, 2, 3, 4]))
        self.assertTrue(sum28([2, 2, 2, 2]))
        self.assertTrue(sum28([1, 2, 2, 2, 2, 4]))
        self.assertFalse(sum28([]))
        self.assertFalse(sum28([2]))
        self.assertFalse(sum28([8]))
        self.assertFalse(sum28([2, 2, 2]))
        self.assertFalse(sum28([2, 2, 2, 2, 2]))
        self.assertTrue(sum28([1, 2, 2, 1, 2, 2]))
        self.assertTrue(sum28([5, 2, 2, 2, 4, 2]))



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