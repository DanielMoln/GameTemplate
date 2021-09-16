package hu.molnard;

import hu.molnard.render.DisplayManager;
import hu.molnard.render.MasterRenderer;
import org.lwjgl.opengl.Display;

public class Engine
{
    public static final String PREFIX = "[GAME] ";

    private static MasterRenderer masterRenderer;
    private static DisplayManager displayManager;

    public static void main(String[] args)
    {
        displayManager = new DisplayManager("Game Template", 1150, 700);
        masterRenderer = new MasterRenderer();
        System.out.println(PREFIX + "Game initialized!");

        while (!Display.isCloseRequested())
        {
            masterRenderer.render();
            displayManager.updateDisplay();
        }

        displayManager.closeDisplay();
        System.out.println(PREFIX + "Game is down. Good bye!");
    }
}
