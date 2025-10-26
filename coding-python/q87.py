def oneTwo(s: str) -> str:
    """
    Description:
        Given a string, compute a new string by moving the first char of each
        consecutive group of 3 chars to come after the next two chars.
        Ignore any group of fewer than 3 chars at the end.

    Examples:
        oneTwo("abc") → "bca"
        oneTwo("tca") → "cat"
        oneTwo("tcagdo") → "catdog"

    Instructions to run the tests via the CLI:
        1. Open your terminal or command prompt.
        2. Run the tests by executing: `python async-ap/q87.py`

    Args:
        s (str): The input string.

    Returns:
        str: The transformed string after applying the oneTwo rule.
    """
    ### [Your Implementation Here]
    
    # Case-1. If the question can be solved with 'iteration (for/while)', 
    # design the most efficient algorithm.

    # Case-2. If the question can be solved with 'recursion', design a 
    # correct algorithm. Since the recursion can be inefficient, use 
    # either 'tabulation' or 'memorization' to break it down into 'iteration'.


# Unit tests for oneTwo function
import unittest

class TestOneTwo(unittest.TestCase):
    def test_oneTwo(self):
        self.assertEqual(oneTwo("abc"), "bca")
        self.assertEqual(oneTwo("tca"), "cat")
        self.assertEqual(oneTwo("tcagdo"), "catdog")
        self.assertEqual(oneTwo("chocolate"), "hocolctea")
        self.assertEqual(oneTwo("1234567890"), "231564897")
        self.assertEqual(oneTwo("xabxabxabxabxabxabxab"), "abxabxabxabxabxabxabx")
        self.assertEqual(oneTwo("abcdefx"), "bcaefd")
        self.assertEqual(oneTwo("abcdefxy"), "bcaefd")
        self.assertEqual(oneTwo("abcdefxyz"), "bcaefdyzx")
        self.assertEqual(oneTwo(""), "")
        self.assertEqual(oneTwo("x"), "")
        self.assertEqual(oneTwo("xy"), "")
        self.assertEqual(oneTwo("xyz"), "yzx")
        long_input = "abcdefghijklkmnopqrstuvwxyz1234567890"
        long_expected = "bcaefdhigkljmnkpqostrvwuyzx231564897"
        self.assertEqual(oneTwo(long_input), long_expected)
        # Also test inputs where length just under multiple of 3
        self.assertEqual(oneTwo(long_input[:-1]), long_expected)
        self.assertEqual(oneTwo(long_input[:-2]), long_expected[:-3])

if __name__ == "__main__":
    unittest.main()
