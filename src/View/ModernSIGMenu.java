package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import view.CadastroEmpresaFrame;
// Remova a importação do JInternalFrame se não for mais usá-lo diretamente
// import view.CadastroEmpresaFrame; 

/**
 * Menu modernizado para o Sistema Integrado Gerencial (SIG)
 * Utiliza design moderno com cores suaves, tipografia melhorada e interatividade com abas.
 */
public class ModernSIGMenu extends JFrame {

    // Cores do tema moderno
    private static final Color PRIMARY_COLOR = new Color(52, 73, 94);      // Azul escuro
    private static final Color SECONDARY_COLOR = new Color(236, 240, 241); // Cinza claro
    private static final Color ACCENT_COLOR = new Color(41, 128, 185);     // Azul médio
    private static final Color TEXT_COLOR = new Color(44, 62, 80);         // Cinza escuro
    private static final Color HOVER_COLOR = new Color(52, 152, 219);      // Azul claro
    private static final Color WHITE = Color.WHITE;

    // --- COMPONENTES PRINCIPAIS CORRIGIDOS ---
    private JPanel mainPanel;
    private JTabbedPane contentPanel; // Agora é um JTabbedPane e variável de instância
    private JLabel statusLabel;
    private JLabel userLabel;

    public ModernSIGMenu() {
        initializeFrame();
        createMenuBar();
        createMainContent(); // Este método agora inicializa o JTabbedPane
        createStatusBar();
        // O método setupLayout() não é mais necessário, o layout é construído nos outros métodos.
        setVisible(true);
    }

    private void initializeFrame() {
        setTitle("SIG - Sistema Integrado Gerencial");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800); // Aumentei um pouco para melhor visualização
        setLocationRelativeTo(null);
        setMinimumSize(new Dimension(900, 700));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
    }

    private void createMenuBar() {
     JMenuBar menuBar = new JMenuBar();
    // --- ALTERAÇÃO AQUI ---
    // menuBar.setBackground(PRIMARY_COLOR); // Antigo: Azul escuro
    menuBar.setBackground(ACCENT_COLOR);    // Novo: Azul médio e vibrante
    
    menuBar.setBorder(new EmptyBorder(8, 15, 8, 15));
    menuBar.setPreferredSize(new Dimension(0, 60));
        // Menus
        JMenu cadastrosMenu = createModernMenu("📋 Cadastros");
        cadastrosMenu.add(createMenuItem("Clientes"));
        cadastrosMenu.add(createMenuItem("Produtos"));
        cadastrosMenu.add(createMenuItem("Fornecedores"));
        cadastrosMenu.add(createMenuItem("Funcionários"));
        menuBar.add(cadastrosMenu);

        JMenu consultasMenu = createModernMenu("🔍 Consultas");
        consultasMenu.add(createMenuItem("Vendas"));
        consultasMenu.add(createMenuItem("Estoque"));
        menuBar.add(consultasMenu);

        JMenu relatoriosMenu = createModernMenu("📊 Relatórios");
        relatoriosMenu.add(createMenuItem("Vendas por Período"));
        relatoriosMenu.add(createMenuItem("Relatório Financeiro"));
        menuBar.add(relatoriosMenu);

        menuBar.add(Box.createHorizontalGlue());
        setJMenuBar(menuBar);
    }

