'''
Calculator
Cort Adkins - 2024
'''

def exponent(a,b):
    return a**b

def multiply(mult_list):
    result = mult_list[0]
    for num in mult_list[1:]:
        result *= num
    return result

def divide(div_list):
    result = div_list[0]
    for num in div_list[1:]:
        result /= num
    return result

def add(add_list):
    result = add_list[0]
    for num in add_list[1:]:
        result += num
    return result

def subtract(sub_list):
    result = sub_list[0]
    for num in sub_list[1:]:
        result -= num
    return result

def sqrt(a):
   return a**0.5

print("Select operation:")
print("1. Add")
print("2. Subtract")
print("3. Multiply")
print("4. Divide")
print("5. Square Root")
print("6. Exponent")
print()


on=True
while on:

    choice = input("Enter operation (1-6): ")

    '''
    TODO: Add validation on user input
    More equations / graphing
    '''
    if choice == '1':
        print("You chose ADD")
        add_list = list(map(int, input("Please enter a comma separated list of numbers to add: ").split(',')))
        result = add(add_list)
        print(result)

    elif choice == '2':
        print("You chose SUBTRACT")
        sub_list = list(map(int, input("Please enter a comma separated list of numbers to subtract: ").split(',')))
        result = subtract(sub_list)
        print(result)

    elif choice == '3':
        print("You chose MULTIPLY")
        mult_list = list(map(float, input("Please enter a comma separated list of numbers to multiply: ").split(',')))
        result = multiply(mult_list)
        print(result)

    elif choice == '4':
        print("You chose DIVIDE")
        div_list = list(map(float, input("Please enter a comma separated list of numbers to divide: ").split(',')))
        result = divide(div_list)
        print(result)

    elif choice == '5':
        print("You chose SQUARE ROOT")
        sqrt_num = float(input("Please enter a number to find the square root: "))
        result = sqrt(sqrt_num)
        print(result)

    elif choice == '6':
        print("You chose EXPONENT")
        base,power = list(map(float, input("Please enter a number and power: ").split(',')))
        result = exponent(base,power)
        print(result)

    # Check if more calculations are needed
    retry = input("Continue (Y/N): ")
    if retry == "N":
      break

