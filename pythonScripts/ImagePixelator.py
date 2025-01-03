from PIL import Image
import sys

textures_path = "/Users/gordyjack/IdeaProjects/jaavaa-rewrite/src/main/resources/assets/jaavaa/textures/"
reference_textures_path = f"{textures_path}reference_textures/"

def pixelate(input_image_path, output_image_path, pixel_size):
    # Open the input image
    image = Image.open(input_image_path)
    
    # Calculate the size of the output image
    new_width, new_height = pixel_size, pixel_size
    
    # Resize the image to a smaller size
    image = image.resize((new_width, new_height), Image.LANCZOS)
    
    # Save the pixelated image
    image.save(output_image_path)

if __name__ == "__main__":
    if len(sys.argv) != 4:
        print("Usage: python ImagePixelator.py <input_image_name> <output_image_path> <pixel_size>")
        sys.exit(1)
    
    input_image_path = reference_textures_path + sys.argv[1]
    output_image_path = textures_path + sys.argv[2]
    pixel_size = int(sys.argv[3])
    
    print(f"Pixelating\n\tInput: {input_image_path}\n\tOutput: {output_image_path}\n\tWidth: {pixel_size}")
    pixelate(input_image_path, output_image_path, pixel_size)