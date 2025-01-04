import math
from PIL import Image
import random

reference_textures_path = "/Users/gordyjack/IdeaProjects/jaavaa-rewrite/src/main/resources/assets/jaavaa/textures"
image_name = "item/malum_stellae_incantatae"
base_texture = Image.open(f"{reference_textures_path}/{image_name}.png").convert("RGBA")

# Get the size of the block texture
width, height = base_texture.size

# Calculate the number of pixels for 10% of the image
num_pixels = int(width * height * 0.1)

# Generate random white pixels with random alpha values
tiles = 16
tiled_image = Image.new("RGBA", (width, height * tiles))
tiled_image.paste(base_texture, (0, 0))
for i in range(1, tiles):
    new_image = base_texture.copy()
    for x in range(width):
        for y in range(height):
            if new_image.getpixel((x, y)) == (0, 0, 0, 0):
                continue
            if random.random() < 0.15:
                r, g, b, a = new_image.getpixel((x, y))
                brightness = random.uniform(1.1, 1.5)  # Random brightness factor between 1.1 and 1.5
                new_r = min(int(r * brightness), 255)
                new_g = min(int(g * brightness), 255)
                new_b = min(int(b * brightness), 255)
                new_image.putpixel((x, y), (new_r, new_g, new_b, a))
    tiled_image.paste(new_image, (0, height * i))


# Save the new image
tiled_image.save(f"{reference_textures_path}/{image_name}_tile.png")
tiles=tiled_image.height//height
print(f"{tiled_image.size=}, {tiles=}")
