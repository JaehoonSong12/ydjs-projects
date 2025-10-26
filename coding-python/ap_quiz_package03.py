#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt.
    2. Run the tests by executing: `python async-ap/ap_quiz_package03.py`

This is a list of quiz questions for practice, including:
1.  commonTwo: Counts unique common strings in two sorted arrays.
2.  doubleChar: Duplicates each character in a string.
3.  countHi: Counts occurrences of "hi" in a string.
4.  countEvens: Counts even integers in a list.
5.  bigDiff: Finds the difference between the largest and smallest integers in a list.
6.  centeredAverage: Calculates the centered average of a list of integers.
7.  makeBricks: Determines if a goal length can be achieved with given bricks.
8.  countYZ: Counts words ending in 'y' or 'z' in a string.
9.  maxSpan: Finds the largest span of identical values in a list.
10. catDog: Checks if "cat" and "dog" appear the same number of times in a string.
"""

# Import the unittest module, needed for creating test cases.
# We only need to import it once at the top of the file.
import unittest

# Import List from the typing module for type hinting (e.g., List[str])
# This is preferred for compatibility with Python versions < 3.10
from typing import List

































# -----------------------------------------------------------------
# Question 1: commonTwo
# -----------------------------------------------------------------
def commonTwo(a: list[str], b: list[str]) -> int:
    """
    Description:
        Start with two arrays of strings, `a` and `b`, each in 
        alphabetical order, possibly with duplicates.
        Return the count of the number of unique strings which 
        appear in both arrays. Use a single-pass
        linear solution taking advantage of the sorted order.

    Examples:
        commonTwo(["a", "c", "x"], ["b", "c", "d", "x"]) -> 2
        commonTwo(["a", "c", "x"], ["a", "b", "c", "x", "z"]) -> 3
        commonTwo(["a", "b", "c"], ["a", "b", "c"]) -> 3

    Args:
        a (list[str]): First sorted list of strings, may contain duplicates.
        b (list[str]): Second sorted list of strings, may contain duplicates.

    Returns:
        int: The count of unique strings that appear in both `a` and `b`.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    temp = []
    for string in a:
        if string not in temp:
            temp.append(string)
    a = temp
    temp = []
    for string in b:
        if string not in temp:
            temp.append(string)
    b = temp

    count = 0
    i = 0
    while i < len(b):
        if b[i] in a:
            count += 1
        i += 1
    return count
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 2: doubleChar
# -----------------------------------------------------------------
def doubleChar(s: str) -> str:
    """
    Description:
        Given a string, return a new string where for every character in the original,
        there are two characters in the result.

    Examples:
        doubleChar("The") -> "TThhee"
        doubleChar("AAbb") -> "AAAAbbbb"
        doubleChar("Hi-There") -> "HHii--TThheerree"
        
    Args:
        s (str): The input string.

    Returns:
        str: A string where each character from `s` is repeated twice.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    new_str = ""
    for char in s:
        new_str += char
        new_str += char
    return new_str
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 3: countHi
# -----------------------------------------------------------------
def countHi(s: str) -> int:
    """
    Description:
        Return the number of times the string "hi" appears anywhere in the given string.
        The match is case-sensitive, so only lowercase "hi" counts.

    Examples:
        countHi("abc hi ho") -> 1
        countHi("ABChi hi") -> 2
        countHi("hihi") -> 2

    Args:
        s (str): Input string to search within.

    Returns:
        int: Number of times "hi" appears.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    i = 0
    count = 0
    while i < len(s) - 1:
        if s[i] == "h":
            if s[i + 1] == "i":
                count += 1
        i += 1
    return count

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 4: countEvens
# -----------------------------------------------------------------
def countEvens(nums: list[int]) -> int:
    """
    Description:
        Return the number of even integers in the given array.
        An integer is even if it has no remainder when divided by 2 (i.e., num % 2 == 0).

    Examples:
        countEvens([2, 1, 2, 3, 4]) -> 3
        countEvens([2, 2, 0]) -> 3
        countEvens([1, 3, 5]) -> 0

    Args:
        nums (list[int]): A list of integers.

    Returns:
        int: Count of even integers in the list.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    count = 0
    for num in nums:
        if num % 2 == 0:
            count += 1
    return count
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 5: bigDiff
# -----------------------------------------------------------------
def bigDiff(nums: list[int]) -> int:
    """
    Description:
        Given a list of integers with length 1 or more, return the difference
        between the largest and smallest values in the list.

    Examples:
        bigDiff([10, 3, 5, 6]) -> 7
        bigDiff([7, 2, 10, 9]) -> 8
        bigDiff([2, 10, 7, 2]) -> 8

    Args:
        nums (list[int]): A list of one or more integers.

    Returns:
        int: The difference between the maximum and minimum values in the list.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    largest = nums[0]
    smallest = nums[0]
    for num in nums:
        if num > largest:
            largest = num
        if num < smallest:
            smallest = num
    difference = largest - smallest
    return difference
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 6: centeredAverage
# -----------------------------------------------------------------
def centeredAverage(nums: list[int]) -> int:
    """
    Description:
        Return the "centered" average of a list of integers.
        The centered average is the mean of the values, excluding the smallest
        and largest values (ignoring only one copy of each).
        Use integer division for the result.
        The input list is guaranteed to have a length of 3 or more.

    Examples:
        centeredAverage([1, 2, 3, 4, 100]) -> 3
        centeredAverage([1, 1, 5, 5, 10, 8, 7]) -> 5
        centeredAverage([-10, -4, -2, -4, -2, 0]) -> -3

    Args:
        nums (list[int]): A list of integers with length 3 or more.

    Returns:
        int: The centered average of the list using integer division.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    largest = nums[0]
    smallest = nums[0]
    for num in nums:
        if num > largest:
            largest = num
        if num < smallest:
            smallest = num
    sum = 0
    total = 0
    for num in nums:
        sum += num
        total += 1
    average = (sum - smallest - largest) // (total - 2)
    return average
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 7: makeBricks
# -----------------------------------------------------------------
def makeBricks(small: int, big: int, goal: int) -> bool:
    """
    Description:
        Determine if it is possible to reach the desired goal 
        length using a combination
        of small bricks (1 inch each) and big bricks (5 inches each). 
        The function returns True if the goal can be reached 
        exactly, otherwise False.
        No loops are needed for this solution.

    Examples:
        makeBricks(3, 1, 8) -> True
        makeBricks(3, 1, 9) -> False
        makeBricks(3, 2, 10) -> True

    Args:
        small (int): Number of small bricks (1 inch each).
        big (int): Number of big bricks (5 inches each).
        goal (int): The target length to achieve.

    Returns:
        bool: True if the goal can be reached using the available bricks, False otherwise.
    """
    ### [Your Implementation Here]
    if big * 5 > goal:
        if goal % 5 == small:
            return True
    if big * 5 == goal:
        return True
    if small == goal:
        return True
    if big * 5 < goal:
        if big * 5 + small >= goal:
            return True
    return False
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

# -----------------------------------------------------------------
# Question 8: countYZ
# -----------------------------------------------------------------
def countYZ(s: str) -> int:
    """
    Description:
        Given a string, count the number of words ending in 'y' or 'z'.
        The character must appear at the end of a word — that is, it must not be
        immediately followed by another alphabetic letter. The comparison is
        case-insensitive.

    Examples:
        countYZ("fez day") -> 2
        countYZ("day fez") -> 2
        countYZ("day fyyyz") -> 2

    Args:
        s (str): The input string.

    Returns:
        int: The number of words ending in 'y' or 'z'.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    i = 0
    count = 0
    s = s.lower()
    while i < len(s):
        if i == len(s) - 1:
            if s[i] == "y" or s[i] == "z":
                count += 1
            break
        if not s[i + 1].isalpha():
            if s[i] == "y" or s[i] == "z":
                count += 1
        i += 1
    return count
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




