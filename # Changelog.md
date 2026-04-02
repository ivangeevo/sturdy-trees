# <center>Sturdy Trees</center>

## v2.0(dev)
### WARNING! Game breaking update, proceed with caution.
+ Added saplings growing in stages! They now grow in 4 stages (from small saplings/seedlings) to mature (normal vanilla saplings). Mangrove propagules, Azalea and Flowering Azalea do not have stages, but they grow much slower than normal mature saplings
+ Added logs/wood/stump blocks to turn into smouldering variants when exposed to fire for a while. Smouldering blocks will turn to cinder variants after a while or when extinguished by water/rain
+ Added the ability for stump blocks to be able to incrementally break like other log blocks when mined with an improper tool that doesn't convert them to crafting stumps. All items that are considered valid tools for conversion to crafting stumps should be registered under the "stump_efficient" tag in addition to them being added as efficient blocks in their material definition's inverse tag
+ Added Ash to generate from burning leaves and wood cinder blocks when they land after falling
+ Changed leaves to be passable-through. This can be configured per entity type via the "sturdy_trees:collides_with_leaves" tag
+ Changed how stump blocks are handled internally. This means that existing stumps in your world would likely be affected/missing
+ Changed the collision shapes for all log/stump block variations to be a full cube. This matches retail BTW behavior and fixes issues with mobs trying to pathfind on top of those blocks
+ Changed(added) all log/stump blocks from the mod to be proper flammable blocks
+ Fixed stump removers decrementing their stack count when in creative mode
+ Fixed missing blockstate combinations for spike log blocks which caused many warnings in the console
+ Removed the neighboring replacement logic, which makes log models flow better as it was causing issues and didn't work exactly as intended. Ideally, all the log classes need to be reworked to match BTW more closely. However, that turned out to be a bigger endeavor than I can handle at the moment, so I'm leaving it for later on when I have the technical knowledge to do so
+ Rewrote most of the project internally
+ Updated the mod to Fabric API 0.116.9, Fabric Loader 0.18.4 & BTWR: Shared Library 0.8.2

## v1.6.6
+ Changed directional drops to be handled with the DirectionalDropsConditions class from BTWR: SL 0.7
+ Updated the mod to BTWR: Shared Library 0.7

## v1.6.5
+ Fixed problems with existing configuration option where it was displaying wrong value/incorrect comments for what it does
+ Changed all configuration options in the mod to use the custom config library added by BTWR: Shared Library. This fixes the bug from last version that crashed the game without any warnings of the missing library that created the configurations
+ Removed Supermartijn642's config lib as the one creating configuration setting as it requires itself as a dependency to work properly
+ Updated the mod to BTWR: Shared Library 0.6.5

## v1.6.4
+ Changed all configuration options setting to be handled with Supermartijn642's Config Lib internally instead of Cloth Config API, which is used only for client side config options. Cloth Config is still used for creating all screens for access through Mod Menu.
+ Refactored pretty much the whole code; mainly for readability and cleaning up, but also so it's more in order with other mods from the BTWR project
+ Updated the mod to Fabric Loader 0.17.3 & BTWR: Shared Library 0.6.4

## v1.6.3
+ Updated ExtendedShapelessRecipe from BTWR: Shared Library, which fixes some bugs with recipes using it
+ Updated the mod to Fabric API 0.116.7 & BTWR: Shared Library 0.61

## v1.6.2
+ Updated mod recipes to use the new renamed ExtendedShapelessRecipe from BTWR: SL v0.60
+ Added a configuration option with Mod Menu to toggle whether saplings can be fertilized with bone meal or not
+ Added missing recipes for bamboo stripped/normal blocks to planks with tool and disabled the vanilla recipe for those
+ Added missing recipe for making bamboo planks by combining 2 bamboo slabs
+ Added translations for all mod blocks, most of them were ones without an item, but with this change they will properly display with mods like WAILA
+ Split the mod client environment from the main package
+ Updated the mod to BTWR Shared Library 0.60

## v1.6.1
+ Fixed recipes for crafting planks/sticks with axes and other tools to work without issues by using a custom recipe by BTWR: Shared Library and remove the old logic for modifying recipe remainder for items with mixins.
+ Added recipes for stripped logs for planks/sticks crafting with tools.
+ Removed leaves blocks from the AXE_MINEABLE block tag to normalize leaves blocks breaking speed as it was never intended to increase it.
+ Updated the mod to BTWR Shared Library 0.59

## v1.6
+ Made certain logs to update themselves and/or their neighbors on break so the logs model flows better overall (as it does in the original BTW)
+ Fixed partial logs (chewed, stripped or spikes) to break instantly in creative instead of in layers
+ Fixed logs from the mod to be able to break into horizontal logs and also improved models/blockstate json files a lot.
+ Changed some block breaking logic to use Fabric API break events instead of mixing in into code of existing blocks.
+ Optimized and fixed the wooden breaking sound for stripped, chewed & spike log blocks to play as they should.
+ Updated the mod to Fabric API 0.116.6, Fabric Loader 0.17.2 & BTWR Shared Library 0.58

