package fr.romdhani.aymen.toolios.view.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Add an icon resource
 *
 * @author Aromdhani
 */
public class IconResource {
    /**
     * Enum type that indicates the name of the icon.
     */
    public enum ICON {
        SEARCH_ARROW, REFRESH, ARROW_CIRCLE, PLUS, EDIT_SMALL, DELETE_BLUE, LOCK_UNLOCK, EXIT, CONSOLE, EXPORT, EXPORTDATA, EDIT, HELP, INFORMATION, LOAD, CROSS, TICK;
    }

    private static HashMap<ICON, ImageIcon> images = new HashMap<ICON, ImageIcon>();

    /**
     * Return an image icon
     *
     * @param icon the icon to load.
     * @return Image
     */
    public static ImageIcon getImage(ICON icon) {
        if (!images.containsKey(icon)) {
            switch (icon) {
                case SEARCH_ARROW:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/search-arrow.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case REFRESH:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/refresh.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case ARROW_CIRCLE:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/arrow-circle.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case PLUS:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/plus.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case EDIT_SMALL:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/edit-small.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case DELETE_BLUE:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/delete-blue.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case LOCK_UNLOCK:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/lock-unlock.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case EXPORT:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/export.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case EDIT:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/edit-parsing-rules.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case HELP:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/help.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case LOAD:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/load.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case CROSS:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/cross.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case EXIT:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/exit.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case INFORMATION:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/information.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case TICK:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/tick.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case EXPORTDATA:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/export-batch.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case CONSOLE:
                    try {
                        images.put(icon, new ImageIcon(ImageIO.read(new File("src/main/resources/icons/console.png"))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
        return images.get(icon);
    }
}