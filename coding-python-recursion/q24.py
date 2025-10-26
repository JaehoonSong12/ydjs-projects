def stringClean(s: str) -> str:
    """
    Description:
        Recursively returns a "cleaned" string where adjacent duplicate characters
        have been reduced to a single character.

    Examples:
        stringClean("yyzzza") → "yza"
        stringClean("abbbcdd") → "abcd"
        stringClean("Hello") → "Helo"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q24.py`

    Args:
        s (str): The input string.

    Returns:
        str: The cleaned string without consecutive duplicate characters.
    """
    # Base case: your implementation and comment here.

    # Recursive case: your implementation and comment here.


# Unit tests for the stringClean function
import unittest

class TestStringCleanFunction(unittest.TestCase):
    def test_stringClean(self):
        self.assertEqual(stringClean("yyzzza"), "yza")  # stringClean("yyzzza") → "yza"
        self.assertEqual(stringClean("abbbcdd"), "abcd")  # stringClean("abbbcdd") → "abcd"
        self.assertEqual(stringClean("Hello"), "Helo")  # stringClean("Hello") → "Helo"
        self.assertEqual(stringClean("XXabcYY"), "XabcY")  # stringClean("XXabcYY") → "XabcY"
        self.assertEqual(stringClean("112ab445"), "12ab45")  # stringClean("112ab445") → "12ab45"
        self.assertEqual(stringClean("Hello Bookkeeper"), "Helo Bokeper")  
        # stringClean("Hello Bookkeeper") → "Helo Bokeper"

if __name__ == '__main__':
    unittest.main()
