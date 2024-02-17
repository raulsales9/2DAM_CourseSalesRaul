import pytest
from practica3.factorial import factorial

def test_factorial():
    assert factorial(1) == 1
    assert factorial(5) == 120
    with pytest.raises(TypeError):
        factorial('a')
    with pytest.raises(ValueError):
        factorial(-1)