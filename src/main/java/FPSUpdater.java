

public class FPSUpdater {

    long lastFrame;
    int fps;
    long lastFPS;
    private String title = "Test Bench";

    public FPSUpdater() {
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer
    }

    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        lastFrame = time;

        return delta;
    }


    public long getTime() {
        return System.currentTimeMillis();
    }

    public boolean updateFPS() {
        if (getTime() - lastFPS > 1000) {
            title = "Test Bench - FPS: " + fps;
            fps = 0;
            lastFPS += 1000;

            return true;
        }
        fps++;
        return false;
    }

    public String getTitle() {
        return title;
    }


}
