/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.ra;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Yuiridia
 */

public class ChatBot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private DatabaseHandler dbHandler;

    public ChatBot() {
        // Inicializa componentes
        chatArea = new JTextArea();
        inputField = new JTextField();
        sendButton = new JButton("Enviar");
        dbHandler = new DatabaseHandler();
        
        // Configura JFrame
        setTitle("ChatBot");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        
        // Establece el nuevo icono
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/new_icon.png")));
        
        // Personaliza chatArea
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        chatArea.setMargin(new Insets(10, 10, 10, 10));
        
        // Crea un JScrollPane para chatArea
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Personaliza inputField
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
        
        // Personaliza sendButton
        sendButton.setFont(new Font("Arial", Font.BOLD, 14));
        sendButton.setBackground(new Color(0, 123, 255));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        
        // Añade componentes al JFrame
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        
        // Acción Listener para el botón de enviar
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim();
                if (!userInput.isEmpty()) {
                    chatArea.append("User: " + userInput + "\n");
                    inputField.setText("");
                    
                    // Lógica de respuesta automática
                    String botResponse = dbHandler.getAnswer(userInput);
                    chatArea.append("Bot: " + botResponse + "\n");
                    
                    // Scroll automático hacia abajo
                    chatArea.setCaretPosition(chatArea.getDocument().getLength());
                }
            }
        });
        
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatBot().setVisible(true);
            }
        });
    }
}