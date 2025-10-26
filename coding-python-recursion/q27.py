def nestParen(s: str) -> bool:
    """
    Description:
        Recursively checks if a string is a valid nesting of zero or more pairs
        of parentheses. A valid nesting consists only of matching '(' and ')' pairs.

    Examples:
        nestParen("(())") → True
        nestParen("((()))") → True
        nestParen("(((x))") → False

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q27.py`

    Args:
        s (str): The input string.

    Returns:
        bool: True if the string is a valid nesting of parentheses, False otherwise.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the nestParen function
import unittest

class TestNestParenFunction(unittest.TestCase):
    def test_nestParen(self):
        self.assertTrue(nestParen("(())"))  # nestParen("(())") → True
        self.assertTrue(nestParen("((()))"))  # nestParen("((()))") → True
        self.assertFalse(nestParen("(((x))"))  # nestParen("(((x))") → False
        self.assertFalse(nestParen("((())"))  # nestParen("((())") → False
        self.assertTrue(nestParen("()"))  # nestParen("()") → True
        self.assertTrue(nestParen(""))  # nestParen("") → True
        self.assertFalse(nestParen("(yy)"))  # nestParen("(yy)") → False
        self.assertTrue(nestParen("(())"))  # nestParen("(())") → True
        self.assertFalse(nestParen("(((y))"))  # nestParen("(((y))") → False
        self.assertFalse(nestParen("((y)))"))  # nestParen("((y)))") → False
        self.assertTrue(nestParen("((()))"))  # nestParen("((()))") → True
        self.assertFalse(nestParen("(())))"))  # nestParen("(())))") → False
        self.assertFalse(nestParen("((yy())))"))  # nestParen("((yy())))") → False
        self.assertTrue(nestParen("(((())))"))  # nestParen("(((())))") → True

if __name__ == '__main__':
    unittest.main()
