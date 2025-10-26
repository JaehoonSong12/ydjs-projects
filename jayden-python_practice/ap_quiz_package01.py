#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt.
    2. Run the tests by executing: `python ap_quiz_package01.py`

This is a list of quiz questions for practice, including:
1.  scores_increasing: Checks if scores are in non-decreasing order.
2.  scores100: Checks if there are consecutive 100's in the scores.
3.  scoresClump: Checks for 3 adjacent scores differing by at most 2.
4.  scoresAverage: Computes the average of the scores.
5.  wordsCount: Counts the number of strings of a given length.
6.  wordsFront: Returns the first 'n' strings from a list.
7.  wordsWithoutList: Returns a list with strings of a specific length removed.
8.  hasOne: Checks if a number contains the digit '1'.
9.  dividesSelf: Checks if a number divides itself.
10. copyEvens: Returns the first 'count' even numbers from a list.
"""
# Import the unittest module, needed for creating test cases.
# We only need to import it once at the top of the file.
import unittest

# Import List from the typing module for type hinting (e.g., List[str])
# This is preferred for compatibility with Python versions < 3.10
from typing import List

















# -----------------------------------------------------------------
# Question 1: scores_increasing
# -----------------------------------------------------------------
def scores_increasing(scores: list[int]) -> bool:
    """
    Description:
    Given a list of scores (integers), return True if the scores are 
    in non-decreasing order — that is, each score is equal to or 
    greater than the one before.

    Examples:
        scores_increasing([1, 3, 4]) -> True
        scores_increasing([1, 3, 2]) -> False
        scores_increasing([1, 1, 4]) -> True

    Args:
        scores (list[int]): A list of integers, length 2 or more.

    Returns:
        bool: True if each score is equal or greater than the previous one.
    """
    ### [Your Implementation Here]

    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.
    for i in range(1, len(scores)):
        if scores[i - 1] > scores[i]: #scores = array: [1,3,4]
            return False
    return True




# -----------------------------------------------------------------
# Question 2: scores100
# -----------------------------------------------------------------
def scores100(scores: list[int]) -> bool:
    """
    Description:
        Given an array of scores, return True if there are scores 
        of 100 next to each other
        in the array. The array length will be at least 2.
    
    Examples:
        scores100([1, 100, 100]) -> True
        scores100([1, 100, 99, 100]) -> False
        scores100([100, 1, 100, 100]) -> True

    Args:
        scores (list[int]): A list of integer scores.

    Returns:
        bool: True if there is at least one occurrence of consecutive 100's, else False.
    """
    ### [Your Implementation Here]

    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.
    return False






# -----------------------------------------------------------------
# Question 3: scoresClump
# -----------------------------------------------------------------
def scoresClump(scores: list[int]) -> bool:
    """
    Description:
        Given an array of scores sorted in increasing order, 
        return True if the array contains 
        3 adjacent scores that differ from each other by at 
        most 2. In other words, if any 
        consecutive triplet of scores has a maximum difference 
        (between the highest and lowest) 
        of 2 or less, the function returns True.

    Examples:
        scoresClump([3, 4, 5]) -> True
        scoresClump([3, 4, 6]) -> False
        scoresClump([1, 3, 5, 5]) -> True

    Args:
        scores (list[int]): A list of integer scores, sorted in increasing order.

    Returns:
        bool: True if there exists a triplet of adjacent scores with a max difference of 2 or less,
              otherwise False.
    """
    ### [Your Implementation Here]
    return False
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.






# -----------------------------------------------------------------
# Question 4: scoresAverage
# -----------------------------------------------------------------
def scoresAverage(scores: list[int]) -> int:
    """
    Description:
        Given an array of scores, compute the integer average 
        of the first half and the second half,
        and return whichever is larger. The second half begins 
        at index len(scores)//2.
        You must use a helper function that computes the average 
        of the values between two indices.

    Examples:
        scoresAverage([2, 2, 4, 4]) -> 4
        scoresAverage([4, 4, 4, 2, 2, 2]) -> 4
        scoresAverage([3, 4, 5, 1, 2, 3]) -> 4

    Args:
        scores (list[int]): A list of scores (at least 2 elements long).

    Returns:
        int: The higher integer average between the first and second half of the list.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    pl = ParallelList(scores)
    first_avg = pl.get_first_avg()
    second_avg = pl.get_second_avg()
    return (first_avg) if (first_avg > second_avg) else (second_avg)

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


