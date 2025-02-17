from PIL import Image
import random
import math
from tqdm import tqdm

root_path = '/Users/gordyjack/IdeaProjects/jaavaa-rewrite/src/main/resources/assets/jaavaa/textures/reference_textures'
base_pixel_count = 64

def create_background():
    black = Image.new('RGBA', (base_pixel_count, base_pixel_count), (0, 0, 0, 255))
    img = Image.new('RGBA', (base_pixel_count, base_pixel_count), (0, 0, 0, 0))
    progress_bar = tqdm(total=base_pixel_count * base_pixel_count, desc="Creating Background")
    for x in range(base_pixel_count):
        for y in range(base_pixel_count):
            cx, cy = base_pixel_count / 2, base_pixel_count / 2
            max_dist = math.sqrt(cx * cx + cy * cy)
            dist = math.sqrt((x - cx) ** 2 + (y - cy) ** 2)
            t = min(dist / max_dist, 1.0)
            red = int((128 - (127 * t)) / 3)
            blue = int((255 - (127 * t)) / 2)
            r, g, b = (red, 0, blue)
            fade_to_black = int(255 * (t ** 2))
            img.putpixel((x, y), (r, g, b, 255 - fade_to_black))
            if random.random() < 0.25:
                noise_intensity = 5
                nr = min(255, max(0, r + random.randint(-noise_intensity, noise_intensity)))
                ng = min(255, max(0, g + random.randint(-noise_intensity, noise_intensity)))
                nb = min(255, max(0, b + random.randint(-noise_intensity, noise_intensity)))
                img.putpixel((x, y), (nr, ng, nb, fade_to_black))
            progress_bar.update(1)
    return Image.alpha_composite(black, img)

def create_starfield():
    starfield_pixel_count =  int(base_pixel_count * 2)
    img = Image.new('RGBA', (starfield_pixel_count, starfield_pixel_count), (0, 0, 0, 0))
    pixels = [(x, y) for x in range(starfield_pixel_count) for y in range(starfield_pixel_count)]
    random.shuffle(pixels)

    n = len(pixels)
    nbp = n * 0.0075
    white_count = int(nbp * (3/5))
    grey_count = int(nbp * (2/5))

    white_stars = [(255, 255, 255, 255)] * white_count
    grey_stars = [(1, 1, 1, 255)] * grey_count
    background = [(0, 0, 0, 0)] * (n - white_count - grey_count)

    color_pool = white_stars + grey_stars + background
    random.shuffle(color_pool)

    def is_neighbor_white(x, y):
        neighbors = [
            (x-1, y), (x+1, y), (x, y-1), (x, y+1),
            (x-1, y-1), (x+1, y+1), (x-1, y+1), (x+1, y-1)
        ]
        for nx, ny in neighbors:
            if 0 <= nx < starfield_pixel_count and 0 <= ny < starfield_pixel_count:
                if img.getpixel((nx, ny)) in white_stars:
                    return True
        return False

    progress_bar = tqdm(total=n, desc="Creating Starfield")
    for (x, y) in pixels:
        star_color = color_pool.pop()
        if star_color == (1, 1, 1, 255):
            brighten_factor = random.uniform(5, 10)
            brightened_color = (255, 255, 255, int(255 / brighten_factor))
            img.putpixel((x, y), brightened_color)
        elif star_color == (0, 0, 0, 0):
            progress_bar.update(1)
            continue
        else:
            if star_color in white_stars and is_neighbor_white(x, y):
                color_pool.append(star_color)
                progress_bar.update(1)
                continue
            img.putpixel((x, y), star_color)
        progress_bar.update(1)
    return img

def animate_image_shift():
    background = create_background()
    starfield = create_starfield()
    w, h = background.size

    def shift_image(img, offset):
        left = img.crop((0, 0, w - offset, h))
        right = img.crop((w - offset, 0, w, h))
        combined = Image.new('RGB', (w, h))
        combined.paste(right, (0, 0))
        combined.paste(left, (offset, 0))
        return combined

    animated = Image.new('RGB', (w, h * w))
    for i in range(w):
        frame = shift_image(starfield, i)
        animated.paste(frame, (0, i * h))

    return animated

def animate_image_rotate():
    background = create_background()
    starfield = create_starfield()
    w, h = background.size

    def rotate_image(img, angle):
        center_x, center_y = img.size[0] // 2, img.size[1] // 2
        left = center_x - w // 2
        top = center_y - h // 2
        right = center_x + w // 2
        bottom = center_y + h // 2
        img = img.rotate(angle, resample=0, expand=False, fillcolor=(255, 0, 0))
        return img.crop((left, top, right, bottom))
    
    def dim_stars(img, factor, brightness):
        img = img.convert('RGBA')
        progress_bar = tqdm(total=img.size[0] * img.size[1], desc="Dimming Stars")
        for x in range(img.size[0]):
            for y in range(img.size[1]):
                r, g, b, a = img.getpixel((x, y))
                if (r, g, b) == (255, 255, 255) and random.random() < factor:
                    r = g = b = random.randint(brightness, 255)
                    img.putpixel((x, y), (r, g, b, a))
                progress_bar.update(1)
        progress_bar.clear()
        return img

    frame_count = 128
    animated = Image.new('RGBA', (w, h * frame_count))
    progress_bar = tqdm(total=frame_count, desc="Creating Animation")
    for i in range(frame_count):
        main_angle = i * (360 / frame_count)
        prev_angle = ((i-1) % frame_count) * (360 / frame_count)
        prev_prev_angle = ((i-2) % frame_count) * (360 / frame_count)
        frame = rotate_image(starfield, main_angle)
        frame = dim_stars(frame, 0.6, 128)
        prev_frame = rotate_image(starfield, prev_angle)
        prev_frame.putalpha(128)
        prev_frame = dim_stars(prev_frame, 0.8, 64)
        prev_prev_frame = rotate_image(starfield, prev_prev_angle)
        prev_prev_frame.putalpha(64)
        prev_prev_frame = dim_stars(prev_prev_frame, 0.95, 32)
        starfields = Image.alpha_composite(prev_prev_frame, prev_frame)
        starfields = Image.alpha_composite(starfields, frame)
        combined = Image.alpha_composite(background, starfields)
        animated.paste(combined, (0, i * h))
        progress_bar.update(1)

    return animated

if __name__ == '__main__':
    template_path = f'{root_path}/raw_voidium.png'
    animate_image_rotate().save(template_path)