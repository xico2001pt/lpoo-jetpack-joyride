# LPOO-2021-g73 - Jetpack Joyride

This project was developed by:
- Adriano Soares (up201904873@edu.fe.up.pt)
- Catarina Pires (up201907925@edu.fe.up.pt)
- Francisco Cerqueira (up201905337@edu.fe.up.pt)

## Table of contents
1. [Description](#description)
2. [Implemented features](#implemented-features)
3. [Planned features](#planned-features)
4. [Design](#design)
    1. [Arena Builder](#i-arena-builder)
    2. [Game Loop](#ii-game-loop)
    3. [Movement Strategy](#iii-movement-strategy)
    4. [States](#iv-game-states)
    5. [Power Ups](#v-power-ups)
5. [Code smells and refactoring techniques](#code-smells-and-refactoring-techniques)
6. [Testing](#testing)

## Description

In this game, the Player's primary goal is to travel as far as possible, while collecting coins to buy useful power-ups, and avoiding hazards such as Energy Walls and Laser Beams.

![game](./images/game.gif)

### Power Ups

**Immortal:**

![immortal](./images/immortal.gif)

**Double Coins:**

![double-coins](./images/double-coins.gif)

**Slow Down Effect:**

![slow-down](./images/slow-down.gif)

## Implemented features

- [x] Movable Objects:
  - [x] Player
  - [x] Coins
  - [x] Obstacles (Laser and EnergyWall)

- [x] Arena Builder - we created a random arena builder (RandomArenaBuilder)
- [x] Information bar - we created a window that has the player's information and, inside it, is the arena where the game runs
- [x] Collisions - box and detailed image collision

## Planned features

- [x]  Objects:
    - [x] Static obstacles
    - [x] Obstacle with different movements

- [x] Movements:
    - [x] Player falls with gravity
    - [x] Movements aside from going left (Obstacles)
    
- [x] Menus
    - [x] Main Menu
    - [x] Instructions Menu
    - [x] Pause Menu
    - [x] GameOver Menu
    
- [x] Levels:
    - [x] Increase the difficulty linearly throughout a play through
    - [x] Improve Random Infinite
    - [x] Score (Distance)
    - [x] Buy Power-up with coins
    
- [x] Power-ups:
    - [x] Double coins
    - [x] Shield
    - [x] Slow down time

## Design
### i. Arena Builder
#### Problem in context

To allow the creation of an Arena of multiple ways, randomly or by reading it from a file.

#### The pattern

To solve this problem, we implemented a Factory Method that creates specific Arenas depending on the form of arena creation we want.

#### Implementation

To implement the Factory Method we created an abstract class, *ArenaBuilder*, that specifies what each arena creator must be able to create (player, coins, obstacles, etc). Then, various strategies of building an arena can implement/override the methods of the abstract class accordingly. If, for some reason, we want to add new methods to create arenas afterwards, we only need to specify the target platform when the application starts. This way, the wanted arena builder is instantiated and used throughout the rest of the program, not having to worry with the specific type in use.

![](./images/factory_pattern.png)

These classes can be found in the following files:
- [ArenaBuilder](../src/main/java/org/jetpack/model/arena/ArenaBuilder.java)
- [RandomArenaBuilder](../src/main/java/org/jetpack/model/arena/RandomArenaBuilder.java)
- [Arena](../src/main/java/org/jetpack/model/arena/Arena.java)
- [ArenaController](../src/main/java/org/jetpack/controller/game/ArenaController.java)

#### Consequences

By using the Factory Method, each target platform has its own isolated concrete class, which can be implemented differently. In addition, it makes exchanging product families easy, only needing to call a different constructor at the start of the program, since they extend the same abstract class.

### ii. Game Loop
#### Problem in context

In order to control the main cycle of the game, we need a way to make sure the game runs properly, processes the input and renders accordingly with a certain amount of frames per second.

#### The pattern

The pattern Game Loop runs continuously during the gameplay. Each turn it processes the user input without blocking it, updates the game state, renders the game and tracks the passage of time to control the rate of the gameplay.

#### Implementation

![](./images/game_loop.png)

These classes can be found in the following files:
- [Game](../src/main/java/org/jetpack/Game.java)
- [GameLoop](../src/main/java/org/jetpack/controller/GameLoop.java)

#### Consequences

The Game Loop pattern allow us to have more control in the way the game runs and separate each processing.

### iii. Movement Strategy

#### Problem in context

In order for the objects to have different types of movement, we needed to specify their movement.
This information could be placed in model, in each object's class, but this shouldn't be a concern to the element, it would be a violation of the Single Responsibility Principle, hence why we implemented this design pattern.

#### The pattern

To solve this problem we used the Strategy Pattern. This pattern allows us to isolate the distinct movements into classes away from the object, making them interchangeable. With this, we are able to separate the movement algorithm from the objects, so we can easily switch their behaviors and add new ones.

#### Implementation

![](./images/strategy_pattern_movements.png)

These classes can be found in the following files:
- [Element](../src/main/java/org/jetpack/model/elements/Element.java)
- [MovementStrategy](../src/main/java/org/jetpack/model/elements/movements/MovementStrategy.java)
- [ZigZagMovement](../src/main/java/org/jetpack/model/elements/movements/ZigZagMovement.java)
- [UpMovement](../src/main/java/org/jetpack/model/elements/movements/UpMovement.java)
- [DownMovement](../src/main/java/org/jetpack/model/elements/movements/DownMovement.java)

#### Consequences

With this pattern, we can eliminate the possibility of differentiating each behavior with conditional statements and substitute them with classes that have different implementation accordingly. Each object doesn't have to know its type of movement.

### iv. Game States
#### Problem in context

We wanted to switch from different states smoothly as the game proceeded, from the Menus to the Game for instance.

#### The pattern

To implement the different states of the game, as the name implies, we used the State Pattern. This pattern allows the implementation of each state as subclass. By doing so, we can switch from different states only by switching to another class/state. Each state has a controller and a viewer, the controller is responsible to transit from one state to another, as the design pattern implies.

#### Implementation

![](./images/state_pattern.png)

These classes can be found in the following files:
- [GameLoop](../src/main/java/org/jetpack/controller/GameLoop.java)
- [State](../src/main/java/org/jetpack/states/State.java)
- [MainMenuState](../src/main/java/org/jetpack/states/MainMenuState.java)
- [ArenaState](../src/main/java/org/jetpack/states/ArenaState.java)

#### Consequences

This pattern facilitates the creation of more states for the game and changes to the ones already created.
The creation of different states gives us freedom to differentiate behaviors depending on the actual stage in the game. There's also no need to use flags or complex if statements. However, this means that there will be more classes in the game.

### v. Power Ups

#### Problem in context

The player should have different attributes/stats/power-ups throughout the course of the game.

#### The pattern

To implement the power-ups we used the Strategy Pattern. This pattern consists on creating an Interface (or an abstract class), *PlayerStrategy* in our case, and implementing it accordingly in the various power-ups that we want  to feature. The Player class stores the strategy and changes it's behavior according to it.

#### Implementation

![](./images/strategy_pattern_powerups.png)

These classes can be found in the following files:
- [Player](../src/main/java/org/jetpack/model/elements/player/Player.java)
- [PlayerStrategy](../src/main/java/org/jetpack/model/elements/player/playerStrategies/DoubleCoinStrategy.java)
- [DoubleCoinStrategy](../src/main/java/org/jetpack/model/elements/player/playerStrategies/DoubleCoinStrategy.java)
- [SlowDownStrategy](../src/main/java/org/jetpack/model/elements/player/playerStrategies/SlowDownStrategy.java)
- [ImmortalStrategy](../src/main/java/org/jetpack/model/elements/player/playerStrategies/ImmortalStrategy.java)
- [NormalStrategy](../src/main/java/org/jetpack/model/elements/player/playerStrategies/NormalStrategy.java)

#### Consequences

With this pattern we can interchange *powerUps*/*playerStrategies* easily. We can separate the Player from the Strategy, since it doesn't need to know it's behavior, leaving the responsibility to the state itself.
We wanted to change the Player's appearance depending on the strategy it encounters itself in, this resulted in a minor inconvenience: since we save the Image of a certain Element in the model itself we found ourselves obligated to save the Image in the Strategy. On the other hand, this eases the implementation of this feature, and, in our opinion, enhances the readability of the code.

## Code smells and refactoring techniques

### Bloaters

#### Primitive Obsession and Data Clump

- [x] The size of the arena was represented as two integers, which are used several times in different parts of the code. This code smell was fixed by using a class Dimension which is responsible for managing these variables.

#### Long Parameter List

- [x]  The method *checkBoxCollision*([CollisionController](../src/main/java/org/jetpack/controller/game/CollisionController.java)) has eight arguments, receiving two points and two dimensions. This issue can be solved by changing the given parameters to classes that'll group the variables. ([Position](../src/main/java/org/jetpack/model/Position.java))

### Object-Orientation Abusers

#### Switch Statements

The method responsible to interpret the user's input has a lot of if statements to map each input key to an action. Unfortunately, it's not easy to solve this problem, as there are no obvious alternatives. The function responsible for generating random obstacles also has the same issue.

### Change Preventers

#### Parallel Inheritance Hierarchies

As we are using the MVC architecture, this implies that almost every object from the Model should have a controller and viewer class. Each game state also need to have a correspondent controller and viewer. We thought a lot about this code smell and we haven't encountered an easy fix for this problem without violating the SOLID principles.

### Dispensables

#### Comments

- [x] As our project is still in the beginning, it has a lot of comments to help us to understand what we're doing (especially TODO's). This should (and will) be fixed in the final delivery.


#### Duplicate Code

- [x] We had a method that has a lot of code fragments which are identical, as we were still thinking about the structure of that specific part. We thought that this could be fixed by improving the code structure and creating generic methods. 
So, we created a class with only static methods that detects all the collisions (images, elements and boxes) ([CollisionController](../src/main/java/org/jetpack/controller/game/CollisionController.java)).


#### Data Class

Our game uses data classes to store the image (structure) of the various elements and some useful colors ([ImageLibrary](../src/main/java/org/jetpack/model/elements/ImageLibrary.java) and [ColorDatabase](../src/main/java/org/jetpack/gui/ColorDatebase.java)).
We think that with this singleton our code is more organized.

#### Speculative Generality

- [x] We have some methods, like *checkImageCollision* and some getters and setters, that are not being used, but were created to support anticipated future features. This will obviously be fixed until the final delivery, as these features will be used/implemented.


### Couplers

#### Message Chains

- [x] The *WindowViewer* class calls methods of the *ArenaViewer* class, that will call some other methods of the *ElementViewer* class. This is a chain of calls that we would like to improve and simplify until the final delivery, by reviewing the structure of the code.
We simplified it by eliminating the WindowViewer as it didn't have a strong purpose and moved the display of the information bar to the ArenaViewer.


## Testing

![](./testResults/tests.png)

### Jqwik Tests

```
timestamp = 2021-05-30T17:39:04.095931900, PlayerTest:takeDamage = 
                              |--------------------jqwik--------------------
tries = 4                     | # of calls to property
checks = 4                    | # of not rejected calls
generation = EXHAUSTIVE       | parameters are exhaustively generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 0          | # of all combined edge cases
edge-cases#tried = 0          | # of edge cases tried in current run
seed = -4885071859622138674   | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:04.163935400, PlayerTest:addCoin = 
                              |--------------------jqwik--------------------
tries = 4                     | # of calls to property
checks = 4                    | # of not rejected calls
generation = EXHAUSTIVE       | parameters are exhaustively generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 0          | # of all combined edge cases
edge-cases#tried = 0          | # of edge cases tried in current run
seed = 3423937253069352147    | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:04.311943200, PlayerTest:movement = 
                              |--------------------jqwik--------------------
tries = 6                     | # of calls to property
checks = 6                    | # of not rejected calls
generation = EXHAUSTIVE       | parameters are exhaustively generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 0          | # of all combined edge cases
edge-cases#tried = 0          | # of edge cases tried in current run
seed = 3183002813649430711    | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:05.015078100, PaceTest:testUpdate = 
                              |--------------------jqwik--------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 64         | # of all combined edge cases
edge-cases#tried = 64         | # of edge cases tried in current run
seed = -4583084724403146003   | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:05.573118500, PositionTest:getIncrementedPosition = 
                              |--------------------jqwik--------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 729        | # of all combined edge cases
edge-cases#tried = 172        | # of edge cases tried in current run
seed = -7798245107177343178   | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:05.833137800, PositionTest:getDown = 
                              |--------------------jqwik--------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 81         | # of all combined edge cases
edge-cases#tried = 79         | # of edge cases tried in current run
seed = 6076542933718054498    | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:06.103162900, PositionTest:getLeft = 
                              |--------------------jqwik--------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 81         | # of all combined edge cases
edge-cases#tried = 64         | # of edge cases tried in current run
seed = -2617487701907794906   | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:06.248172700, PositionTest:getUp = 
                              |--------------------jqwik--------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 81         | # of all combined edge cases
edge-cases#tried = 68         | # of edge cases tried in current run
seed = 2281043458650245405    | random seed to reproduce generated values


timestamp = 2021-05-30T17:39:06.468188600, PositionTest:getRight = 
                              |--------------------jqwik--------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
when-fixed-seed = ALLOW       | fixing the random seed is allowed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 81         | # of all combined edge cases
edge-cases#tried = 80         | # of edge cases tried in current run
seed = 3203805707839883331    | random seed to reproduce generated values
```

### Test Coverage

![](./testResults/coverage_tests.png)

![](./testResults/pitest.png)

[Link to the reports in HTML format](testResults/html/index.html)