class ParallelList:
    def __init__(self, original: list[int]): # constructor
        self.first_half = []
        self.second_half = []
        for i in range(len(original)):
            if i < len(original) // 2:
                self.first_half.append(original[i])
            else:
                self.second_half.append(original[i])
    
    def __repr__(self): # string representation
        return f"first_half={self.first_half}, second_half={self.second_half}"

    def get_average(self, list_data: list[int]):
        sum = 0
        for i in range(len(list_data)):
            sum += list_data[i]
        average = sum / len(list_data)
        return average if list_data else 0
    
    def get_first_avg(self):
        return self.get_average(self.first_half)
    
    def get_second_avg(self):
        return self.get_average(self.second_half)



# -----------------------------------------------------------------
# Question 5: wordsCount
# -----------------------------------------------------------------
def wordsCount(words: list[str], length: int) -> int:
    """
    Description:
        Given an array of strings, return the count of the 
        number of strings that have the given length.

    Examples:
        wordsCount(["a", "bb", "b", "ccc"], 1) -> 2
        wordsCount(["a", "bb", "b", "ccc"], 3) -> 1
        wordsCount(["a", "bb", "b", "ccc"], 4) -> 0

    Args:
        words (list[str]): A list of words (strings).
        length (int): The length of the strings to count.

    Returns:
        int: The number of strings that have the given length.
    """
    ### [Your Implementation Here]
    count = 0
    for word in words:
        if len(word) == length: 
            count = count + 1
    return count

    # https://www.w3schools.com/python/python_strings.asp



    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    return 0
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




# -----------------------------------------------------------------
# Question 6: wordsFront
# -----------------------------------------------------------------

def wordsFront(words: List[str], n: int) -> List[str]:
    """
    Description:
        Given an array of strings, return a new array containing the first N strings.
        N will be in the range 1..length of the input array.

    Examples:
        wordsFront(["a", "b", "c", "d"], 1) -> ["a"]
        wordsFront(["a", "b", "c", "d"], 2) -> ["a", "b"]
        wordsFront(["a", "b", "c", "d"], 3) -> ["a", "b", "c"]

    Args:
        words (List[str]): A list of strings.
        n (int): The number of strings to return from the start of the list.

    Returns:
        List[str]: A list containing the first N strings from the input array.
    """
    
    ### [Your Implementation Here]
    selectedlist=[]
    count = 0
    for word in words:
        selectedlist.append(word)
        count = count + 1
        if(count == n): break
    return selectedlist

    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    return None
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 7: wordsWithoutList
# -----------------------------------------------------------------
def wordsWithoutList(words: List[str], length: int) -> List[str]:
    """
    Description:
        Given an array of strings, return a new list where all the 
        strings of the given length are omitted.

    Examples:
        wordsWithoutList(["a", "bb", "b", "ccc"], 1) -> ["bb", "ccc"]
        wordsWithoutList(["a", "bb", "b", "ccc"], 3) -> ["a", "bb", "b"]
        wordsWithoutList(["a", "bb", "b", "ccc"], 4) -> ["a", "bb", "b", "ccc"]

    Args:
        words (List[str]): A list of strings.
        length (int): The length of the strings to omit from the list.

    Returns:
        List[str]: A new list with all strings of the given length omitted.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    return None
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




# -----------------------------------------------------------------
# Question 8: hasOne
# -----------------------------------------------------------------
def hasOne(n: int) -> bool:
    """
    Description:
        Given a positive integer n, return true if it contains a digit '1'. 
        Note: Use % to get the rightmost digit, and / to discard the rightmost digit.

    Examples:
        hasOne(10) -> True
        hasOne(22) -> False
        hasOne(220) -> False

    Args:
        n (int): A positive integer.

    Returns:
        bool: True if the number contains at least one digit '1', False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    return False
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.



