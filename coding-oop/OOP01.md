<!--
  @requires
    1. VSCode extension: Markdown Preview Enhanced
    2. Shortcut: 'Ctrl' + 'Shift' + 'V'
    3. Split: Drag to right (->)

  @requires
    1. VSCode extension: Markdown All in One
    2. `File` > `Preferences` > `Keyboard Shortcuts`
    3. toggle code span > `Ctrl + '`
    4. toggle code block > `Ctrl + Shift + '`
-->

<!---------------------------------------------------------------------- @usage (end_of_proof)
<div style="text-align: right;">&#11035;</div> 
------------------------------------------------------------------------>

<!---------------------------------------------------------------------- @usage (end_of_section) 
<br /><br /><br />

---

Your paragraphs...

<p align="right">(<a href="#readme-top">back to top</a>)</p>
------------------------------------------------------------------------>

<!---------------------------------------------------------------------- @usage (image_hyperlink) 
<div align="center">
    <img src="images/fluid.webp"/>
</div>
------------------------------------------------------------------------>

<!---------------------------------------------------------------------- @usage (video_hyperlink) 
<div align="center">
    <video width="640" height="360" controls>
        <source src="videos/UX_research.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>
</div>
------------------------------------------------------------------------>

<!---------------------------------------------------------------------- @usage (url_hyperlink)
[url_title](URL)
------------------------------------------------------------------------>

<!-- Anchor Tag (Object) for "back to top" -->
<a id="readme-top"></a> 



# How to Start OOP in Python

**Dunder (double underscore) methods**, also known as **magic methods** in Python, are built-in functions for OOP related operations. 

## Magic Methods Examples

### 1. `__new__(cls, ...)`
- **Purpose:** Creates a new instance before `__init__` is called.
- **Usage:** Used when subclassing immutable types (like tuples or strings) or when you need to control instance creation.
- **Example:**

```python
class CustomStr(str):
    def __new__(cls, value):
        print("Creating a CustomStr instance")
        return super().__new__(cls, value)

print(CustomStr("Hi1"))
print(CustomStr("Hi2"))
```

---

### 2. `__init__(self, ...)`
- **Purpose:** Initializes the instance after it has been created.
- **Usage:** Used to set up instance attributes and perform any initialization steps.
- **Example:**

```python
class MyClass:
    def __init__(self, value):
        self.value = value
    
    def get_value(self):
        return self.value

print(MyClass(41561).get_value())
print(MyClass(145154).get_value())
```

---

### 3. `__repr__(self)` and `__str__(self)`
- **Purpose:**  
  - **`__repr__`:** Provides an official string representation of the object, mainly used for debugging.  
  - **`__str__`:** Offers a more user-friendly or informal string representation, used when printing.
- **Usage:**  
  Implement `__repr__` for an unambiguous description (often useful during development) and `__str__` for a readable display.
- **Example:**

```python
class MyClass:
    def __init__(self, value):
        self.value = value
    
    def __repr__(self):
        return f"MyClass({self.value!r})"  # Clear, unambiguous representation
    
    def __str__(self):
        return f"MyClass with value {self.value}"  # User-friendly output

obj = MyClass(10)
print(repr(obj))  # Uses __repr__
print(obj)       # Uses __str__
```

---

### 4. `__eq__(self, other)`
- **Purpose:** Defines the behavior for equality comparison using `==`.
- **Usage:**  
  Implement this method to customize how two instances of a class are considered equal.
- **Example:**

```python
class NameAndNumber:
    def __init__(self, name, value):
        self.name = name
        self.value = value
    
    def __eq__(self, other):
        if not isinstance(other, NameAndNumber): raise TypeError(f"Cannot compare 'NameAndNumber' with {type(other).__name__}")
        if (self.name != other.name): return False
        if (self.value != other.value): return False
        return True

a = NameAndNumber("Jake", 5)
b = NameAndNumber("Molly", 5)
c = NameAndNumber("Jake", 10)
d = NameAndNumber("Jake", 5)
str_example = "Jake"
print(a == b)  # False
print(a == c)  # False
print(a == d)  # True
print(a == str_example)  # TypeError
```

---

### 5. `__del__(self)`
- **Purpose:** Acts as a destructor; it is called when an object is about to be destroyed.
- **Usage:**  
  Use `__del__` for cleanup actions, such as releasing external resources. Keep in mind that its timing is unpredictable.
- **Example:**

```python
class MyClass:
    def __del__(self):
        print("Instance is being deleted")

obj = MyClass()
del obj  # Triggers __del__
```

---

### 6. `__enter__(self)` and `__exit__(self, exc_type, exc_value, traceback)`
- **Purpose:**  
  - **`__enter__`:** Prepares the resource and returns it, allowing an object to be used in a `with` statement.  
  - **`__exit__`:** Handles the cleanup of the resource after the block inside the `with` statement is executed.
- **Usage:**  
  Implement these methods to manage resources (like files or network connections) with automatic setup and teardown.
- **Example:**

```python
class MyResource:
    def __enter__(self):
        print("Acquiring resource")
        return self

    def __exit__(self, exc_type, exc_value, traceback):
        print("Releasing resource")

with MyResource() as resource:
    print("Inside with block")
```

---

### 7. `__call__(self, ...)`
- **Purpose:** Makes an instance callable, allowing it to be used like a function.
- **Usage:**  
  Implement `__call__` when you want your object to perform an action when it is "called" with parentheses.
- **Example:**

```python
class CallableClass:
    def __call__(self, x):
        return x * 2

obj = CallableClass()
result = obj(5)  # This calls __call__(self, 5)
print(result)    # Outputs: 10
```

---

### 8. `__getattr__(self, name)` and `__setattr__(self, name, value)`
- **Purpose:**  
  - **`__getattr__`:** Customizes behavior for attribute access when the attribute is not found in the usual places.  
  - **`__setattr__`:** Intercepts attribute assignment to allow for custom handling or logging.
- **Usage:**  
  Use `__getattr__` to handle missing attributes and `__setattr__` to modify how attributes are set before storing them.
- **Example:**

```python
class MyClass:
    def __getattr__(self, name):
        print(f"Attribute '{name}' not found!")
        return None
    
    def __setattr__(self, name, value):
        print(f"Setting attribute '{name}' to {value}")
        super().__setattr__(name, value)

obj = MyClass()
print(obj.non_existing)  # Triggers __getattr__
obj.new_attr = 42        # Triggers __setattr__
```


---

## Comprehensive Example

```python
class MyNumber:
    def __new__(cls, value):
        print("Creating a new MyNumber instance")
        return super().__new__(cls)

    def __init__(self, value):
        print("Initializing MyNumber")
        self.value = value

    def __repr__(self):
        return f"MyNumber({self.value!r})"

    def __str__(self):
        return f"MyNumber with value {self.value}"

    def __eq__(self, other):
        return isinstance(other, MyNumber) and self.value == other.value

    def __del__(self):
        print(f"Deleting MyNumber instance with value {self.value}")

    def __enter__(self):
        print("Entering context")
        return self

    def __exit__(self, exc_type, exc_value, traceback):
        print("Exiting context")

    def __call__(self, increment):
        self.value += increment
        return self.value

    def __getattr__(self, name):
        print(f"Attribute '{name}' not found!")
        return None

    def __setattr__(self, name, value):
        print(f"Setting attribute '{name}' to {value}")
        super().__setattr__(name, value)

num = MyNumber(10)
print(repr(num))
print(str(num))
num(5)
with MyNumber(20) as num_context:
    print("Inside with block:", num_context)
```

This guide provides a structured understanding of Python's OOP magic methods to help you create powerful, intuitive classes.
