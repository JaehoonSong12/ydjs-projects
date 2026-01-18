package dev.lwjgl;

import java.io.InputStream;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.stb.STBImage.stbi_failure_reason;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;
import static org.lwjgl.stb.STBImage.stbi_set_flip_vertically_on_load;
import org.lwjgl.system.MemoryStack;

/**
 * Utility class for loading textures from resource files.
 */
public class TextureLoader {
    
    /**
     * Loads a texture from a resource path.
     * The path should be relative to the resources folder (e.g., "anderson/number_0.png").
     * 
     * @param resourcePath The path to the texture resource
     * @return OpenGL texture ID
     * @throws RuntimeException if the texture cannot be loaded
     */
    public static int loadTexture(Path resourcePath) {
        return loadTexture(resourcePath, TextureLoader.class);
    }
    
    /**
     * Loads a texture from a resource path using a specific class for resource loading.
     * 
     * @param resourcePath The path to the texture resource
     * @param clazz The class to use for loading resources
     * @return OpenGL texture ID
     * @throws RuntimeException if the texture cannot be loaded
     */
    public static int loadTexture(Path resourcePath, Class<?> clazz) {
        int textureID;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer width = stack.mallocInt(1);
            IntBuffer height = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            String resourcePathStr = "/" + resourcePath.toString().replace('\\', '/');
            InputStream in = clazz.getResourceAsStream(resourcePathStr);
            if (in == null) {
                throw new RuntimeException("Image not found: " + resourcePathStr);
            }

            Path tempFile = Files.createTempFile("texture", ".png");
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);

            stbi_set_flip_vertically_on_load(true);
            java.nio.ByteBuffer image = stbi_load(tempFile.toString(), width, height, channels, 4);
            if (image == null) {
                throw new RuntimeException("Failed to load texture: " + stbi_failure_reason());
            }

            textureID = glGenTextures();
            glBindTexture(GL_TEXTURE_2D, textureID);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

            stbi_image_free(image);
            Files.deleteIfExists(tempFile);
        } catch (Exception e) {
            throw new RuntimeException("Error loading texture: " + resourcePath, e);
        }
        return textureID;
    }
}

