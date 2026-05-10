# **KSPCC**

Kerbal Space Programm Communications Calculator is a small portable programm, that can calculate the distance between any two points in any two orbits in 3D space with reasonable accuracy, and calculate signal strength based on that distance.

## **How to use**

You can either two prefab orbits from the dropdown selector, or input custom orbital parameters and use those. You can also input a custom orbit by selecting a Kopernicus cfg file.  
The reference body is the body around which ther selectable orbits are located.
Currently implemented prefab Systems are OPM, MPE, and the RSS config of Sol.

You also need to choose which antennae the vessels in each orbit have. There a few vanilla ones, but you can also create your own custom antenna by choosing the "custom" option. Click on an antenna in the "tower" of antenna to remove it from the list of antennae on your vessel.

You can open up the settings by clicking the cog in the top right, which will open up another window.

You can switch between light and dark mode by clicking the moon/sun in the top left. If you use light mode, I recommed to change orbital line colors in the settings for better contrast.

If you input garbage data you will get garbage data. Don't resize the window too much, or the UI will break. I recommend using it in fullscreen.

## **Preview**

Dark mode:

<img src="https://imgur.com/aOAAzwA.png" width=800> 

  
Light mode:

<img src="https://imgur.com/UGg1MaI.png" width=800>


## **Installation Guide**

I only properly tested on Windows so far, but it should maybe also work on Linux.

(_KSPCC.exe File Size ~150KB_)  
The executable of KSPCC is portable, which means that you only need to download the executable file, and run it to start the program.

(_KSPCC.jar File Size ~50KB_)
Alternatively, you can download the jar instead. To run it, navigate to its directory on your computer, and open a terminal there. In the terminal, type
~~~
java -jar "KSPCC.jar"
~~~ 

## **Build Instructions**

Requirements: jdk-8 or newer.
To build KSPCC, you need to download its source code

## **TODO**
<ul>
<li>fix weird UI stuff when resizing window</li>
<li>implement seperate relay behavior</li>
<li>custom back-/foreground colors</li>
<li>in app guide</li>
<li>probably some qol features</li>
<li>make it <em><strong>pretty</strong></em> </li>
</ul># **KSPCC**

Kerbal Space Programm Communications Calculator is a small portable programm, that can calculate the distance between any two points in any two orbits in 3D space with reasonable accuracy, and calculate signal strength based on that distance.

## **How to use**

You can either two prefab orbits from the dropdown selector, or input custom orbital parameters and use those. You can also input a custom orbit by selecting a Kopernicus cfg file.  
The reference body is the body around which ther selectable orbits are located.
Currently implemented prefab Systems are OPM, MPE, and the RSS config of Sol.

You also need to choose which antennae the vessels in each orbit have. There a few vanilla ones, but you can also create your own custom antenna by choosing the "custom" option. Click on an antenna in the "tower" of antenna to remove it from the list of antennae on your vessel.

You can open up the settings by clicking the cog in the top right, which will open up another window.

You can switch between light and dark mode by clicking the moon/sun in the top left. If you use light mode, I recommed to change orbital line colors in the settings for better contrast.

If you input garbage data you will get garbage data. Don't resize the window too much, or the UI will break. I recommend using it in fullscreen.

## **Preview**

Dark mode:

<img src="https://imgur.com/aOAAzwA.png" width=800> 

  
Light mode:

<img src="https://imgur.com/UGg1MaI.png" width=800>


## **Installation Guide**

#### Executable
(_KSPCC.exe File Size ~150KB_)  
The executable of KSPCC is portable, which means that you only need to download the executable file, and run it to start the program.

#### Jar File
(_KSPCC.jar File Size ~50KB_)
Alternatively, you can download the jar instead. To run it, navigate to its directory on your computer, and open a terminal there. In the terminal, type: 
~~~
java -jar "KSPCC.jar"
~~~
and hit enter to launch KSPCC.

## **Build Instructions**

**You need to have Java version 8 or newer installed to build KSPCC from source, I have only done this on Windows**

To build KSPCC, you need to download its source code, and navigate to its directory. Open the src folder in a terminal, decide on a build folder, and type:
~~~
javac --release 8 -d <Path to Build Folder> kspcc/KSPCC.java
~~~
Then you have to copy over the resoures folder in kspcc, which is system specific. For Windows(in Powershell) it is:
~~~
Copy-Item -Recurse kspcc/resources <Path to Build Folder/kspcc/resources>
~~~
Then navigate to your build folder, and run:
~~~
jar -cfe KSPCC.jar kspcc.KSPCC kspcc/*
~~~
This will build the contents of the kspcc folder into an executable Jar File. See above on how to run the Jar File. 

## **TODO**
<ul>
<li>fix weird UI stuff when resizing window</li>
<li>implement seperate relay behavior</li>
<li>custom back-/foreground colors</li>
<li>in app guide</li>
<li>probably some qol features</li>
<li>make it <em><strong>pretty</strong></em> </li>
</ul>
