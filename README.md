# HanoiTowerBattle


<h2>Online Hanoi Tower</h2>

<h2>The net work is based on UDP protocal;</h2>
<h2>Net work data formation:<h2>
<strong>
join;         -- The player presses the buttonJoin and send a "join;", Who receives a "join;" is the main host.<br>
connect;      -- The player sends back a "connect;" immed when receives a "join;".<br>
connected;    -- For the fair of the game, a confirm message is needed. When a player reveives a "connect;", sends back  <br>
              -- a "connected;" and waiting for the opponent(main host) presses the buttonStart. <br>
start;        -- The main host receives a "connected;" and the two players preparing the game. It is time to start. Press buttonStart. <br>

Below is for rendering the pane at the right top of the main window to show what is the opponent doing.<br>
firstClick;fromStickName   -- To show the top plate clearly on the stick with the a name of fromStickName. <br>
secondClick;targetStickName  -- Move a plate from the stcik with a name of fromStickName to the stick with a name of targetStickName.<br>

win;time      -- Who finish the game sends back a "win;" and its time used.<br>
exit;         -- When one player exits the application or switches to the single game mode.<br>

</strong>

<strong>
<p>
<font>
  Game processing:
  1.Switch to the battle mode, choose a remote IP and who press the buttonJoin first is the main host. The application will be  listening to the port 8888.
  2.The sub host presses the buttonJoin and sends a "join;".
  3.The main host receives a "join;" from the sub host and sends back a "connect;" immediately.
  4.the sub host reveives a "connect;" sends back a "connected;" immediately to confirm the message, then waiting for the host pressing buttonStart.
  5.The main host presses buttonStart, the game begin!! 
   
</font>
</p>

</strong>








