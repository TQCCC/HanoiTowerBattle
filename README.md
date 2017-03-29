# HanoiTowerBattle


Online Hanoi Tower;

The net work is based UDP protocal;
Net work data formation:

join;         -- The player presses the buttonJoin and send a "join;", Who receives a "join;" is the main host;<br>
connect;      -- The player sends back a "connect;" immed when receives a "join;";<br>
connected;    -- For the fair of the game, a confirm message is needed. When a player reveives a "connect;", sends back a "connected;" and
              -- waiting for the opponent(main host) presses the buttonStart.
start;        -- The main host receives a "connected;" and the two players preparing the game. It is time to start. Press buttonStart.

Below is for rendering the pane at the right top of the main window to show what is the opponent doing.
firstClick;fromStickName   -- To show the top plate clearly on the stick with the a name of fromStickName;  
secondClick;targetStickName  -- Move a plate from the stcik with a name of fromStickName to the stick with a name of targetStickName;

win;time      -- Who finish the game sends back a "win;" and its time used.
exit;         -- When one player exits the application or switches to the single game mode.






