#!/usr/bin/env python3
# -*- coding: utf-8 -*-

"""
Instructions to run the tests via the CLI:
    1. Open your terminal or command prompt.
    2. Run the tests by executing: `python ap_quiz_package02.py`

This is a list of quiz questions for practice, including:

"""
# Import the unittest module, needed for creating test cases.
# We only need to import it once at the top of the file.
import unittest

# Import List from the typing module for type hinting (e.g., List[str])
# This is preferred for compatibility with Python versions < 3.10
from typing import List

































# -----------------------------------------------------------------
# Question 1: copyEndy
# -----------------------------------------------------------------
def copyEndy(nums: list[int], count: int) -> list[int]:
    """
    Description:
        Given an array of positive integers, return a new list containing 
        the first `count` endy numbers from the original array.
        An integer is endy if it is in the range 0..10 or 90..100 (inclusive).

    Examples:
        copyEndy([9, 11, 90, 22, 6], 2) → [9, 90]
        copyEndy([9, 11, 90, 22, 6], 3) → [9, 90, 6]
        copyEndy([12, 1, 1, 13, 0, 20], 2) → [1, 1]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python q11.py`
        
    Args:
        nums (list[int]): The list of positive integers to check.
        count (int): The number of endy integers to return.
        
    Returns:
        list[int]: A list containing the first `count` endy integers.
    """
    ### [Your Implementation Here]
    return None
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




# -----------------------------------------------------------------
# Question 2: matchUp
# -----------------------------------------------------------------
def matchUp(a: list[str], b: list[str]) -> int:
    """
    Description:
        Given two arrays of strings of the same length, compare each 
        string in the first array to the corresponding string in the second array. 
        Count the number of times that the two strings are non-empty 
        and start with the same character.

    Examples:
        matchUp(["aa", "bb", "cc"], ["aaa", "xx", "bb"]) → 1
        matchUp(["aa", "bb", "cc"], ["aaa", "b", "bb"]) → 2
        matchUp(["aa", "bb", "cc"], ["", "", "ccc"]) → 1
        
    Args:
        a (list[str]): The first list of strings to compare.
        b (list[str]): The second list of strings to compare.

    Returns:
        int: The count of non-empty string pairs that start with the same character.
    """
    ### [Your Implementation Here]
    return 0
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.



# -----------------------------------------------------------------
# Question 3: scoreUp
# -----------------------------------------------------------------
def scoreUp(key: list[str], answers: list[str]) -> int:
    """
    Description:
        Given two arrays of strings, the "key" array containing the 
        correct answers and the "answers" array containing the student's responses,
        calculate the score for the student. 
        A correct answer is worth +4 points, an incorrect answer is worth 
        -1 point, and a blank answer ("?") is worth 0 points.

    Examples:
        scoreUp(["a", "a", "b", "b"], ["a", "c", "b", "c"]) → 6
        scoreUp(["a", "a", "b", "b"], ["a", "a", "b", "c"]) → 11
        scoreUp(["a", "a", "b", "b"], ["a", "a", "b", "b"]) → 16
        
    Args:
        key (list[str]): The list of correct answers.
        answers (list[str]): The list of student's answers.

    Returns:
        int: The student's total score based on the comparison.
    """
    ### [Your Implementation Here]
    return 0
        


    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.
    
    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




# -----------------------------------------------------------------
# Question 4: wordsWithout
# -----------------------------------------------------------------
def wordsWithout(words: list[str], target: str) -> list[str]:
    """
    Description:
        Given an array of strings, return a new list without 
        the strings that are equal to the target string.
        The new list should contain all the strings from the 
        original list except the ones that match the target string.

    Examples:
        wordsWithout(["a", "b", "c", "a"], "a") → ["b", "c"]
        wordsWithout(["a", "b", "c", "a"], "b") → ["a", "c", "a"]
        wordsWithout(["a", "b", "c", "a"], "c") → ["a", "b", "a"]

    Args:
        words (list[str]): The list of words to filter.
        target (str): The target word to remove from the list.
        
    Returns:
        list[str]: A new list containing all words except those that are equal to the target string.
    """
    ### [Your Implementation Here]
    return None

    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# -----------------------------------------------------------------
