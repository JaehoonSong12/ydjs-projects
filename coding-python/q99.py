def fizzBuzz(start: int, end: int) -> list[str]:
    """
    Description:
        Generate the FizzBuzz sequence as strings for numbers from `start` up to but not including `end`.
        - For multiples of 3, use "Fizz".
        - For multiples of 5, use "Buzz".
        - For multiples of both 3 and 5, use "FizzBuzz".
        - Otherwise, use the number itself.

    Examples:
        fizzBuzz(1, 6) → ["1", "2", "Fizz", "4", "Buzz"]
        fizzBuzz(1, 8) → ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7"]
        fizzBuzz(1, 11) → ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"]

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q99.py`

    Args:
        start (int): The starting integer (inclusive).
        end (int): The ending integer (exclusive).

    Returns:
        list[str]: The FizzBuzz sequence as a list of strings.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for fizzBuzz function
import unittest

class TestFizzBuzz(unittest.TestCase):
    def test_fizzBuzz(self):
        self.assertEqual(fizzBuzz(1, 6), ["1", "2", "Fizz", "4", "Buzz"])
        self.assertEqual(fizzBuzz(1, 8), ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7"])
        self.assertEqual(
            fizzBuzz(1, 11),
            ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz"]
        )
        self.assertEqual(
            fizzBuzz(1, 16),
            ["1", "2", "Fizz", "4", "Buzz", "Fizz", "7", "8", "Fizz", "Buzz",
             "11", "Fizz", "13", "14", "FizzBuzz"]
        )
        self.assertEqual(fizzBuzz(1, 4), ["1", "2", "Fizz"])
        self.assertEqual(fizzBuzz(1, 2), ["1"])
        self.assertEqual(
            fizzBuzz(50, 56),
            ["Buzz", "Fizz", "52", "53", "Fizz", "Buzz"]
        )
        self.assertEqual(fizzBuzz(15, 17), ["FizzBuzz", "16"])
        self.assertEqual(
            fizzBuzz(30, 36),
            ["FizzBuzz", "31", "32", "Fizz", "34", "Buzz"]
        )
        self.assertEqual(
            fizzBuzz(1000, 1006),
            ["Buzz", "1001", "Fizz", "1003", "1004", "FizzBuzz"]
        )
        self.assertEqual(fizzBuzz(99, 102), ["Fizz", "Buzz", "101"])
        self.assertEqual(
            fizzBuzz(14, 20),
            ["14", "FizzBuzz", "16", "17", "Fizz", "19"]
        )

if __name__ == "__main__":
    unittest.main()
