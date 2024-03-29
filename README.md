# WhatAreYouScoring StationAPI Edition for Minecraft Beta 1.7.3

A StationAPI mod for Minecraft Beta 1.7.3 that adds a purpose to your score!
Player score resets upon death in singleplayer and upon logout in multiplayer.
* Achievements are saved per instance and do not reset.

# What Are You Scoring? (W.A.Y.S. Score/Achievement Mod)

You will need ModMenu and GlassConfigAPI if you want to tweak different parts of the mod. See installation instructions below.
* (Incomplete) Mod now works on Multiplayer with GlassConfigAPI version 2.0+ used to sync configs!

## Basic Scoring (Yellow)
- Placing a block:              +1
- Removing a block:             +1
- Killing a monster mob:        +1
- Killing a passive mob:        +1

## Days Scoring (Blue)
- Surviving a minecraft day:    +1
- (Real world days are also tracked, but not scored)

## 404 Challenge Scoring (Red)
- Score multiplier of 1.5 on hard difficulty:
- Killing a certain mob:
  - Zombies:                      +0.5
  - Skeletons:                    +1
  - Spiders:                      +1
  - Creepers:                     +2
  - Zombie Pigmen:                +3
  - Ghast:                        +6
- Getting certain achievements (or not getting them):
  - Never use a bed:              +15
  - Never wear armor:             +15
  - Break 3 pumpkins:             +2
  - Break 20 sugar Canes:         +4
  - Break 32 cacti:               +4
  - Break 15 wheat:               +10
  - Break an obsidian block:      +5
  - Exit the nether:              +15
  - Place glowstone in overworld: +10
  - Place 32 glass:               +5
  - Place 20 bricks:              +10
  - Place 8 types of wool:        +10
  - Place all types of wool:      +15
  - Place a crash slab:           +15
  - Craft jack-o'-Lantern:        +2
  - Craft bread:                  +4
  - Craft bow + 64 arrows:        +10
  - Craft full leather armor:     +10
  - Craft full iron armor:        +10
  - Craft full gold armor:        +20
  - Craft full diamond armor:     +50
  - Craft block of iron:          +2
  - Craft block of gold:          +5
  - Craft block of lapis lazuli:  +5
  - Craft block of diamond:       +20
  - Creeper breaks iron block:    +10
  - Creeper breaks gold block:    +10
  - Creeper breaks lapis block:   +10
  - Creeper breaks diamond block: +20

## Installation using Prism Launcher

1. Download an instance of Babric for Prism Launcher: https://github.com/babric/prism-instance
2. Install Java 17, set the instance to use it, and disable compatibility checks on the instance: https://adoptium.net/temurin/releases/
3. Add StationAPI to the mod folder for the instance: https://jenkins.glass-launcher.net/job/StationAPI/lastSuccessfulBuild/
4. (Optional) Add Mod Menu to the mod folder for the instance: https://github.com/calmilamsy/ModMenu/releases
5. (Optional) Add GlassConfigAPI 2.0+ to the mod folder for the instance: https://maven.glass-launcher.net/#/releases/net/glasslauncher/mods/GlassConfigAPI
6. Add this mod to the mod folder for the instance: https://github.com/telvarost/WhatAreYouScoring-StationAPI/releases
7. Run and enjoy! 👍

## Feedback

Got any suggestions on what should be added next? Feel free to share it by [creating an issue](https://github.com/telvarost/WhatAreYouScoring-StationAPI/issues/new). Know how to code and want to do it yourself? Then look below on how to get started.

## Contributing

Thanks for considering contributing! To get started fork this repository, make your changes, and create a PR. 

If you are new to StationAPI consider watching the following videos on Babric/StationAPI Minecraft modding: https://www.youtube.com/watch?v=9-sVGjnGJ5s&list=PLa2JWzyvH63wGcj5-i0P12VkJG7PDyo9T