# Question 5: scoresSpecial
# -----------------------------------------------------------------
def scoresSpecial(a: list[int], b: list[int]) -> int:
    """
    Description:
        Given two arrays of non-negative integer scores, a "special" 
        score is one which is a multiple of 10
        (e.g., 40 or 90). Return the sum of the largest special score 
        in array `a` plus the largest special
        score in array `b`.

    Examples:
        scoresSpecial([12, 10, 4], [2, 20, 30]) → 40
        scoresSpecial([20, 10, 4], [2, 20, 10]) → 40
        scoresSpecial([12, 11, 4], [2, 20, 31]) → 20
        scoresSpecial([1, 20, 2, 50], [3, 4, 5]) → 50
        scoresSpecial([10, 4, 20, 30], [30, 20, 99]) → 60

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python q15.py`

    Args:
        a (list[int]): First list of non-negative scores.
        b (list[int]): Second list of non-negative scores.

    Returns:
        int: Sum of the largest multiple-of-10 in `a` and the largest multiple-of-10 in `b`.
    """
    def max_special(scores: list[int]) -> int:
        """
        Helper:
            Finds the largest "special" score (a multiple of 10) in the given list.
            Returns 0 if there are no multiples of 10.
        """
        i = 0
        specials = []
        while i < len(scores):
            if scores[i] % 10 == 0:
                specials.append(scores[i])
            i += 1
        i = 0
        if specials == []:
            return 0
        maximum = specials[0]
        while i < len(specials):
            if specials[i] > maximum:
                maximum = specials[i]
            i += 1
        return maximum
    ### [Your Implementation Here]
    sum_of_specials = max_special(a) + max_special(b)
    return sum_of_specials
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.
    return None




# 
def sumHeights(heights: list[int], start: int, end: int) -> int:
    """
    Description:
        We have an array of heights, representing the altitude along a walking trail.
        Given start/end indexes into the array, return the sum of the changes for a walk
        beginning at the start index and ending at the end index. For example, with the
        heights [5, 3, 6, 7, 2] and start=2, end=4 yields a sum of 1 + 5 = 6.
        The start and end indices will both be valid with start <= end.

    Examples:
        sumHeights([5, 3, 6, 7, 2], 2, 4) → 6
        sumHeights([5, 3, 6, 7, 2], 0, 1) → 2
        sumHeights([5, 3, 6, 7, 2], 0, 4) → 11

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q16.py`

    Args:
        heights (list[int]): List of integer altitudes along the trail.
        start (int): Starting index of the walk.
        end (int): Ending index of the walk.

    Returns:
        int: The total sum of absolute height changes from start to end.
    """
    ### [Your Implementation Here]
    i = start
    abs_height_sum = 0
    while i < end:
        abs_height_sum += abs(heights[i] - heights[i + 1])
        i += 1
    return abs_height_sum
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.



def sumHeights2(heights: list[int], start: int, end: int) -> int:
    """
    Description:
        A variation on the sumHeights problem. We have an array of heights representing
        the altitude along a walking trail. Given start/end indexes into the array,
        return the sum of the changes for a walk beginning at the start index and ending
        at the end index, however increases in height count double.

    Examples:
        sumHeights2([5, 3, 6, 7, 2], 2, 4) → 7
        sumHeights2([5, 3, 6, 7, 2], 0, 1) → 2
        sumHeights2([5, 3, 6, 7, 2], 0, 4) → 15

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q17.py`

    Args:
        heights (list[int]): List of integer altitudes along the trail.
        start (int): Starting index of the walk.
        end (int): Ending index of the walk.

    Returns:
        int: The total sum of height changes, counting each upward change twice.
    """
    ### [Your Implementation Here]
    i = start
    abs_height_sum = 0
    while i < end:
        if heights[i + 1] - heights[i] > 0:
            abs_height_sum += 2 * (heights[i + 1] - heights[i])
        else:
            abs_height_sum += abs(heights[i] - heights[i + 1])
        i += 1
    return abs_height_sum
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




