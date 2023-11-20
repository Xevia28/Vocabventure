import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class app {
    JFrame frame;
    JPanel activePanel;
    Timer easyTimer;
    Timer mediumTimer;
    Timer hardTimer;
    Score scorePage;
    EasyPage easyPage;
    MediumPage mediumPage;
    HardPage hardPage;
    HelpPage helpPage;
    IndexPage indexPage;

    JLabel easyTimerLabel = new JLabel("Elapsed Time: 0 seconds");
    JLabel mediumTimerLabel = new JLabel("Elapsed Time: 0 seconds");
    JLabel hardTimerLabel = new JLabel("Elapsed Time: 0 seconds");

    public app() {
        easyTimer = createTimer(easyTimerLabel);
        mediumTimer = createTimer(mediumTimerLabel);
        hardTimer = createTimer(hardTimerLabel);
    }

    Timer createTimer(JLabel timerLabel) {
        return new Timer(1000, new ActionListener() {
            int secondsElapsed = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++;
                timerLabel.setText("Elapsed Time: " + secondsElapsed + " seconds");
            }
        });
    }

    void startTimer(Timer timer) {
        if (timer != null && !timer.isRunning()) {
            timer.start();
        }
    }

    void stopTimerMain(Timer timer) {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }
    }

    void setActivePanel(JFrame frame, JPanel panel) {
        frame.remove(activePanel);
        frame.add(panel);
        activePanel = panel;
        frame.validate();
        frame.repaint();
    }

    public class CustomMouseAdapter extends MouseAdapter {
        Timer timer;
        String[] foundWords;
        int elapsedTimeInSeconds;
        Score scorePageInstance;
        JPanel page;
        JLabel TimerLabel;
        String level;

        public CustomMouseAdapter(Timer timer, String[] foundWords, Score scorePageInstance, JPanel page,
                JLabel TimerLabel, String level) {
            this.timer = timer;
            this.TimerLabel = TimerLabel;
            this.foundWords = foundWords;
            this.elapsedTimeInSeconds = 0;
            this.scorePageInstance = scorePageInstance;
            this.page = page;
            this.level = level;

            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    elapsedTimeInSeconds++;
                    scorePageInstance.setTimeElapsed(elapsedTimeInSeconds);
                }
            });
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // stopTimer(easyTimerLabel);
            stopTimer(TimerLabel);
            TimerLabel = new JLabel("Elapsed Time: 0 seconds");
            if (level.equals("E")) {
                easyTimer = createTimer(TimerLabel);
                easyTimerLabel.setText("Elapsed Time: 0 seconds");
            } else if (level.equals("M")) {
                mediumTimer = createTimer(TimerLabel);
                mediumTimerLabel.setText("Elapsed Time: 0 seconds");
            } else {
                hardTimer = createTimer(TimerLabel);
                hardTimerLabel.setText("Elapsed Time: 0 seconds");
            }

            int pointsForWords = calculateTotalPointsOfFoundWords(foundWords);
            int pointsForTime = calculateTimeScore(elapsedTimeInSeconds);
            scorePageInstance.setScore(pointsForWords, pointsForTime);

            scorePage.getEasyButton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    easyTimerLabel.setText("Elapsed Time: 0 seconds");
                    stopTimerMain(easyTimer);
                    easyTimer = createTimer(easyTimerLabel);
                    easyPage = new EasyPage(easyTimerLabel, easyTimer);
                    startTimer(easyTimer);
                    easyPage.getLabel()
                            .addMouseListener(
                                    new CustomMouseAdapter(easyTimer, easyPage.getFoundWords(), scorePage,
                                            easyPage.getPage(), easyTimerLabel, "E"));
                    setActivePanel(frame, easyPage.getPage());
                }
            });

            scorePage.getMediumButton().addActionListener(new ActionListener() {
                // public void actionPerformed(ActionEvent e) {
                // startTimer(mediumTimer);
                // setActivePanel(frame, mediumPage.getPage());
                // }
                public void actionPerformed(ActionEvent e) {
                    mediumTimerLabel.setText("Elapsed Time: 0 seconds");
                    stopTimerMain(mediumTimer);
                    mediumTimer = createTimer(mediumTimerLabel);
                    mediumPage = new MediumPage(mediumTimerLabel, mediumTimer);
                    startTimer(mediumTimer);
                    mediumPage.getLabel()
                            .addMouseListener(
                                    new CustomMouseAdapter(mediumTimer, mediumPage.getFoundWords(), scorePage,
                                            mediumPage.getPage(), mediumTimerLabel, "M"));
                    setActivePanel(frame, mediumPage.getPage());
                }
            });

            scorePage.getHardButton().addActionListener(new ActionListener() {
                // public void actionPerformed(ActionEvent e) {
                // startTimer(hardTimer);
                // setActivePanel(frame, hardPage.getPage());
                // }
                public void actionPerformed(ActionEvent e) {
                    hardTimerLabel.setText("Elapsed Time: 0 seconds");
                    stopTimerMain(hardTimer);
                    hardTimer = createTimer(hardTimerLabel);
                    hardPage = new HardPage(hardTimerLabel, hardTimer);
                    startTimer(hardTimer);
                    hardPage.getLabel()
                            .addMouseListener(
                                    new CustomMouseAdapter(hardTimer, hardPage.getFoundWords(), scorePage,
                                            hardPage.getPage(), hardTimerLabel, "H"));
                    setActivePanel(frame, hardPage.getPage());
                }
            });

            scorePage.getInfoLabel().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setActivePanel(frame, helpPage.getPage());
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    scorePage.getInfoLabel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    scorePage.getInfoLabel()
                            .setText("<html><u>Find out what makes each difficulty challenging</u></html>");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    scorePage.getInfoLabel().setCursor(Cursor.getDefaultCursor());
                    scorePage.getInfoLabel().setText("Find out what makes each difficulty challenging");
                }
            });

            setActivePanel(frame, scorePage.getPage());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ((JLabel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ((JLabel) e.getSource()).setCursor(Cursor.getDefaultCursor());
        }

        void stopTimer(JLabel timerLabel) {
            if (timer != null && timer.isRunning()) {
                timer.stop();
                timerLabel.setText("Elapsed Time: 0 seconds");
            }
        }
    }

    int calculateTotalPointsOfFoundWords(String[] words) {
        int totalPoints = 0;
        boolean bonus = true;
        for (String word : words) {
            if (word != null) {
                totalPoints += calculatePointsForWord(word);
            } else {
                bonus = false;
            }
        }
        if (bonus)
            totalPoints += 5000;
        return totalPoints;
    }

    int calculatePointsForWord(String word) {
        int wordLength = word.length();
        return wordLength * 10;
    }

    int calculateTimeScore(int timeElapsed) {
        if (timeElapsed <= 20) {
            return 1500;
        } else if (timeElapsed <= 40) {
            return 1250;
        } else if (timeElapsed <= 60) {
            return 1000;
        } else if (timeElapsed <= 80) {
            return 750;
        } else if (timeElapsed <= 100) {
            return 500;
        } else if (timeElapsed <= 120) {
            return 250;
        } else {
            return 100;
        }
    }

    void createGUI() {
        // FRAME
        frame = new JFrame();
        frame.setMinimumSize(new Dimension(1024, 824));
        frame.setResizable(false);
        frame.setTitle("VocabVenture");
        frame.setIconImage(
                new ImageIcon("C:/Users/GCIT/Desktop/VocabVenture/vocabventure/images/logo.png").getImage());
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // PAGES
        easyPage = new EasyPage(easyTimerLabel, easyTimer);
        mediumPage = new MediumPage(mediumTimerLabel, mediumTimer);
        hardPage = new HardPage(hardTimerLabel, hardTimer);
        helpPage = new HelpPage();
        indexPage = new IndexPage(frame, activePanel, easyPage);
        scorePage = new Score(frame, activePanel, easyPage);
        activePanel = indexPage.getPage();

        // ADD PAGE TO FRAME
        frame.add(indexPage.getPage());

        // FOR BACK AND HOVER
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setActivePanel(frame, indexPage.getPage());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ((JLabel) e.getSource()).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JLabel) e.getSource()).setCursor(Cursor.getDefaultCursor());
            }
        };

        // EVENT LISTENERS
        indexPage.getEasyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer(easyTimer);
                setActivePanel(frame, easyPage.getPage());
            }
        });

        indexPage.getMediumButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer(mediumTimer);
                setActivePanel(frame, mediumPage.getPage());
            }
        });

        indexPage.getHardButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startTimer(hardTimer);
                setActivePanel(frame, hardPage.getPage());
            }
        });

        indexPage.getInfoLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setActivePanel(frame, helpPage.getPage());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                indexPage.getInfoLabel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                indexPage.getInfoLabel().setText("<html><u>Find out what makes each difficulty challenging</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                indexPage.getInfoLabel().setCursor(Cursor.getDefaultCursor());
                indexPage.getInfoLabel().setText("Find out what makes each difficulty challenging");
            }
        });

        scorePage.getEasyButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                easyTimerLabel.setText("Elapsed Time: 0 seconds");
                stopTimerMain(easyTimer);
                easyTimer = createTimer(easyTimerLabel);
                easyPage = new EasyPage(easyTimerLabel, easyTimer);
                startTimer(easyTimer);
                easyPage.getLabel()
                        .addMouseListener(
                                new CustomMouseAdapter(easyTimer, easyPage.getFoundWords(), scorePage,
                                        easyPage.getPage(), easyTimerLabel, "E"));
                setActivePanel(frame, easyPage.getPage());
            }
        });

        scorePage.getMediumButton().addActionListener(new ActionListener() {
            // public void actionPerformed(ActionEvent e) {
            // mediumPage.startTimer();
            // mediumPage.getLabel().addMouseListener(
            // new CustomMouseAdapter(mediumTimer, mediumPage.getFoundWords(), scorePage,
            // mediumPage.getPage(), mediumTimerLabel, "M"));

            // setActivePanel(frame, mediumPage.getPage());
            // }
            public void actionPerformed(ActionEvent e) {
                mediumTimerLabel.setText("Elapsed Time: 0 seconds");
                stopTimerMain(mediumTimer);
                mediumTimer = createTimer(mediumTimerLabel);
                mediumPage = new MediumPage(mediumTimerLabel, mediumTimer);
                startTimer(mediumTimer);
                mediumPage.getLabel()
                        .addMouseListener(
                                new CustomMouseAdapter(mediumTimer, mediumPage.getFoundWords(), scorePage,
                                        mediumPage.getPage(), mediumTimerLabel, "M"));
                setActivePanel(frame, mediumPage.getPage());
            }
        });

        scorePage.getHardButton().addActionListener(new ActionListener() {
            // public void actionPerformed(ActionEvent e) {
            // hardPage.startTimer();
            // hardPage.getLabel()
            // .addMouseListener(
            // new CustomMouseAdapter(hardTimer, hardPage.getFoundWords(), scorePage,
            // hardPage.getPage(), hardTimerLabel, "H"));
            // setActivePanel(frame, hardPage.getPage());
            // }
            public void actionPerformed(ActionEvent e) {
                hardTimerLabel.setText("Elapsed Time: 0 seconds");
                stopTimerMain(hardTimer);
                hardTimer = createTimer(hardTimerLabel);
                hardPage = new HardPage(hardTimerLabel, hardTimer);
                startTimer(hardTimer);
                hardPage.getLabel()
                        .addMouseListener(
                                new CustomMouseAdapter(hardTimer, hardPage.getFoundWords(), scorePage,
                                        hardPage.getPage(), hardTimerLabel, "H"));
                setActivePanel(frame, hardPage.getPage());
            }
        });

        scorePage.getInfoLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setActivePanel(frame, helpPage.getPage());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                scorePage.getInfoLabel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                scorePage.getInfoLabel().setText("<html><u>Find out what makes each difficulty challenging</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                scorePage.getInfoLabel().setCursor(Cursor.getDefaultCursor());
                scorePage.getInfoLabel().setText("Find out what makes each difficulty challenging");
            }
        });

        helpPage.getLabel().addMouseListener(mouseAdapter);

        easyPage.getLabel()
                .addMouseListener(
                        new CustomMouseAdapter(easyTimer, easyPage.getFoundWords(), scorePage,
                                easyPage.getPage(), easyTimerLabel, "E"));
        mediumPage.getLabel().addMouseListener(
                new CustomMouseAdapter(mediumTimer, mediumPage.getFoundWords(), scorePage,
                        mediumPage.getPage(), mediumTimerLabel, "M"));
        hardPage.getLabel()
                .addMouseListener(
                        new CustomMouseAdapter(hardTimer, hardPage.getFoundWords(), scorePage,
                                hardPage.getPage(), hardTimerLabel, "H"));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        app myApp = new app();
        myApp.createGUI();
    }
}
