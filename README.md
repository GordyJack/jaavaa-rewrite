# JAAVAA
## (Just Another Actually Vanilla Additions Addon)
### Still in development, but the first release is coming _soon™_.
#### Most of the features on this list are currently unimplemented. _(Some things may stay that way)_
___
The purpose of this mod is 3-fold:
### 1. Adding features that should be in _Vanilla_ by default. 
* This mainly includes items that are **missing** from the game. Such as:
   * ❌ - Slab, Stair, Fence, and Wall variants.
   * ⚠️ - Potions for effects that don't already have potions.
   * ❌ - A **Woodcutter** that is the Stonecutter we are familiar with but for wood blocks.
   * ❌ - An **Artisan's Kiln** that rounds out the Furnace lineup to cover all the recipes Blast Furnaces and Smokers don't.
   * ⚠️ - **Quicksand**: We have Powdered Snow. We might as well have this too.
* But also includes some things to help round out the experience with these new blocks.
  * New Villager Types:
    *  ❌ - **Artisan**
      * Uses the **Artisan's Kiln** to create all manner of items, including the pottery sherds.
    * ❌ - **Lumberjack**
      * Uses the **Woodcutter** to create planks and other wooden items from logs.
* ❌ - Filling Cauldrons with Potions
* ✅ - Remove Anvil level cap
* ✅ - Adding a version of the **Poison** effect that can be fatal.
### 2. Adding features that still have a _Vanilla_ feel but aren't drawn from already existing features.
#### **New Blocks**:
  * ✅ - **Blocktants** These are 1/8 the size of a normal block so that you can create any slab or stair shape that you might want.
  * ✅ - **Alloy Furnace**: A new kind of furnace that works passively off lava underneat it to craft new _(and old)_ alloys.
  * ✅ - **Recycling Table**: A block that allows recycling of old gear or unused blocks to reclaim their most important components.
#### **New Materials**:
  * ❌ - **Mercury**: Found as a liquid metal, this material is dense and poisonous to the touch making it near impossible to move through. But due to its conductivity, sensitivity, and reactivity it has many implications for new technologies and other advancements.
  * **Alloys**:
    * ✅ - **Auron**: An alloy of Iron and Gold, tools made of this metal are both faster and more durable than iron with higher enchantability. Diamond is still better, but this fills the gap.
    * ⚠️ - **Cupaureum**: I honestly don't know why you would use this material, but the alloys felt incomplete without it. Oh, this is Gold and Copper by the way.
    * ️⚠️ - **Cuperum**: An alloy of Iron and Copper. This alloy is more durable than copper but is just as fast and uses minimal iron for those stuck in the early game.
    * ⚠️ - **Quicksilver**: The best alloy, a combination of mercury and iron. Almost mystical in nature, its durability and speed rival that of diamonds with better magical properties than gold. But keep in mind it is poisonous. So every time the tool takes durability damage, there is a chance that the user becomes poisoned.
    * ✅ - **Steel**: Good old Iron and Coal. This alloy serves as a midpoint between Iron and Diamond, being more durable than Iron but slower than Diamond. It is also more enchantable than Diamond, but less than Iron.
#### **New Items**:
  * ✅ - **Biome Compass**: It seems that when you infuse a compass needle with Mercury and use some Amethyst for calibration and have a more resilient casing, you get a new kind of compass that can point to any biome you like.
  * ✅ - **Hammers**: Mining tools made from the Heavy Core that clear out entire areas at once. Scaling from 3x3 all the way to 9x9. 
  * ✅ - **Simple Magnets**: Magnets that attract items using the power of the Allay.
* **New Redstone Components**:
  * ❌ - **Redstone Pipes**: Basically a pipe filled with redstone dust that can be used to transport redstone signals over longer distances without the need for repeaters. Can also be used to send signals directly vertical. Can only connect to other redstone components that are full blocks.
  * ❌ - **Skinny Hopper**: A version of the Hopper that only has a single inventory slot.
  * ❌ - **Hopper Pipes**: A skinny block that is used for linking Hoppers (or Skinny Hoppers) together. Each hopper pipe can only hold 1 item.
    * ❌ - **Hopper Pipe Junctions**: Used in conjunction with hopper pipes for more advanced item routing.
  * ✅ - **Adjustable Redstone Lamp**: A Redstone Lamp that can be configured to emit any light level. Whenever it emits light, it has a comparator output equal to it's light level. It will also output light equal to it's redstone input when powered.
  * ✅ - **Encased Redstone Pillars**: These blocks are just bi-directional redstone blocks so that they only emit signal out of two opposing sides. _(Really helpful for avoiding unwanted quasi-connectivity)_
  * **New Redstone Gates**:
    * ✅ - **Adder**: A Redstone gate that recieves input on 3 sides and outputs the sum of the inputs (max 15).
    * ✅ - **Advanced Repeater**: A Redstone Repeater that has delay and duration individually configurable.
    * ✅ - **Decoder**: An advanced redstone gate that will output to different sides based on the input given. It can either output the same signal given to it, or repeat the signal.
    * ✅ - **Randomizer**: Another advanced redstone gate that utilizes the power of the new **Adjustable Redstone Lamp** to generate a random output strength whenever powered.
    * **Logic Gates**:
      * ✅ - **AND Gate**: Outputs a signal if all inputs are powered.
      * ✅ - **OR Gate**: Outputs a signal if any input is powered.
      * ✅ - **XOR Gate**: Outputs a signal if a single inputs is powered.
