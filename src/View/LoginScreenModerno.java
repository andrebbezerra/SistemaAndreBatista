
package View;

import control.ControleDb;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginScreenModerno extends JFrame {

    // Cores para o design
    private static final Color COR_PAINEL_ESQUERDO = new Color(24, 46, 82); // Azul escuro
    private static final Color COR_BOTAO = new Color(58, 134, 255); // Azul brilhante
    private static final Color COR_TEXTO_CLARO = Color.BLACK;
    private static final Color COR_FUNDO_DIREITO = Color.WHITE;

    public LoginScreenModerno() {
        setTitle("SIG - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Painel principal que divide a tela em dois
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(0.4); // 40% do espaço para o painel esquerdo
        splitPane.setEnabled(false); // Impede o redimensionamento pelo usuário
        splitPane.setDividerSize(0); // Remove a borda da divisão

        splitPane.setLeftComponent(createLeftPanel());
        splitPane.setRightComponent(createRightPanel());

        add(splitPane);
    }

    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_PAINEL_ESQUERDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Ícone/Logo (usando um JLabel com um ícone grande)
        // Substitua "logo.png" pelo caminho do seu logo
        ImageIcon logoIcon = new ImageIcon("C://Users/andre/Downloads/LogoMarcaAndre_background_changed.png"); // Exemplo de como carregar do classpath
        
                // 2. Obter o objeto Image do ImageIcon
        Image imagemOriginal = logoIcon.getImage();

        // 3. Definir as novas dimensões (largura e altura)
        int novaLargura = 250;
        int novaAltura = 250;

        // 4. Redimensionar a imagem
        // Image.SCALE_DEFAULT é uma boa opção para a escala
        Image imagemRedimensionada = imagemOriginal.getScaledInstance(novaLargura, novaAltura, Image.SCALE_DEFAULT);

        // 5. Criar um novo ImageIcon com a imagem redimensionada
        ImageIcon iconRedimensionado = new ImageIcon(imagemRedimensionada);
        
        JLabel logoLabel = new JLabel(iconRedimensionado);
        gbc.gridy = 0;
        panel.add(logoLabel, gbc);

        // Título do Sistema
        JLabel titleLabel = new JLabel("Sistema Integrado Gerencial");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(COR_TEXTO_CLARO);
        gbc.gridy = 1;
        panel.add(titleLabel, gbc);

        // Slogan
        JLabel sloganLabel = new JLabel("Gestão completa para o seu negócio");
        sloganLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        sloganLabel.setForeground(COR_TEXTO_CLARO);
        gbc.gridy = 2;
        panel.add(sloganLabel, gbc);

        return panel;
    }

    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(COR_FUNDO_DIREITO);
        panel.setBorder(new EmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Título "Login"
        JLabel loginTitle = new JLabel("Bem-vindo(a)!");
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        gbc.weighty = 0.1;
        panel.add(loginTitle, gbc);

        // Campo de Usuário com placeholder simulado
        JTextField userField = new PlaceholderTextField("Usuário");
        userField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridy = 1;
        gbc.weighty = 0;
        panel.add(userField, gbc);

        // Campo de Senha com placeholder simulado
        JPasswordField passField = new PlaceholderPasswordField("Senha");
        passField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        gbc.gridy = 2;
        panel.add(passField, gbc);

        // Botão Entrar
        JButton loginButton = new JButton("Entrar");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setBackground(COR_BOTAO);
        loginButton.setForeground(COR_TEXTO_CLARO);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new EmptyBorder(10, 0, 10, 0));
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 3;
        gbc.insets = new Insets(20, 0, 10, 0);
        panel.add(loginButton, gbc);
        
                loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 3. Código a ser executado quando o botão é clicado
                ControleDb conexao = new ControleDb();
        conexao.conectar();
        
             String query = "SELECT login, pswd FROM public.usuarios WHERE login ='" +userField.getText()+"' AND pswd = '"+passField.getText()+"'";
                System.out.println(query);
        try {
              PreparedStatement pst = conexao.conexao.prepareStatement(query);
              ResultSet rs= pst.executeQuery(); 
              if(rs.next())
                {
                    ModernSIGMenu menu = new ModernSIGMenu();  
                    menu.setVisible(true);
                    menu.setLocationRelativeTo(null);
                    menu.setExtendedState(Frame.MAXIMIZED_BOTH);
                    setVisible(false);
                   //splitPane.dispose();
                }
              else
                  {
                       JOptionPane.showMessageDialog(null,"Usuario não Cadastrado ou Senha invalida! ");                     
                  }
              
           
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Erro na inserção!\nErro: " + ex.getMessage());
        }
                               
        conexao.desconectar();
            }
        });
        
        
        // Link "Esqueceu sua senha?"
        JLabel forgotPasswordLabel = new JLabel("<html><a href=''>Esqueceu sua senha?</a></html>");
        forgotPasswordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        forgotPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 10, 0);
        gbc.weighty = 1.0; // Empurra para baixo
        gbc.anchor = GridBagConstraints.NORTH;
        panel.add(forgotPasswordLabel, gbc);
        

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Tenta usar o Look and Feel do sistema para uma aparência mais nativa
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                 System.setProperty("awt.useSystemAAFontSettings", "on");
                System.setProperty("swing.aatext", "true");
            } catch (Exception e) {
                e.printStackTrace();
            }
            new LoginScreenModerno().setVisible(true);
        });
    }
}

// Classe auxiliar para simular placeholder em JTextField
class PlaceholderTextField extends JTextField implements FocusListener {
    private final String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderTextField(final String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        super.addFocusListener(this);
        this.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText("");
            showingPlaceholder = false;
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (this.getText().isEmpty()) {
            super.setText(placeholder);
            showingPlaceholder = true;
            this.setForeground(Color.GRAY);
        }
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : super.getText();
    }
}

// Classe auxiliar para simular placeholder em JPasswordField
class PlaceholderPasswordField extends JPasswordField implements FocusListener {
    private final String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderPasswordField(final String placeholder) {
        super(placeholder);
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        super.addFocusListener(this);
        this.setEchoChar((char) 0); // Mostra o texto do placeholder
        this.setForeground(Color.GRAY);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (showingPlaceholder) {
            super.setText("");
            this.setEchoChar('•'); // Ativa o modo senha
            showingPlaceholder = false;
            this.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (new String(super.getPassword()).isEmpty()) {
            super.setText(placeholder);
            this.setEchoChar((char) 0); // Mostra o texto do placeholder
            showingPlaceholder = true;
            this.setForeground(Color.GRAY);
        }
    }

    @Override
    public char[] getPassword() {
        return showingPlaceholder ? new char[0] : super.getPassword();
    }
}

