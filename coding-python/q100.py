#!/usr/bin/env python3
def longest_consecutive(nums: list) -> int:
    """
    Description:
    Find the length of the longest run of consecutive integers in an unsorted list,
    in O(n) time by leveraging a set for uniqueness and constant‑time membership tests.

    Examples:
        longest_consecutive([100, 4, 200, 1, 3, 2]) → 4   # sequence is [1,2,3,4]
        longest_consecutive([0, -1, 1, 2, -2, 5]) → 5   # sequence is [-2,-1,0,1,2]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/set_vs_list.py`

    Args:
        nums (list): Unsorted list of integers.
    
    Returns:
        int: Length of the longest run of consecutive integers.
    """
    ### [Your Implementation Here]
    if len(nums) == 0:
        return 0
    nums = list(set(nums))
    nums.sort()
    i = 0
    max_count = 1
    count = 1
    while i < len(nums) - 1:
        if nums[i + 1] - nums[i] == 1:
            count += 1
        else:
            count = 1
        max_count = max(max_count, count)
        i += 1
    return max_count
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.




# Unit tests
import unittest

class TestLongestConsecutive(unittest.TestCase):
    def test_empty(self):
        self.assertEqual(longest_consecutive([]), 0)

    def test_single(self):
        self.assertEqual(longest_consecutive([42]), 1)

    def test_simple(self):
        self.assertEqual(longest_consecutive([1,2,3,4]), 4)

    def test_unsorted(self):
        self.assertEqual(longest_consecutive([100,4,200,1,3,2]), 4)

    def test_with_negatives(self):
        self.assertEqual(longest_consecutive([0,-1,1,2,-2,5]), 5)

    def test_duplicates(self):
        self.assertEqual(longest_consecutive([1,1,2,2,3,3]), 3)

    def test_disjoint(self):
        self.assertEqual(longest_consecutive([10,5,6,7,100]), 3)

if __name__ == '__main__':
    unittest.main()








####################################### CLI COMMANDS #######################################

# # Using curly braces
# my_set = {1, 2, 3} # this is more like math notation defining a set

# my_list = [1, 2,2,2,2,2,2, 3,3,3,3,3,3,3,3,4,4,4,4,4,4] # this is a list
# print(my_list)

# print(list(set(my_list)))

# # Using the set() constructor
# my_set = set([1, 2, 3])

# # Empty set
# empty_set = set()




# my_set = {1, 2, 3}

# # Add an element
# my_set.add(4)
# print(my_set)  # Result: {1, 2, 3, 4}
# # Result: {1, 2, 3, 4}
# my_set.add(4)
# print(my_set)  # Result: {1, 2, 3, 4}
# # Result: {1, 2, 3, 4}
# my_set.add(4)
# print(my_set)  # Result: {1, 2, 3, 4}
# # Result: {1, 2, 3, 4}

# # Remove an element
# my_set.remove(4)
# # Result: {1, 2, 3}
