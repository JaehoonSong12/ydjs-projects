def parenBit(s: str) -> str:
    """
    Description:
        Recursively extracts and returns the substring enclosed in parentheses
        from a given string containing exactly one pair of parentheses.

    Examples:
        parenBit("xyz(abc)123") → "(abc)"
        parenBit("x(hello)") → "(hello)"
        parenBit("(xy)1") → "(xy)"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q26.py`

    Args:
        s (str): The input string.

    Returns:
        str: The substring enclosed within parentheses.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the parenBit function
import unittest

class TestParenBitFunction(unittest.TestCase):
    def test_parenBit(self):
        self.assertEqual(parenBit("xyz(abc)123"), "(abc)")  # parenBit("xyz(abc)123") → "(abc)"
        self.assertEqual(parenBit("x(hello)"), "(hello)")  # parenBit("x(hello)") → "(hello)"
        self.assertEqual(parenBit("(xy)1"), "(xy)")  # parenBit("(xy)1") → "(xy)"
        self.assertEqual(parenBit("not really (possible)"), "(possible)")  # parenBit("not really (possible)") → "(possible)"
        self.assertEqual(parenBit("(abc)"), "(abc)")  # parenBit("(abc)") → "(abc)"
        self.assertEqual(parenBit("(abc)xyz"), "(abc)")  # parenBit("(abc)xyz") → "(abc)"
        self.assertEqual(parenBit("(abc)x"), "(abc)")  # parenBit("(abc)x") → "(abc)"
        self.assertEqual(parenBit("(x)"), "(x)")  # parenBit("(x)") → "(x)"
        self.assertEqual(parenBit("()"), "()")  # parenBit("()") → "()"
        self.assertEqual(parenBit("res (ipsa) loquitor"), "(ipsa)")  # parenBit("res (ipsa) loquitor") → "(ipsa)"
        self.assertEqual(parenBit("hello(not really)there"), "(not really)")  # parenBit("hello(not really)there") → "(not really)"
        self.assertEqual(parenBit("ab(ab)ab"), "(ab)")  # parenBit("ab(ab)ab") → "(ab)"

if __name__ == '__main__':
    unittest.main()
