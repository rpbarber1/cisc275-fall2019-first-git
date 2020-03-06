University of Delaware
CISC 275 FALL 2019 
Ryan Barber
Partners: Humpher Owusu, Katarina Pfiefer

Estuary Exploration

  This game is an educational game about estuaries, specifically the Delaware Bay. The
game was designed to be played by tourists, aged 8+, on a large touch screen, however this
version of the game is a simple prototype that is made for mouse and keyboard use and would
be given to a different team for further development if the client wanted to adopt the game.

  This game is written in Java, uitilizing mainly JavaFX to implement animation. The game
was written in MVC (model view controller) style. Each mini-game was mainly written by one
of the team members, however the team did collaborate with each other to solve problems.

  The game consists of 3 minigames: An oil clean-up game, a natural barrier building game,
and a fish tagging game. Once all mini-games are complete, the game is done.

  The game has a save and load feature that is implementd by Java's Serializable. The user
can save the sate of each game upon pressing the 'save' button and will restore the last
saved state upon pressing the 'load' button. 

  The game also has as tutorial mode. The user presses the tutorial button and will be able
to play all the games with a series of pop-ups that display instructions. The user can 
exit the tutorial at any time.

Home Screen
  The first screen the user sees is a map with buttons wich will take them to the related
mini-game. Once the user has completed the mini-game, the game will either automatically
return to the map screen or a button will take them back.

Oil Clean-Up
  In this game the user controls a boat that deploys oil booms with the objective being to
encircle the oil slick while avoiding driving over animals or trapping them in the booms.
Once the user has encircled the booms, the user can then stop the boat and control a skimmer
which when driven over the oil will remove it from the water. Once all the oil is removed,
the mini-game will end.

Natural Barrier Game
  In this game, there are a set of three buttons on the screen with descriptions and images
of a plant species. The user clicks on one of these buttons which will either place the
plant in front of a trail of runoff from a farm or display a pop-up that says the species
chosen is invasive. Once the user has filled all the plant spots and blocked the runoff,
they can return to the home screen.

Fish Tagging Game
  In this game, the user will click a button that takes them to a "fishing" screen in which
they will click a fish to tag and then return to the first screen. On this screen, the fish
will be in the center of the screen and the user will drag the appropriate tag to the cneter
of the fish. This will make the fish dissappear. The user repeats this for all fish then
returns to the home screen.