# -----------------------------------------------------------------
# Question 9: dividesSelf
# -----------------------------------------------------------------
def dividesSelf(n: int) -> bool:
    """
    Description:
        A positive integer divides itself if every digit in the number 
        divides into the number evenly.
        For example, 128 divides itself since 1, 2, and 8 all divide 
        into 128 evenly.
        A number with a 0 digit does not divide itself, as 0 does not 
        divide into any number.

    Examples:
        dividesSelf(128) -> True
        dividesSelf(12) -> True
        dividesSelf(120) -> False

    Args:
        n (int): A positive integer.

    Returns:
        bool: True if the number divides itself, False otherwise.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    return False

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 10: copyEvens
# -----------------------------------------------------------------
def copyEvens(nums: list[int], count: int) -> list[int]:
    """
    Description:
        Given an array of positive integers, return a new list containing 
        the first `count` even numbers from the original array.
        The original array will contain at least `count` even numbers.

    Examples:
        copyEvens([3, 2, 4, 5, 8], 2) -> [2, 4]
        copyEvens([3, 2, 4, 5, 8], 3) -> [2, 4, 8]
        copyEvens([6, 1, 2, 4, 5, 8], 3) -> [6, 2, 4]

    Args:
        nums (list[int]): A list of positive integers.
        count (int): The number of even integers to return.

    Returns:
        list[int]: A list of the first `count` even integers from the original array.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    return None

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.































# -----------------------------------------------------------------
# Unit Tests
# -----------------------------------------------------------------
class TestScoresIncreasing(unittest.TestCase):
    def test_examples(self):
        self.assertTrue(scores_increasing([1, 3, 4]))            # -> True
        self.assertFalse(scores_increasing([1, 3, 2]))           # -> False
        self.assertTrue(scores_increasing([1, 1, 4]))            # -> True
        self.assertTrue(scores_increasing([1, 1, 2, 4, 4, 7]))   # -> True
        self.assertFalse(scores_increasing([1, 1, 2, 4, 3, 7]))  # -> False
        self.assertTrue(scores_increasing([-5, 4, 11]))          # -> True

class TestScores100(unittest.TestCase):
    def test_scores100(self):
        self.assertTrue(scores100([1, 100, 100]))             # -> True
        self.assertFalse(scores100([1, 100, 99, 100]))        # -> False
        self.assertTrue(scores100([100, 1, 100, 100]))        # -> True
        self.assertFalse(scores100([100, 1, 100, 1]))         # -> False
        self.assertFalse(scores100([1, 2, 3, 4, 5]))          # -> False
        self.assertFalse(scores100([1, 2, 100, 4, 5]))        # -> False

class TestScoresClump(unittest.TestCase):
    def test_scoresClump(self):
        self.assertTrue(scoresClump([3, 4, 5]))           # -> True
        self.assertFalse(scoresClump([3, 4, 6]))          # -> False
        self.assertTrue(scoresClump([1, 3, 5, 5]))         # -> True
        self.assertTrue(scoresClump([2, 4, 5, 6]))         # -> True
        self.assertFalse(scoresClump([2, 4, 5, 7]))         # -> False
        self.assertTrue(scoresClump([2, 4, 4, 7]))         # -> True
        self.assertFalse(scoresClump([3, 3, 6, 7, 9]))      # -> False
        self.assertTrue(scoresClump([3, 3, 7, 7, 9]))       # -> True
        self.assertFalse(scoresClump([4, 5, 8]))            # -> False

class TestScoresAverage(unittest.TestCase):
    def test_scoresAverage(self):
        self.assertEqual(scoresAverage([2, 2, 4, 4]), 4)
        self.assertEqual(scoresAverage([4, 4, 4, 2, 2, 2]), 4)
        self.assertEqual(scoresAverage([3, 4, 5, 1, 2, 3]), 4)
        self.assertEqual(scoresAverage([5, 6]), 6)
        self.assertEqual(scoresAverage([5, 4]), 5)
        self.assertEqual(scoresAverage([5, 4, 5, 6, 2, 1, 2, 3]), 5)

class TestWordsCount(unittest.TestCase):
    def test_wordsCount(self):
        self.assertEqual(wordsCount(["a", "bb", "b", "ccc"], 1), 2)
        self.assertEqual(wordsCount(["a", "bb", "b", "ccc"], 3), 1)
        self.assertEqual(wordsCount(["a", "bb", "b", "ccc"], 4), 0)
        self.assertEqual(wordsCount(["xx", "yyy", "x", "yy", "z"], 1), 2)
        self.assertEqual(wordsCount(["xx", "yyy", "x", "yy", "z"], 2), 2)
        self.assertEqual(wordsCount(["xx", "yyy", "x", "yy", "z"], 3), 1)

