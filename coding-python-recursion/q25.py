def countHi2(s: str) -> int:
    """
    Description:
        Recursively computes the number of times the lowercase "hi" appears in the string,
        but does not count "hi" if it is immediately preceded by an 'x'.

    Examples:
        countHi2("ahixhi") → 1
        countHi2("ahibhi") → 2
        countHi2("xhixhi") → 0

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q25.py`

    Args:
        s (str): The input string.

    Returns:
        int: The count of valid "hi" occurrences.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the countHi2 function
import unittest

class TestCountHi2Function(unittest.TestCase):
    def test_countHi2(self):
        self.assertEqual(countHi2("ahixhi"), 1)  # countHi2("ahixhi") → 1
        self.assertEqual(countHi2("ahibhi"), 2)  # countHi2("ahibhi") → 2
        self.assertEqual(countHi2("xhixhi"), 0)  # countHi2("xhixhi") → 0
        self.assertEqual(countHi2("hixhi"), 1)  # countHi2("hixhi") → 1
        self.assertEqual(countHi2("hixhhi"), 2)  # countHi2("hixhhi") → 2
        self.assertEqual(countHi2("hihihi"), 3)  # countHi2("hihihi") → 3
        self.assertEqual(countHi2("hihihix"), 3)  # countHi2("hihihix") → 3
        self.assertEqual(countHi2("xhihihix"), 2)  # countHi2("xhihihix") → 2
        self.assertEqual(countHi2("xxhi"), 0)  # countHi2("xxhi") → 0
        self.assertEqual(countHi2("hixxhi"), 1)  # countHi2("hixxhi") → 1
        self.assertEqual(countHi2("hi"), 1)  # countHi2("hi") → 1
        self.assertEqual(countHi2("xxxx"), 0)  # countHi2("xxxx") → 0
        self.assertEqual(countHi2("h"), 0)  # countHi2("h") → 0
        self.assertEqual(countHi2("x"), 0)  # countHi2("x") → 0
        self.assertEqual(countHi2(""), 0)  # countHi2("") → 0
        self.assertEqual(countHi2("Hellohi"), 1)  # countHi2("Hellohi") → 1

if __name__ == '__main__':
    unittest.main()
