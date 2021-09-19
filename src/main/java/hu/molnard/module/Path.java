package hu.molnard.module;

public class Path
{
    private static Path instance;

    public String join(String contentDirectory, String fileName)
    {
        String result = "";
        if (contentDirectory.endsWith("/"))
            result = contentDirectory+fileName;
        else
            result = contentDirectory+"/"+fileName;
        return result;
    }

    public static Path getInstance()
    {
        if (instance == null)
            instance = new Path();
        return instance;
    }
}