## v1.5.4
+ General code improvement/cleanup
+ Updated the mod to Fabric API 0.115.3 & BTWR Shared Library 0.48

## v1.5.3
+ Updated the mod to Fabric API 0.115.0, Fabric Loader 0.16.10 & BTWR Shared Library 0.47
+ Updated the mod license in the mod list to display the proper one (changed from MIT to CC-BY-4.0)
+ Update the author of the mod (me) to display in the mod list

## v1.5.2
+ Fixed a bug with trees from other mods where their bottom part would not generate. This should allow compatibility with other mods
to generate normally without missing "trunks".

## v1.5.1
+ Fixed version requirement for BTWR-SL
+ Updated the mod to Fabric API 0.114.0 && BTWR-SL v0.40

## v1.5

+ Added BTWR-SL (Library mod) as dependency and removed BTWR: Core
+ Added mod description to display properly
+ Changed the stump remover recipe to not require creeper oysters
+ Updated the mod to Fabric API 0.110.0 & Fabric Loader 0.16.9

## v1.4.4

+ Fixed stumps to be able to be broken by explosions (they were too tough before)
+ Updated the mod to Fabric Loader 0.16.7 & Fabric API 0.106.0

## v1.4.3

+ Added support for stump blocks being broken by new chisel item tags introduced with the new Tough Environment update.
+ Fixed stumps to be able to be broken by explosions (they were too tough before)
+ Updated the mod to Fabric Loader 0.16.5 & Fabric API 0.104.0
+ Updated the mod to require v0.26 of BTWR: Core


+ ## v1.4.2

+ Fixed some bugs related to crafting log recipes into sticks and planks to work as intended.
+ Updated the mod to Fabric Loader 0.16.3
+ Updated the mod to Fabric API 0.103.0

## v1.4.1

+ Fixed a bug that caused leaves to always drop when broken by hand
+ General improvements for some loot tables and recipes that were causing problems since the 1.20.6 update
+ Updated the mod to latest fabric api version for 1.20.6

## v1.4

+ Added bamboo block to bamboo planks recipe with an axe.
+ Reduced Dead Bush Shaft(Stick) drop rate even further, from 20 to 11 percent.
+ Reduced fuel values for Saw Dust and all Bark items.
+ Fixed missing textures for some stump blocks.
+ Fixed models in general for some of the custom log models.
+ Balanced (brough back to normal) the axes breaking speed for leaves, as it was too fast.
+ Fixed code to work as intended on the server side as well.
+ Updated the mod to Fabric API 0.92.2 & Fabric Loader 0.15.11 

## v1.3

+ Rewrote most of the mod logic to work better.
+ Added recipes for making plank blocks from wooden slabs.
+ Added Crafting Stump blocks. ( currently only acquired by breaking with Tough Environment chisel.)
+ Made the drops for mod log blocks to be datapack compatible (they were hardcoded before)
+ Reduced drop rate of sticks from Dead bush.
+ Brough back the logic to create vanilla stripped logs with right click.
+ Fixed Deepslate from not dropping because of wrong insertion of custom code.
+ Fixed all stump textures & made them appropriately convert to crafting tables when using with Tough Environment Chisels.

+ Updated the mod to Fabric API 0.92.1 & Fabric Loader 0.15.10

## v1.2

+ Requires the BTWR-Core mod to run from now on.
+ BTWR-Core provides a way for shearing the creepers and acquiring the creeper oysters needed to craft Stump Remover item(wasn't available before).

+ Added secondary drops from logs. (Bark & Saw Dust)

+ Changed how breaking logs into lesser items work. Logs must be crafted together with the axe item to get planks/shafts(sticks) depending on the type of axe used.
+ Changed Spruce stump texture to match the log's lighter texture.
+ Changed effects on stump removing.

+ Fixed apples not dropping from Oak Leaves.
+ Fixed a bug where Flowering Azalea leaves were not harvestable with Shears.
+ Fixed a bug where Cherry Logs weren't breaking correctly
+ Fixed a bug that made breaking logs cost too much hunger.

+ Removed Shaft item.
+ Removed log stripping via right-clicking with axe.

+ Updated the mod to Fabric API 0.92.0 & Fabric Loader 0.15.6


+ ## v1.1

+ Added Stump Remover item.
+ Fixed Mangrove Roots to break faster.
+ Removed being able to quick grow saplings with Bone meal.


## v1.0

+ Release


## pre-release 0.5

+ Initial start of keeping changelogs.
+ Moved all the tree logic from BTWR to this mod.