class TestWordsFront(unittest.TestCase):
    """
    Unit tests for the wordsFront function.
    """
    def test_wordsFront(self):
        # This single test method runs multiple assertions for wordsFront
        self.assertEqual(wordsFront(["a", "b", "c", "d"], 1), ["a"])
        self.assertEqual(wordsFront(["a", "b", "c", "d"], 2), ["a", "b"])
        self.assertEqual(wordsFront(["a", "b", "c", "d"], 3), ["a", "b", "c"])
        self.assertEqual(wordsFront(["a", "b", "c", "d"], 4), ["a", "b", "c", "d"])
        self.assertEqual(wordsFront(["Hi", "There"], 1), ["Hi"])
        self.assertEqual(wordsFront(["Hi", "There"], 2), ["Hi", "There"])

class TestWordsWithoutList(unittest.TestCase):
    """
    Unit tests for the wordsWithoutList function.
    """
    def test_wordsWithoutList(self):
        # This single test method runs multiple assertions for wordsWithoutList
        self.assertEqual(wordsWithoutList(["a", "bb", "b", "ccc"], 1), ["bb", "ccc"])
        self.assertEqual(wordsWithoutList(["a", "bb", "b", "ccc"], 3), ["a", "bb", "b"])
        self.assertEqual(wordsWithoutList(["a", "bb", "b", "ccc"], 4), ["a", "bb", "b", "ccc"])
        self.assertEqual(wordsWithoutList(["xx", "yyy", "x", "yy", "z"], 1), ["xx", "yyy", "yy"])
        self.assertEqual(wordsWithoutList(["xx", "yyy", "x", "yy", "z"], 2), ["yyy", "x", "z"])


class TestHasOne(unittest.TestCase):
    def test_hasOne(self):
        self.assertTrue(hasOne(10))
        self.assertFalse(hasOne(22))
        self.assertFalse(hasOne(220))
        self.assertTrue(hasOne(212))
        self.assertTrue(hasOne(1))
        self.assertFalse(hasOne(9))
        self.assertTrue(hasOne(211112))
        self.assertTrue(hasOne(121121))
        self.assertFalse(hasOne(222222))
        self.assertTrue(hasOne(56156))
        self.assertFalse(hasOne(56556))

class TestDividesSelf(unittest.TestCase):
    def test_dividesSelf(self):
        self.assertTrue(dividesSelf(128))
        self.assertTrue(dividesSelf(12))
        self.assertFalse(dividesSelf(120))
        self.assertTrue(dividesSelf(122))
        self.assertFalse(dividesSelf(13))
        self.assertFalse(dividesSelf(32))
        self.assertTrue(dividesSelf(22))
        self.assertFalse(dividesSelf(42))
        self.assertTrue(dividesSelf(212))
        self.assertFalse(dividesSelf(213))
        self.assertTrue(dividesSelf(162))

class TestCopyEvens(unittest.TestCase):
    def test_copyEvens(self):
        self.assertEqual(copyEvens([3, 2, 4, 5, 8], 2), [2, 4])
        self.assertEqual(copyEvens([3, 2, 4, 5, 8], 3), [2, 4, 8])
        self.assertEqual(copyEvens([6, 1, 2, 4, 5, 8], 3), [6, 2, 4])
        self.assertEqual(copyEvens([6, 1, 2, 4, 5, 8], 4), [6, 2, 4, 8])
        self.assertEqual(copyEvens([3, 1, 4, 1, 5], 1), [4])
        self.assertEqual(copyEvens([2], 1), [2])
        self.assertEqual(copyEvens([6, 2, 4, 8], 2), [6, 2])
        self.assertEqual(copyEvens([6, 2, 4, 8], 3), [6, 2, 4])
        self.assertEqual(copyEvens([6, 2, 4, 8], 4), [6, 2, 4, 8])
        self.assertEqual(copyEvens([1, 8, 4], 1), [8])
        self.assertEqual(copyEvens([1, 8, 4], 2), [8, 4])
        self.assertEqual(copyEvens([2, 8, 4], 2), [2, 8])


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