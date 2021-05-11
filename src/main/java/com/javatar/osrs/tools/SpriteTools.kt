package com.javatar.osrs.tools

import com.javatar.osrs.definitions.impl.SpriteDefinition
import com.javatar.osrs.definitions.impl.SpriteGroupDefinition
import javafx.scene.image.Image
import javafx.scene.image.PixelFormat
import javafx.scene.image.WritableImage
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object SpriteTools {

    fun SpriteDefinition.toImage() : Image {
        if(width > 0 && height > 0) {
            val image = WritableImage(width, height)
            image.pixelWriter.setPixels(
                0,
                0,
                width,
                height,
                PixelFormat.getIntArgbInstance(),
                pixels,
                0,
                width
            )
            return image
        }
        return WritableImage(1, 1)
    }

    fun fromFXImage(group: SpriteGroupDefinition = SpriteGroupDefinition(), vararg images: Image) : SpriteGroupDefinition {
        group.width = images.maxOf { it.width }.toInt()
        group.height = images.maxOf { it.height }.toInt()
        val sprites = images.map { it.toSprite() }.toList()
        if (group.sprites == null || group.sprites.isEmpty()) {
            group.sprites = sprites.toTypedArray()
        } else {
            val list = group.sprites.toMutableList()
            list.addAll(sprites)
            group.sprites = list.toTypedArray()
        }
        return group
    }

    fun writeSpriteToFile(file: File, sprite: SpriteDefinition) {
        val width = if(sprite.width <= 0) {
            sprite.maxWidth
        } else sprite.width
        val height = if(sprite.height <= 0) {
            sprite.maxHeight
        } else sprite.height

        if(width <= 0 || height <= 0) {
            return
        }
        if(sprite.pixels == null || sprite.pixels.isEmpty()) {
            return
        }

        val spriteImage = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        spriteImage.setRGB(0, 0, width, height, sprite.pixels, 0, width)
        ImageIO.write(spriteImage, "png", file)
    }

    fun Image.toSprite(): SpriteDefinition {
        val argbPixels = IntArray((width.toInt() * height.toInt()))
        pixelReader.getPixels(
            0,
            0,
            width.toInt(),
            height.toInt(),
            PixelFormat.getIntArgbInstance(),
            argbPixels,
            0,
            width.toInt()
        )
        val pixels = IntArray(argbPixels.size)
        val paletteList = mutableListOf<Int>()
        paletteList.add(0)

        for (index in argbPixels.indices) {
            val argb: Int = argbPixels[index]
            val alpha = argb shr 24 and 0xFF
            val rgb = argb and 0xFFFFFF

            var paletteIndex = 0

            if (alpha == 0xFF) {
                paletteIndex = paletteList.indexOf(rgb)
                if (paletteIndex == -1) {
                    paletteIndex = paletteList.size
                    paletteList.add(rgb)
                }
            }

            pixels[index] = paletteIndex
        }

        if(paletteList.size > 255) {
            error("Image has a color palette that is too large.")
        }
        val sprite = SpriteDefinition()
        sprite.pixelIdx = pixels.map { it.toByte() }.toByteArray()
        sprite.pixels = argbPixels
        sprite.setPalette(paletteList.toIntArray())
        sprite.width = width.toInt()
        sprite.height = height.toInt()
        sprite.maxWidth = width.toInt()
        sprite.maxHeight = height.toInt()
        sprite.offsetX = 0
        sprite.offsetY = 0

        return sprite
    }

}