# -----------------------------------------------------------------
# Question 9: maxSpan
# -----------------------------------------------------------------
def maxSpan(nums: list[int]) -> int:
    """
    Description:
        Consider the leftmost and rightmost appearances of some value in an array.
        We'll say that the "span" is the number of elements between the two, inclusive.
        A single occurrence of a value has a span of 1.
        Return the largest span found in the given array.
        Note: Efficiency is not a priority.

    Examples:
        maxSpan([1, 2, 1, 1, 3]) -> 4
        maxSpan([1, 4, 2, 1, 4, 1, 4]) -> 6
        maxSpan([1, 4, 2, 1, 4, 4, 4]) -> 6

    Args:
        nums (list[int]): The input list of integers.

    Returns:
        int: The largest span found in the array.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    if len(nums) == 0:
        return 0
    
    if len(nums) == len(list(set(nums))):
        return 1


    i = 0
    current_span = 0
    while i < len(nums):
        j = i + 1
        new_span = 0
        start_span_ind = i
        end_span_ind = j
        while j < len(nums):
            if nums[j] == nums[i]:
                if j != end_span_ind:
                    end_span_ind = j
            j += 1
        new_span = end_span_ind - start_span_ind + 1
        if current_span < new_span:
            current_span = new_span
        i += 1
    return current_span
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 10: catDog
# -----------------------------------------------------------------
def catDog(s: str) -> bool:
    """
    Description:
        Return True if the strings "cat" and "dog" appear the 
        same number of times in the given string.
        The comparison is case-sensitive and counts non-overlapping 
        occurrences.

    Examples:
        catDog("catdog") -> True
        catDog("catcat") -> False
        catDog("1cat1cadodog") -> True

    Args:
        s (str): The input string to examine.

    Returns:
        bool: True if "cat" and "dog" occur the same number of times, False otherwise.
    """
    ### [Your Implementation Here]
    i = 0
    cat_count = 0
    dog_count = 0
    while i < len(s) - 2:
        if s[i] == "c":
            if s[i + 1] == "a":
                if s[i + 2] == "t":
                    cat_count += 1
        if s[i] == "d":
            if s[i + 1] == "o":
                if s[i + 2] == "g":
                    dog_count += 1
        i += 1
    if cat_count == dog_count:
        return True
    return False
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.











































# -----------------------------------------------------------------
# Unit Tests
# -----------------------------------------------------------------
class TestCommonTwo(unittest.TestCase):
    def test_commonTwo(self):
        self.assertEqual(commonTwo(["a", "c", "x"], ["b", "c", "d", "x"]), 2)
        self.assertEqual(commonTwo(["a", "c", "x"], ["a", "b", "c", "x", "z"]), 3)
        self.assertEqual(commonTwo(["a", "b", "c"], ["a", "b", "c"]), 3)
        self.assertEqual(commonTwo(["a", "a", "b", "b", "c"], ["a", "b", "c"]), 3)
        self.assertEqual(commonTwo(["a", "a", "b", "b", "c"], ["a", "b", "b", "b", "c"]), 3)
        self.assertEqual(commonTwo(["a", "a", "b", "b", "c"], ["a", "b", "b", "c", "c"]), 3)
        self.assertEqual(commonTwo(["b", "b", "b", "b", "c"], ["a", "b", "b", "b", "c"]), 2)
        self.assertEqual(commonTwo(["a", "b", "c", "c", "d"], ["a", "b", "b", "c", "d", "d"]), 4)
        self.assertEqual(commonTwo(["a", "a", "b", "b", "c"], ["b", "b", "b"]), 1)
        self.assertEqual(commonTwo(["a", "a", "b", "b", "c"], ["c", "c"]), 1)
        self.assertEqual(commonTwo(["a", "a", "b", "b", "c"], ["b", "b", "b", "x"]), 1)
        self.assertEqual(commonTwo(["a", "a", "b", "b", "c"], ["b", "b"]), 1)
        self.assertEqual(commonTwo(["a"], ["a", "b"]), 1)
        self.assertEqual(commonTwo(["a"], ["b"]), 0)
        self.assertEqual(commonTwo(["a", "a"], ["b", "b"]), 0)
        self.assertEqual(commonTwo(["a", "b"], ["a", "b"]), 2)

class TestDoubleChar(unittest.TestCase):
    def test_doubleChar(self):
        self.assertEqual(doubleChar("The"), "TThhee")
        self.assertEqual(doubleChar("AAbb"), "AAAAbbbb")
        self.assertEqual(doubleChar("Hi-There"), "HHii--TThheerree")
        self.assertEqual(doubleChar("Word!"), "WWoorrdd!!")
        self.assertEqual(doubleChar("!!"), "!!!!")
        self.assertEqual(doubleChar(")"), "))")
        self.assertEqual(doubleChar("a"), "aa")
        self.assertEqual(doubleChar("."), "..")
        self.assertEqual(doubleChar("aa"), "aaaa")
    
class TestCountHi(unittest.TestCase):
    def test_countHi(self):
        self.assertEqual(countHi("abc hi ho"), 1)
        self.assertEqual(countHi("ABChi hi"), 2)
        self.assertEqual(countHi("hihi"), 2)
        self.assertEqual(countHi("hiHIhi"), 2)
        self.assertEqual(countHi(""), 0)
        self.assertEqual(countHi("h"), 0)
        self.assertEqual(countHi("hi"), 1)
        self.assertEqual(countHi("Hi is no HI on ahI"), 0)
        self.assertEqual(countHi("hiho not HOHIhi"), 2)

class TestCountEvens(unittest.TestCase):
    def test_countEvens(self):
        self.assertEqual(countEvens([2, 1, 2, 3, 4]), 3)
        self.assertEqual(countEvens([2, 2, 0]), 3)
        self.assertEqual(countEvens([1, 3, 5]), 0)
        self.assertEqual(countEvens([]), 0)
        self.assertEqual(countEvens([11, 9, 0, 1]), 1)
        self.assertEqual(countEvens([2, 11, 9, 0]), 2)
        self.assertEqual(countEvens([2]), 1)
        self.assertEqual(countEvens([2, 5, 12]), 2)

class TestBigDiff(unittest.TestCase):
    def test_bigDiff(self):
        self.assertEqual(bigDiff([10, 3, 5, 6]), 7)
        self.assertEqual(bigDiff([7, 2, 10, 9]), 8)
        self.assertEqual(bigDiff([2, 10, 7, 2]), 8)
        self.assertEqual(bigDiff([2, 10]), 8)
        self.assertEqual(bigDiff([10, 2]), 8)
        self.assertEqual(bigDiff([10, 0]), 10)
        self.assertEqual(bigDiff([2, 3]), 1)
        self.assertEqual(bigDiff([2, 2]), 0)
        self.assertEqual(bigDiff([2]), 0)
        self.assertEqual(bigDiff([5, 1, 6, 1, 9, 9]), 8)
        self.assertEqual(bigDiff([7, 6, 8, 5]), 3)
        self.assertEqual(bigDiff([7, 7, 6, 8, 5, 5, 6]), 3)

class TestCenteredAverage(unittest.TestCase):
    def test_centeredAverage(self):
        self.assertEqual(centeredAverage([1, 2, 3, 4, 100]), 3)
        self.assertEqual(centeredAverage([1, 1, 5, 5, 10, 8, 7]), 5)
        self.assertEqual(centeredAverage([-10, -4, -2, -4, -2, 0]), -3)
        self.assertEqual(centeredAverage([5, 3, 4, 6, 2]), 4)
        self.assertEqual(centeredAverage([5, 3, 4, 0, 100]), 4)
        self.assertEqual(centeredAverage([100, 0, 5, 3, 4]), 4)
        self.assertEqual(centeredAverage([4, 0, 100]), 4)
        self.assertEqual(centeredAverage([0, 2, 3, 4, 100]), 3)
        self.assertEqual(centeredAverage([1, 1, 100]), 1)
        self.assertEqual(centeredAverage([7, 7, 7]), 7)
        self.assertEqual(centeredAverage([1, 7, 8]), 7)
        self.assertEqual(centeredAverage([1, 1, 99, 99]), 50)
        self.assertEqual(centeredAverage([1000, 0, 1, 99]), 50)
        self.assertEqual(centeredAverage([4, 4, 4, 4, 5]), 4)
        self.assertEqual(centeredAverage([4, 4, 4, 1, 5]), 4)
        self.assertEqual(centeredAverage([6, 4, 8, 12, 3]), 6)

class TestMakeBricks(unittest.TestCase):
    def test_makeBricks(self):
        self.assertTrue(makeBricks(3, 1, 8))
        self.assertFalse(makeBricks(3, 1, 9))
        self.assertTrue(makeBricks(3, 2, 10))
        self.assertTrue(makeBricks(3, 2, 8))
        self.assertFalse(makeBricks(3, 2, 9))
        self.assertTrue(makeBricks(6, 1, 11))
        self.assertFalse(makeBricks(6, 0, 11))
        self.assertTrue(makeBricks(1, 4, 11))
        self.assertTrue(makeBricks(0, 3, 10))
        self.assertFalse(makeBricks(1, 4, 12))
        self.assertTrue(makeBricks(3, 1, 7))
        self.assertFalse(makeBricks(1, 1, 7))
        self.assertTrue(makeBricks(2, 1, 7))
        self.assertTrue(makeBricks(7, 1, 11))
        self.assertTrue(makeBricks(7, 1, 8))
        self.assertFalse(makeBricks(7, 1, 13))
        self.assertTrue(makeBricks(43, 1, 46))
        self.assertFalse(makeBricks(40, 1, 46))
        self.assertTrue(makeBricks(40, 2, 47))
        self.assertTrue(makeBricks(40, 2, 50))
        self.assertFalse(makeBricks(40, 2, 52))
        self.assertFalse(makeBricks(22, 2, 33))
        self.assertTrue(makeBricks(0, 2, 10))
        self.assertTrue(makeBricks(1000000, 1000, 1000100))
        self.assertFalse(makeBricks(2, 1000000, 100003))
        self.assertTrue(makeBricks(20, 0, 19))
        self.assertFalse(makeBricks(20, 0, 21))
        self.assertFalse(makeBricks(20, 4, 51))
        self.assertTrue(makeBricks(20, 4, 39))

class TestCountYZ(unittest.TestCase):
    def test_countYZ(self):
        self.assertEqual(countYZ("fez day"), 2)
        self.assertEqual(countYZ("day fez"), 2)
        self.assertEqual(countYZ("day fyyyz"), 2)
        self.assertEqual(countYZ("day yak"), 1)
        self.assertEqual(countYZ("day:yak"), 1)
        self.assertEqual(countYZ("!!day--yaz!!"), 2)
        self.assertEqual(countYZ("yak zak"), 0)
        self.assertEqual(countYZ("DAY abc XYZ"), 2)
        self.assertEqual(countYZ("aaz yyz my"), 3)
        self.assertEqual(countYZ("y2bz"), 2)
        self.assertEqual(countYZ("zxyx"), 0)

class TestMaxSpan(unittest.TestCase):
    def test_maxSpan(self):
        self.assertEqual(maxSpan([1, 2, 1, 1, 3]), 4)
        self.assertEqual(maxSpan([1, 4, 2, 1, 4, 1, 4]), 6)
        self.assertEqual(maxSpan([1, 4, 2, 1, 4, 4, 4]), 6)
        self.assertEqual(maxSpan([3, 3, 3]), 3)
        self.assertEqual(maxSpan([3, 9, 3]), 3)
        self.assertEqual(maxSpan([3, 9, 9]), 2)
        self.assertEqual(maxSpan([3, 9]), 1)
        self.assertEqual(maxSpan([3, 3]), 2)
        self.assertEqual(maxSpan([]), 0)
        self.assertEqual(maxSpan([1]), 1)

class TestCatDog(unittest.TestCase):
    def test_catDog(self):
        self.assertTrue(catDog("catdog"))
        self.assertFalse(catDog("catcat"))
        self.assertTrue(catDog("1cat1cadodog"))
        self.assertFalse(catDog("catxxdogxxxdog"))
        self.assertTrue(catDog("catxdogxdogxcat"))
        self.assertFalse(catDog("catxdogxdogxca"))
        self.assertFalse(catDog("dogdogcat"))
        self.assertTrue(catDog("dogogcat"))
        self.assertFalse(catDog("dog"))
        self.assertFalse(catDog("cat"))
        self.assertTrue(catDog("ca"))
        self.assertTrue(catDog("c"))
        self.assertTrue(catDog(""))

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