private JMenu createModernMenu(String text) {
    JMenu menu = new JMenu(text);
    
    // --- ALTERAÇÃO AQUI ---
    // menu.setForeground(WHITE);         // Antigo: Texto branco
    menu.setForeground(TEXT_COLOR);      // Novo: Texto cinza escuro (quase preto)
    // ou, para preto puro:
    // menu.setForeground(Color.BLACK);

    menu.setFont(new Font("Segoe UI", Font.BOLD, 14));
    menu.setBorder(new EmptyBorder(10, 15, 10, 15));
    menu.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    // Adiciona um efeito de hover para melhorar a interatividade
    menu.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            // Deixa o fundo do menu um pouco mais claro ao passar o mouse
            menu.setOpaque(true); // Necessário para o setBackground funcionar
            menu.setBackground(ACCENT_COLOR.brighter());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            // Remove o fundo para voltar ao normal
            menu.setOpaque(false);
            menu.setBackground(null); // Reseta a cor de fundo
        }
    });
    
    return menu;
}

    private JMenuItem createMenuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        item.setBorder(new EmptyBorder(8, 20, 8, 20));
        // --- CHAMADA CORRIGIDA ---
        item.addActionListener(e -> showContent(text));
        return item;
    }

  private void createMainContent() {
        // --- INICIALIZAÇÃO CORRIGIDA ---
        contentPanel = new JTabbedPane();
        contentPanel.setFont(new Font("Segoe UI", Font.BOLD, 12)); // Fonte em negrito para destaque

        // --- APLICA A NOVA UI CUSTOMIZADA ---
        contentPanel.setUI(new ModernTabbedPaneUI());
        
        // Cria o painel de boas-vindas
        JPanel welcomePanel = createWelcomePanel();
        
        // Adiciona o painel de boas-vindas como a primeira aba
        contentPanel.addTab("Início", welcomePanel);
        
        // --- MODIFICAÇÃO: Deixa a aba "Início" sem botão de fechar ---
        // (O código para adicionar o cabeçalho com botão de fechar já está no showContent,
        // então a aba "Início" não o receberá, o que é o comportamento desejado)
        
        mainPanel.add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(WHITE);
        welcomePanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0);

        JLabel titleLabel = new JLabel("Sistema Integrado Gerencial");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(PRIMARY_COLOR);
        welcomePanel.add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        JLabel subtitleLabel = new JLabel("Bem-vindo ao seu sistema de gestão");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitleLabel.setForeground(TEXT_COLOR);
        welcomePanel.add(subtitleLabel, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        JPanel cardsPanel = createQuickAccessCards();
        welcomePanel.add(cardsPanel, gbc);

        return welcomePanel;
    }

    private JPanel createQuickAccessCards() {
        JPanel cardsPanel = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsPanel.setOpaque(false);

        // --- CHAMADAS CORRIGIDAS NOS CARDS ---
        JPanel cadastroCard = createCard("📋", "Clientes", "Gerencie clientes, produtos e fornecedores");
        cadastroCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showContent("Clientes");
            }
        });

        JPanel consultaCard = createCard("🔍", "Consultas", "Consulte vendas, estoque e relatórios");
        consultaCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showContent("Vendas");
            }
        });

        JPanel relatorioCard = createCard("📊", "Relatórios", "Gere relatórios detalhados do sistema");
        relatorioCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showContent("Vendas por Período");
            }
        });

        cardsPanel.add(cadastroCard);
        cardsPanel.add(consultaCard);
        cardsPanel.add(relatorioCard);

        return cardsPanel;
    }

    private JPanel createCard(String icon, String title, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
            new EmptyBorder(20, 20, 20, 20)
        ));
        card.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel iconLabel = new JLabel(icon, SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        card.add(iconLabel, BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout());
        content.setOpaque(false);
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(PRIMARY_COLOR);
        titleLabel.setBorder(new EmptyBorder(10, 0, 5, 0));

        JLabel descLabel = new JLabel("<html><center>" + description + "</center></html>", SwingConstants.CENTER);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(TEXT_COLOR);

        content.add(titleLabel, BorderLayout.NORTH);
        content.add(descLabel, BorderLayout.CENTER);
        card.add(content, BorderLayout.CENTER);

        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBackground(new Color(248, 249, 250));
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                    new EmptyBorder(19, 19, 19, 19)
                ));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBackground(WHITE);
                card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                    new EmptyBorder(20, 20, 20, 20)
                ));
            }
        });
        return card;
    }

    private void createStatusBar() {
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBackground(PRIMARY_COLOR);
        statusPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        statusPanel.setPreferredSize(new Dimension(0, 40));

        statusLabel = new JLabel("Sistema para pontos de venda e emissão de notas fiscais.");
        statusLabel.setForeground(WHITE);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        userLabel = new JLabel("👤 Usuário: admin");
        userLabel.setForeground(WHITE);
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        statusPanel.add(statusLabel, BorderLayout.WEST);
        statusPanel.add(userLabel, BorderLayout.EAST);
        mainPanel.add(statusPanel, BorderLayout.SOUTH);
    }

     /**
     * Exibe o conteúdo em uma nova aba. Se a aba já existir, a seleciona.
     * @param content O título do conteúdo a ser exibido (ex: "Clientes").
     */
    private void showContent(String content) {
        // 1. Verifica se uma aba com este título já existe e a seleciona
        for (int i = 0; i < contentPanel.getTabCount(); i++) {
            Component tabComponent = contentPanel.getTabComponentAt(i);
            String tabTitle = contentPanel.getTitleAt(i);
            
            if (tabComponent instanceof JPanel) {
                JLabel titleLabel = (JLabel) ((JPanel) tabComponent).getComponent(0);
                tabTitle = titleLabel.getText();
            }
            
            if (tabTitle.equalsIgnoreCase(content)) {
                contentPanel.setSelectedIndex(i);
                statusLabel.setText("Módulo ativo: " + content);
                return;
            }
        }

        // 2. Se a aba não existe, cria o painel de conteúdo apropriado
        Component newTabContent; // Usamos Component para ser mais genérico

        if ("Clientes".equalsIgnoreCase(content)) {
            // --- LÓGICA PARA ABRIR JINTERNALFRAME ---

            // a. Cria um JDesktopPane, que servirá como a "área de trabalho" para as janelas internas
            JDesktopPane desktopPane = new JDesktopPane();
            desktopPane.setBackground(new Color(214, 217, 223)); // Um fundo neutro

            // b. Cria a instância do seu JInternalFrame
            //CadastroEmpresaFrame internalFrame = new CadastroEmpresaFrame();
            CadastroEmpresaFrame internalFrame = new CadastroEmpresaFrame();

            // c. Adiciona a janela interna ao desktopPane
            desktopPane.add(internalFrame);
            internalFrame.setVisible(true);

            // d. Tenta posicionar a janela no centro (opcional)
            //Dimension desktopSize = desktopPane.getSize();
            //Dimension frameSize = internalFrame.getSize();
            //internalFrame.setLocation((desktopSize.width - frameSize.width) / 2, (desktopSize.height - frameSize.height) / 2);

            // e. O conteúdo da nova aba será o JDesktopPane
            newTabContent = desktopPane;

        } else {
            // --- Lógica para outros conteúdos (painéis simples) ---
            JPanel panel = new JPanel(new BorderLayout());
            panel.setBackground(Color.WHITE);
            panel.setBorder(new EmptyBorder(30, 30, 30, 30));
            panel.add(new JLabel("Conteúdo para: " + content, SwingConstants.CENTER), BorderLayout.CENTER);
            newTabContent = panel;
        }

        // 3. Adiciona o conteúdo (seja o JDesktopPane ou um JPanel) como uma nova aba
        contentPanel.addTab(content, newTabContent);
        int newTabIndex = contentPanel.indexOfComponent(newTabContent);

        // 4. Cria um cabeçalho de aba customizado com botão de fechar
        JPanel tabHeader = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabHeader.setOpaque(false);
        
        JLabel titleLabel = new JLabel(content);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));

        JButton closeButton = new JButton("×");
        closeButton.setUI(new javax.swing.plaf.basic.BasicButtonUI());
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setBorder(null);
        closeButton.setForeground(new Color(220, 220, 220));
        closeButton.setFont(new Font("Arial", Font.BOLD, 16));
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { closeButton.setForeground(Color.RED); }
            public void mouseExited(MouseEvent e) { closeButton.setForeground(new Color(220, 220, 220)); }
        });
        closeButton.addActionListener(e -> {
            int index = contentPanel.indexOfTabComponent(tabHeader);
            if (index != -1) {
                contentPanel.remove(index);
                if (contentPanel.getTabCount() == 0) { statusLabel.setText("Pronto"); }
            }
        });

        tabHeader.add(titleLabel);
        tabHeader.add(closeButton);

        // 5. Define o cabeçalho e seleciona a nova aba
        contentPanel.setTabComponentAt(newTabIndex, tabHeader);
        contentPanel.setSelectedIndex(newTabIndex);

        // 6. Atualiza a barra de status
        statusLabel.setText("Módulo ativo: " + content);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModernSIGMenu());
    }
}