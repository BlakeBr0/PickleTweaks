# Pickle Tweaks [![](http://cf.way2muchnoise.eu/full_238761_downloads.svg)](https://minecraft.curseforge.com/projects/pickle-tweaks) 
A bunch of features and tweaks that I don't think need their own mods.

## Download

The official release builds can be downloaded from the following websites.

- [Blake's Mods](https://blakesmods.com/pickle-tweaks/download)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/pickle-tweaks)
- [Modrinth](https://modrinth.com/mod/pickle-tweaks)

## Development

To use this mod in a development environment, you will need to add the following to your `build.gradle`.

```groovy
repositories {
    maven {
        url 'https://maven.blakesmods.com'
    }
}

dependencies {
    implementation fg.deobf('com.blakebr0.cucumber:Cucumber:<minecraft_version>-<mod_version>')
    implementation fg.deobf('com.blakebr0.pickletweaks:PickleTweaks:<minecraft_version>-<mod_version>')
}
```

## License

[MIT License](./LICENSE)
