# Frogger: a classic arcade game
## Description
This application emulates "Frogger" and "Crossy Road", allowing players to move across the screen towards the endzone, navigating around vehicles, across moving logs, and over rivers and streets.  I created this project using Java, with JUnit testing and Java GUIs.  While working on this project, I was faced with many design decisions: where and when is the player allowed to move?  Is there a timer?  Can they go backwards?  Are all like-rows (ex. all roads) in the same starting format (e.g. cars in certain places, moving a certain direction)?  What rows or items be grouped together based on shared behavior, and which objects are fundamentally different?  I started this project with the goal of exercising my design and ability to pick up new technologies.  If I continue this project in the future, I hope to add additional rows with behavior different than those in the original game, perhaps bridges over rivers and roads.  Or, a scoring system so the game isn't just pass-fail.
## How to run
1) Fork or clone the repository into your local machine
2) Run the main method in class Main
## Gameplay 
Your goal is to get the frog from its starting position into the black "endzone", which will eventually be at the top of the screen.
### Controls: 
 **up arrow:** move frog up/forward
 **left arrow:** move frog left 
 **right arrow:** move frog right
 ** Note: Be careful!  Once you move up, you cannot move back!
### Rows:
  **green:** safety, stay here as long as you like without threat 
  **blue with green spots:** river with lilypads, green is safe, blue is not 
  **blue with moving brown rectangles:** river with logs, brown is safe, blue is not (don't fall off!)
  **grey with red rectangles:** road with vehicles, grey is safe, red is not (don't get hit!)
  **black:** endzone, you've won!
