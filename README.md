# 📝REQUIRES FABRIC API

📖 If you launch the mod on server side, it will use the latest version of minecraft, but if you use it on client side, it will allow you to play any version of the same number (ex: 1.17, 1.17.1 ...)

#### ✅ List of commands :

* /bed -> teleport the player to his bed or respawn anchor, depending one which one is the respawn point
* /bedof -> teleport the player to the bed of the player passed in the argument of the command (for now it only works when the player is online)
* /surface -> teleport the player to the surface
#
* /kgi (kill ground items) -> kill the items that are floating on the ground in a range of 14 chunks (can be changed in config using /setDistanceToEntitiesKgi)
* /kgi <distance_in_chunks> (an number of chunks) -> kill the items that are floating on the ground in the specified range
#
* /Chelp (Cyan help) -> Displays help for this mod
* /ops -> displays a list of all op players
#
* /setAllowBed <true|false> -> enables/disables the use of /bed
* /setDistanceToEntitiesKgi <number> -> set the distance (in chunks) in which the ground items will be removed (-> useful on servers where multiple players are online but not in the same location)

*not working :
* /mods

#### ✔️ List of current aliases:

- /bed -> */b*
- /bedof -> */bo*
- /surface -> */s*
- /killgrounditems -> */kgi* or kgi <number>

### ❗ Important :

If you are not using this mod on client but only on server side, download the resource pack for messages translations (it can be found [here 🔗](https://github.com/Raphoulfifou/Cyan/blob/main/Cyan%201.17.X.zip))
