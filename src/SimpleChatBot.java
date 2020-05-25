import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SimpleChatBot extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;
    final String CHB_AI = "AI";
    final String BTN_ENTER = "Enter";

    //JTextArea dialogue;
    JTextPane dialogue; // area for dialog
    JCheckBox ai;       // enable/disable AI
    JTextField message; // field for entering messages
    SimpleBot sbot;     // chat-bot class (in bot package)
    SimpleAttributeSet botStyle, humanStyle; // style bot text

    public static void main(String[] args) {
        new SimpleChatBot();
    }

    SimpleChatBot() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
        // area for dialog
        dialogue = new JTextPane();
        //dialogue = new JTextArea();
        dialogue.setEditable(false);
        JScrollPane scrollBar = new JScrollPane(dialogue);
        // panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
        ai = new JCheckBox("AI");
        //ai.setSelected(true);
        ai.doClick();
        message = new JTextField();
        message.addActionListener(this);
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);
        // adding all elements to the window
        bp.add(ai);
        bp.add(message);
        bp.add(enter);
        add(BorderLayout.CENTER, scrollBar);
        add(BorderLayout.SOUTH, bp);
        setVisible(true);
        sbot = new SimpleBot(); // creating bot object
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            try {
                StyledDocument doc = dialogue.getStyledDocument();
                doc.insertString(doc.getLength(), "Вы: " + message.getText() + "\n",
                        humanStyle);
                doc.insertString(doc.getLength(), "Бот: " +
                                sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n",
                        botStyle);
            } catch (Exception e) {
            }
        }
        message.setText("");
        message.requestFocusInWindow();
    }

}