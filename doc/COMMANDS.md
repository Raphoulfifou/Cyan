# Documentation for the commands of the mod

Commands are present in 2 different forms :

1. /cyan ...
2. /_commandName_

## I). /cyan commands

> These commands are mostly for configuration and description

```bash
cyan
├── config
│   ├── allow -> Used to allow or not the usage of the available commands (from the Cyan mod)
│   │   ├── all [true|false] -> Sets all the 'allow' options to the given value
│   │   ├── bed [true|false] -> Sets the 'allowBed' option to the given value
│   │   ├── kgi [true|false] -> Sets the 'allowkgi' option to the given value
│   │   └── surface [true|false] -> Sets the 'allowSurface' option to the given value
│   │
│   ├── minOpLevelExe -> Used to define which OP level is required for a player to execute a command (0 means the player is not OP)
│   │   ├── all [0|1|2|3|4] -> Sets all the 'minOpLevelExe' options to the given value
│   │   ├── modifConfig [0|1|2|3|4] -> Sets the 'minOpLevelExeModifConfig' options to the given value
│   │   ├── bed [0|1|2|3|4] -> Sets the 'minOpLevelExeBed' option to the given value
│   │   ├── kgi [0|1|2|3|4] -> Sets the 'minOpLevelExekgi' option to the given value
│   │   └── surface [0|1|2|3|4] -> Sets the 'minOpLevelExeSurface' option to the given value
│   │
│   └── other
│       ├── boolean
│       │   └── useOneLanguage [true|false] -> Sets the 'useOneLanguage' option to the given value
│       └── integer
│           └── distanceToEntitiesKgi [integer] -> Sets the 'distanceToEntitiesKgi' option to the given value
│
└── description
```