def bigHeights(heights: list[int], start: int, end: int) -> int:
    """
    Description:
        A variation on the sumHeights problem. We have an array of heights representing
        the altitude along a walking trail. Given start/end indexes into the array,
        return the number of "big" steps for a walk beginning at the start index and
        ending at the end index. A step is "big" if the change is 5 or more up or down.

    Examples:
        bigHeights([5, 3, 6, 7, 2], 2, 4) → 1
        bigHeights([5, 3, 6, 7, 2], 0, 1) → 0
        bigHeights([5, 3, 6, 7, 2], 0, 4) → 1
        bigHeights([5, 3, 6, 7, 3], 0, 4) → 0

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q18.py`

    Args:
        heights (list[int]): The list of integer altitudes along the trail.
        start (int): The starting index of the walk (inclusive).
        end (int): The ending index of the walk (inclusive).

    Returns:
        int: The count of "big" steps (difference ≥ 5) between start and end.
    """
    ### [Your Implementation Here]
    i = start
    count = 0
    while i < end:
        if abs(heights[i] - heights[i + 1]) >= 5:
            count += 1
        i += 1
    return count
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.

def userCompare(aName: str, aId: int, bName: str, bId: int) -> int:
    """
    Description:
        We have data for two users, A and B, each with a string 
        `name` and an integer `id`.
        The goal is to order the users for sorting. Return:
          - -1 if A comes before B
          -  1 if A comes after B
          -  0 if they are the same.
        Order first by name (lexicographically), and if the names 
        are equal, order by id.

    Examples:
        userCompare("bb", 1, "zz", 2) → -1
        userCompare("bb", 1, "aa", 2) → 1
        userCompare("bb", 1, "bb", 1) → 0
        userCompare("bb", 5, "bb", 1) → 1
        userCompare("bb", 5, "bb", 10) → -1
        userCompare("adam", 1, "bob", 2) → -1
        userCompare("bob", 1, "bob", 2) → -1
        userCompare("bzb", 1, "bob", 2) → 1

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q19.py`

    Args:
        aName (str): Name of user A.
        aId   (int): ID of user A.
        bName (str): Name of user B.
        bId   (int): ID of user B.

    Returns:
        int: -1 if A < B, 1 if A > B, 0 if they are equal.
    """
    ### [Your Implementation Here]
    if aName == bName:
        if aId < bId:
            return -1
        if bId < aId:
            return 1
        if aId == bId:
            return 0
    i = 0
    while i < len(aName):
        if ord(aName[i]) < ord(bName[i]):
            return -1
        if ord(aName[i]) > ord(bName[i]):
            return 1
        i += 1
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.








