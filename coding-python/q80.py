def fizzArray3(start: int, end: int) -> list[int]:
    """
    Description:
        Given start and end numbers, return a new list containing the sequence of integers
        from start up to but not including end. The end number will be greater than or equal
        to the start number. A length-0 list is valid if start == end.

    Examples:
        fizzArray3(5, 10) → [5, 6, 7, 8, 9]
        fizzArray3(11, 18) → [11, 12, 13, 14, 15, 16, 17]
        fizzArray3(1, 1) → []
        fizzArray3(1000, 1005) → [1000, 1001, 1002, 1003, 1004]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q80.py`

    Args:
        start (int): The starting integer (inclusive).
        end   (int): The ending integer (exclusive).

    Returns:
        list[int]: A list of integers from start up to end-1.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for fizzArray3 function
import unittest

class TestFizzArray3(unittest.TestCase):
    def test_fizzArray3(self):
        self.assertEqual(fizzArray3(5, 10), [5, 6, 7, 8, 9])
        self.assertEqual(fizzArray3(11, 18), [11, 12, 13, 14, 15, 16, 17])
        self.assertEqual(fizzArray3(1, 3), [1, 2])
        self.assertEqual(fizzArray3(1, 2), [1])
        self.assertEqual(fizzArray3(1, 1), [])
        self.assertEqual(fizzArray3(1000, 1005), [1000, 1001, 1002, 1003, 1004])

if __name__ == "__main__":
    unittest.main()
