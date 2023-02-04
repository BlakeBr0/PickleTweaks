# Pickle Tweaks

<p align="left">
    <a href="https://blakesmods.com/pickle-tweaks" alt="Downloads">
        <img src="https://img.shields.io/endpoint?url=https://api.blakesmods.com/v2/badges/pickletweaks/downloads&style=for-the-badge" />
    </a>
    <a href="https://blakesmods.com/pickle-tweaks" alt="Latest Version">
        <img src="https://img.shields.io/endpoint?url=https://api.blakesmods.com/v2/badges/pickletweaks/version&style=for-the-badge" />
    </a>
    <a href="https://blakesmods.com/pickle-tweaks" alt="Minecraft Version">
        <img src="https://img.shields.io/endpoint?url=https://api.blakesmods.com/v2/badges/pickletweaks/mc_version&style=for-the-badge" />
    </a>
    <a href="https://blakesmods.com/wiki/pickletweaks" alt="Wiki">
        <img src="https://img.shields.io/static/v1?label=wiki&message=view&color=brightgreen&style=for-the-badge" />
    </a>
</p>

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