def mergeTwo(a: list[str], b: list[str], n: int) -> list[str]:
    """
    Description:
        Start with two arrays of strings, `a` and `b`, each sorted 
        alphabetically and without duplicates.
        Return a new list containing the first `n` elements from the 
        two arrays merged together.
        The result list should be in alphabetical order and 
        without duplicates.
        You should make a single pass over `a` and `b`, taking 
        advantage of their sorted order.

    Examples:
        mergeTwo(["a", "c", "z"], ["b", "f", "z"], 3) → ["a", "b", "c"]
        mergeTwo(["a", "c", "z"], ["c", "f", "z"], 3) → ["a", "c", "f"]
        mergeTwo(["f", "g", "z"], ["c", "f", "g"], 3) → ["c", "f", "g"]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q20.py`

    Args:
        a (list[str]): First sorted list of unique strings.
        b (list[str]): Second sorted list of unique strings.
        n (int): Number of elements to include in the merged result.

    Returns:
        list[str]: A sorted list of the first `n` unique strings from merging `a` and `b`.
    """
    def quicksort(arr: list[str]) -> list[str]:
        if len(arr) <= 1:
            return arr
        pivot = arr[len(arr) // 2]
        left = [x for x in arr if x < pivot]
        middle = [x for x in arr if x == pivot]
        right = [x for x in arr if x > pivot]
        return quicksort(left) + middle + quicksort(right)
    ### [Your Implementation Here]
    c = a + b
    c = quicksort(c) # n log n
    unique_c = [c[0]] # O(1)
    for i in range(1, len(c) - 1): # O(n)
        if (len(unique_c) == n): 
            break
        if (c[i] != unique_c[-1]): 
            unique_c.append(c[i])
    return unique_c

    # i = 0
    # j = 0
    # curr_ord = -1
    # while len(c) < n:
    #     if ord(a[i]) < ord(b[j]):
    #         c.append(a[i])
    #         curr_ord = ord(a[i])
    #         i += 1
    #     elif ord(b[j]) < ord(a[i]):
    #         c.append(b[i])
    #         curr_ord = ord(b[j])
    #         j += 1
    #     elif ord(a[i]) == ord(b[j]):
    #         if ord(a[i]) != curr_ord: 
    #             c.append(a[i])
    #             curr_ord = ord(a[i])
    #             i += 1
    #         j += 1
    # return c

    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


























# -----------------------------------------------------------------
# Unit Tests
# -----------------------------------------------------------------
class TestCopyEndy(unittest.TestCase):
    def test_copyEndy(self):
        self.assertEqual(copyEndy([9, 11, 90, 22, 6], 2), [9, 90])
        self.assertEqual(copyEndy([9, 11, 90, 22, 6], 3), [9, 90, 6])
        self.assertEqual(copyEndy([12, 1, 1, 13, 0, 20], 2), [1, 1])
        self.assertEqual(copyEndy([12, 1, 1, 13, 0, 20], 3), [1, 1, 0])
        self.assertEqual(copyEndy([0], 1), [0])
        self.assertEqual(copyEndy([10, 11, 90], 2), [10, 90])
        self.assertEqual(copyEndy([90, 22, 100], 2), [90, 100])
        self.assertEqual(copyEndy([12, 11, 10, 89, 101, 4], 1), [10])
        self.assertEqual(copyEndy([13, 2, 2, 0], 2), [2, 2])
        self.assertEqual(copyEndy([13, 2, 2, 0], 3), [2, 2, 0])
        self.assertEqual(copyEndy([13, 2, 13, 2, 0, 30], 2), [2, 2])
        self.assertEqual(copyEndy([13, 2, 13, 2, 0, 30], 3), [2, 2, 0])


class TestMatchUp(unittest.TestCase):
    def test_matchUp(self):
        self.assertEqual(matchUp(["aa", "bb", "cc"], ["aaa", "xx", "bb"]), 1)
        self.assertEqual(matchUp(["aa", "bb", "cc"], ["aaa", "b", "bb"]), 2)
        self.assertEqual(matchUp(["aa", "bb", "cc"], ["", "", "ccc"]), 1)
        self.assertEqual(matchUp(["", "", "ccc"], ["aa", "bb", "cc"]), 1)
        self.assertEqual(matchUp(["", "", ""], ["", "bb", "cc"]), 0)
        self.assertEqual(matchUp(["aa", "bb", "cc"], ["", "", ""]), 0)
        self.assertEqual(matchUp(["aa", "", "ccc"], ["", "bb", "cc"]), 1)
        self.assertEqual(matchUp(["x", "y", "z"], ["y", "z", "x"]), 0)
        self.assertEqual(matchUp(["", "y", "z"], ["", "y", "x"]), 1)
        self.assertEqual(matchUp(["x", "y", "z"], ["xx", "yyy", "zzz"]), 3)
        self.assertEqual(matchUp(["x", "y", "z"], ["xx", "yyy", ""]), 2)
        self.assertEqual(matchUp(["b", "x", "y", "z"], ["a", "xx", "yyy", "zzz"]), 3)
        self.assertEqual(matchUp(["aaa", "bb", "c"], ["aaa", "xx", "bb"]), 1)


class TestScoreUp(unittest.TestCase):
    def test_scoreUp(self):
        self.assertEqual(scoreUp(["a", "a", "b", "b"], ["a", "c", "b", "c"]), 6)
        self.assertEqual(scoreUp(["a", "a", "b", "b"], ["a", "a", "b", "c"]), 11)
        self.assertEqual(scoreUp(["a", "a", "b", "b"], ["a", "a", "b", "b"]), 16)
        self.assertEqual(scoreUp(["a", "a", "b", "b"], ["?", "c", "b", "?"]), 3)
        self.assertEqual(scoreUp(["a", "a", "b", "b"], ["?", "c", "?", "?"]), -1)
        self.assertEqual(scoreUp(["a", "a", "b", "b"], ["c", "?", "b", "b"]), 7)
        self.assertEqual(scoreUp(["a", "a", "b", "b"], ["c", "?", "b", "?"]), 3)
        self.assertEqual(scoreUp(["a", "b", "c"], ["a", "c", "b"]), 2)
        self.assertEqual(scoreUp(["a", "a", "b", "b", "c", "c"], ["a", "c", "a", "c", "a", "c"]), 4)
        self.assertEqual(scoreUp(["a", "a", "b", "b", "c", "c"], ["a", "c", "?", "?", "a", "c"]), 6)
        self.assertEqual(scoreUp(["a", "a", "b", "b", "c", "c"], ["a", "c", "?", "?", "c", "c"]), 11)
        self.assertEqual(scoreUp(["a", "b", "c"], ["a", "b", "c"]), 12)

class TestWordsWithout(unittest.TestCase):
    def test_wordsWithout(self):
        self.assertEqual(wordsWithout(["a", "b", "c", "a"], "a"), ["b", "c"])
        self.assertEqual(wordsWithout(["a", "b", "c", "a"], "b"), ["a", "c", "a"])
        self.assertEqual(wordsWithout(["a", "b", "c", "a"], "c"), ["a", "b", "a"])
        self.assertEqual(wordsWithout(["b", "c", "a", "a"], "b"), ["c", "a", "a"])
        self.assertEqual(wordsWithout(["xx", "yyy", "x", "yy", "x"], "x"), ["xx", "yyy", "yy"])
        self.assertEqual(wordsWithout(["xx", "yyy", "x", "yy", "x"], "yy"), ["xx", "yyy", "x", "x"])
        self.assertEqual(wordsWithout(["aa", "ab", "ac", "aa"], "aa"), ["ab", "ac"])

class TestScoresSpecial(unittest.TestCase):
    def test_scoresSpecial(self):
        self.assertEqual(scoresSpecial([12, 10, 4], [2, 20, 30]), 40)
        self.assertEqual(scoresSpecial([20, 10, 4], [2, 20, 10]), 40)
        self.assertEqual(scoresSpecial([12, 11, 4], [2, 20, 31]), 20)
        self.assertEqual(scoresSpecial([1, 20, 2, 50], [3, 4, 5]), 50)
        self.assertEqual(scoresSpecial([3, 4, 5], [1, 50, 2, 20]), 50)
        self.assertEqual(scoresSpecial([10, 4, 20, 30], [20]), 50)
        self.assertEqual(scoresSpecial([10, 4, 20, 30], [3, 20, 99]), 50)
        self.assertEqual(scoresSpecial([10, 4, 20, 30], [30, 20, 99]), 60)
        self.assertEqual(scoresSpecial([], [2]), 0)
        self.assertEqual(scoresSpecial([], [20]), 20)
        self.assertEqual(scoresSpecial([14, 10, 4], [4, 20, 30]), 40)


class TestSumHeights(unittest.TestCase):
    def test_sumHeights(self):
        self.assertEqual(sumHeights([5, 3, 6, 7, 2], 2, 4), 6)
        self.assertEqual(sumHeights([5, 3, 6, 7, 2], 0, 1), 2)
        self.assertEqual(sumHeights([5, 3, 6, 7, 2], 0, 4), 11)
        self.assertEqual(sumHeights([5, 3, 6, 7, 2], 1, 1), 0)
        self.assertEqual(sumHeights([1, 2, 3, 4, 5, 4, 3, 2, 10], 0, 3), 3)


class TestSumHeights2(unittest.TestCase):
    def test_sumHeights2(self):
        self.assertEqual(sumHeights2([5, 3, 6, 7, 2], 2, 4), 7)
        self.assertEqual(sumHeights2([5, 3, 6, 7, 2], 0, 1), 2)
        self.assertEqual(sumHeights2([5, 3, 6, 7, 2], 0, 4), 15)
        self.assertEqual(sumHeights2([5, 3, 6, 7, 2], 1, 1), 0)
        self.assertEqual(sumHeights2([1, 2, 3, 4, 5, 4, 3, 2, 10], 0, 3), 6)
        self.assertEqual(sumHeights2([1, 2, 3, 4, 5, 4, 3, 2, 10], 4, 8), 19)
        self.assertEqual(sumHeights2([1, 2, 3, 4, 5, 4, 3, 2, 10], 7, 8), 16)
        self.assertEqual(sumHeights2([1, 2, 3, 4, 5, 4, 3, 2, 10], 8, 8), 0)
        self.assertEqual(sumHeights2([1, 2, 3, 4, 5, 4, 3, 2, 10], 2, 2), 0)
        self.assertEqual(sumHeights2([1, 2, 3, 4, 5, 4, 3, 2, 10], 3, 6), 4)
        self.assertEqual(sumHeights2([10, 8, 7, 7, 7, 6, 7], 1, 4), 1)
        self.assertEqual(sumHeights2([10, 8, 7, 7, 7, 6, 7], 1, 5), 2)


class TestBigHeights(unittest.TestCase):
    def test_bigHeights(self):
        self.assertEqual(bigHeights([5, 3, 6, 7, 2], 2, 4), 1)
        self.assertEqual(bigHeights([5, 3, 6, 7, 2], 0, 1), 0)
        self.assertEqual(bigHeights([5, 3, 6, 7, 2], 0, 4), 1)
        self.assertEqual(bigHeights([5, 3, 6, 7, 3], 0, 4), 0)
        self.assertEqual(bigHeights([5, 3, 6, 7, 2], 1, 1), 0)
        self.assertEqual(bigHeights([5, 13, 6, 7, 2], 1, 2), 1)
        self.assertEqual(bigHeights([5, 13, 6, 7, 2], 0, 2), 2)
        self.assertEqual(bigHeights([5, 13, 6, 7, 2], 1, 4), 2)
        self.assertEqual(bigHeights([5, 13, 6, 7, 2], 0, 4), 3)
        self.assertEqual(bigHeights([5, 13, 6, 7, 2], 0, 3), 2)
        self.assertEqual(bigHeights([1, 2, 3, 4, 5, 4, 3, 2, 10], 0, 3), 0)
        self.assertEqual(bigHeights([1, 2, 3, 4, 5, 4, 3, 2, 10], 4, 8), 1)
        self.assertEqual(bigHeights([1, 2, 3, 14, 5, 4, 3, 2, 10], 0, 3), 1)
        self.assertEqual(bigHeights([1, 2, 3, 14, 5, 4, 3, 2, 10], 7, 8), 1)
        self.assertEqual(bigHeights([1, 2, 3, 14, 5, 4, 3, 2, 10], 3, 8), 2)
        self.assertEqual(bigHeights([1, 2, 3, 14, 5, 4, 3, 2, 10], 2, 8), 3)

class TestUserCompare(unittest.TestCase):
    def test_userCompare(self):
        self.assertEqual(userCompare("bb", 1, "zz", 2), -1)
        self.assertEqual(userCompare("bb", 1, "aa", 2), 1)
        self.assertEqual(userCompare("bb", 1, "bb", 1), 0)
        self.assertEqual(userCompare("bb", 5, "bb", 1), 1)
        self.assertEqual(userCompare("bb", 5, "bb", 10), -1)
        self.assertEqual(userCompare("adam", 1, "bob", 2), -1)
        self.assertEqual(userCompare("bob", 1, "bob", 2), -1)
        self.assertEqual(userCompare("bzb", 1, "bob", 2), 1)

class TestMergeTwo(unittest.TestCase):
    def test_mergeTwo(self):
        self.assertEqual(mergeTwo(["a", "c", "z"], ["b", "f", "z"], 3), ["a", "b", "c"])
        self.assertEqual(mergeTwo(["a", "c", "z"], ["c", "f", "z"], 3), ["a", "c", "f"])
        self.assertEqual(mergeTwo(["f", "g", "z"], ["c", "f", "g"], 3), ["c", "f", "g"])
        self.assertEqual(mergeTwo(["a", "c", "z"], ["a", "c", "z"], 3), ["a", "c", "z"])
        self.assertEqual(mergeTwo(["a", "b", "c", "z"], ["a", "c", "z"], 3), ["a", "b", "c"])
        self.assertEqual(mergeTwo(["a", "c", "z"], ["a", "b", "c", "z"], 3), ["a", "b", "c"])
        self.assertEqual(mergeTwo(["a", "c", "z"], ["a", "c", "z"], 2), ["a", "c"])
        self.assertEqual(mergeTwo(["a", "c", "z"], ["a", "c", "y", "z"], 3), ["a", "c", "y"])
        self.assertEqual(mergeTwo(["x", "y", "z"], ["a", "b", "z"], 3), ["a", "b", "x"])


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