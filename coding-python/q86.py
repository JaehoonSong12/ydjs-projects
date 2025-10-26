def seriesUp(n: int) -> list[int]:
    """
    Description:
        Given a non-negative integer `n`, create and return a list containing
        the sequence 1, then 1,2, then 1,2,3, … up to 1,2,...,n. The total length
        of the list will be n*(n+1)/2.

    Examples:
        seriesUp(3) → [1, 1, 2, 1, 2, 3]
        seriesUp(4) → [1, 1, 2, 1, 2, 3, 1, 2, 3, 4]
        seriesUp(2) → [1, 1, 2]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q86.py`

    Args:
        n (int): The non-negative integer up to which to build the sequence.

    Returns:
        list[int]: The expanded series list.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for seriesUp function
import unittest

class TestSeriesUp(unittest.TestCase):
    def test_seriesUp(self):
        self.assertEqual(seriesUp(3), [1, 1, 2, 1, 2, 3])
        self.assertEqual(seriesUp(4), [1, 1, 2, 1, 2, 3, 1, 2, 3, 4])
        self.assertEqual(seriesUp(2), [1, 1, 2])
        self.assertEqual(seriesUp(1), [1])
        self.assertEqual(seriesUp(0), [])
        self.assertEqual(seriesUp(6),
                         [1, 1, 2, 1, 2, 3, 1, 2, 3, 4,
                          1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6])

if __name__ == "__main__":
    unittest.main()
