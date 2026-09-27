package recorderall;

import PamModel.PamDependency;
import PamModel.PamPluginInterface;

public class LFHFRecorderButtonsPlugin implements PamPluginInterface {

    private String jarFile;

    @Override
    public String getDefaultName() {
        return "LF + HF Recorder Control";
    }

    @Override
    public String getHelpSetName() {
        return null;
    }

    @Override
    public void setJarFile(String jarFile) {
        this.jarFile = jarFile;
    }

    @Override
    public String getJarFile() {
        return jarFile;
    }

    @Override
    public String getDeveloperName() {
        return "Custom PAMGuard plugin";
    }

    @Override
    public String getContactEmail() {
        return "";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }

    @Override
    public String getPamVerDevelopedOn() {
        return "2.02.17";
    }

    @Override
    public String getPamVerTestedOn() {
        return "2.02.17";
    }

    @Override
    public String getAboutText() {
        return "Manual two-button control of the LF and HF Sound Recorders "
                + "in PAMGuard 2.02.17. REC starts both recorders and STOP "
                + "stops both. No automatic recording and no RecorderTrigger "
                + "are used.";
    }

    @Override
    public String getClassName() {
        return "recorderall.LFHFRecorderButtonsControl";
    }

    @Override
    public String getDescription() {
        return "Manual LF + HF Sound Recorder control";
    }

    @Override
    public String getMenuGroup() {
        return "Utilities";
    }

    @Override
    public String getToolTip() {
        return "Start or stop the LF and HF Sound Recorders together";
    }

    @Override
    public PamDependency getDependency() {
        return null;
    }

    @Override
    public int getMinNumber() {
        return 0;
    }

    @Override
    public int getMaxNumber() {
        return 1;
    }

    @Override
    public int getNInstances() {
        return 0;
    }

    @Override
    public boolean isItHidden() {
        return false;
    }

    @Override
    public int allowedModes() {
        return PamPluginInterface.NOTINVIEWER;
    }
}
