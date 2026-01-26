# Squad Distance Ruler

This is a tool for measuring the distance between points on the map in [Squad](https://store.steampowered.com/app/393380/Squad/) game.  
It will help you navigate better when using a hand-held or under-barrel grenade launcher, playing as a sniper, or operating a mortar.

This program does not access in-game data and is not a cheat. Measurements are made using indirect indicators: the number of rendered pixels in the scale line.

## Requirements
Java 17 or higher.  
Install the official Java version for your operating system from the official website - https://www.oracle.com/java/technologies/downloads/  
On *Windows* or *Linux*, you can check the current Java version via the *command line*/*terminal*: `java -version`.

## Download
Download the latest version - https://github.com/mixmaxmax/Squad-Distance-Ruler/releases   
You can download only the executable file - `SquadDistanceRuler.jar`.  
Or download the entire project and build it yourself.

***
## Description
### 1. Run `SquadDistanceRuler.jar `.

![Main window](imgs/img1.png)

### 2. In the main window of the program, select the resolution of your screen (the resolution of the game must be identical).

![Select resolution](imgs/img2.png)

### 3. Also select a game map.

![Select map](imgs/img3.png)

### 4. Open the overlay panel.

![Overlay panel](imgs/img4.png)

### 5. Use the buttons in the control panel to select the minimap type and zoom level.

![Map type and zoom](imgs/img5.png)

Also note that different game maps support a different number of minimap zoom levels. By default, they are opened with the *M*, *CapsLock*, and *Enter* keys.  
Make sure that you have selected the appropriate type in the overlay.

You also need to know that there are different degrees of approximation of the minimap on different game maps.  
Unfortunately, the zoom level is not clearly indicated in the game. To match the zoom level correctly, start zooming the in-game map from the minimum level, pausing for about one second between each zoom step. This will help you sync the zoom level between the game and the overlay.
### 6. Position the overlay over the game's minimap so you can mark the two points you want to measure.

![Measuring the distance](imgs/img6.png)

If you have chosen the zoom level and the type of minimap correctly, then the green scale overlay lines will be the same length as the in-game ones.
It is not necessary to position the overlay so that its scale lines are on top of the in-game ones. In the screenshot, this is done for clarity.

Click two points on the map with your mouse. A red line will appear between them, showing the measured distance.

The yellow line visually compares the measured distance against the scale lines.

### 7. After finding out the distance, hit the target

![Defeating the target](imgs/img7.png)

### 8. Reset and Close buttons
![Reset and Close buttons](imgs/img8.png)

The *Reset* button clears the overlay panel.

The *Close* button closes the overlay panel.
You can also close the overlay panel by pressing *Escape*.

***

# Supported maps and screen resolutions

| Map                     | 1920x1080 | 2560x1440 | 3840x2160 |
|:------------------------|:---------:|:---------:|:---------:|
| Jensen's Range          |    ✔️     |    ✔️     |    ⚠️     |
| Al Basrah               |    ✔️     |    ✔️     |    ⚠️     |
| Anvil                   |    ✔️     |    ✔️     |    ⚠️     |
| Belaya Pass             |     ❌     |     ❌     |     ❌     |
| Black Coast             |    ✔️     |    ✔️     |    ⚠️     |
| Chora                   |     ❌     |     ❌     |     ❌     |
| Fallujah                |    ✔️     |    ✔️     |    ⚠️     |
| Fool's Road             |    ✔️     |    ✔️     |    ⚠️     |
| Goose Bay               |    ✔️     |    ✔️     |    ⚠️     |
| Gorodok                 |    ✔️     |    ✔️     |    ⚠️     |
| Harju                   |     ❌     |     ❌     |     ❌     |
| Kamdesh Highlands       |     ❌     |     ❌     |     ❌     |
| Kohat Toi               |     ❌     |     ❌     |     ❌     |
| Kokan                   |     ❌     |     ❌     |     ❌     |
| Lashkar Valley          |     ❌     |     ❌     |     ❌     |
| Logar Valley            |     ❌     |     ❌     |     ❌     |
| Manicougan              |    ✔️     |    ✔️     |    ⚠️     |
| Mestia                  |     ❌     |     ❌     |     ❌     |
| Mutaha                  |    ✔️     |    ✔️     |    ⚠️     |
| Narva                   |    ✔️     |    ✔️     |    ⚠️     |
| Pacific Proving Grounds |     ❌     |     ❌     |     ❌     |
| Sanxian Islands         |    ✔️     |    ✔️     |    ⚠️     |
| Skorpo                  |     ✔️     |     ✔️     |     ️⚠️     |
| Sumari Bala             |     ❌     |     ❌     |     ❌     |
| Talil Outskirts         |     ❌     |     ❌     |     ❌     |
| Yegoryevka              |    ✔️     |    ✔️     |    ⚠️     |


Unfortunately, I don't have a native 4K screen and I can't be absolutely sure that Squad Distance Ruler works accurately enough at this resolution.

Adding support for new maps is possible in the future.