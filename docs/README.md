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
    4. [States](#iv-states)
    5. [Power Ups](#v-power-ups)
5. [Code smells and refactoring techniques](#code-smells-and-refactoring-techniques)
6. [Testing](#testing)

## Description
In this game, the Player's primary goal is to travel as far as possible, while collecting coins, and avoiding hazards such as Energy Walls and Laser Beams.

![](./images/gameplay.gif)

## Implemented features

- [x] Movable Objects:
  - [x] Player
  - [x] Coins
  - [x] Obstacles (Laser and EnergyWall)

- [x] Arena Builder - we created a random arena builder (RandomArenaBuilder)
- [x] Information bar - we created a window that has the player's information and, inside it, is the arena where the game runs
- [x] Collisions

## Planned features

- [x]  Objects:
    - [x] Static obstacles
    - [x] Obstacle with different movements

- [x] Movements:
    - [x] Player falls with gravity
    - [x] Movements aside from going left (Obstacles)
    
- [x] Menus
- [ ] Levels:
    - [x] Increase the difficulty linearly throughout a play through
    - [x] Improve Random Infinite 
    - [ ] Pre-built Levels
    - [x] Score (Distance)
- [ ] Power-ups:
    - [x] Double coins
    - [x] Shield
    - [x] Slow down time 
    - [ ] Zero Gravity
    - [ ] Nitro

## Design
### i. Arena Builder
#### Problem in context

To allow the creation of an Arena of multiple ways, randomly or by reading it from a file.

#### The pattern

To solve this problem, we implemented a Factory Method that creates specific Arenas depending on the what form of arena creation we want.

#### Implementation

To implement the Factory Method we created an abstract class, ArenaBuilder, that specifies what each arena must be able to create (player, coins, obstacles, etc). Then, various options of building an arena can implement/override the methods of the abstract class accordingly. If, for some reason, later we want to add new methods to create arenas, we only need to specify the target platform when the application starts. This way, the wanted arena builder is instantiated and used throughout the rest of the program, not having to worry with the specific type in use.

![](./images/factory_pattern.png)

#### Consequences

By using the Factory Method, each target platform has its own isolated concrete class, which can be implemented differently. In addition, it makes exchanging product families easy, only needing to call a different constructor at the start of the program, since they extend the same abstract class.

### ii. Game Loop
#### Problem in context

In order to control the main cycle of the game, we need a way to make sure the game runs properly, processes the input and renders accordingly with a certain amount of frames per seconds (that won't interfere in the game itself).

#### The pattern

A game loop runs continuously during the gameplay. Each turn it processes the user input without blocking it, updates the game state and renders the game. It tracks the passage of time to control the rate of gameplay.

#### Implementation

To implement the Game Loop pattern we created a class, GameLoop, that contains the three main methods stated above, and some other utility functions.

![](./images/game_loop.png)

#### Consequences

The Game Loop pattern allow us to have more control in the way the game runs and separate each processing.

### iii. Movement Strategy

#### Problem in context

In order for the objects to have different types of movement, we needed to specify their movement.
This information could be placed in model, in each object's class, but it would be much to know about, and it would not concern the element itself.

#### The pattern

To solve this problem we used the Strategy Pattern. This pattern allows us to isolate the distinct movements into classes away from the object, making them interchangeable. With this, we are able to separate the movement algorithm from the objects, so we can easily switch their behaviors and add new ones.

#### Implementation

![](./images/strategy_pattern.png)

#### Consequences

With this pattern, we can eliminate the possibility of differentiating each behavior with conditional statements and substitute them with classes that have different implementation accordingly. In this way, each object does not have to know its type of movement.

### iv. Game States
#### Problem in context

To switch from different states smoothly as the game proceeded. This is useful for the menus and power-ups in the game.

#### The pattern

To implement the different states of the game, we used the State Pattern. This pattern allows the implementation of each state as subclass. If so, we can switch from different states only by switching to another class/state.

#### Implementation

![](./images/state_pattern.png)

#### Consequences

This pattern facilitates the creation of more states for the game and changes to the ones already created.
The creation of different states gives us freedom to differentiate behaviors depending on the actual stage in the game. There's also no need to use flags or complex if statements, however, this means that there will be more classes in the game.

### v. Power Ups

> Not implemented yet!

#### Problem in context

The player may have different attributes/stats throughout the game.

#### The pattern

To implement the power-ups we used the Command Pattern. This pattern consists of deriving power-ups from an abstract class while modifying a common function, *execute* in our case, so that they can have different results, change different attributes and/or change the Player's state.

#### Implementation

> TODO: UML

#### Consequences

> TODO

## Code smells and refactoring techniques

### Bloaters

#### Primitive Obsession and Data Clump

- [x] Currently, the size of the arena is represented as two integers, which are used several times in different parts of the code. This code smell can be fixed by creating a class which will be responsible for managing these variables.

#### Long Parameter List

- [x]  The method checkBoxCollision() has eight arguments, receiving two points and two dimensions. This issue can be solved by changing the given parameters to classes that'll group the variables.

### Object-Orientation Abusers

#### Switch Statements

The method responsible to interpret the user's input has a lot of if statements to map each input key to an action. Unfortunately, it's not easy to solve this problem, as there are no obvious alternatives. The function responsible for generating random obstacles also has the same issue.

### Change Preventers

#### Parallel Inheritance Hierarchies

As we are using the MVC architecture, this implies that almost every object from the Model should have a controller and viewer class. Each game state also need to have a correspondent controller and viewer. We thought a lot about this code smell and we haven't encountered an easy fix for this problem without violating the SOLID principles yet.

### Dispensables

#### Comments

As our project is still in the beginning, it has a lot of comments to help us to understand what we're doing (especially TODO's). This should (and will) be fixed in the final delivery.

#### Duplicate Code

Currently, we have a method that has a lot of code fragments which are identical, as we are still thinking about the structure of that specific part. We think that this can be fixed improving the code structure and creating generic methods. 

#### Data Class

Our game uses a data class to store the image (structure) of the various elements. We think that with this singleton our code is more organized.

#### Speculative Generality

We have some methods, like checkImageCollision() and some getters and setters, that are not being used, but were created to support anticipated future features. This will obviously be fixed until the final delivery, as these features will be used/implemented.

### Couplers

#### Message Chains

The WindowViewer class calls methods of the ArenaViewer class, that will call some other methods of the ElementViewer class. This is a chain of calls that we would like to improve and simplify until the final delivery, by reviewing the structure of the code.

## Testing

![](./testResults/tests.png)
### Test Coverage
![](./testResults/test_coverage.png)

![](./testResults/pitest.png)

[Link to the reports in HTML format](testResults/html)