def countAbc(s: str) -> int:
    """
    Description:
        Recursively counts the total number of "abc" and "aba" substrings 
        that appear in the given string.

    Examples:
        countAbc("abc") → 1
        countAbc("abcxxabc") → 2
        countAbc("abaxxaba") → 2

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-recursion/q22.py`

    Args:
        s (str): The input string.

    Returns:
        int: The count of occurrences of "abc" or "aba" substrings.
    """
    # Base case: your implementation and comment here.
    if len(s) < 3:
        return 0
    if len(s) == 3:
        if s[0:3] == "abc" or s[0:3] == "aba":
            return 1
    # Recursive case: your implementation and comment here.
    first_3, rest = s[0:3], s[3:]
    if first_3 == "abc":
        return 1 + countAbc(rest)
    if first_3 == "aba":
        return 1 + countAbc(s[2] + rest)
    else:
        return countAbc(s[1:3] + rest)
    

# Unit tests for the countAbc function
import unittest

class TestCountAbcFunction(unittest.TestCase):
    def test_countAbc(self):
        self.assertEqual(countAbc("abc"), 1)  # countAbc("abc") → 1
        self.assertEqual(countAbc("abcxxabc"), 2)  # countAbc("abcxxabc") → 2
        self.assertEqual(countAbc("abaxxaba"), 2)  # countAbc("abaxxaba") → 2
        self.assertEqual(countAbc("ababc"), 2)  # countAbc("ababc") → 2
        self.assertEqual(countAbc("abxbc"), 0)  # countAbc("abxbc") → 0
        self.assertEqual(countAbc("aaabc"), 1)  # countAbc("aaabc") → 1
        self.assertEqual(countAbc("hello"), 0)  # countAbc("hello") → 0
        self.assertEqual(countAbc(""), 0)  # countAbc("") → 0
        self.assertEqual(countAbc("ab"), 0)  # countAbc("ab") → 0
        self.assertEqual(countAbc("aba"), 1)  # countAbc("aba") → 1
        self.assertEqual(countAbc("aca"), 0)  # countAbc("aca") → 0
        self.assertEqual(countAbc("aaa"), 0)  # countAbc("aaa") → 0

if __name__ == '__main__':
    unittest.main()
