'''
Tic Tac Toe game vs. a random number generator
Cort Adkins - 2024
'''

import random


def player_marker():
    marker = ""
    player1_marker = ""
    player2_marker = ""

    while marker not in ["X", "O"]:
        marker = input("Player 1, pick X or O: ")
    player1_marker = marker
    if player1_marker == "X":
        player2_marker = "O"
    else:
        player2_marker = "X"
    return player1_marker, player2_marker


def user_choice(places):
    choice = ""
    while choice not in places:
        choice = input("Choose your next position: (1-9): ")
        if choice.isdigit():
            choice = int(choice)
        if choice not in places:
            print("Try again...")
    return choice


def display_game(board):
    print(board[1] + "|" + board[2] + "|" + board[3])
    print("-|-|-")
    print(board[4] + "|" + board[5] + "|" + board[6])
    print("-|-|-")
    print(board[7] + "|" + board[8] + "|" + board[9])


def game_on_choice():
    game_on = False
    game_choice = ""
    while game_choice not in ["Y", "N"]:
        game_choice = input("Would you like to play again? (Y or N) ")
    if game_choice == "Y":
        game_on = True
    else:
        game_on = False
    return game_on


def place_marker(board, marker_choice, position):
    game_complete = False
    print("\n" * 50)
    board[position] = marker_choice
    return display_game(board)


def win_condition(board, marker):
    win_check = False
    if board[1] == board[2] == board[3] == marker:
        win_check = True
    elif board[4] == board[5] == board[6] == marker:
        win_check = True
    elif board[7] == board[8] == board[9] == marker:
        win_check = True
    elif board[1] == board[5] == board[9] == marker:
        win_check = True
    elif board[7] == board[5] == board[3] == marker:
        win_check = True
    elif board[1] == board[4] == board[7] == marker:
        win_check = True
    elif board[2] == board[5] == board[8] == marker:
        win_check = True
    elif board[3] == board[6] == board[9] == marker:
        win_check = True
    else:
        win_check = False
    return win_check


def who_first():
    player1 = bool(random.getrandbits(1))
    if player1 == True:
        player2 = False
    else:
        player2 = True
    return player1, player2


def full_board_check(board):
    count = 0
    for i in range(1, len(board)):
        if board[i] == "X" or board[i] == "O":
            count += 1
        if count == 9:
            return True


game_on = True

while game_on:
    board = [" "] * 10
    places = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    game_complete = False
    player1_marker, player2_marker = player_marker()
    display_game(board)
    player1, player2 = who_first()

    while not game_complete:
        if player1:
            print("Player 1")
            position = user_choice(places)
            places.remove(position)
            place_marker(board, player1_marker, position)
            player1 = False
            player2 = True
            game_complete = win_condition(board, player1_marker)
        elif player2:
            print("Player 2")
            position = int(random.choice(places))
            places.remove(position)
            place_marker(board, player2_marker, position)
            player1 = True
            player2 = False
            game_complete = win_condition(board, player2_marker)
        if full_board_check(board):
            game_complete = True
            break
    if game_complete:
        print("Good Game!")
        if win_condition(board, player1_marker):
            print("Player 1 WINS!!")
        elif win_condition(board, player2_marker):
            print("Player 2 WINS!!")
        else:
            print("It's a tie!")
        game_on = game_on_choice()
