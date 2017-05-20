# FreeTransform

This is a bachelor project at Faculty of electrical engineering and computing (Zagreb).
The goal of this project is to implement Maltego transformations into Freeplane software 
in order to make mind mapping process easier.

To try these scripts, you will need Freeplane that can be downloaded for free at https://www.freeplane.org/wiki/index.php/Main_Page.
This software automatically comes with groovy support so no additional downloads are required.
The scripts need to be saved in the specific folder called "scripts" that can be accessed by choosing tools->open user directory option within Freeplane.
After locating the folder, place any script you want to run inside it. Note that every script needs to have .groovy extension in order to run properly (other languages require additional setup).
After creating new script, you need to restart Freeplane if it is already running in order to use the script. Modifying existing scripts does not require a restart.

Freeplane API provides access to change the mind map. Most notable and used variables from this are:

  -node -> an object that contains the node the script is called upon
  
  -ui -> interface object that provides writing error/informational messages and more to the user.
  
The scripts are easily called within Freeplane. Simply choose the node you want to run a groovy script on, right click on it, choose "scripts" and then click on the desired script to execute it.
Every script uses node name as a parameter and does a transformation on it.
Although the user is free to use any script on desired node, if node text is not compatible with the called script, an appropriate message will be displayed.
Certain scripts, like ones that use twitter API, will require an API key in order to work.

Feel free to use these scripts to discover an easier way of creating your own mind maps!
Every comment and suggestion on this project is most welcomed!
