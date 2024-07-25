import random

# Select random word from list of 1000 words
def getSecretWord():
    word_file = "C:\\Users\corta\mu_code\Scripts\practice-projects\Python\Cow and Bull\words.txt"
    WORDS = open(word_file).read().splitlines()
    word = random.choice(WORDS)
    return word

# Return list of letters within a word
def getLetters(word):
    return [letter for letter in word]

# Returns count with exact matches (bulls) and partial matches (cows)
def countBullsCows(word,guess):
    bull_cow = [0,0]
    word_list = getLetters(word)
    guess_list = getLetters(guess)

    for i,j in zip(word_list,guess_list):

        # common letter present
        if j in word_list:

            # exact match
            if j == i:
                bull_cow[0] += 1

            # partial match
            else:
                bull_cow[1] += 1

    return bull_cow


# Secret Code
word = getSecretWord()
word_length = len(word)
attempts = int(input('Enter number of attempts: '))

# Play continues until word is guessed or attempts are exhausted
while attempts > 0:
    guess = input(f"Enter a {word_length} letter word - ({attempts} attempts remaining): ")

    if len(guess) != word_length:
        print(f"Enter {word_length} letter word only. Try again.")
        continue

    bull_cow = countBullsCows(word,guess)
    print(f"{bull_cow[0]} bulls, {bull_cow[1]} cows")
    attempts -=1

    if bull_cow[0] == word_length:
        print("Correct!")
        break
else:
    print(f"You ran out of attempts. The word was '{word}'")
