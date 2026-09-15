# Neoforge - 26.2

This is the branch specifically for neoforge 26.2.
Any differences will be listed below. For full documentation, see the `core` branch.

Minecraft 26.2 ships unobfuscated, so `ResourceLocation` is now `Identifier` and the toast rendering API uses the new `extractRenderState`/`GuiGraphicsExtractor` pattern. Toolchain uses ModDevGradle (`net.neoforged.moddev`) and targets Java 25.
