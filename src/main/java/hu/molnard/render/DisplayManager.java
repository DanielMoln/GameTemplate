package hu.molnard.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager
{
    private String sct;
    private int w, h, FPS = 60;

    public DisplayManager(String screenTitle, int width, int height)
    {
        this.sct = screenTitle;
        this.w = width;
        this.h = height;
        init();
    }

    public void init()
    {
        try {
            ContextAttribs attrb = new ContextAttribs(3,2);
                attrb.withForwardCompatible(true);
                attrb.withProfileCore(true);

            Display.setDisplayMode(new DisplayMode(w, h));
            Display.create(new PixelFormat(), attrb);

            /* @param 1. x coordinate, 2. y coordinate, 3. width, 4. height */
            GL11.glViewport(0, 0, Display.getDesktopDisplayMode().getWidth(), Display.getDesktopDisplayMode().getHeight());
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    public void updateDisplay() { Display.sync(FPS); Display.update(); }
    public void closeDisplay() { Display.destroy(); }
}
