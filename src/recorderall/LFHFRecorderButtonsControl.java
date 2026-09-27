package recorderall;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import PamController.PamConfiguration;
import PamController.PamControlledUnit;
import PamView.PamSidePanel;

public class LFHFRecorderButtonsControl extends PamControlledUnit {

    private static final String UNIT_TYPE = "LF + HF Recorder Control";
    private static final String RECORDER_UNIT_TYPE = "Sound Recorder";

    private final RecorderButtonPanel sidePanel;

    public LFHFRecorderButtonsControl(String unitName) {
        super(UNIT_TYPE, unitName);

        sidePanel = new RecorderButtonPanel();
        setSidePanel(sidePanel);
    }

    private ArrayList<PamControlledUnit> getSoundRecorders() {
        PamConfiguration configuration = getPamConfiguration();

        if (configuration == null) {
            return new ArrayList<PamControlledUnit>();
        }

        return configuration.findControlledUnits(RECORDER_UNIT_TYPE);
    }

    private PamControlledUnit findRecorder(String token) {
        ArrayList<PamControlledUnit> recorders = getSoundRecorders();

        // First try an exact name match.
        for (PamControlledUnit recorder : recorders) {
            if (recorder.getUnitName().trim().equalsIgnoreCase(token)) {
                return recorder;
            }
        }

        // Otherwise accept names such as "LF Sound Recorder".
        for (PamControlledUnit recorder : recorders) {
            String name = recorder.getUnitName().toUpperCase(Locale.ROOT);

            if (name.contains(token)) {
                return recorder;
            }
        }

        return null;
    }

    private boolean commandBoth(String command) {

        PamControlledUnit lf = findRecorder("LF");
        PamControlledUnit hf = findRecorder("HF");

        if (lf == null || hf == null) {

            StringBuilder missing = new StringBuilder();

            if (lf == null) {
                missing.append("LF");
            }

            if (hf == null) {
                if (missing.length() > 0) {
                    missing.append(" and ");
                }

                missing.append("HF");
            }

            sidePanel.setStatus("Missing " + missing);

            JOptionPane.showMessageDialog(
                    getGuiFrame(),
                    "Sound Recorder " + missing + " was not found.\n\n"
                            + "The plugin did not send the command to either recorder.",
                    "LF + HF Recorder Control",
                    JOptionPane.WARNING_MESSAGE);

            return false;
        }

        // Send the same manual command to both existing Sound Recorders.
        lf.tellModule(command);
        hf.tellModule(command);

        if ("start".equals(command)) {
            sidePanel.setStatus("LF + HF RECORDING");
        }
        else {
            sidePanel.setStatus("LF + HF STOPPED");
        }

        return true;
    }

    private void startBoth() {
        commandBoth("start");
    }

    private void stopBoth() {
        commandBoth("stop");
    }

    private class RecorderButtonPanel implements PamSidePanel {

        private final JPanel panel;
        private final JLabel statusLabel;

        RecorderButtonPanel() {

            panel = new JPanel(new BorderLayout(6, 8));
            panel.setBorder(
                    BorderFactory.createEmptyBorder(6, 6, 6, 6));

            panel.setPreferredSize(
                    new Dimension(190, 210));

            JPanel buttonPanel = new JPanel();

            buttonPanel.setLayout(
                    new javax.swing.BoxLayout(
                            buttonPanel,
                            javax.swing.BoxLayout.Y_AXIS));

            // ---------------------------------------------------------
            // REC BUTTON
            // ---------------------------------------------------------

            JButton recordButton = new JButton(
                    "<html><center>REC<br>LF + HF</center></html>");

            recordButton.setFont(
                    recordButton.getFont().deriveFont(
                            Font.BOLD, 20f));

            recordButton.setAlignmentX(
                    JButton.CENTER_ALIGNMENT);

            recordButton.setMaximumSize(
                    new Dimension(175, 85));

            recordButton.setPreferredSize(
                    new Dimension(175, 85));

            recordButton.setMargin(
                    new Insets(5, 5, 5, 5));

            recordButton.setToolTipText(
                    "Start the LF and HF Sound Recorders manually");

            recordButton.addActionListener(
                    e -> startBoth());

            // ---------------------------------------------------------
            // STOP BUTTON
            // ---------------------------------------------------------

            JButton stopButton = new JButton(
                    "<html><center>STOP<br>LF + HF</center></html>");

            stopButton.setFont(
                    stopButton.getFont().deriveFont(
                            Font.BOLD, 20f));

            stopButton.setAlignmentX(
                    JButton.CENTER_ALIGNMENT);

            stopButton.setMaximumSize(
                    new Dimension(175, 85));

            stopButton.setPreferredSize(
                    new Dimension(175, 85));

            stopButton.setMargin(
                    new Insets(5, 5, 5, 5));

            stopButton.setToolTipText(
                    "Stop the LF and HF Sound Recorders manually");

            stopButton.addActionListener(
                    e -> stopBoth());

            // ---------------------------------------------------------
            // ADD BUTTONS
            // ---------------------------------------------------------

            buttonPanel.add(recordButton);

            buttonPanel.add(
                    javax.swing.Box.createVerticalStrut(7));

            buttonPanel.add(stopButton);

            // ---------------------------------------------------------
            // STATUS
            // ---------------------------------------------------------

            statusLabel = new JLabel(
                    "READY",
                    SwingConstants.CENTER);

            statusLabel.setFont(
                    statusLabel.getFont().deriveFont(
                            Font.BOLD, 12f));

            panel.add(
                    buttonPanel,
                    BorderLayout.CENTER);

            panel.add(
                    statusLabel,
                    BorderLayout.SOUTH);
        }

        void setStatus(String text) {
            statusLabel.setText(text);
        }

        @Override
        public javax.swing.JComponent getPanel() {
            return panel;
        }

        @Override
        public void rename(String newName) {
            // Nothing to rename in this custom panel.
        }
    }
}
