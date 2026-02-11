<div align="center">
  <img src="https://repository-images.githubusercontent.com/917029326/b55757a1-a971-49e5-a873-645ab1c9ce1a" width="80%">
<br><br>
 
[![Support 1.9-1.21.8](https://img.shields.io/badge/Support_Version-1.9--1.21.8-blue?style=for-the-badge)](#)
[![License AGPL-3.0](https://img.shields.io/github/license/MOC-Tournament/StatCleaner?style=for-the-badge)](https://github.com/MOC-Tournament/StatCleaner/blob/master/LICENSE)
[![GitHub Issues](https://img.shields.io/github/issues/MOC-Tournament/StatCleaner?style=for-the-badge)](https://github.com/MOC-Tournament/StatCleaner/issues)

[![Modrinth Download](https://img.shields.io/modrinth/dt/YIxURHKO?style=for-the-badge&label=Modrinth%20Download&color=1bd96a)](https://modrinth.com/plugin/statcleaner)

**StatCleaner**，一个命令即可重置玩家的全部属性。   
**StatCleaner**, reset player's all stats in one command. 
</div>

## 功能 | Features
* **一键重置。** 使用`/statreset <目标>`命令一次性重置目标玩家的全部属性。<br>**One-command Reset.** Reset target players' all stats in one command: `/statreset <target>`.
  * 一次性恢复生命值，补满饥饿值并设置饱和度为5点，清除所有状态效果，重置所有属性至默认值，关闭飞行能力。<br>Regenerate health, fill hunger and set saturation to 5, clear all active effects, reset all attributes to default, and disable flying in one command. 
* **目标选择器支持。** 支持原版中`@a`、`@p`、`@r`、`@s`等选中玩家的目标选择器。<br>**Target Selector Support.** Support vanilla selectors that choose players, like `@a`, `@p`, `@r`, `@s`, etc.
* **完全可配置。** 5大重置内容均可通过配置文件随意开关。<br>**Fully Configurable.** All 5 aspect can be toggled seperately through configuration.
* **本地化支持。** 所有插件消息均可通过消息文件修改。<br>**Localization Support.** All plugin messages can be modified with the message file.

## 依赖项 | Dependencies
- Java 17 或以上版本。<br>Java 17 or higher.
- Spigot服务器及其分支1.9~1.21.8版本。<br>Spigot server and its forks from 1.9 to 1.21.8. 