* **Overhauled Potions**:
  * ❌ - **Potion Flasks**: A new type of container that can hold multiple servings of a single potion.
  * ❌ - **Ancient Flagons**: Another new type of container that can hold multiple potion effects to all apply simultaneously.
  * **New Effects**:
    * ❌ - **Fearsome**: Causes all Mobs in range to flee in terror.
    * ❌ - **Infestation**: Causes Mobs to rapidly spawn in range.
* **Extended Enchanting**
  * **New Enchantments**:
    * ✅ - **Bloodletter**: Heals the attacker for a percentage of the health missing from the target.
    * ⚠️ - **Pacted**: Ensures the item remains intact, even when its durability depletes to zero and ensures it remains with you upon respawning if it was in your hand or equipped at the time of death.
    * ❌ - **Truesight**: Allows the player to see all hostile mobs in range as if they have the glowing effect. Higher levels have higher ranges.
    * ❌ - **Farsight**: Can be applied to the Spyglass. Makes the Spyglass zoom in even further. Higher levels have further zoom.
  * **New Curses**:
    * ⚠️ - **Curse of Destruction**: Any tool with this curse, will mine all blocks instantly, but destroy them beyond use in the process.
    * ⚠️ - **Curse of Persistance**: This curse can actually be very helpful, but does come with an explosive downside. The more you mine, the faster it gets. The faster it gets, the larger the explosion will be when you stop.
    * ✅ - **Curse of the Capricious**: Whenever a block is mined by a tool with this curse, a random block drops in its place.
### 3. Extending the endgame of _Vanilla_ with more materials and powerful exploration rewards.
#### **New Materials**:
  * ⚠️ - **Starsteel**: An unholy alloy of Netherite and Nether Stars so dangerous that most Crafters who have attempted to forge it perish. But those who are successful become gods among men.
  * ❌ - **Dragonsteel**: Being a god wasn't enough for you? How about being a dragon? Made from infusing Starsteel with a Dragon Egg, this material is about as rare and powerful as you can get. But you have heard myths, whispers, of another...
  * ⚠️ - **Voidium**: Made from Dragonsteel and the Void Corrupted Endstone found on the underside of islands, Armor made from this material turns you into the void incarnate, not even having a true physical form.
#### **New _Legendary_ Loot**:
  * ⚠️ - **The Tool of the Ancients**: This tool is made from Netherite and is only found in the deepest bowels of Bastions. It is able to mine anything put in front of it, from dirt to Ancient Debris and everything in between. It also makes for a rather formidable weapon. Since it's made of Netherite, you can also upgrade it using **Starsteel**.
  * ✅ - **The Architect's Compass**: A compass whose method of construction has been lost to time, this compass can find any structure in the world. It may be found in any structure, but is extremely rare. The Architect is nothing if not meticulous.
  * ❌ - **A New Kind of Beacon**: Crafted using **Starsteel** this beacon behaves similarly to a _Vanilla_ beacon, but it's effect is determined by a potion you give it. It is also more sensitive to how it's base is constructed.
___
All of these features are fully configurable through the config file. I plan on adding compatibility with [Mod Menu](https://www.curseforge.com/minecraft/mc-mods/modmenu) in the future.  
All documentation will be available through [Patchouli](https://www.curseforge.com/minecraft/mc-mods/patchouli-fabric) with an official [wiki](https://github.com/GordyJack/JAAVAA/wiki) coming _soon™_.  
All issues and suggestions will be tracked on the [issue tracker](https://github.com/GordyJack/JAAVAA/issues).
___
#### Key:
- ✅: Fully implemented and functional - no anticipated changes.
- ⚠️: Currently in progress - expect lots of changes.
- ❌: Have not yet started implementation but plan to in the future.
